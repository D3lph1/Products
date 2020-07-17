package ru.ssau.practice.service.db.pagination;

import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.function.Supplier;

/**
 * @param <I> Type of paginated entity.
 */
@Service
public interface Paginator<I>
{
    PaginationResult<I> paginate(Query query, int page, int perPage, Supplier<Long> counter);
}
