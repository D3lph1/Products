package ru.ssau.practice.service.db.pagination;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The class uses standard (native) JPA query abilities to implement pagination mechanism.
 */
public class JpaQueryPaginator<I> implements Paginator<I>
{
    @Override
    @SuppressWarnings("unchecked")
    public PaginationResult<I> paginate(Query query, int page, int perPage, Supplier<Long> counter)
    {
        long total = counter.get();

        long pages = (long) Math.ceil((double) total / perPage);

        if (page > pages) {
            return new PaginationResult<>(new ArrayList<>(), pages);
        }

        List<I> items = (List<I>) query.setFirstResult(perPage * (page - 1))
                .setMaxResults(perPage)
                .getResultList();

        return new PaginationResult<>(items, pages);
    }
}
