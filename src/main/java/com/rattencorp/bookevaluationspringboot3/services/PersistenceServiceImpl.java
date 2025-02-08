package com.rattencorp.bookevaluationspringboot3.services;

import com.rattencorp.bookevaluationspringboot3.api.PersistenceService;
import com.rattencorp.bookevaluationspringboot3.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stop gap on the way to spring data
 *
 */
@Service
@ApplicationScope
public class PersistenceServiceImpl implements PersistenceService {

    private final Set<Author> authors = new HashSet<Author>();
    private final Map<Book, Set<BookEdition>> editions = new HashMap<>();
    private final Map<BookEdition,Set<Review>> reviews = new HashMap<>();
    private final Set<Publisher> publishers = new HashSet<>();
    private final Set<Reviewer> reviewers = new HashSet<>();


    private static final Log LOG = LogFactory.getLog(PersistenceServiceImpl.class);

    @Override
    public void persistBook(Book book) {
        LOG.debug("persisting book '%s'".formatted(book));
        editions.putIfAbsent(book, new HashSet<>());
        persistAuthor(book.author());
    }

    @Override
    public void persistEdition(BookEdition bookEdition) {
        LOG.debug("persisting edition '%s'".formatted(bookEdition));
        persistBook(bookEdition.book());
        editions.get(bookEdition.book()).add(bookEdition);
        reviews.putIfAbsent(bookEdition, new HashSet<>());
        publishers.add(bookEdition.publisher());
    }

    @Override
    public void persistAuthor(Author author) {
        authors.add(author);
    }

    @Override
    public void persistReview(Review review) {
        var r = reviews.get(review.getBook());
        if (r != null ) {
            r.add(review);
        } else {
            LOG.error("Tried to persist Review for unknown book '%s'".formatted(review.getBook()));
        }
    }

    @Override
    public void persistPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    @Override
    public void persistReviewer(Reviewer reviewer) {
        reviewers.add(reviewer);
    }

    @Override
    public BookEdition findBookByIsbn(String isbn) {
        return editions.values()
                .stream()
                .flatMap(Set::stream)
                .filter(e -> e.isbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book findBookByTitle(String title) {
        return editions.keySet()
                .stream()
                .filter(e -> e.title().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Book> findAllBookByAuthor(Author author) {
        return editions.keySet().stream()
                .filter(e -> e.author().equals(author))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BookEdition> findAllEditionsByTitle(String title) {
        return editions.getOrDefault(findBookByTitle(title), new HashSet<>());
    }

    @Override
    public Reviewer findReviewerByName(String name) {
        return reviewers.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Book> getAllBooks() {
        return Collections.unmodifiableSet(editions.keySet());
    }

    @Override
    public Set<BookEdition> getAllEditions() {
        return editions.values()
                .stream()
                .flatMap(Set::stream)
                .collect(Collectors.toUnmodifiableSet());
    }
}
