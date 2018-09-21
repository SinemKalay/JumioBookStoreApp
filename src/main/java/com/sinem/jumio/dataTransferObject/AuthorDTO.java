package com.sinem.jumio.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinem.jumio.domainObject.BookDO;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO
{
    private Long id;

    private String firstName;

    private String lastName;

    private Set<BookDO> booksOfAuthor;


    private AuthorDTO()
    {

    }

    private AuthorDTO(Long id, String firstName, String lastName)
    {
        this.id=id;
        this.firstName = firstName;
        this.lastName=lastName;
    }

    private AuthorDTO(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName=lastName;
    }


    public static AuthorDTOBuilder newBuilder()
    {
        return new AuthorDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public String getLastName()    {        return lastName;    }


    public Set<BookDO> getBooksOfAuthor()
    {
        return booksOfAuthor;
    }


    public static class AuthorDTOBuilder
    {

        private Long id;
        private String firstName;
        private String lastName;
        private Set<BookDO> booksOfAuthor;


        public AuthorDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public AuthorDTOBuilder setFirstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public AuthorDTOBuilder setLastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }


        public AuthorDTOBuilder setBooksOfAuthor(Set<BookDO> booksOfAuthor)
        {
            this.booksOfAuthor = booksOfAuthor;
            return this;
        }


        public AuthorDTO createAuthorDTO()
        {
            return new AuthorDTO(this.firstName, this.lastName);
        }
    }
}
