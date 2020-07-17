package ru.ssau.practice.service.db.pagination;

public interface PaginationQueryBuilder<I>
{
    PaginationQueryBuilder<I> order(String orderBy, boolean desc);

    PaginationResult<I> execute(int page, int perPage);
}
