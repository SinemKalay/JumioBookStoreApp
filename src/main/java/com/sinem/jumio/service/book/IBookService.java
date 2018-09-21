package com.sinem.jumio.service.book;

import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.domainObject.BookDO;
import com.sinem.jumio.exception.ConstraintsViolationException;
import com.sinem.jumio.exception.EntityNotFoundException;
import java.util.List;

public interface IBookService
{
    List<BookDTO> findAll() throws EntityNotFoundException;

    BookDTO find(Long bookId) throws EntityNotFoundException;

    BookDO findBookDO(Long bookId) throws EntityNotFoundException;

    BookDTO create(BookDTO bookDTO)throws ConstraintsViolationException;

    void delete(Long bookId) throws EntityNotFoundException;

    void update(BookDTO bookDTO);

    List<BookDTO> advanceSearchBook(BookDTO bookDTO) throws EntityNotFoundException;
}
