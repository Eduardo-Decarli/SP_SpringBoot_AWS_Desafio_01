package application;

import model.entities.*;
import model.entities.enumeration.StatusLoan;
import model.services.AuthorServices;
import model.services.BookServices;
import model.services.LoanServices;
import model.services.MemberServices;
import model.services.dao.AuthorDAO;
import model.services.dao.BookDAO;
import model.services.dao.LoanDAODAO;
import model.services.dao.MemberDAO;

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
        LoanServices loanServices = new LoanServices(new LoanDAODAO());

        Member member = null;
        Author author = null;
        Book book = null;

        //precisa testar
        //bookServices.findBookByIsbn();

        System.out.println("welcome to library manager\n");
        int optionSelected;

        System.out.println("press 1 to login or 2 to register");
        int optionLogin = sc.nextInt();
        sc.nextLine();

        switch (optionLogin){
            case 1:
                System.out.println("== Login ==");
                System.out.print("Enter with a email: ");
                String emailLogin = sc.nextLine();
                member = memberServices.findMemberByEmail(emailLogin);
                System.out.println("Login Complete");
                System.out.println("hello " + member.getName());
                break;

            case 2:
                System.out.println("== Register ==");
                System.out.print("Write your name: ");
                String name = sc.nextLine();
                System.out.print("Write your address (city/state): ");
                String address = sc.nextLine();
                System.out.print("Write your phone number (some numbers): ");
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
            System.out.println("\nSelect an option");
            System.out.println("Press 1 to register a new book");
            System.out.println("Press 2 to register a new author");
            System.out.println("Press 3 to register a new member");
            System.out.println("Press 4 to register a new loan");
            System.out.println("Press 5 to register a new return");
            System.out.println("Press 6 to generate a report");
            System.out.println("Press 7 to exit");
            System.out.print("Number selected: ");
            optionSelected = sc.nextInt();
            sc.nextLine();

            switch (optionSelected) {

                case 1:
                    System.out.println("== Register a new Book ==\n");
                    System.out.println("Write the book information");

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

                    System.out.println("let's write the author information");
                    System.out.println("Press 1 to associate an already registered author or press 2 to create a new author");
                    int controllerAuthor = sc.nextInt();
                    sc.nextLine();

                    switch (controllerAuthor) {

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

                            author = new Author(authorName,authorBirthOfDate, authorNationality, authorBiography);
                            authorServices.registerNewAuthor(author);

                            book = new Book(titleBook, author, datePublication, isbn, genre, quantity);
                            bookServices.registerBook(book);

                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;

                case 2:

                    System.out.println("== Register a new Author ==\n");
                    System.out.print("Write the author's name: ");
                    String authorName = sc.nextLine();
                    System.out.print("Write the birth of date: ");
                    LocalDate authorBirthOfDate = LocalDate.parse(sc.nextLine(), fmt);
                    System.out.print("Write the author's nationality: ");
                    String authorNationality = sc.nextLine();
                    System.out.print("Write the Author's biography: ");
                    String authorBiography = sc.nextLine();

                    author = new Author(authorName,authorBirthOfDate, authorNationality, authorBiography);
                    authorServices.registerNewAuthor(author);
                    break;

                case 3:
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

                case 4:
                    System.out.println("== Register a new Loan ==\n");
                    System.out.println("do you want to list books for author (y/n)?: ");
                    char controllerAuthorBook = sc.nextLine().toUpperCase().charAt(0);

                    switch (controllerAuthorBook){
                        case 'Y':
                            System.out.println("What Author do you want to list the his books?");
                            for(Author correntAuthor : authorServices.findAllAuthors()){
                                System.out.println("ID: " + correntAuthor.getId() + ", " + correntAuthor);
                            }
                            int idAuthor = sc.nextInt();
                            sc.nextLine();
                            List<Book> listBooks = bookServices.findBooksAuthor(idAuthor);
                            if(listBooks != null) {
                                for (Book correntBook : listBooks) {
                                    System.out.println("ID: " + correntBook.getId() + ", " + correntBook);
                                }
                            }else{
                                System.out.println("this is not have books");
                                break;
                            }
                            break;

                        case 'N':
                            System.out.println("What Book do you want ");
                            for(Book correntBook : bookServices.findAllBooks()){
                                System.out.println(correntBook);
                            }
                            break;
                    }
                    System.out.print("What is the ISBN of Book: ");
                    int isbnBook = sc.nextInt();
                    sc.nextLine();
                    System.out.print("What is the Date of loan?(dd/MM/yyyy): ");
                    LocalDate dateLoan = LocalDate.parse(sc.nextLine(), fmt);
                    System.out.print("What is the return date?(dd/MM/yyyy): ");
                    LocalDate dateReturn = LocalDate.parse(sc.nextLine(), fmt);
                    System.out.print("Write the tax value: ");
                    double taxFine = sc.nextDouble();

                    book = bookServices.findBookByIsbn(isbnBook);
                    Loan loan = new Loan(book, member, dateLoan, dateReturn, StatusLoan.ACTIVE, taxFine);
                    loanServices.registerLoan(loan);
                    break;

                case 5:
                    //retorno de um empréstimo

                    //lista os emprestimos

                    //Seleciona o ID do loan

                    //se tiver, mostrar o status e o valor a se pagar

                    //aumentar a quantidade de um livro no banco de dados

                    //permitir o usuario a trocar o status e fazer o update no banco de dados

                    break;
            }
        }
        while (optionSelected != 7);
        sc.close();
    }
}