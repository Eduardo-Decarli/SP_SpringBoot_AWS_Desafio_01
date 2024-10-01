package model.repositories.dao;

import model.entities.Author;

import java.util.List;

public interface AuthorServicesDAO {
    public Author authorFindById();
    public Author authorFindByName();
    public List<Author> authorFindAll();
    public void insertAuthor(Author author);
}
