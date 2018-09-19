package com.sinem.jumio.domainObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinem.jumio.domainValue.OrderStatus;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class OrderDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Customer_ID")
    @JsonIgnore
    private CustomerDO customerDO;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    //    @JoinTable(name = "order_book", joinColumns = @JoinColumn(name = "Order_ID"), inverseJoinColumns = @JoinColumn(name = "Book_ID"))
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderDO")
    private List<OrderItemDO> orderItemDOSet;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private Boolean deleted = false;


    public OrderDO()
    {

    }


//    public OrderDO(Long id, CustomerDO customerDO, List<OrderItemDO> orderItemDOSet, OrderStatus orderStatus)
    public OrderDO(Long id, List<OrderItemDO> orderItemDOSet, OrderStatus orderStatus)
    {
        this.id = id;
//        this.customerDO = customerDO;
        this.orderItemDOSet = orderItemDOSet;
        this.orderStatus = orderStatus;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public CustomerDO getCustomerDO()
    {
        return customerDO;
    }


    public void setCustomerDO(CustomerDO customerDO)
    {
        this.customerDO = customerDO;
    }


    public List<OrderItemDO> getOrderItemDOSet()
    {
        return orderItemDOSet;
    }


    public void setOrderItemDOSet(List<OrderItemDO> orderItemDOSet)
    {
        this.orderItemDOSet = orderItemDOSet;
    }


    public OrderStatus getOrderStatus()
    {
        return orderStatus;
    }


    public void setOrderStatus(OrderStatus orderStatus)
    {
        this.orderStatus = orderStatus;
    }


}
