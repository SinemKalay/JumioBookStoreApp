package com.sinem.jumio.dataAccessObject;

import com.sinem.jumio.domainObject.OrderDO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDO, Long>
{
    Optional<List<OrderDO>> findByDeleted(Boolean deleted);

    Optional<OrderDO> findById(Long id);

}
