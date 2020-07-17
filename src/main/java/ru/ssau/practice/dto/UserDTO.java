package ru.ssau.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO
{
    @JsonProperty
    private long id;

    @JsonProperty
    private String email;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
