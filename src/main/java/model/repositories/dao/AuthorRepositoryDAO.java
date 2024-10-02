package model.repositories.dao;

import model.entities.Author;

import java.util.List;

public interface AuthorRepositoryDAO {
    public Author selectAuthorById(int id);
    public List<Author> selectAuthorByName(String authorName);
    public List<Author> selectAllAuthor();
    public void insertAuthor(Author author);
}
