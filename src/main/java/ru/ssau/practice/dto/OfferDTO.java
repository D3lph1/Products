package ru.ssau.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OfferDTO
{
    @JsonProperty
    private long id;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private LocalDateTime actualUntil;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public LocalDateTime getActualUntil()
    {
        return actualUntil;
    }

    public void setActualUntil(LocalDateTime actualUntil)
    {
        this.actualUntil = actualUntil;
    }
}
