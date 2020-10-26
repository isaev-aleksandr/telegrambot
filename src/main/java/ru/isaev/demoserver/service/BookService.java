package ru.isaev.demoserver.service;

import ru.isaev.demoserver.model.Book;
import ru.isaev.demoserver.persist.BookRepository;

import java.util.List;

public interface BookService {

    public void setBookRepository(BookRepository bookRepositoryImpl);

    public List<Book> showAllBookInRepo();

    public void saveBookInRepo (Book book);

    public void deleteBookByIDInRepo(short id);

    public Book findByIDInRepo(short id);

    public Book updateBookInRepo(Book book);

    public List<Book> findAllByBookNameOrGenreOrAuthorInRepo(String request);
}
