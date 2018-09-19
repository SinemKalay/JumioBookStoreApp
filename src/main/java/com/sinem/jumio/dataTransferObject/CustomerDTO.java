package com.sinem.jumio.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO
{
    private Long id;

    private String username;

    private String password;

    private List<OrderDTO> orderDTOList;


    public CustomerDTO()
    {

    }

    public CustomerDTO(Long id, String username, String password, List<OrderDTO> orderDTOList)
    {
        this.id = id;
        this.orderDTOList = orderDTOList;
        this.username = username;
        this.password = password;

    }


    public static CustomerDTOBuilder newBuilder()
    {
        return new CustomerDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public List<OrderDTO> getOrderDTOList()
    {
        return orderDTOList;
    }


    public static class CustomerDTOBuilder
    {
        private Long id;

        private String username;

        private String password;

        private List<OrderDTO> orderDTOList;


        public CustomerDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CustomerDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public CustomerDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public CustomerDTOBuilder setOrderDTOSet(List<OrderDTO> orderDTOList)
        {
            this.orderDTOList = orderDTOList;
            return this;
        }


        public CustomerDTO createCustomerDTO()
        {
            return new CustomerDTO(id, username, password, orderDTOList);
        }
    }
}
