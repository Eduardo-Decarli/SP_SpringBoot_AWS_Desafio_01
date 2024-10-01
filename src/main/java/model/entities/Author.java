package model.entities;

import dao.AuthorDAO;
import model.services.AuthorServices;

import java.time.LocalDate;

public class Author extends People implements AuthorServices {
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

    @Override
    public void registerNewAuthor() {
        AuthorDAO authorDao = new AuthorDAO();
        authorDao.insertAuthor(this);
    }
}
