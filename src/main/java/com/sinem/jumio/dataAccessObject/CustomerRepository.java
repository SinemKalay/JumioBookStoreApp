package com.sinem.jumio.dataAccessObject;

import com.sinem.jumio.domainObject.CustomerDO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerDO, Long>
{
    Optional<CustomerDO> findByIdAndDeleted(Long customerId, Boolean deleted);

    Optional<CustomerDO>  findCustomerDOByUsername(String userName);

    Optional<List<CustomerDO>> findAllByDeleted(boolean isDeleted);
}
