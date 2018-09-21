package com.sinem.jumio.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO
{
    private Long id;

    private String isbn;

    private String title;

    private AuthorDTO authorDTO;

    private StockDTO stockDTO;


    private BookDTO()
    {

    }


    private BookDTO(Long id, String isbn, String title, AuthorDTO authorDTO, StockDTO stockDTO)
    {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorDTO = authorDTO;
        this.stockDTO = stockDTO;
    }


    public static BookDTOBuilder newBuilder()
    {
        return new BookDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public String getIsbn()
    {
        return isbn;
    }


    public String getTitle()
    {
        return title;
    }


    public AuthorDTO getAuthorDTO()
    {
        return authorDTO;
    }


    public StockDTO getStockDTO()
    {
        return stockDTO;
    }


    public static class BookDTOBuilder
    {
        private Long id;
        private String isbn;
        private String title;
        private AuthorDTO authorDTO;
        private StockDTO stockDTO;


        public BookDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public BookDTOBuilder setIsbn(String isbn)
        {
            this.isbn = isbn;
            return this;
        }


        public BookDTOBuilder setTitle(String title)
        {
            this.title = title;
            return this;
        }


        public BookDTOBuilder setAuthorDTO(AuthorDTO authorDTO)
        {
            this.authorDTO = authorDTO;
            return this;
        }

        public BookDTOBuilder setStockDTO(StockDTO stockDTO)
        {
            this.stockDTO = stockDTO;
            return this;
        }


        public BookDTO createBookDTO()
        {
            return new BookDTO(id, isbn, title, authorDTO,stockDTO);
        }
    }
}
