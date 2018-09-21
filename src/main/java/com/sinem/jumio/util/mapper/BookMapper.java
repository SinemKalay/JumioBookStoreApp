package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.domainObject.BookDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper
{
    public static BookDO makeBookDO(BookDTO bookDTO)
    {
        if (bookDTO.getStockDTO() == null)
        {
            return new BookDO(
                bookDTO.getId(),
                bookDTO.getIsbn(),
                bookDTO.getTitle(),
                AuthorMapper.makeAuthorDO(bookDTO.getAuthorDTO()));
        }
        else
        {
            return new BookDO(
                bookDTO.getId(),
                bookDTO.getIsbn(),
                bookDTO.getTitle(),
                AuthorMapper.makeAuthorDO(bookDTO.getAuthorDTO()),
                StockMapper.makeStockDO(bookDTO.getStockDTO()));

        }

    }


    public static BookDTO makeBookDTO(BookDO bookDO)
    {
        BookDTO.BookDTOBuilder bookDTOBuilder = BookDTO.newBuilder()
            .setId(bookDO.getId())
            .setTitle(bookDO.getTitle())
            .setIsbn(bookDO.getIsbn())
            .setAuthorDTO(AuthorMapper.makeAuthorDTO(bookDO.getAuthorDO()))
            .setStockDTO(StockMapper.makeStockDTO(bookDO.getStockDO()));

        return bookDTOBuilder.createBookDTO();
    }


    public static List<BookDTO> makeBookDTOList(Collection<BookDO> bookList)
    {
        return bookList.stream()
            .map(BookMapper::makeBookDTO)
            .collect(Collectors.toList());
    }

}
