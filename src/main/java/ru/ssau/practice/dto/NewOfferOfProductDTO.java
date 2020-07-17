package ru.ssau.practice.dto;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class NewOfferOfProductDTO
{
    @NotNull
    @DecimalMin(value = "0.01", message = "{offers.fields.price.min}")
    private BigDecimal price;

    @NotNull(message = "{offers.fields.actualUntil.not_null}")
    private Long actualUntil;

    public NewOfferOfProductDTO(BigDecimal price, Long actualUntil)
    {
        this.price = price;
        this.actualUntil = actualUntil;
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
