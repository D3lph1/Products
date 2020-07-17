package ru.ssau.practice.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"article", "brand_id"})})
public class Product extends Identifiable
{
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @Column(nullable = false)
    private String article;

    @Column(unique = true, length = 13, nullable = false)
    private String barcode;

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Offer> offers;

    public Product()
    {
    }

    public Product(String name, Brand brand, String article, String barcode)
    {
        this.name = name;
        this.brand = brand;
        this.article = article;
        this.barcode = barcode;
    }

    public String getName()
    {
        return name;
    }

    public void rename(String name)
    {
        this.name = name;
    }

    public Brand getBrand()
    {
        return brand;
    }

    public void setBrand(Brand brand)
    {
        this.brand = brand;
    }

    public String getArticle()
    {
        return article;
    }

    public void setArticle(String article)
    {
        this.article = article;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }

    public Collection<Offer> getOffers()
    {
        return offers;
    }
}
