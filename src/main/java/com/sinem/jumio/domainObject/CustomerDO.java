package com.sinem.jumio.domainObject;

import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "customer",
    uniqueConstraints = @UniqueConstraint(name = "username", columnNames = {"username"})
)
public class CustomerDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Column(nullable = false)
    private Boolean deleted = false;

    //    @OneToMany(
    //        cascade = CascadeType.ALL,
    //        orphanRemoval = true
    //    )
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerDO")
    private List<OrderDO> orderDOList;


    public CustomerDO()
    {

    }


    public CustomerDO(Long id, String username, String password, List<OrderDO> orderDOList)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.orderDOList = orderDOList;
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


    public String getUsername()
    {
        return username;
    }


    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public List<OrderDO> getOrderDOList()
    {
        return orderDOList;
    }


    public void setOrderDOList(List<OrderDO> orderDOList)
    {
        this.orderDOList = orderDOList;
    }
}
