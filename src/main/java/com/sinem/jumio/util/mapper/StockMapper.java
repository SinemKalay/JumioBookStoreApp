package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.StockDTO;
import com.sinem.jumio.domainObject.StockDO;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class StockMapper
{
    public static StockDO makeStockDO(StockDTO stockDTO)
    {
        return new StockDO(stockDTO.getNumberInStock(), BookMapper.makeBookDO(stockDTO.getBookDTO()));
    }


    public static StockDTO makeStockDTO(StockDO stockDO)
    {
        StockDTO.StockDTOBuilder stockDTOBuilder = StockDTO.newBuilder()
            .setId(stockDO.getId())
            .setBookDTO(BookMapper.makeBookDTO(stockDO.getBookDO()))
            .setNumberInStock(stockDO.getNumberOfCopy());

        return stockDTOBuilder.createStockDTO();
    }


    public static Set<StockDTO> makeStockDTOList(Collection<StockDO> bookList)
    {
        return bookList.stream()
            .map(StockMapper::makeStockDTO)
            .collect(Collectors.toSet());
    }
}
