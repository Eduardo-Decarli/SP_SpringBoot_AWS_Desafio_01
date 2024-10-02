package model.services;

import exceptions.ServicesException;
import model.entities.Book;
import model.repositories.BookRepository;
import model.services.dao.BookDAO;

public class BookServices implements BookRepository {

    private BookDAO bookDao;

    public BookServices(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void registerBook(Book book) {
        System.out.println(book.getAuthor().getId());
        if(book.getAuthor().getId() <= 0){
            throw new ServicesException("Invalid ID!");
        }
        if(String.valueOf(book.getIsbn()).length() != 8){
            throw new ServicesException("Invalid ISBN, ISBN require 8 numbers");
        }
        bookDao.insertBook(book);
    }

    @Override
    public Book findBookByIsbn(long isbn) {
        if(isbn > 8 || isbn < 8){
            throw new ServicesException("Invalid ISBN, ISBN require 8 numbers");
        }

        Book book = bookDao.selectBookByIsbn(isbn);

        return book;
    }
}
