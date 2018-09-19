package com.sinem.jumio.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinem.jumio.domainValue.OrderStatus;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO
{
    private Long id;

    private OrderStatus orderStatus;

//    private CustomerDTO customerDTO;

    private List<OrderItemDTO> orderItemDTOSet;


    private OrderDTO()
    {

    }


    public OrderDTO(Long id, OrderStatus orderStatus, List<OrderItemDTO> orderItemDTOSet)
    {
        this.id = id;
        this.orderItemDTOSet = orderItemDTOSet;
//        this.customerDTO = customerDTO;
        this.orderStatus = orderStatus;
    }


    public static OrderDTOBuilder newBuilder()
    {
        return new OrderDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public OrderStatus getOrderStatus()
    {
        return orderStatus;
    }

//
//    public CustomerDTO getCustomerDTO()
//    {
//        return customerDTO;
//    }


    public List<OrderItemDTO> getOrderItemDTOSet()
    {
        return orderItemDTOSet;
    }

    public static class OrderDTOBuilder
    {
        private Long id;

        private OrderStatus orderStatus;

        private CustomerDTO customerDTO;

        private List<OrderItemDTO> orderItemDTOSet;


        public OrderDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public OrderDTOBuilder setOrderStatus(OrderStatus orderStatus)
        {
            this.orderStatus = orderStatus;
            return this;
        }


        public OrderDTOBuilder setCustomerDTO(CustomerDTO customerDTO)
        {
            this.customerDTO = customerDTO;
            return this;
        }


        public OrderDTOBuilder setOrderItemDTO(List<OrderItemDTO> orderItemDTOSet)
        {
            this.orderItemDTOSet = orderItemDTOSet;
            return this;
        }


        public OrderDTO createOrderDTO()
        {
            return new OrderDTO(id, orderStatus,  orderItemDTOSet);
        }
    }
}
