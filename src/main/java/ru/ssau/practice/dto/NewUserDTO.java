package ru.ssau.practice.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class NewUserDTO
{
    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 6)
    private String password;

    public NewUserDTO(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
