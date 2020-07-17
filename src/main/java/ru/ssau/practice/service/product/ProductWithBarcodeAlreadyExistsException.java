package ru.ssau.practice.service.product;

public class ProductWithBarcodeAlreadyExistsException extends Exception
{
    public ProductWithBarcodeAlreadyExistsException(String barcode)
    {
        super("Product with barcode " + barcode +" already exists.");
    }
}
