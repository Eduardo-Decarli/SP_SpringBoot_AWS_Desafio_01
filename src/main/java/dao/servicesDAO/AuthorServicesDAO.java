package dao.servicesDAO;

import model.entities.Author;

public interface AuthorServicesDAO {
    public Author authorFindById();
    public Author authorFindByName();
    public void insertAuthor(Author author);
}
