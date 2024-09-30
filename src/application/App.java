package application;

import entities.Author;
import entities.Book;
import entities.Member;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("welcome to library manager\n");
        Member member = new Member("Eduardo", "teste", 99.0, "teste@teste", LocalDate.of(2024, 4, 20));

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
/*
                    System.out.println("== Register a new Book ==\n");
                    System.out.println("Write de book information");

                    System.out.println("Write the book title");
                    String titleBook = sc.nextLine();
                    System.out.println("Write the author");
                    String authorName = sc.nextLine();
                    System.out.println("Write the Date of Publication");
                    String datePublication = sc.nextLine();
                    System.out.println("Write the ISBN");
                    Double isbn = sc.nextInt();
                    System.out.println("Write the book's genre");
                    String genre = sc.nextLine();
                    System.out.println("Write quantity books arrive");
                    String quantity = sc.nextLine();

                    System.out.println("do you want to list the author or create a new author?");
                    System.out.println("Press 1 to list and 2 to create a new author");
                    int controllerAuthor = sc.nextInt();

                    System.out.println("Write de author information");

                    if(controllerAuthor == 1){
                        System.out.println("== Register a new author ==\n");
                    }else{
                        /* Listar os authores
                    }
                    */

                    LocalDate ld = LocalDate.of(2024, 4, 20);
                    Book book = new Book("Mago", new Author("Raymond", ld, "Portuguese", "Teste"), ld, "9788580415513", "Ficção", 85);
                    member.registerBook(book);
                    System.out.println(book);
                    break;
            }


        } while (optionSelected != 7);


    }
}