package ru.ssau.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateBrandDTO
{
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
