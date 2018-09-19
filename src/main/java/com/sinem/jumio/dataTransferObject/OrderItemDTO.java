package com.sinem.jumio.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(as = OrderItemDTO.class)
public class OrderItemDTO
{
    private Long id;

    private BookDTO bookDTO;

    private Integer quantity;

    private OrderItemDTO(){

    }

    private OrderItemDTO(Long id,BookDTO bookDTO, Integer quantity)
    {
        this.id=id;
        this.bookDTO = bookDTO;
        this.quantity = quantity;
    }


    public static OrderItemDTOBuilder newBuilder()
    {
        return new OrderItemDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public BookDTO getBookDTO()
    {
        return bookDTO;
    }


    public Integer getQuantity()
    {
        return quantity;
    }


    public static class OrderItemDTOBuilder
    {
        private Long id;

        private BookDTO bookDTO;

        private Integer quantity;


        public OrderItemDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public OrderItemDTOBuilder setbookDTO(BookDTO bookDTO)
        {
            this.bookDTO = bookDTO;
            return this;
        }


        public OrderItemDTOBuilder setQuantity(Integer quantity)
        {
            this.quantity = quantity;
            return this;
        }


        public OrderItemDTO createOrderItemDTO()
        {
            return new OrderItemDTO(id, bookDTO, quantity);
        }
    }
}

