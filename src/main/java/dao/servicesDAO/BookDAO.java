package dao.servicesDAO;

import model.entities.Book;
import model.entities.Loan;

public interface BookDAO {

    void insertBook(Book book);
    Book findBookByISBN(Long isbn);

    void insertLoanBook(Loan loan);
    void updateReturnLoan(Loan loan);
}
