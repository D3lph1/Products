package ru.ssau.practice.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "brands")
public class Brand extends Identifiable
{
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Product> products;

    public Brand()
    {
    }

    public Brand(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void rename(String name)
    {
        this.name = name;
    }
}
