package model.repositories;

import model.entities.Author;

import java.util.List;

public interface AuthorRepository {
    public void registerNewAuthor(Author author);
    public Author findAuthorById(int id);
    public List<Author> findAllAuthors();
}
