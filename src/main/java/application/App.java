package application;

import exceptions.ServicesException;
import model.entities.*;
import model.services.AuthorServices;
import model.services.BookServices;
import model.services.dao.AuthorDAO;
import model.services.dao.BookDAO;

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
        BookDAO bookDao = new BookDAO();

        //precisa testar
        //bookServices.findBookByIsbn();

        LocalDate ld = LocalDate.of(2024, 10, 20);


        System.out.println("welcome to library manager\n");
        int optionSelected;

        do {
            System.out.println("Select an option");
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
                    System.out.print("\nWrite the Date of Publication (dd/MM/yyyy): ");
                    LocalDate datePublication = LocalDate.parse(sc.nextLine(), fmt);
                    System.out.print("Write the ISBN: ");
                    long isbn = sc.nextLong();
                    sc.nextLine();
                    System.out.print("\nWrite the book's genre: ");
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
                            System.out.println("What is the author's id?: ");
                            int authorId = sc.nextInt();

                            Author author = authorServices.findAuthorById(authorId);

                            Book book = new Book(titleBook, author, ld, isbn, genre, quantity);
                            bookServices.registerBook(book);
                            break;

                        case 2:
                            /* Instanciar um novo autor */

                            System.out.println("== Register a new author ==\n");
                            
                            //Author author = new Author("Raymond", ld, "Portuguese", "Teste");

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