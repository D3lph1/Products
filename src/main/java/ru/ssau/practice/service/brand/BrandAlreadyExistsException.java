package ru.ssau.practice.service.brand;

public class BrandAlreadyExistsException extends Exception
{
    public BrandAlreadyExistsException(String message)
    {
        super(message);
    }

    public static BrandAlreadyExistsException withName(String name)
    {
        return new BrandAlreadyExistsException("Brand with name " + name + " already exists");
    }
}
