package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;

    private String title;
    private Author author;
    private LocalDate datePlublication;
    private String isbn;
    private String genre;
    private Integer quantity;

    public Book(String title, Author author, LocalDate datePlublication, String isbn, String genre, Integer quantity) {
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

    public String getIsbn() {
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
        return "\n=====================\n" +
                "Title: " + title +
                "\nAuthor: " + author.getName() +
                "\nDate of Publication: " + datePlublication.format(fmt) +
                "\nISBN: " + isbn +
                "\nGenre: " + genre +
                "\nQuantity in Stock: " + quantity +
                "\n=====================\n";
    }
}
