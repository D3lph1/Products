package ru.ssau.practice.repository.product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ProductRepositoryBasic, ProductRepositoryCustom
{
}
