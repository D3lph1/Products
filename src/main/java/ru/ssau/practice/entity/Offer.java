package ru.ssau.practice.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends Identifiable
{
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime actualUntil;

    public Offer()
    {
    }

    public Offer(Product product, BigDecimal price, LocalDateTime actualUntil)
    {
        this.product = product;
        this.price = price;
        this.actualUntil = actualUntil;
    }

    public Product getProduct()
    {
        return product;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public LocalDateTime getActualUntil()
    {
        return actualUntil;
    }
}
