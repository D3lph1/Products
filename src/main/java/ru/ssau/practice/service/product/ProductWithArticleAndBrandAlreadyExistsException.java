package ru.ssau.practice.service.product;

import ru.ssau.practice.entity.Brand;

public class ProductWithArticleAndBrandAlreadyExistsException extends Exception
{
    public ProductWithArticleAndBrandAlreadyExistsException(String article, Brand brand)
    {
        super(String.format("Product with article %s and brand with id %d already exists.", article, brand.getId()));
    }
}
