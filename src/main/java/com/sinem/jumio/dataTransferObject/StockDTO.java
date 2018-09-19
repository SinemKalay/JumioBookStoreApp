package com.sinem.jumio.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDTO
{
    private Long id;

    private BookDTO bookDTO;

    private Integer numberInStock;


    private StockDTO()
    {

    }


    private StockDTO(Long id,  BookDTO bookDTO, Integer numberInStock)
    {
        this.id = id;
        this.bookDTO=bookDTO;
        this.numberInStock = numberInStock;
    }


    public static StockDTOBuilder newBuilder()
    {
        return new StockDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public BookDTO getBookDTO()
    {
        return bookDTO;
    }


    public Integer getNumberInStock()
    {
        return numberInStock;
    }


    public static class StockDTOBuilder
    {
        private Long id;
        private BookDTO bookDTO;
        private Integer numberInStock;


        public StockDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public StockDTOBuilder setBookDTO(BookDTO bookDTO)
        {
            this.bookDTO = bookDTO;
            return this;
        }


        public StockDTOBuilder setNumberInStock(Integer numberInStock)
        {
            this.numberInStock = numberInStock;
            return this;
        }


        public StockDTO createStockDTO()
        {
            return new StockDTO(id, bookDTO, numberInStock);
        }
    }
}
