package com.rattencorp.bookevaluationspringboot3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

@Entity
public record Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int bookId,
        String title, 
        @ManyToMany(cascade = CascadeType.ALL)
        Set<Author> authors) implements Serializable, Comparable<Book> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * <b>ignore generated Id for comparisons</b>
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Book(int id1, String title1, Set<Author> authors1))
            return Objects.equals(title, title1) 
                    && authors.size()== authors1.size() 
                    && authors.containsAll(authors1);
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors);
    }

    @Override
    public int compareTo(@NonNull Book book) {
        return Comparator
                .comparing(Book::title)
                .thenComparing(b -> b.authors().size())
                .compare(this, book);
    }

}
