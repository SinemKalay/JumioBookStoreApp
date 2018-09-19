package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.dataTransferObject.OrderItemDTO;
import com.sinem.jumio.domainObject.OrderDO;
import com.sinem.jumio.domainObject.OrderItemDO;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderItemMapper
{
    public static OrderItemDO makeOrderItemDO(OrderItemDTO orderItemDTO)
    {
        return new OrderItemDO(orderItemDTO.getId(), BookMapper.makeBookDO(orderItemDTO.getBookDTO()), orderItemDTO.getQuantity());
    }

    public static OrderItemDTO makeOrderItemDTO(OrderItemDO orderItemDO)
    {
        OrderItemDTO.OrderItemDTOBuilder orderItemDTOBuilder = OrderItemDTO.newBuilder()
            .setId(orderItemDO.getId())
            .setbookDTO(BookMapper.makeBookDTO(orderItemDO.getBookDO()))
            .setQuantity(orderItemDO.getQuantity());


        return orderItemDTOBuilder.createOrderItemDTO();
    }

    public static List<OrderItemDTO> makeOrderItemDTOList(List<OrderItemDO> orderItemDOList)
    {
        return orderItemDOList.stream()
            .map(OrderItemMapper::makeOrderItemDTO)
            .collect(Collectors.toList());
    }

    public static List<OrderItemDO> makeOrderItemDOList(List<OrderItemDTO> orderItemDTOList)
    {
        return orderItemDTOList.stream()
            .map(OrderItemMapper::makeOrderItemDO)
            .collect(Collectors.toList());
    }
}
