package com.sinem.jumio.domainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItemDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Book_ID")
    private BookDO bookDO;

    @ManyToOne
    @JoinColumn(name = "Order_ID")
    private OrderDO orderDO;

    @Column(nullable = false)
    private Integer quantity;


    public OrderItemDO(){

    }
    public OrderItemDO(Long id, BookDO bookDO, Integer quantity)
    {
        this.id = id;
        this.bookDO = bookDO;
        this.quantity = quantity;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public BookDO getBookDO()
    {
        return bookDO;
    }


    public void setBookDO(BookDO bookDO)
    {
        this.bookDO = bookDO;
    }


    public Integer getQuantity()
    {
        return quantity;
    }


    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }


    public OrderDO getOrderDO()
    {
        return orderDO;
    }


    public void setOrderDO(OrderDO orderDO)
    {
        this.orderDO = orderDO;
    }
}
