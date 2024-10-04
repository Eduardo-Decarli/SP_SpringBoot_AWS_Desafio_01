package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;

    private String title;
    private Author author;
    private LocalDate datePlublication;
    private long isbn;
    private String genre;
    private Integer quantity;

    public Book(String title, Author author, LocalDate datePlublication, long isbn, String genre, Integer quantity) {
        this.title = title;
        this.author = author;
        this.datePlublication = datePlublication;
        this.isbn = isbn;
        this.genre = genre;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getDatePlublication() {
        return datePlublication;
    }

    public void setDatePlublication(LocalDate datePlublication) {
        this.datePlublication = datePlublication;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return  "Title: " + title +
                ", Author: " + author.getName() +
                ", Date of Publication: " + datePlublication.format(fmt) +
                ", ISBN: " + isbn +
                ", Genre: " + genre +
                ", Quantity in Stock: " + quantity;
    }
}
