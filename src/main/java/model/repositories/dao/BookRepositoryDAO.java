package model.repositories.dao;

import model.entities.Book;

import java.util.List;

public interface BookRepositoryDAO {

    void insertBook(Book book);
    public Book selectBookByIsbn(String isbn);
    public Book selectBookByName(int id);
    public List<Book> selectBooksByAuthor(int idAuthor);
    public List<Book> selectAllBooks();
    public void updateBookMinusQT(int idBook, int quantity);
    public void updateBookPlusQT(int idBook, int quantity);
    public Book findBookById(int id);
}
