package application;

import exceptions.AppException;
import model.entities.*;
import model.entities.enumeration.StatusLoan;
import model.services.AuthorServices;
import model.services.BookServices;
import model.services.LoanServices;
import model.services.MemberServices;
import model.services.dao.AuthorDAO;
import model.services.dao.BookDAO;
import model.services.dao.LoanDAO;
import model.services.dao.MemberDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        AuthorServices authorServices = new AuthorServices(new AuthorDAO());
        BookServices bookServices = new BookServices(new BookDAO());
        MemberServices memberServices = new MemberServices(new MemberDAO());
        LoanServices loanServices = new LoanServices(new LoanDAO());

        Member member = null;

        System.out.println("welcome to library manager\n");
        int optionSelected;

        System.out.print("press 1 to login or 2 to register: ");
        int optionLogin = sc.nextInt();
        sc.nextLine();

        if (!(optionLogin == 1 || optionLogin == 2)) {
            throw new AppException("Invalid option");
        }

        switch (optionLogin) {
            case 1:
                System.out.println("\n=== Login ===");
                System.out.print("Enter with a email: ");
                String emailLogin = sc.nextLine();
                member = memberServices.findMemberByEmail(emailLogin);
                System.out.println("\n\n=====================");
                System.out.println("Login Complete");
                System.out.println("Hello! " + member.getName());
                System.out.println("=====================");
                break;

            case 2:
                System.out.println("=== Register ===");
                System.out.print("Write your name: ");
                String name = sc.nextLine();
                System.out.print("Write your address (city/state): ");
                String address = sc.nextLine();
                System.out.print("Write your phone number (only numbers): ");
                long phoneNumber = sc.nextLong();
                sc.nextLine();
                System.out.print("Write your email: ");
                String emailRegister = sc.nextLine();
                System.out.print("Write your date of association (dd/MM/yyyy): ");
                LocalDate dateAssociation = LocalDate.parse(sc.nextLine(), fmt);

                member = new Member(name, address, phoneNumber, emailRegister, dateAssociation);
                memberServices.registerNewMember(member);

                System.out.println("Create a new member with this information: " + member);
                break;
        }


        do {
            Author author = null;
            Book book = null;

            System.out.println("\n=== Select an option ===");
            System.out.println("Press 1 to list book options");
            System.out.println("Press 2 to list Author options");
            System.out.println("Press 3 to list Member options");
            System.out.println("Press 4 to list loan options");
            System.out.println("Press 5 to register a new return");
            System.out.println("Press 6 to generate a report");
            System.out.println("Press 7 to exit\n");
            System.out.print("Option selected: ");
            optionSelected = sc.nextInt();
            sc.nextLine();
            if (!(optionSelected == 1 || optionSelected == 2 || optionSelected == 3 || optionSelected == 4
                    || optionSelected == 5 || optionSelected == 6 || optionSelected == 7)) {
                throw new AppException("Invalid Option");
            }

            switch (optionSelected) {

                case 1:
                    System.out.println("\n=== Books Options ===");
                    System.out.println("Press 1 to list books in stock");
                    System.out.println("Press 2 to register new books in stock");
                    System.out.print("Option selected: ");
                    optionSelected = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    if (!(optionSelected == 1 || optionSelected == 2)) {
                        throw new AppException("Invalid Option");
                    }

                    switch (optionSelected) {
                        case 1:
                            System.out.println("=== Books in Stock ===\n");
                            for (Book correntBook : bookServices.findAllBooks()) {
                                System.out.println(correntBook);
                            }
                            break;

                        case 2:
                            System.out.println("=== Register a new Book ===");
                            System.out.print("Write the book title: ");
                            String titleBook = sc.nextLine();
                            System.out.print("Write the Date of Publication (dd/MM/yyyy): ");
                            LocalDate datePublication = LocalDate.parse(sc.nextLine(), fmt);
                            System.out.print("Write the ISBN: ");
                            long isbn = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Write the book's genre: ");
                            String genre = sc.nextLine();
                            System.out.print("Write quantity books arrive: ");
                            int quantity = sc.nextInt();
                            sc.nextLine();

                            System.out.println("\n=== let's write the author information ===");
                            System.out.println("Press 1 associate an already registered author");
                            System.out.println("Press 2 register a new author");
                            System.out.print("Option selected: ");
                            optionSelected = sc.nextInt();
                            sc.nextLine();

                            if (!(optionSelected == 1 || optionSelected == 2)) {
                                throw new AppException("Invalid option");
                            }

                            switch (optionSelected) {
                                case 1:
                                    List<Author> authors = authorServices.findAllAuthors();
                                    for (Author correntAuthor : authors) {
                                        System.out.println(correntAuthor);
                                    }
                                    System.out.print("What is the author's id?: ");
                                    int authorId = sc.nextInt();
                                    sc.nextLine();

                                    author = authorServices.findAuthorById(authorId);
                                    book = new Book(titleBook, author, datePublication, isbn, genre, quantity);
                                    bookServices.registerBook(book);
                                    break;

                                case 2:
                                    System.out.println("== Register a new author ==\n");
                                    System.out.print("Write the author's name: ");
                                    String authorName = sc.nextLine();
                                    System.out.print("Write the birth of date: ");
                                    LocalDate authorBirthOfDate = LocalDate.parse(sc.nextLine(), fmt);
                                    System.out.print("Write the author's nationality: ");
                                    String authorNationality = sc.nextLine();
                                    System.out.print("Write the Author's biography: ");
                                    String authorBiography = sc.nextLine();

                                    author = new Author(authorName, authorBirthOfDate, authorNationality, authorBiography);
                                    authorServices.registerNewAuthor(author);

                                    book = new Book(titleBook, author, datePublication, isbn, genre, quantity);
                                    bookServices.registerBook(book);
                                    break;

                                default:
                                    System.out.println("Invalid Option");
                                    break;
                            }
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Press 1 to list authors in the system");
                    System.out.println("Press 2 to register new author in the system");
                    System.out.print("Option selected: ");
                    optionSelected = sc.nextInt();
                    sc.nextLine();
                    if (!(optionSelected == 1 || optionSelected == 2)) {
                        throw new AppException("Invalid option");
                    }

                    switch (optionSelected) {
                        case 1:
                            System.out.println("\n=== Authors in the System ===");
                            List<Author> authors = authorServices.findAllAuthors();
                            for (Author correntAuthor : authors) {
                                System.out.println(correntAuthor);
                            }
                            break;

                        case 2:
                            System.out.println("== Register a new author ==\n");
                            System.out.print("Write the author's name: ");
                            String authorName = sc.nextLine();
                            System.out.print("Write the birth of date: ");
                            LocalDate authorBirthOfDate = LocalDate.parse(sc.nextLine(), fmt);
                            System.out.print("Write the author's nationality: ");
                            String authorNationality = sc.nextLine();
                            System.out.print("Write the Author's biography: ");
                            String authorBiography = sc.nextLine();

                            author = new Author(authorName, authorBirthOfDate, authorNationality, authorBiography);
                            authorServices.registerNewAuthor(author);
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Press 1 to list members in the system");
                    System.out.println("Press 2 to register new member in the system");
                    System.out.print("Option selected: ");
                    optionSelected = sc.nextInt();
                    sc.nextLine();
                    if (!(optionSelected == 1 || optionSelected == 2)) {
                        throw new AppException("Invalid option");
                    }

                    switch (optionSelected) {
                        case 1:
                            System.out.println("\n=== Members in the System ===");
                            List<Member> members = memberServices.findAllMembers();
                            for (Member correntMember : members) {
                                System.out.println(correntMember);
                            }
                            break;

                        case 2:
                            System.out.println("== Register a new Member ==\n");
                            System.out.print("Write Member's name: ");
                            String name = sc.nextLine();
                            System.out.print("Write Member's address (city/state): ");
                            String address = sc.nextLine();
                            System.out.print("Write Member's phone number (some numbers): ");
                            long phoneNumber = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Write Member's email: ");
                            String emailRegister = sc.nextLine();
                            System.out.print("Write Member's date of association (dd/MM/yyyy): ");
                            LocalDate dateAssociation = LocalDate.parse(sc.nextLine(), fmt);

                            member = new Member(name, address, phoneNumber, emailRegister, dateAssociation);
                            memberServices.registerNewMember(member);

                            System.out.println("Create a new member with this information -> " + member);
                            break;
                    }
                    break;

                case 4:
                    System.out.println("\n=== Loan Options ===");
                    System.out.println("Press 1 to register a new loan");
                    System.out.println("Press 2 to register a return");
                    System.out.print("Option selected: ");
                    optionSelected = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    if (!(optionSelected == 1 || optionSelected == 2)) {
                        throw new AppException("Invalid Option");
                    }

                    switch (optionSelected) {
                        case 1:
                            System.out.println("== Register a new Loan ==");
                            System.out.println("Press 1 to list books by author");
                            System.out.println("Press 2 to list all books in stock");
                            System.out.print("Option Selected: ");
                            optionSelected = sc.nextInt();
                            sc.nextLine();
                            System.out.println();
                            if (!(optionSelected == 1 || optionSelected == 2)) {
                                throw new AppException("Invalid Option");
                            }

                            switch (optionSelected) {
                                case 1:
                                    System.out.println("\n=== What Author do you want to list the his books? ===");
                                    for (Author correntAuthor : authorServices.findAllAuthors()) {
                                        System.out.println(correntAuthor);
                                    }
                                    System.out.print("Select id of Author: ");
                                    int idAuthor = sc.nextInt();
                                    sc.nextLine();

                                    System.out.println("\n=== These are his books ===");
                                    List<Book> listBooks = bookServices.findBooksAuthor(idAuthor);
                                    if (!(listBooks.isEmpty())) {
                                        for (Book correntBook : listBooks) {
                                            System.out.println("ID: " + correntBook.getId() + ", " + correntBook);
                                        }
                                    } else {
                                        System.out.println("this is not have books");
                                        break;
                                    }
                                    break;

                                case 2:
                                    System.out.println("What Book do you want ");
                                    for (Book correntBook : bookServices.findAllBooks()) {
                                        System.out.println(correntBook);
                                    }
                                    break;
                            }

                            System.out.print("What is the ISBN of Book: ");
                            long isbnBook = sc.nextLong();
                            sc.nextLine();
                            System.out.print("What is the Date of loan?(dd/MM/yyyy): ");
                            LocalDate dateLoan = LocalDate.parse(sc.nextLine(), fmt);
                            System.out.print("What is the return date?(dd/MM/yyyy): ");
                            LocalDate dateReturn = LocalDate.parse(sc.nextLine(), fmt);


                            book = bookServices.findBookByIsbn(isbnBook);
                            Loan loan = new Loan(book, member, dateLoan, dateReturn, StatusLoan.ACTIVE, new BigDecimal(0));
                            loanServices.registerLoan(loan);
                            break;

                        case 2:
                            System.out.println("=== Return Loan ===");
                            System.out.println("Select A to loan active or L to Late");
                            System.out.print("Option Selected: ");
                            char optionSelectReturnLoan = sc.nextLine().toUpperCase().charAt(0);
                            if (!(optionSelectReturnLoan == 'A' || optionSelectReturnLoan == 'L')) {
                                throw new AppException("Invalid Option");
                            }

                            switch (optionSelectReturnLoan) {
                                case 'A':
                                    System.out.println("=== List all loans with active status ===");
                                    List<Loan> listLoanActive = loanServices.findLoanByStatus(StatusLoan.ACTIVE);
                                    for (Loan correntLoan : listLoanActive) {
                                        System.out.println(correntLoan);
                                    }
                                    System.out.print("Select the loan ID to write off: ");
                                    int loanActiveId = sc.nextInt();
                                    sc.nextLine();

                                    Loan loanActive = loanServices.findLoanById(loanActiveId);
                                    loanServices.loanReturn(loanActive.getId());
                                    System.out.println("there is not have a pending tax");
                                    System.out.println("The loan was returned successful");
                                    break;

                                case 'L':
                                    System.out.println("\n=== List all loans with late status ===");
                                    List<Loan> listLoanLate = loanServices.findLoanByStatus(StatusLoan.LATE);
                                    if(listLoanLate == null){
                                        break;
                                    }
                                    for (Loan correntLoan : listLoanLate) {
                                        System.out.println(correntLoan);
                                    }
                                    System.out.print("Select the loan ID to write off: ");
                                    int loanLateId = sc.nextInt();
                                    sc.nextLine();

                                    Loan loanLate = loanServices.findLoanById(loanLateId);
                                    loanServices.loanReturn(loanLate.getId());


                                    System.out.println("The loan was returned successful");
                                    break;
                            }
                        case 5:
                            //Emitir relatorio
                            break;
                    }
                    break;
            }
        }
        while (optionSelected != 7);

        sc.close();
    }
}