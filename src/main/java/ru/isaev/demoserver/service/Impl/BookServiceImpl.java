package ru.isaev.demoserver.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isaev.demoserver.model.Book;
import ru.isaev.demoserver.persist.BookRepository;
import ru.isaev.demoserver.service.BookService;


import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepositoryImpl;

    @Autowired
    @Override
    public void setBookRepository (@Qualifier(value = "bookRepositoryImpl") BookRepository bookRepositoryImpl){
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    @Override
    @Transactional
    public List<Book> showAllBookInRepo() {
        return bookRepositoryImpl.showAllInDB();
    }

    @Override
    @Transactional
    public void saveBookInRepo (Book book){
        bookRepositoryImpl.saveBookInDB(book);
    }

    @Override
    @Transactional
    public void deleteBookByIDInRepo(short id){
        bookRepositoryImpl.deleteBookByIDInDB(id);
    }

    @Override
    @Transactional
    public Book findByIDInRepo(short id) {
            return bookRepositoryImpl.findByIDInDB(id);
    }

    @Override
    @Transactional
    public Book updateBookInRepo(Book book){
        bookRepositoryImpl.updateBookInDB(book);
        return findByIDInRepo(book.getId());
    }

    @Override
    @Transactional
    public List<Book> findAllByBookNameOrGenreOrAuthorInRepo(String request){
        return bookRepositoryImpl.findAllByBookNameOrGenreOrAuthorInDB(request);
    }

}
