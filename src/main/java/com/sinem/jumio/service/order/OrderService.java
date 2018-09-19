package com.sinem.jumio.service.order;

import com.sinem.jumio.dataAccessObject.OrderRepository;
import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.dataTransferObject.OrderItemDTO;
import com.sinem.jumio.domainObject.OrderDO;
import com.sinem.jumio.domainObject.OrderItemDO;
import com.sinem.jumio.domainValue.OrderStatus;
import com.sinem.jumio.exception.EntityNotFoundException;
import com.sinem.jumio.service.book.IBookService;
import com.sinem.jumio.service.stock.IStockService;
import com.sinem.jumio.util.mapper.OrderMapper;
import java.util.List;
import java.util.function.Function;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService
{
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final IStockService iStockService;

    @Autowired
    private final IBookService iBookService;


    public OrderService(final OrderRepository orderRepository, IStockService iStockService, IBookService iBookService)
    {
        this.orderRepository = orderRepository;
        this.iStockService = iStockService;
        this.iBookService = iBookService;

    }


    /**
     * Get order  by order id.
     *
     * @param orderId
     * @return OrderDO
     * @throws EntityNotFoundException if no order found.
     */
    @Override
    public OrderDO findByOrderId(Long orderId) throws EntityNotFoundException
    {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find any order"));

    }


    /**
     * Get all orders
     *
     * @return List<OrderDTO>
     * @throws EntityNotFoundException if no order found.

     */
    @Override
    public List<OrderDTO> findAll() throws EntityNotFoundException
    {
        return OrderMapper.makeOrderDTOList(findAllDO());
    }

    /**
     * Get all orders
     *
     * @return List<OrderDO>
     * @throws EntityNotFoundException if no order found.

     */
    private List<OrderDO> findAllDO() throws EntityNotFoundException
    {
        return orderRepository.findByDeleted(false)
            .orElseThrow(() -> new EntityNotFoundException("Could not find any order"));
    }

    /**
     * Create all fields of order to be ready to save
     * Check stock in case of  insufficient storage
     *
     * @param orderDTO
     * @return OrderDO
     */
    @Override
    public OrderDO createOrder(OrderDTO orderDTO)
    {
        OrderDO orderDO = null;
        if (checkStock(orderDTO))
        {
            orderDO = rearrangeOrderItems(orderDTO);
        }

        return orderDO;

    }

    /**
     * After controls no problem, save the order
     *
     * @param orderDO
     * @return OrderDO
     */
    @Override
    public OrderDO saveOrder(OrderDO orderDO)
    {
        return orderRepository.save(orderDO);
    }


    /**
     * Add new order to orderDOList
     *
     * @param orderDOList
     * @param orderDTO
     * @return List<OrderDO>
     */
    @Override
    public List<OrderDO> updateOrderList(List<OrderDO> orderDOList, OrderDTO orderDTO)
    {
        if (checkStock(orderDTO))
        {
            OrderDO orderDO = orderDOList.stream()
                .filter(s -> Long.compare(s.getId(), orderDTO.getId()) == 0).findAny().orElse(null);
            orderDOList.remove(orderDO);
            orderDO = OrderMapper.makeOrderDO(orderDTO);
            orderDOList.add(orderDO);
        }
        return orderDOList;
    }

    /**
     * Send the order to approval
     *
     * @param orderId
     * @return List<OrderDO>
     * @throws EntityNotFoundException if no order found.
     */
    @Override
    public OrderDTO sendOrder(Long orderId) throws EntityNotFoundException
    {
        OrderDO orderDO = findByOrderId(orderId);
        if (checkStock(OrderMapper.makeOrderDTO(orderDO)))
        {
            orderDO.setOrderStatus(OrderStatus.APPROVED);
            orderRepository.save(orderDO);
            iStockService.decreaseStocks(orderDO.getOrderItemDOSet());
        }
        else
        {
            orderDO.setOrderStatus(OrderStatus.REJECTED);
            orderRepository.save(orderDO);
        }

        return OrderMapper.makeOrderDTO(orderDO);
    }


    /**
     * Check storage whether there is enough unit exist for coming Order
     *
     * @param orderDTO
     * @return boolean stock is exist or not
     */
    @Override
    public boolean checkStock(OrderDTO orderDTO)
    {
        List<OrderItemDTO> orderItemDOSet = orderDTO.getOrderItemDTOSet();
        iStockService.checkStocks(orderItemDOSet);
        return true;
    }

    /**
     * Rearrange coming orderDTO in case of wrong info received
     *
     * @param orderDTO
     * @return OrderDO
     */
    private OrderDO rearrangeOrderItems(OrderDTO orderDTO)
    {

        OrderDO orderDO = OrderMapper.makeOrderDO(orderDTO);

        Function<OrderItemDO, OrderItemDO> functionSetBookDODetails = (OrderItemDO orderItem) -> {
            orderItem.setBookDO(iBookService.findBookDO(orderItem.getBookDO().getId()));
            return orderItem;
        };

        orderDO.getOrderItemDOSet().stream().forEach(o -> o = functionSetBookDODetails.apply(o));

        return orderDO;
    }
}
