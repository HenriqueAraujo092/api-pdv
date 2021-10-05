package com.apipdv.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @NotNull
    private Number salePrice;

    private Number costPrice;

    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Number salePrice) {
        this.salePrice = salePrice;
    }

    public Number getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Number costPrice) {
        this.costPrice = costPrice;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getSalePrice(), product.getSalePrice()) && Objects.equals(getCostPrice(), product.getCostPrice()) && Objects.equals(getActive(), product.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSalePrice(), getCostPrice(), getActive());
    }

}
