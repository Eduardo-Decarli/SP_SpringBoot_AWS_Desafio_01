package model.repositories.dao;

import model.entities.Author;
import model.entities.Book;
import model.entities.Loan;

import java.util.List;

public interface BookRepositoryDAO {

    void insertBook(Book book);
    public Book selectBookByIsbn(long isbn);
    public Book selectBookByName(int id);
    public List<Book> selectBooksByAuthor(int idAuthor);
    public List<Book> selectAllBooks();
    public void updateBookQt(int idBook, int quantity);
    public Book findBookById(int id);
}
