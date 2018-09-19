package com.sinem.jumio.dataAccessObject;

import com.sinem.jumio.domainObject.BookDO;
import com.sinem.jumio.domainObject.StockDO;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<StockDO, Long>
{
    Optional<StockDO> findByBookDO(BookDO bookDO);
}
