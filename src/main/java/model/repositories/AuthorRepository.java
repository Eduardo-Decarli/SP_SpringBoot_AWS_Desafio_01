package model.repositories;

import model.entities.Author;

public interface AuthorRepository {
    public void registerNewAuthor(Author author);
    public Author findAuthorById(int id);
}
