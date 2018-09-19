package com.sinem.jumio.controller;

import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.exception.DontHaveRightException;
import com.sinem.jumio.exception.EntityNotFoundException;
import com.sinem.jumio.service.customer.ICustomerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a customer will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/customer")
public class CustomerController
{
    @Autowired
    private final ICustomerService iCustomerService;


    public CustomerController(final ICustomerService iCustomerService)
    {
        this.iCustomerService = iCustomerService;
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersofCustomer(@PathVariable long customerId)
        throws EntityNotFoundException
    {
        List<OrderDTO> orderList = iCustomerService.getAllOrdersOfCustomer(customerId);

        return new ResponseEntity<>(orderList, HttpStatus.OK);

    }


    @PostMapping("/{customerId}")
    public ResponseEntity<List<OrderDTO>> createOrder(@Valid @RequestBody OrderDTO orderDTO, @PathVariable long customerId)
        throws EntityNotFoundException
    {
        List<OrderDTO> orderList = iCustomerService.createOrder(orderDTO, customerId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }


    @PatchMapping("/{customerId}")
    public ResponseEntity<List<OrderDTO>> updateOrder(@Valid @RequestBody OrderDTO orderDTO, @PathVariable long customerId)
        throws EntityNotFoundException
    {
        return new ResponseEntity<>(iCustomerService.updateOrder(orderDTO, customerId), HttpStatus.OK);
    }


    @PatchMapping("/{customerId}/{orderId}")
    public ResponseEntity<List<OrderDTO>> sendOrder(@Valid @PathVariable Long orderId, @PathVariable Long customerId)
        throws DontHaveRightException,
               EntityNotFoundException
    {

        return new ResponseEntity<>(iCustomerService.sendOrder(orderId, customerId), HttpStatus.OK);
    }
}
