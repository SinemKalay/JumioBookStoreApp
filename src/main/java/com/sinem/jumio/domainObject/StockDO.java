package com.sinem.jumio.domainObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class StockDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "numberOfCopy can not be null!")
    private Integer numberOfCopy;

    @OneToOne(fetch= FetchType.LAZY, mappedBy = "stockDO")
    @JsonIgnore
    private BookDO bookDO;


    public StockDO()
    {

    }

    public StockDO(Integer numberOfCopy)
    {
        this.numberOfCopy = numberOfCopy;
    }


    public StockDO(Long id,Integer numberOfCopy)
    {
        this.id=id;
        this.numberOfCopy = numberOfCopy;
    }

    public StockDO(Integer numberOfCopy, BookDO bookDO)
    {
        this.numberOfCopy = numberOfCopy;
        this.bookDO = bookDO;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Integer getNumberOfCopy()
    {
        return numberOfCopy;
    }


    public void setNumberOfCopy(Integer numberOfCopy)
    {
        this.numberOfCopy = numberOfCopy;
    }


    public BookDO getBookDO()
    {
        return bookDO;
    }


    public void setBookDO(BookDO bookDO)
    {
        this.bookDO = bookDO;
    }
}
