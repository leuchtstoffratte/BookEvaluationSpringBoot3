package com.rattencorp.bookevaluationspringboot3.internal;

import com.rattencorp.bookevaluationspringboot3.model.*;

import java.util.Set;

public interface PersistenceService {

    void persistBook(Book book);

    void persistEdition(BookEdition bookEdition);

    void persistAuthor(Author author);

    void persistReview(Review review);


    void persistReviewer(Reviewer reviewer);

    BookEdition findBookByIsbn(String isbn);

    Book findBookByTitle(String title);

    Set<Book> findAllBookByAuthor(Author author);

    Set<BookEdition> findAllEditionsByTitle(String title);

    Reviewer findReviewerByName(String name);

    Set<Book> getAllBooks();
    Set<BookEdition> getAllEditions();

    Set<Author> getAllAuthors();

    Set<Review> getAllReviewsForBook(Book book);

}
