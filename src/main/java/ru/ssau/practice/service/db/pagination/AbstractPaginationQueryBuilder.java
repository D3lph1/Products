package ru.ssau.practice.service.db.pagination;

import ru.ssau.practice.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

public abstract class AbstractPaginationQueryBuilder<E> implements PaginationQueryBuilder<E>
{
    protected final Class<E> type;

    protected final EntityManager em;

    protected final Paginator<E> paginator;

    protected final CriteriaBuilder cb;

    protected Root<E> criteriaRoot;

    protected Root<E> criteriaCountRoot;

    protected CriteriaQuery<E> criteria;

    protected CriteriaQuery<Long> criteriaCount;

    public AbstractPaginationQueryBuilder(Class<E> type, EntityManager em, Paginator<E> paginator)
    {
        this.type = type;
        this.em = em;
        this.paginator = paginator;
        cb = em.getCriteriaBuilder();
        createCriteriaQuery();
        createCriteriaQueryCount();
    }

    private void createCriteriaQuery()
    {
        criteria = cb.createQuery(type);
        criteriaRoot = criteria.from(type);
        criteria.select(criteriaRoot);
    }

    private void createCriteriaQueryCount()
    {
        criteriaCount = cb.createQuery(Long.class);
        criteriaCountRoot = criteriaCount.from(type);
        criteriaCount.select(cb.count(criteriaCountRoot));
    }

    protected Predicate whereLikePredicate(Path<String> path, String name)
    {
        return cb.like(cb.lower(path), name.toLowerCase() + "%");
    }

    @Override
    public PaginationQueryBuilder<E> order(String orderBy, boolean desc)
    {
        Path<?> path = mapOrderByToPath(orderBy);
        if (path == null) {
            return this;
        }

        criteria.orderBy(desc ? cb.desc(path) : cb.asc(path));

        return this;
    }

    protected abstract <Y> Path<Y> mapOrderByToPath(String orderBy);

    protected void preExecute()
    {
    }

    @Override
    public PaginationResult<E> execute(int page, int perPage)
    {
        preExecute();

        return paginator.paginate(
                em.createQuery(criteria),
                page,
                perPage,
                () -> em.createQuery(criteriaCount).getSingleResult()
        );
    }
}
