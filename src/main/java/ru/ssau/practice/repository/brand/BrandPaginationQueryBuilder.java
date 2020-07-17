package ru.ssau.practice.repository.brand;

import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;

public interface BrandPaginationQueryBuilder extends PaginationQueryBuilder<Brand>
{
    BrandPaginationQueryBuilder whereNameLike(String name);
}
