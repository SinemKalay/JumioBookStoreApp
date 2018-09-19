package com.sinem.jumio.service.stock;

import com.sinem.jumio.dataAccessObject.StockRepository;
import com.sinem.jumio.dataTransferObject.OrderItemDTO;
import com.sinem.jumio.domainObject.BookDO;
import com.sinem.jumio.domainObject.OrderItemDO;
import com.sinem.jumio.domainObject.StockDO;
import com.sinem.jumio.exception.EntityNotFoundException;
import com.sinem.jumio.exception.InsufficientStorageException;
import com.sinem.jumio.service.book.IBookService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService
{
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private final StockRepository stockRepository;

    @Autowired
    private final IBookService iBookService;


    public StockService(final StockRepository stockRepository, IBookService iBookService)
    {
        this.stockRepository = stockRepository;
        this.iBookService = iBookService;

    }


    /**
     * getStockDetail for specific book
     *
     * @param bookId
     * @return StockDO
     * @throws EntityNotFoundException if no stock record found.
     */
    @Override
    public StockDO getStockDetail(Long bookId) throws EntityNotFoundException
    {
        BookDO bookDO = iBookService.findBookDO(bookId);
        return stockRepository.findByBookDO(bookDO)
            .orElseThrow(() -> new EntityNotFoundException("Could not find any stock with id: " + bookId));
    }


    /**
     * Get stock number for specific book
     *
     * @param bookId
     * @return stock number as Integer
     * @throws EntityNotFoundException if no stock record found.
     */
    @Override
    public Integer getStockNumberByBookId(Long bookId) throws EntityNotFoundException
    {
        BookDO bookDO = iBookService.findBookDO(bookId);
        return stockRepository.findByBookDO(bookDO).map(StockDO::getNumberOfCopy).get();
    }


    /**
     * Decrease stock numbers for specific book according to received order quantity
     *
     * @param orderItems
     */
    @Override
    public void decreaseStocks(List<OrderItemDO> orderItems)
    {
        orderItems.stream().forEach(s -> {

            try
            {
                decreaseStockNumber(s.getBookDO().getId(), s.getQuantity());
            }
            catch (EntityNotFoundException e)
            {
                e.printStackTrace();
            }

        });
    }


    /**
     * Check numbers  of stocks for specific list of books according to received order quantity
     *
     * @param orderItemDOSet
     */
    @Override
    public void checkStocks(List<OrderItemDTO> orderItemDOSet)
    {
        orderItemDOSet.stream().forEach(s -> {
            try
            {
                checkStock(s.getBookDTO().getId(), s.getQuantity());
            }
            catch (InsufficientStorageException e)
            {
                e.printStackTrace();
            }
        });

    }


    private void decreaseStockNumber(Long bookId, Integer quantity) throws EntityNotFoundException
    {
        StockDO stockDetail = getStockDetail(bookId);
        stockDetail.setNumberOfCopy(stockDetail.getNumberOfCopy() - quantity);
        stockRepository.save(stockDetail);
    }


    private Integer checkStock(Long bookId, Integer quantity) throws InsufficientStorageException
    {
        Integer currentStockNumber = -1;
        try
        {
            currentStockNumber = getStockNumberByBookId(bookId);
            if (currentStockNumber < quantity)
            {
                throw new InsufficientStorageException("Book id " + bookId + " is not in the stock.");
            }
        }
        catch (EntityNotFoundException e)
        {
            e.printStackTrace();
        }
        return currentStockNumber;
    }


}
