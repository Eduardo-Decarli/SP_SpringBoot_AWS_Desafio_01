package application;

import exceptions.ServicesException;
import model.entities.*;
import model.services.AuthorServices;
import model.services.BookServices;
import model.services.MemberServices;
import model.services.dao.AuthorDAO;
import model.services.dao.BookDAO;
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

                            /* Arrumar o problema com ID do author novo retornando 0 */
                            author = new Author(authorName,authorBirthOfDate, authorNationality, authorBiography);
                            authorServices.registerNewAuthor(new Author(authorName,authorBirthOfDate, authorNationality, authorBiography));
                            book = new Book(titleBook, author, datePublication, isbn, genre, quantity);
                            bookServices.registerBook(book);

                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
            }
        }
        while (optionSelected != 7);
        sc.close();
    }
}