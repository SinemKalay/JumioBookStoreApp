package com.sinem.jumio.service.stock;

import com.sinem.jumio.dataTransferObject.OrderItemDTO;
import com.sinem.jumio.domainObject.OrderItemDO;
import com.sinem.jumio.domainObject.StockDO;
import com.sinem.jumio.exception.InsufficientStorageException;
import com.sinem.jumio.exception.EntityNotFoundException;
import java.util.List;

public interface IStockService
{
    StockDO getStockDetail(Long bookId) throws EntityNotFoundException;

    Integer getStockNumberByBookId(Long bookId) throws EntityNotFoundException;

    void checkStocks(List<OrderItemDTO> orderItemDOSet);

    void decreaseStocks(List<OrderItemDO> orderItems);


}
