package ru.ssau.practice.repository.product;

import ru.ssau.practice.entity.Product;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;

public interface ProductPaginationQueryBuilder extends PaginationQueryBuilder<Product>
{
    ProductPaginationQueryBuilder whereNameLike(String name);

    ProductPaginationQueryBuilder whereArticleLike(String article);

    ProductPaginationQueryBuilder whereBrandNameLike(String brandName);
}
