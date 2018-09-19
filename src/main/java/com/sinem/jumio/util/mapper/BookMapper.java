package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.domainObject.BookDO;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper
{
    public static BookDO makeBookDO(BookDTO bookDTO)
    {
        if(bookDTO.getAuthorDTO()==null){
            return new BookDO(bookDTO.getId(),bookDTO.getIsbn(), bookDTO.getTitle());
        }
        else{
            return new BookDO(bookDTO.getId(),bookDTO.getIsbn(), bookDTO.getTitle(), AuthorMapper.makeAuthorDO(bookDTO.getAuthorDTO()));
        }

    }

    public static BookDTO makeBookDTO(BookDO bookDO)
    {
        BookDTO.BookDTOBuilder bookDTOBuilder = BookDTO.newBuilder()
            .setId(bookDO.getId())
            .setTitle(bookDO.getTitle())
            .setIsbn(bookDO.getIsbn())
            .setAuthorDTO(AuthorMapper.makeAuthorDTO(bookDO.getAuthorDO()));


        return bookDTOBuilder.createBookDTO();
    }

    public static List<BookDTO> makeBookDTOList(Collection<BookDO> bookList)
    {
        return bookList.stream()
            .map(BookMapper::makeBookDTO)
            .collect(Collectors.toList());
    }

}
