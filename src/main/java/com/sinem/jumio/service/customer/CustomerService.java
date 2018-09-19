package com.sinem.jumio.service.customer;

import com.sinem.jumio.dataAccessObject.CustomerRepository;
import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.domainObject.CustomerDO;
import com.sinem.jumio.domainObject.OrderDO;
import com.sinem.jumio.exception.DontHaveRightException;
import com.sinem.jumio.exception.EntityNotFoundException;
import com.sinem.jumio.service.order.IOrderService;
import com.sinem.jumio.util.mapper.OrderMapper;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService implements ICustomerService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IOrderService iOrderService;


    /**
     * Get all orders of customer as DTO
     *
     * @param customerId
     * @return List<OrderDTO>
     * @throws EntityNotFoundException if no order or no customer with customerId  found.
     */
    @Override
    public List<OrderDTO> getAllOrdersOfCustomer(Long customerId) throws EntityNotFoundException
    {
        return OrderMapper.makeOrderDTOList(getAllOrderDOsOfCustomer(customerId));
    }


    /**
     * Get all orders of customer as DO
     *
     * @param customerId
     * @return List<OrderDO>
     * @throws EntityNotFoundException if no order or no customer with customerId  found.
     */
    @Override
    public List<OrderDO> getAllOrderDOsOfCustomer(Long customerId) throws EntityNotFoundException
    {
        CustomerDO customerDO = customerRepository.findByIdAndDeleted(customerId, false).orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " +
            customerId));

        return customerDO.getOrderDOList();
    }


    /**
     * Create order & assign for specific customer
     *
     * @param orderDTO
     * @param customerId
     * @return List<OrderDTO> thought as after create order orders page will be come.
     * @throws EntityNotFoundException if no order or no customer with customerId  found.
     */
    @Override
    @Transactional
    public List<OrderDTO> createOrder(@Valid OrderDTO orderDTO, Long customerId) throws EntityNotFoundException
    {
        CustomerDO customerDO = findCustomerChecked(customerId);
        OrderDO orderDO = iOrderService.createOrder(orderDTO);
        orderDO.setCustomerDO(customerDO);
        iOrderService.saveOrder(orderDO);
        LOG.info("Order has been saved. New orderID: " + orderDO.getId());

        return OrderMapper.makeOrderDTOList(customerDO.getOrderDOList());

    }


    /**
     * update order details of specific customer
     *
     * @param orderDTO
     * @param customerId
     * @return List<OrderDTO> thought as after create order orders page will be come.
     * @throws EntityNotFoundException if no order or no customer with customerId  found.
     */
    @Override
    public List<OrderDTO> updateOrder(@Valid OrderDTO orderDTO, Long customerId) throws EntityNotFoundException
    {
        CustomerDO customerDO = findCustomerChecked(customerId);

        List<OrderDO> orderDOList = customerDO.getOrderDOList();
        orderDOList = iOrderService.updateOrderList(orderDOList, orderDTO);
        customerDO.setOrderDOList(orderDOList);
        customerRepository.save(customerDO);

        LOG.info("Order has been updated.OrderID: " + orderDTO.getId());

        return OrderMapper.makeOrderDTOList(customerDO.getOrderDOList());
    }


    /**
     * send order if stock is available remaining stock number will be decrased and order status will be APPROVED
     * otherwise order status will be rejected
     *
     * @param orderId
     * @param customerId
     * @return List<OrderDTO> thought as after create order orders page will be come.
     * @throws EntityNotFoundException if no order or no customer with customerId  found.
     * @throws DontHaveRightException  if received orderId doesn't belong to received customerId
     */
    @Override
    public List<OrderDTO> sendOrder(Long orderId, Long customerId) throws EntityNotFoundException, DontHaveRightException
    {
        CustomerDO customerDO = findCustomerChecked(customerId);

        if (checkCustomerHasOrder(customerDO, orderId))
        {
            iOrderService.sendOrder(orderId);
            LOG.info("Order has been to approval.OrderID: " + orderId);

        }
        else
        {
            LOG.warn("Customer don't have right on this order.OrderID: " + orderId);

            throw new DontHaveRightException("Do not have right to do");
        }
        return OrderMapper.makeOrderDTOList(customerDO.getOrderDOList());
    }


    private boolean checkCustomerHasOrder(CustomerDO customerDO, Long orderId)
    {
        Long count = customerDO.getOrderDOList().stream()
            .filter(o -> o.getId() == orderId)
            .count();

        return count > 0;
    }


    private CustomerDO findCustomerChecked(Long customerId) throws EntityNotFoundException
    {
        return customerRepository.findByIdAndDeleted(customerId, false)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + customerId));
    }

}
