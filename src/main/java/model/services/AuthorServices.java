package model.services;

import exceptions.ServicesException;
import model.entities.Author;
import model.repositories.AuthorRepository;
import model.services.dao.AuthorDAO;

import java.util.List;

public class AuthorServices implements AuthorRepository {

    private AuthorDAO authorDao;

    public AuthorServices(AuthorDAO authorDao){
        this.authorDao = authorDao;
    }

    @Override
    public void registerNewAuthor(Author author) {
        List<Author> listAuthors = authorDao.selectAuthorByName(author.getName());
        for(Author correntAuthor : listAuthors){
            if(correntAuthor.getName().equals(author.getName())){
                throw new ServicesException("The author is already registered");
            }
        }
        authorDao.insertAuthor(author);
    }

    @Override
    public Author findAuthorById(int id) {
        if(id <= 0){
            throw new ServicesException("The selected number must be greater than 0");
        }

        Author author = authorDao.selectAuthorById(id);

        if(author == null){
            throw new ServicesException("Author not found!");
        }
        return author;
    }

    @Override
    public List<Author> findAllAuthors() {
        if(authorDao.selectAllAuthor() == null){
            throw new ServicesException("There is not nothing author registered");
        }
        return authorDao.selectAllAuthor();
    }
}
