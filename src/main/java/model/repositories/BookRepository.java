package model.repositories;

import model.entities.Author;
import model.entities.Book;

import java.util.List;

public interface BookRepository {
    public void registerBook(Book book);
    public Book findBookByIsbn(long isbn);
}
