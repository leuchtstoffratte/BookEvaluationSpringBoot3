package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Publisher implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String publisherName;
    private final Set<BookEdition> books;
    private final Set<Author> authors;


    public Publisher(String name) {
        this.publisherName = name;
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

    public String getPublisherName() {
        return publisherName;
    }
}
