package com.sinem.jumio.service.book;

import com.sinem.jumio.dataAccessObject.BookRepository;
import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.domainObject.BookDO;
import com.sinem.jumio.exception.ConstraintsViolationException;
import com.sinem.jumio.exception.EntityNotFoundException;
import com.sinem.jumio.specification.BookSpecification;
import com.sinem.jumio.util.mapper.BookMapper;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService implements IBookService
{
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;


    /**
     * Get all books as DTO
     *
     * @throws EntityNotFoundException if no book found.
     */
    @Override
    public List<BookDTO> findAll() throws EntityNotFoundException
    {
        return BookMapper.makeBookDTOList(findAllDO());
    }


    /**
     * Get undeleted books  by book id.
     *
     * @param bookId
     * @return bookDTO
     */
    @Override
    public BookDTO find(Long bookId)
    {
        return BookMapper.makeBookDTO(findBookDO(bookId));
    }


    /**
     * Get undeleted books  by book id.
     *
     * @param bookId
     * @return bookDO
     * @throws EntityNotFoundException if no book with the given id was found.
     */
    @Override
    public BookDO findBookDO(Long bookId)
    {
        BookDO bookDO = null;
        try
        {
            bookDO = bookRepository.findByIdAndDeleted(bookId, false)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + bookId));
            LOG.info("Book found with received bookId:" + bookId);
        }
        catch (EntityNotFoundException e)
        {
            LOG.warn("Book NOT found with received bookId:" + bookId);
            e.printStackTrace();
        }
        return bookDO;
    }


    /**
     * Create book with given book attributes.
     *
     * @param bookDTO
     * @return bookDTO
     * @throws ConstraintsViolationException if a book already exists with the given ISBN,...
     */
    @Override
    @Transactional
    public BookDTO create(BookDTO bookDTO) throws ConstraintsViolationException
    {
        try
        {
            BookDO bookDO = BookMapper.makeBookDO(bookDTO);
            return BookMapper.makeBookDTO(bookRepository.save(bookDO));

        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are violated due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }


    /**
     * Soft delete an existing book by id and also break the relation with Author.
     *
     * @param bookId
     * @throws EntityNotFoundException if no book with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long bookId)
    {
        BookDO bookDO = findBookDO(bookId);
        bookDO.setDeleted(true);
        bookDO.setAuthorDO(null);
        LOG.info("Book has been soft deleted. BookId:" + bookId);

    }


    /**
     * Update book's entities
     *
     * @param bookDTO
     */
    @Override
    @Transactional
    public void update(BookDTO bookDTO)
    {
        bookRepository.save(BookMapper.makeBookDO(bookDTO));
        LOG.info("Book has been updated. BookId:" + bookDTO.getId());

    }


    /**
     * Advance search for book based on isbn, title, author's firstName&lastName
     *
     * @param bookDTO
     * @return List<BookDTO>
     * @throws EntityNotFoundException if no book with the given criterias
     */
    @Override
    public List<BookDTO> advanceSearchBook(BookDTO bookDTO) throws EntityNotFoundException
    {
        Specification<BookDO> spec = new BookSpecification(bookDTO);
        List<BookDO> bookDOList = bookRepository.findAll(spec);
        if (bookDOList == null)
        {
            LOG.warn("Book NOT found with received the criteria");

            throw new EntityNotFoundException("Could not find entity within the criterias");
        }
        LOG.info("Book found with received the criteria");

        return BookMapper.makeBookDTOList(bookDOList);
    }


    /**
     * Get all books as DO
     *
     * @throws EntityNotFoundException if no book found.
     */
    private List<BookDO> findAllDO() throws EntityNotFoundException
    {
        return bookRepository.findAllByDeleted(false)
            .orElseThrow(() -> new EntityNotFoundException("Could not find any book"));
    }


}
