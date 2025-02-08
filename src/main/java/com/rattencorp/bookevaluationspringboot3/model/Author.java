package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Author implements Serializable, Comparable<Author> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int authorId;
    private String name;
    private String surname;

    private final Set<BookEdition> books;

    public Author(int authorId) {
        this.authorId = authorId;
        this.books = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Set<BookEdition> getBooks() {
        return books;
    }

    public void addBook(BookEdition book) {
        this.books.add(book);
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Author author)) return false;
        return authorId == author.authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }

    /**
     * order alphabetically but disambiguate by id
     */
    @Override
    public int compareTo(Author o) {
        return Comparator
                .comparing(Author::getSurname)
                .thenComparing(Author::getName)
                .thenComparing(Author::getAuthorId)
                .compare(this, o);
    }
}


