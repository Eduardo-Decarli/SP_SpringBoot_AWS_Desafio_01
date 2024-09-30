package entities.services;

import entities.Book;

public interface BookServices {
    public void registerBook(Book book);
    public Book sourchBook(Book book);
    public void loanReturn();
}
