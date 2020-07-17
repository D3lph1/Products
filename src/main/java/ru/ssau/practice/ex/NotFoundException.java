package ru.ssau.practice.ex;

public class NotFoundException extends Exception
{
    private Object reason;

    public NotFoundException(Long[] IDs)
    {
        this.reason = IDs;
    }

    public Object getReason()
    {
        return reason;
    }
}
