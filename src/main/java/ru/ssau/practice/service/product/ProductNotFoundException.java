package ru.ssau.practice.service.product;

public class ProductNotFoundException extends Exception
{
    public ProductNotFoundException(String message)
    {
        super(message);
    }

    public static ProductNotFoundException byId(long id)
    {
        return new ProductNotFoundException("Product with id " + id + " not found");
    }
}
