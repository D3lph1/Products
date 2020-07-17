package ru.ssau.practice.repository.brand;

import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilderAware;

public interface BrandRepositoryCustom extends PaginationQueryBuilderAware<Brand>
{
    @Override
    BrandPaginationQueryBuilder createPaginationQueryBuilder();
}
