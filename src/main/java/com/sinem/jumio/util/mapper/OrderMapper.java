package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.domainObject.OrderDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper
{
    public static OrderDO makeOrderDO(OrderDTO orderDTO)
    {
        return new OrderDO(orderDTO.getId()
            , OrderItemMapper.makeOrderItemDOList(orderDTO.getOrderItemDTOSet()), orderDTO
            .getOrderStatus());
    }


    public static OrderDTO makeOrderDTO(OrderDO orderDO)
    {
        OrderDTO.OrderDTOBuilder orderDTOBuilder = OrderDTO.newBuilder()
            .setId(orderDO.getId())
            .setOrderItemDTO(OrderItemMapper.makeOrderItemDTOList(orderDO.getOrderItemDOSet()))
            .setOrderStatus(orderDO.getOrderStatus());

        return orderDTOBuilder.createOrderDTO();
    }


    public static List<OrderDTO> makeOrderDTOList(Collection<OrderDO> orderDOList)
    {
        return orderDOList.stream()
            .map(OrderMapper::makeOrderDTO)
            .collect(Collectors.toList());
    }


    public static List<OrderDO> makeOrderDOList(Collection<OrderDTO> orderDTOList)
    {
        return orderDTOList.stream()
            .map(OrderMapper::makeOrderDO)
            .collect(Collectors.toList());
    }
}
