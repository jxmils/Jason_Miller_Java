package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="publisher")
public class Publisher {
    @Id
    @Column(name="publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String phone;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn(name="publisher_id")
    private List<Book> books;

    public Publisher() {}
    public Publisher(String name, String street, String city, String state, String postalCode, String phone, String email) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
    }
    public Publisher(int id, String name, String street, String city, String state, String postalCode, String phone, String email) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public int getId() {
        return id;
    }

    public void setId(int publisherId) {
        this.id = publisherId;
    }

    public int getPublisherId() {
        return id;
    }

    public void setPublisherId(int publisherId) {
        this.id = publisherId;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(name, publisher.name) &&
                Objects.equals(street, publisher.street) &&
                Objects.equals(city, publisher.city) &&
                Objects.equals(state, publisher.state) &&
                Objects.equals(postalCode, publisher.postalCode) &&
                Objects.equals(phone, publisher.phone) &&
                Objects.equals(email, publisher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, city, state, postalCode, phone, email);
    }
}

//should I take out the id in the equals and hashcode because I took them out for book