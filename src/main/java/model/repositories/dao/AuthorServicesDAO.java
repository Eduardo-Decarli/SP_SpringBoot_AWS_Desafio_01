package model.repositories.dao;

import model.entities.Author;

import java.util.List;

public interface AuthorServicesDAO {
    public Author authorFindById(int id);
    public List<Author> authorFindByName(String authorName);
    public List<Author> authorFindAll();
    public void insertAuthor(Author author);
}
