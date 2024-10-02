package model.repositories;

import model.entities.Book;

public interface BookRepository {
    public void registerBook(Book book);
    public Book sourchBook(Book book);

}
