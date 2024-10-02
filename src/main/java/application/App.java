package application;

import model.entities.*;
import model.services.AuthorServices;
import model.services.dao.AuthorDAO;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("welcome to library manager\n");
        LocalDate ld = LocalDate.of(2024, 4, 20);

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


            switch (optionSelected) {

                case 1:

                    System.out.println("== Register a new Book ==\n");
                    System.out.println("Write de book informations");

                    System.out.println("Write the book title");
                    String titleBook = sc.nextLine();
                    System.out.println("Write the Date of Publication");
                    String datePublication = sc.nextLine();
                    System.out.println("Write the ISBN");
                    long isbn = sc.nextLong();
                    System.out.println("Write the book's genre");
                    String genre = sc.nextLine();
                    System.out.println("Write quantity books arrive");
                    String quantity = sc.nextLine();

                    System.out.println("let's write the author information");
                    System.out.println("Press 1 to list and 2 to create a new author");
                    int controllerAuthor = sc.nextInt();

                    switch(controllerAuthor){

                        case 1:
                             /*Listar os autores do banco de dados */
                            break;

                        case 2:
                            /* Instanciar um novo autor */

                            System.out.println("== Register a new author ==\n");
                            Author author = new Author("Raymond", ld, "Portuguese", "Teste");

                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
            }
        } while (optionSelected != 7);
    }
}