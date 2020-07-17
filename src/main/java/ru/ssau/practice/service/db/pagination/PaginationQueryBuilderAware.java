package ru.ssau.practice.service.db.pagination;

public interface PaginationQueryBuilderAware<I>
{
    PaginationQueryBuilder<I> createPaginationQueryBuilder();
}
