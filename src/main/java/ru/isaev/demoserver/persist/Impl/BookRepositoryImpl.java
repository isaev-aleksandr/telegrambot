package ru.isaev.demoserver.persist.Impl;

import org.springframework.stereotype.Repository;
import ru.isaev.demoserver.controllers.requestException.ThereIsNoSuchBookException;
import ru.isaev.demoserver.controllers.requestException.ThereIsNotFoundBookException;
import ru.isaev.demoserver.model.Book;
import ru.isaev.demoserver.persist.BookRepository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class BookRepositoryImpl implements BookRepository {

    private EntityManager entityManager;

    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> showAllInDB (){
        Query query = entityManager.createNamedQuery("Book.findBooks");
        return query.getResultList();
    }

    @Override
    public void saveBookInDB (Book book){
        entityManager.persist(book);
    }

    @Override
    public void deleteBookByIDInDB (short id){
//        entityManager.createNamedQuery("Book.deleteBook")
//        .setParameter("Id", id).executeUpdate();
        try {
            entityManager.remove(
                    entityManager.createNamedQuery("Book.findBookById"
                    ).setParameter("Id", id).getSingleResult()
            );
        } catch (NoResultException ex){
            throw new ThereIsNoSuchBookException();
        }
    }

    @Override
    public Book findByIDInDB(short id){
        try {
            Query query = entityManager.createNamedQuery("Book.findBookById")
                    .setParameter("Id", id);
            return (Book) query.getSingleResult();
        } catch (NoResultException ex){
            throw new ThereIsNoSuchBookException();
        }
    }

    @Override
    public void updateBookInDB(Book book){
        entityManager.merge(book);
    }

    @Override
    public List<Book> findAllByBookNameOrGenreOrAuthorInDB(String request){
        Query query = entityManager.createNamedQuery("Book.findBookByTitleOrGenreOrAuthor").
                setParameter("title", request).
                setParameter("genre", request).
                setParameter("author", request);
        if (query.getResultList().isEmpty()){
            throw new ThereIsNotFoundBookException();
        }
        return query.getResultList();
    }
}
