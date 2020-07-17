package ru.ssau.practice.repository.product;

import ru.ssau.practice.entity.Product;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilderAware;

public interface ProductRepositoryCustom extends PaginationQueryBuilderAware<Product>
{
    ProductPaginationQueryBuilder createPaginationQueryBuilder();
}
