package ru.isaev.demoserver.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findBooks",
        query = "FROM Book "),
        @NamedQuery(name = "Book.deleteBook",
        query = "DELETE Book where id = :Id "),
        @NamedQuery(name = "Book.findBookById",
        query = "FROM Book WHERE id = :Id "),
        @NamedQuery(name = "Book.findBookByTitleOrGenreOrAuthor",
        query = "FROM Book WHERE bookName = :title " +
        "OR genre = :genre " +
        "OR author = :author ")
})
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private short id;

    @NotNull(message = "Название книги не может быть null")
    @Size(min = 3, message="Длина названия книги должна быть больше 3 символов")
    @Column(name = "book_name", nullable = false)
    private  String bookName;

    @NotNull(message = "Жанр книги не может быть null")
    @Size(min = 3, message="Длина жанра должна быть больше 3 символов")
    @Column(name = "genre", nullable = false)
    private  String genre;

    @NotNull(message = "Автор книги не может быть null")
    @Size(min = 5, message="Длина ФИО автора должна быть больше 5 символов")
    @Column(name = "author", nullable = false)
    private  String author;

    @NotNull(message = "публикация не можеть быть null")
    @Min(value = 1000, message = "публикация должна быть не ранее 1000 года н.э.")
    @Max(value = 2020, message = "публикация не может быть позднее текущего года")
    @Column(name = "publication", nullable = false)
    private  short publication;

    @NotNull(message = "колличество страниц не можеть быть null")
    @Min(value = 100, message = "колличество страниц не можеть быть менее 100")
    @Max(value = 3000, message = "колличество страниц не можеть быть более 3000")
    @Column(name = "count_pages", nullable = false)
    private  short countPages;

    @NotNull(message = "цена не можеть быть null")
    @Min(value = 1, message = "колличество страниц не можеть быть менее 1")
    @Column(name = "price", nullable = false)
    private  short price;
}
