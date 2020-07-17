package ru.ssau.practice.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class NewBrandDTO
{
    @NotNull
    @Length(min = 2, max = 64, message = "{brands.fields.name.length}")
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
