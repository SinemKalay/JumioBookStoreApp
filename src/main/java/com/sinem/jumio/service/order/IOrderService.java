package com.sinem.jumio.service.order;

import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.domainObject.OrderDO;
import com.sinem.jumio.exception.EntityNotFoundException;
import java.util.List;

public interface IOrderService
{
    OrderDO findByOrderId(Long orderId) throws EntityNotFoundException;

    OrderDO createOrder(OrderDTO orderDTO);

    OrderDO saveOrder(OrderDO orderDO);

    OrderDTO sendOrder(Long orderId) throws EntityNotFoundException;

    List<OrderDTO> findAll() throws EntityNotFoundException;

    boolean checkStock(OrderDTO orderDTO);

    List<OrderDO> updateOrderList(List<OrderDO> orderDOList,OrderDTO orderDTO);

}
