package com.sinem.jumio.dataAccessObject;

import com.sinem.jumio.domainObject.AuthorDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorDO,Long>, JpaSpecificationExecutor<AuthorDO>
{
    Optional<AuthorDO> findByIdAndDeleted(Long authorId, Boolean deleted);

    Optional<AuthorDO> findByFirstNameAndLastNameAndDeleted(String firstName, String lastName, Boolean deleted);



}
