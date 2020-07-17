package ru.ssau.practice.service.brand;

public class BrandNotFoundException extends Exception
{
    public BrandNotFoundException(String message)
    {
        super(message);
    }

    public static BrandNotFoundException byId(long id)
    {
        return new BrandNotFoundException("Brand with id " + id + " not found");
    }
}
