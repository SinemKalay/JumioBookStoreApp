package com.sinem.jumio.domainObject;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.ZonedDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "author")
public class AuthorDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Author first name can not be null!")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "Author last name can not be null!")
    private String lastName;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToMany(cascade = CascadeType.PERSIST)
//    @JsonManagedReference
    private Set<BookDO> bookOfAuthor;


    public AuthorDO()
    {

    }

    public AuthorDO(Long id,String firstName, String lastName)
    {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        //        this.bookOfAuthor = bookOfAuthor;
        this.deleted = false;
    }


    public AuthorDO(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.bookOfAuthor = bookOfAuthor;
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


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public Set<BookDO> getBookOfAuthor()
    {
        return bookOfAuthor;
    }


    public void setBookOfAuthor(Set<BookDO> bookOfAuthor)
    {
        this.bookOfAuthor = bookOfAuthor;
    }
}
