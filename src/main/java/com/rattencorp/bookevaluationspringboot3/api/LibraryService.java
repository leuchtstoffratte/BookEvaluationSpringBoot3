package com.rattencorp.bookevaluationspringboot3.api;

import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;

import java.util.Set;

public interface LibraryService {

    Set<BookEdition> getBooksByAuthor(Author author);

    BookEdition getBookById(int id);

    void addBook(BookEdition book);

    Book getBookByTitle(String title);

    Set<BookEdition> getEditionsByTitle(String title);
}
