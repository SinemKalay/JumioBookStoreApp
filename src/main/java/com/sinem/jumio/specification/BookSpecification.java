package com.sinem.jumio.specification;

import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.domainObject.AuthorDO;
import com.sinem.jumio.domainObject.BookDO;
import com.sinem.jumio.util.StaticValues;
import com.sinem.jumio.util.mapper.BookMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification implements Specification<BookDO>
{
    private BookDTO bookDTO;
    private List<Predicate> predicates = new ArrayList<Predicate>();


    public BookSpecification(BookDTO bookDTO)
    {
        super();
        this.bookDTO = bookDTO;

    }


    @Override
    public Predicate toPredicate(Root<BookDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {

        if (bookDTO != null)
        {

            addIsbnPrediction(root, criteriaBuilder);
            addTitlePrediction(root, criteriaBuilder);
            addAuthorPrediction(criteriaQuery, criteriaBuilder);
            Predicate notDeletedPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_DELETED), false);
            predicates.add(notDeletedPre);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
        return null;
    }


    private void addIsbnPrediction(Root<BookDO> root, CriteriaBuilder criteriaBuilder)
    {
        if (bookDTO.getIsbn() != null)
        {
            Predicate isbnPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_ISBN), bookDTO.getIsbn());
            predicates.add(isbnPre);
        }
    }

    private void addTitlePrediction(Root<BookDO> root, CriteriaBuilder criteriaBuilder)
    {
        if (bookDTO.getTitle() != null)
        {
            Predicate isbnPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_TITLE), bookDTO.getIsbn());
            predicates.add(isbnPre);
        }
    }

    private void addAuthorPrediction(CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {

        if (bookDTO.getAuthorDTO() != null)
        {
            Root<AuthorDO> root = criteriaQuery.from(AuthorDO.class);
            if (bookDTO.getAuthorDTO().getFirstName() != null)
            {
                Predicate autFNamePre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_AUTHOR_FIRST_NAME), bookDTO.getAuthorDTO().getFirstName());
                predicates.add(autFNamePre);
            }
            if (bookDTO.getAuthorDTO().getLastName() != null)
            {
                Predicate autFNamePre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_AUTHOR_LAST_NAME), bookDTO.getAuthorDTO().getLastName());
                predicates.add(autFNamePre);
            }
        }
    }

}
