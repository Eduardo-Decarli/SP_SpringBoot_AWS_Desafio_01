package application;

import dao.AuthorDAO;
import model.entities.*;
import model.entities.enumeration.StatesLoan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("welcome to library manager\n");
        LocalDate ld = LocalDate.of(2024, 4, 20);

        Member member = new Member("Eduardo", "JoséCherem", 48996342153l, "teste@teste", ld, null);
        Author author = new Author("Raymond", ld, "Portuguese", "Teste");

        Book book = new Book("Mago", author, ld, 9788580415513l, "Ficção", 85);
        author.registerNewAuthor();
        member.registerBook(book);


        /*
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
                    System.out.println("Write de book information");

                    System.out.println("Write the book title");
                    String titleBook = sc.nextLine();
                    System.out.println("Write the author");
                    String authorName = sc.nextLine();
                    System.out.println("Write the Date of Publication");
                    String datePublication = sc.nextLine();
                    System.out.println("Write the ISBN");
                    long isbn = sc.nextLong();
                    System.out.println("Write the book's genre");
                    String genre = sc.nextLine();
                    System.out.println("Write quantity books arrive");
                    String quantity = sc.nextLine();



                    System.out.println("do you want to list the author or create a new author?");
                    System.out.println("Press 1 to list and 2 to create a new author");
                    int controllerAuthor = sc.nextInt();

                    System.out.println("Write de author information");
                    Author author = null;
                    if(controllerAuthor == 1){
                        System.out.println("== Register a new author ==\n");
                        LocalDate ld = LocalDate.of(2024, 4, 20);
                        author = new Author("Raymond", ld, "Portuguese", "Teste");

                    }else if(controllerAuthor == 2){
                        /*Listar os authores
                    }


                    LocalDate ld = LocalDate.of(2024, 4, 20);
                    Book book = new Book("Mago", author, ld, 9788580415513l, "Ficção", 85);
                    member.registerBook(book);
                    System.out.println(book);
                    break;
            }


        } while (optionSelected != 7);
*/

    }
}