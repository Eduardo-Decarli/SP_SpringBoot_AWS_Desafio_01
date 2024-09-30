package entities;

import java.time.LocalDate;
import java.util.Date;

public class Author extends Pessoa{
    private LocalDate dateOfBirth;
    private String nationality;
    private String biography;

    public Author(String name, LocalDate dateOfBirth, String nationality, String biography) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.biography = biography;
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
}
