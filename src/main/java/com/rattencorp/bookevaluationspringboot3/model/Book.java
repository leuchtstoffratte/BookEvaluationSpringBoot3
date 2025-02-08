package com.rattencorp.bookevaluationspringboot3.model;

import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public record Book(String title, Author author) implements Serializable, Comparable<Book> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Book(String title1, Author author1))
            return Objects.equals(title, title1) && Objects.equals(author, author1);
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public int compareTo(@NonNull Book book) {
        return Comparator
                .comparing(Book::title)
                .thenComparing(Book::author)
                .compare(this, book);
    }

}
