package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Set<BookEdition> books;
    private final Set<Author> authors;


    public Publisher() {
        this.books = new HashSet<>();
        this.authors = new HashSet<>();
    }

    public Set<BookEdition> getBooks() {
        return books;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void addBook(BookEdition book) {
        books.add(book);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

}
