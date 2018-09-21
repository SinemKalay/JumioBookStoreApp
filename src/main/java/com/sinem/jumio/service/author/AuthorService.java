package com.sinem.jumio.service.author;

import com.sinem.jumio.dataAccessObject.AuthorRepository;
import com.sinem.jumio.domainObject.AuthorDO;
import com.sinem.jumio.exception.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService
{
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;


    /**
     * Get undeleted authorDO  by firstname and lastname.
     *
     * @param firstName
     * @param lastName
     * @return authorDO
     * @throws EntityNotFoundException if no book with the given id was found.
     */
    @Override
    public AuthorDO findAuthorDOByName(String firstName, String lastName) throws EntityNotFoundException
    {
        AuthorDO authorDO = null;

        authorDO = authorRepository.findByFirstNameAndLastNameAndDeleted(firstName, lastName, false)
            .orElseThrow(() ->
                new EntityNotFoundException("Could not find author with firstname: " + firstName + " and lastname " + lastName));
        LOG.info("Author found with authorId:" + authorDO.getId());

        return authorDO;
    }


    /**
     * Get undeleted author  by author id.
     *
     * @param authorId
     * @return AuthorDO
     * @throws EntityNotFoundException if no author with the given id was found.
     */
    @Override
    public AuthorDO findAuthorDOById(Long authorId) throws EntityNotFoundException
    {
        AuthorDO authorDO = null;

            authorDO = authorRepository.findByIdAndDeleted(authorId, false)
                .orElseThrow(() -> new EntityNotFoundException("Author NOT found with received authorId:" + authorId));
            LOG.info("Author found with received authorId:" + authorId);
        return authorDO;
    }

}
