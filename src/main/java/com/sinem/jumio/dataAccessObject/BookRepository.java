package com.sinem.jumio.dataAccessObject;

import com.sinem.jumio.domainObject.BookDO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookDO, Long>, JpaSpecificationExecutor<BookDO>
{
    Optional<BookDO> findByIdAndDeleted(Long bookId, Boolean deleted);

    Optional<List<BookDO>> findAllByDeleted(Boolean deleted);
}
