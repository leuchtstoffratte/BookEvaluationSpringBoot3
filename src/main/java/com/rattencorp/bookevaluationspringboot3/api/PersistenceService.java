package com.rattencorp.bookevaluationspringboot3.api;

import com.rattencorp.bookevaluationspringboot3.model.*;

import java.util.Set;

public interface PersistenceService {

    void persistBook(Book book);

    void persistEdition(BookEdition bookEdition);

    void persistAuthor(Author author);

    void persistReview(Review review);

    void persistPublisher(Publisher publisher);

    void persistReviewer(Reviewer reviewer);

    BookEdition findBookByIsbn(String isbn);

    Book findBookByTitle(String title);

    Set<Book> findAllBookByAuthor(Author author);

    Set<BookEdition> findAllEditionsByTitle(String title);

    Reviewer findReviewerByName(String name);

    Set<Book> getAllBooks();
    Set<BookEdition> getAllEditions();

}
