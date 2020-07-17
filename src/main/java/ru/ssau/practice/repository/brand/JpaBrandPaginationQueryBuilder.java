package ru.ssau.practice.repository.brand;

import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.service.db.pagination.AbstractPaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.Paginator;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.Map;

public class JpaBrandPaginationQueryBuilder extends AbstractPaginationQueryBuilder<Brand> implements BrandPaginationQueryBuilder
{
    private final Map<String, Path<?>> orderByToPath;

    private Predicate nameLike;

    private Predicate nameLikeCount;

    public JpaBrandPaginationQueryBuilder(Class<Brand> type, EntityManager em, Paginator<Brand> paginator)
    {
        super(type, em, paginator);
        orderByToPath = new HashMap<String, Path<?>>() {{
            put("id", criteriaRoot.get("id"));
            put("name", criteriaRoot.get("name"));
        }};
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <Y> Path<Y> mapOrderByToPath(String orderBy)
    {
        return (Path<Y>) orderByToPath.get(orderBy);
    }

    @Override
    protected void preExecute()
    {
        criteria.where(nameLike);
        criteriaCount.where(nameLikeCount);
    }

    @Override
    public BrandPaginationQueryBuilder whereNameLike(String name)
    {
        nameLike = whereLikePredicate(criteriaRoot.get("name"), name);
        nameLikeCount = whereLikePredicate(criteriaCountRoot.get("name"), name);

        return this;
    }
}
