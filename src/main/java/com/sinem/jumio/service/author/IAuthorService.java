package com.sinem.jumio.service.author;

import com.sinem.jumio.domainObject.AuthorDO;
import com.sinem.jumio.exception.EntityNotFoundException;

public interface IAuthorService
{

    AuthorDO findAuthorDOByName(String firstName, String lastName) throws EntityNotFoundException;

    AuthorDO findAuthorDOById(Long authorId) throws EntityNotFoundException;
}
