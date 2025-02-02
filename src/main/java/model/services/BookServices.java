package model.services;

import exceptions.ServicesException;
import model.entities.Book;
import model.repositories.BookRepository;
import model.services.dao.BookDAO;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookServices implements BookRepository {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private BookDAO bookDao;

    public BookServices(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void registerBook(Book book) {
        if(book.getAuthor().getId() <= 0){
            throw new ServicesException("Invalid ID!");
        }
        if(String.valueOf(book.getIsbn()).length() != 13){
            throw new ServicesException("Invalid ISBN, ISBN require 13 numbers");
        }
        if(bookDao.selectBookByIsbn(book.getIsbn()) != null){
            throw new ServicesException("Invalid ISBN, there is a book with this code in stock");
        }
        bookDao.insertBook(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        if(String.valueOf(isbn).length() != 13){
            throw new ServicesException("Invalid ISBN, ISBN require 13 numbers");
        }
        Book book = bookDao.selectBookByIsbn(isbn);
        book.getDatePlublication().format(fmt);
        return book;
    }

    @Override
    public List<Book> findAllBooks() {
        if(bookDao.selectAllBooks().isEmpty()){
            throw new ServicesException("none books registered");
        }
        List<Book> listBooks = bookDao.selectAllBooks();
        listBooks.sort((bookTitle1, bookTitle2) -> bookTitle1.getTitle().toUpperCase().compareTo(bookTitle2.getTitle().toUpperCase()));
        return listBooks;
    }

    @Override
    public List<Book> findBooksAuthor(int id) {
        if(id <= 0){
            throw new ServicesException("Invalid ID");
        }
        List<Book> listBook = bookDao.selectBooksByAuthor(id);

        if(listBook.isEmpty()){
            System.out.println("There are not have a books his Author");
            return null;
        }else {
            System.out.println("These are the books");
            listBook.sort((bookTitle1, bookTitle2) -> bookTitle1.getTitle().toUpperCase().compareTo(bookTitle2.getTitle().toUpperCase()));
            return listBook;
        }
    }
}
