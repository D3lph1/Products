package ru.ssau.practice.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NewProductDTO
{
    @NotNull
    @Length(min = 3, max = 128, message = "{products.fields.name.length}")
    private String name;

    @NotNull
    @Length(min = 3, max = 128, message = "{products.fields.article.length}")
    private String article;

    @NotNull
    @Length(min = 13, max = 13, message = "{products.fields.barcode.length}")
    private String barcode;

    @NotNull(message = "{products.fields.brand.not_null}")
    @Range(min = 1)
    private Long brand;

    @Valid
    private NewOfferOfProductDTO[] offers;

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

    public Long getBrand()
    {
        return brand;
    }

    public void setBrand(Long brand)
    {
        this.brand = brand;
    }

    public NewOfferOfProductDTO[] getOffers()
    {
        return offers;
    }

    public void setOffers(NewOfferOfProductDTO[] offers)
    {
        this.offers = offers;
    }
}
