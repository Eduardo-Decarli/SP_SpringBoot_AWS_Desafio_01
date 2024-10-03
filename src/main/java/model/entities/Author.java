package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Author extends People {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private LocalDate dateOfBirth;
    private String nationality;
    private String biography;

    public Author(String name, int id, LocalDate dateOfBirth, String nationality, String biography) {
        super(name);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.biography = biography;
    }

    public Author(String name, LocalDate dateOfBirth, String nationality, String biography) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.biography = biography;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Author " + getName() +
                ", DateOfBirth: " + dateOfBirth.format(fmt) +
                "\n, Nationality='" + nationality +
                ", Biography='" + biography + '\'' +
                '}';
    }
}
