package com.apipdv.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Embedded
    private Addres addres;

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

    public Addres getAddres() {
        return addres;
    }

    public void setAddres(Addres addres) {
        this.addres = addres;
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
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) && Objects.equals(getName(), customer.getName()) && Objects.equals(getAddres(), customer.getAddres()) && Objects.equals(getActive(), customer.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddres(), getActive());
    }

}
