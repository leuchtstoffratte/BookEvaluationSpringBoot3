package com.rattencorp.bookevaluationspringboot3.api;

import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;

import java.util.List;

public interface LibraryService {



    List<BookEdition> getBooksByAuthor(Author author);

    BookEdition getBookById(int id);

    void addBook(BookEdition book);

    BookEdition getBookByTitle(String title);


}
