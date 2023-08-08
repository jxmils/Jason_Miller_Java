package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private String publishDate;

    @Column(name = "author_id")
    private int authorId;
    private String title;

    @Column(name = "publisher_id")
    private int publisherId;
    private double price;

    public Book() {}
    public Book(String isbn, String publishDate, int authorId, String title, int publisherId, double price) {
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.authorId = authorId;
        this.title = title;
        this.publisherId = publisherId;
        this.price = price;
    }
    public Book(int id, String isbn, String publishDate, int authorId, String title, int publisherId, double price) {
        this.id = id;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.authorId = authorId;
        this.title = title;
        this.publisherId = publisherId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(authorId, book.authorId) &&
                Objects.equals(price, book.price) &&
                Objects.equals(publishDate, book.publishDate) &&
                Objects.equals(publisherId, book.publisherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authorId, price, publishDate, publisherId);
    }
}
