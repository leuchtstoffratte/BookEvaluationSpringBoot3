package com.rattencorp.bookevaluationspringboot3.model;

import jakarta.persistence.Entity;
import java.io.Serial;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public record BookEdition(
        @ManyToOne
        Book book,
        @Id
        String numberIsbn, //isbn-number needs a prefix to avoid confusing jakarta into looking for boolean bn
        LocalDate publishedDate,
        int edition,
        BookForm form

) implements Serializable, Comparable<BookEdition> {

    @Serial
    private static final long serialVersionUID = 1L;


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj instanceof BookEdition(
                Book book1, String numberIsbn1, LocalDate date, int edition1, BookForm form1
                )
        ) {
            return Objects.equals(this.book, book1)
                    && Objects.equals(this.numberIsbn, numberIsbn1)
                    && Objects.equals(this.publishedDate, date)
                    && Objects.equals(this.edition, edition1)
                    && Objects.equals(this.form, form1);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book,
                numberIsbn,
                publishedDate,
                edition,
                form);
    }

    @Override
    public String toString() {
        return "%d,%s edition of '%s' by %s".formatted(edition, form, book.title(), book.authors());
    }

    @Override
    public int compareTo(BookEdition o) {
        return Comparator
                .comparing(BookEdition::book)
                .thenComparing(BookEdition::edition)
                .thenComparing(BookEdition::form)
                .compare(this, o);
    }


    public enum BookForm{
        HARDCOVER,
        PAPERBACK,
        EBOOK,
        GRAPHICNOVEL;

        @Override
        public String toString() {
            return name().toLowerCase();
        }

    }

}
