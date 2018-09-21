package com.sinem.jumio.domainObject;

import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "book",
    uniqueConstraints = @UniqueConstraint(name = "isbn", columnNames = {"isbn"})
)
public class BookDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "ISBN can not be null!")
    private String isbn;

    @Column(nullable = false)
    @NotNull(message = "Title can not be null!")
    private String title;

    @Column(nullable = false)
    private Boolean deleted = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Author_ID")
    @NotNull(message = "Author information can not be null! First need to create Author..")
    private AuthorDO authorDO;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_ID")
    @NotNull(message = "Stock information can not be null!")
    private StockDO stockDO;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();


    public BookDO()
    {

    }


    public BookDO(Long id, String isbn, String title, AuthorDO authorDO)
    {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorDO = authorDO;
        //        this.stockDO=stockDO;
        this.deleted = false;
    }


    public BookDO(Long id, String isbn, String title, AuthorDO authorDO, StockDO stockDO)
    {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorDO = authorDO;
        this.stockDO = stockDO;
        this.deleted = false;
    }


    public BookDO(Long id, String isbn, String title)
    {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.deleted = false;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getIsbn()
    {
        return isbn;
    }


    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
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


    public AuthorDO getAuthorDO()
    {
        return authorDO;
    }


    public void setAuthorDO(AuthorDO authorDO)
    {
        this.authorDO = authorDO;
    }


    public StockDO getStockDO()
    {
        return stockDO;
    }


    public void setStockDO(StockDO stockDO)
    {
        this.stockDO = stockDO;
    }
}
