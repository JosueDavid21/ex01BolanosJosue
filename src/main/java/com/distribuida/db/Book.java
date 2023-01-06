package com.distribuida.db;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private Double price ;
    public Book(){
    }

    public Book(Integer id, String isbn, String title, String author, double price) {
        this.id = id;
        this.isbn= isbn;
        this.title= title;
        this.author=author;
        this.price=price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
