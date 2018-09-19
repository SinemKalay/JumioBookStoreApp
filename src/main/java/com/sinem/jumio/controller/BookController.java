package com.sinem.jumio.controller;

import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.exception.ConstraintsViolationException;
import com.sinem.jumio.exception.EntityNotFoundException;
import com.sinem.jumio.service.book.IBookService;
import com.sinem.jumio.util.mapper.BookMapper;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a book will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/books")
public class BookController
{
    @Autowired
    private final IBookService iBookService;


    public BookController(final IBookService iBookService)
    {
        this.iBookService = iBookService;

    }


    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() throws EntityNotFoundException
    {
        List<BookDTO> bookList = iBookService.findAll();

        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }


    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBook(@Valid @PathVariable long bookId) throws EntityNotFoundException
    {
        BookDTO bookDTO = iBookService.find(bookId);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);

    }


    @PostMapping()
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) throws ConstraintsViolationException
    {
        return new ResponseEntity<>(iBookService.create(bookDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookDTO> deleteBook(@Valid @PathVariable Long bookId)
    {
        iBookService.delete(bookId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PatchMapping()
    public void updateBook(@Valid @RequestBody BookDTO bookDTO)
    {
        iBookService.update(bookDTO);
    }

    @PostMapping("/search")
    public ResponseEntity<List<BookDTO>> advanceSearchBook(@RequestBody BookDTO bookDTO) throws EntityNotFoundException
    {
        List<BookDTO> bookDTOList = iBookService.advanceSearchBook(bookDTO);
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }
}
