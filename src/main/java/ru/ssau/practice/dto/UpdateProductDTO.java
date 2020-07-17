package ru.ssau.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class UpdateProductDTO
{
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String article;

    @JsonProperty
    private String barcode;

    @JsonProperty
    private BrandDTO brand;

    @JsonProperty
    private Collection<OfferDTO> offers;

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

    public BrandDTO getBrand()
    {
        return brand;
    }

    public void setBrand(BrandDTO brand)
    {
        this.brand = brand;
    }

    public Collection<OfferDTO> getOffers()
    {
        return offers;
    }

    public void setOffers(Collection<OfferDTO> offers)
    {
        this.offers = offers;
    }
}
