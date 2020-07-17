package ru.ssau.practice.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NewOfferDTO
{
    @NotNull
    @Range(min = 1)
    private Long product;

    @NotNull
    @DecimalMin(value = "0.01", message = "{offers.fields.price.min}")
    private BigDecimal price;

    @NotNull(message = "{offers.fields.actualUntil.not_null}")
    private long actualUntil;

    public NewOfferDTO(Long product, BigDecimal price, long actualUntil)
    {
        this.product = product;
        this.price = price;
        this.actualUntil = actualUntil;
    }

    public Long getProduct()
    {
        return product;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public LocalDateTime getActualUntil()
    {
        return new java.sql.Timestamp(actualUntil).toLocalDateTime();
    }
}
