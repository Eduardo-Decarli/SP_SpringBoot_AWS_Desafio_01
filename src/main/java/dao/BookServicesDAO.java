package dao;

import dao.servicesDAO.BookDAO;
import model.entities.Book;
import model.entities.Loan;

public class BookServicesDAO implements BookDAO {

    @Override
    public void insertBook(Book book) {

    }

    @Override
    public Book findBookByISBN(Long isbn) {
        return null;
    }

    @Override
    public void insertLoanBook(Loan loan) {

    }

    @Override
    public void updateReturnLoan(Loan loan) {

    }
}
