package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.CustomerDTO;
import com.sinem.jumio.domainObject.CustomerDO;

public class CustomerMapper
{
    public static CustomerDO makeCustomerDO(CustomerDTO customerDTO)
    {
        return new CustomerDO(customerDTO.getId(), customerDTO.getUsername(), customerDTO.getPassword(), OrderMapper.makeOrderDOList(customerDTO.getOrderDTOList()));
    }


    public static CustomerDTO makeCustomerDTO(CustomerDO customerDO)
    {
        CustomerDTO.CustomerDTOBuilder customerDTOBuilder = CustomerDTO.newBuilder()
            .setId(customerDO.getId())
            .setUsername(customerDO.getUsername())
            .setPassword(customerDO.getPassword())
            .setOrderDTOSet(OrderMapper.makeOrderDTOList(customerDO.getOrderDOList()));

        return customerDTOBuilder.createCustomerDTO();
    }
}
