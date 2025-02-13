package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public record BookEdition(
        Book book,
        String isbn,
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
                Book book1, String isbn1, LocalDate date, int edition1, BookForm form1
                )
        ) {
            return Objects.equals(this.book, book1)
                    && Objects.equals(this.isbn, isbn1)
                    && Objects.equals(this.publishedDate, date)
                    && Objects.equals(this.edition, edition1)
                    && Objects.equals(this.form, form1);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book,
                isbn,
                publishedDate,
                edition,
                form);
    }

    @Override
    public String toString() {
        return "%d,%s edition of '%s' by %s".formatted(edition, form, book.title(), book.author());
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
