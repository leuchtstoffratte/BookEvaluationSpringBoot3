package com.rattencorp.bookevaluationspringboot3.api;

import com.rattencorp.bookevaluationspringboot3.model.Author;

import java.util.List;

public interface AuthorsService {

    List<Author> getAuthors();

    void addAuthor(Author author);

    List<Author> getAuthorsByName(String surname);

}
