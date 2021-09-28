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

    @Size(max = 50)
    private String street;

    @Size(max = 11)
    private String number;

    @Size(max = 50)
    private String complement;

    @Size(max = 50)
    private String neighborhood;

    @Size(max = 11)
    private String cep;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String state;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return Objects.equals(getId(), customer.getId()) && Objects.equals(getName(), customer.getName()) && Objects.equals(getStreet(), customer.getStreet()) && Objects.equals(getNumber(), customer.getNumber()) && Objects.equals(getComplement(), customer.getComplement()) && Objects.equals(getNeighborhood(), customer.getNeighborhood()) && Objects.equals(getCep(), customer.getCep()) && Objects.equals(getCity(), customer.getCity()) && Objects.equals(getState(), customer.getState()) && Objects.equals(getActive(), customer.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStreet(), getNumber(), getComplement(), getNeighborhood(), getCep(), getCity(), getState(), getActive());
    }
}
