package com.rattencorp.bookevaluationspringboot3.services;

import com.rattencorp.bookevaluationspringboot3.internal.PersistenceService;
import com.rattencorp.bookevaluationspringboot3.model.*;
import com.rattencorp.bookevaluationspringboot3.model.persistence.AuthorRepository;
import com.rattencorp.bookevaluationspringboot3.model.persistence.BookEditionRepository;
import com.rattencorp.bookevaluationspringboot3.model.persistence.BookRepository;
import com.rattencorp.bookevaluationspringboot3.model.persistence.ReviewRepository;
import com.rattencorp.bookevaluationspringboot3.model.persistence.ReviewerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * stop gap on the way to spring data
 *
 */
@Service
public class PersistenceServiceImpl implements PersistenceService {

    private static final Log LOG = LogFactory.getLog(PersistenceServiceImpl.class);

    private final Set<Author> authors = new HashSet<>();
    private final Map<Book, Set<BookEdition>> editions = new HashMap<>();
    private final Map<BookEdition,Set<Review>> reviews = new HashMap<>();
    private final Set<Reviewer> reviewers = new HashSet<>();


    @PersistenceContext
    private final EntityManager entityManager;
    
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final BookEditionRepository bookEditionRepo;
    private final ReviewRepository reviewRepo;
    private final ReviewerRepository reviewerRepo;

    @Autowired
    public PersistenceServiceImpl(EntityManager entityManager, 
            AuthorRepository authorRepo, 
            BookRepository bookRepo, 
            BookEditionRepository bookEditionRepo, 
            ReviewRepository reviewRepo, 
            ReviewerRepository reviewerRepo) {
        this.entityManager = entityManager;
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.bookEditionRepo = bookEditionRepo;
        this.reviewRepo = reviewRepo;
        this.reviewerRepo = reviewerRepo;
    }
    
    
    

    @Override
    public void persistBook(Book book) {
        LOG.debug("persisting book '%s'".formatted(book));
        editions.putIfAbsent(book, new HashSet<>());
        book.authors().forEach(this::persistAuthor);
    }

    @Override
    public void persistEdition(BookEdition bookEdition) {
        LOG.debug("persisting edition '%s'".formatted(bookEdition));
        persistBook(bookEdition.book());
        editions.get(bookEdition.book()).add(bookEdition);
        reviews.putIfAbsent(bookEdition, new HashSet<>());
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
    public void persistReviewer(Reviewer reviewer) {
        reviewers.add(reviewer);
    }

    @Override
    public BookEdition findBookByIsbn(String isbn) {
        return editions.values()
                .stream()
                .flatMap(Set::stream)
                .filter(e -> e.numberIsbn().equalsIgnoreCase(isbn))
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
                .filter(e -> e.authors().contains(author))
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

    @Override
    public Set<Author> getAllAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    @Override
    public Set<Review> getAllReviewsForBook(Book book) {
        return editions.getOrDefault(book, new HashSet<>())
                .stream()
                .flatMap(s -> reviews.getOrDefault(s, new HashSet<>()).stream())
                .collect(Collectors.toUnmodifiableSet());
    }
}
