package com.rattencorp.bookevaluationspringboot3.api;

import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.Book;

import java.util.List;

public interface LibraryService {



    List<Book> getBooksByAuthor(Author author);

    Book getBookById(int id);

    void addBook(Book book);

    Book getBookByTitle(String title);


}
