package com.sinem.jumio.service.customer;

import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.domainObject.OrderDO;
import com.sinem.jumio.exception.DontHaveRightException;
import com.sinem.jumio.exception.EntityNotFoundException;
import java.util.List;
import javax.validation.Valid;

public interface ICustomerService
{
    List<OrderDTO> getAllOrdersOfCustomer(Long customerId) throws EntityNotFoundException;

    List<OrderDO> getAllOrderDOsOfCustomer(Long customerId) throws EntityNotFoundException;

    List<OrderDTO> createOrder(@Valid OrderDTO orderDTO, Long customerId) throws EntityNotFoundException;

    List<OrderDTO> updateOrder(@Valid OrderDTO orderDTO, Long customerId) throws EntityNotFoundException;

    List<OrderDTO> sendOrder(Long orderId, Long customerId) throws EntityNotFoundException, DontHaveRightException;

}
