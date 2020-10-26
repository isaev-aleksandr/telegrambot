package ru.isaev.demoserver.persist;

import ru.isaev.demoserver.model.Book;
import javax.persistence.EntityManager;
import java.util.List;

public interface BookRepository {

    public void setEntityManager(EntityManager entityManager);

    public List<Book> showAllInDB ();

    public void saveBookInDB (Book book);

    public void deleteBookByIDInDB (short id);

    public Book findByIDInDB(short id);

    public void updateBookInDB(Book book);

    public List<Book> findAllByBookNameOrGenreOrAuthorInDB(String request);

}
