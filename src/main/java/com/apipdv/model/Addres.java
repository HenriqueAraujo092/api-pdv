package com.apipdv.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.util.Objects;

@Embeddable
public class Addres {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Addres)) return false;
        Addres addres = (Addres) o;
        return Objects.equals(getStreet(), addres.getStreet()) && Objects.equals(getNumber(), addres.getNumber()) && Objects.equals(getComplement(), addres.getComplement()) && Objects.equals(getNeighborhood(), addres.getNeighborhood()) && Objects.equals(getCep(), addres.getCep()) && Objects.equals(getCity(), addres.getCity()) && Objects.equals(getState(), addres.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getNumber(), getComplement(), getNeighborhood(), getCep(), getCity(), getState());
    }

}
