package ru.ssau.practice.service.user;

public class UserAlreadyExistsException extends Exception
{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }

    public static UserAlreadyExistsException withEmail(String email)
    {
        return new UserAlreadyExistsException("User with email " + email + " already exists.");
    }
}
