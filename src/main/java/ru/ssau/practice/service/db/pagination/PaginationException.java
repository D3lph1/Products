package ru.ssau.practice.service.db.pagination;

public class PaginationException extends RuntimeException
{
    public PaginationException(String message)
    {
        super(message);
    }
}
