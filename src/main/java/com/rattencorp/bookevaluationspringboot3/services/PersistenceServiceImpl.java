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
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * stop gap on the way to spring data
 *
 */
@Transactional
@Service
public class PersistenceServiceImpl implements PersistenceService {

    private static final Log LOG = LogFactory.getLog(PersistenceServiceImpl.class);

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
        bookRepo.save(book);
    }

    @Override
    public void persistEdition(BookEdition bookEdition) {
        LOG.debug("persisting edition '%s'".formatted(bookEdition));
        bookEditionRepo.save(bookEdition); //TODO is this sufficient?
    }

    @Override
    public void persistAuthor(Author author) {
        authorRepo.save(author);
    }

    @Override
    public void persistReview(Review review) {
        reviewRepo.save(review); //TODO: check whether I'll need to save potentially missing reviewers as well
    }


    @Override
    public void persistReviewer(Reviewer reviewer) {
        reviewerRepo.save(reviewer);
    }

    @Override
    public BookEdition findBookByIsbn(String isbn) {
        return bookEditionRepo.findByNumberIsbn(isbn).getFirst();
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepo.findByTitle(title).getFirst();
    }

    @Override
    public Set<Book> findAllBookByAuthor(Author author) {
        if (author.getBooks() == null || author.getBooks().isEmpty()) {
            return authorRepo.findAll(Example.of(author)).stream().flatMap(a -> a.getBooks().stream()).collect(Collectors.toSet());
        }else
            return new HashSet<>(author.getBooks());
    }

    @Override
    public Set<BookEdition> findAllEditionsByTitle(String title) {
        return new HashSet<>(bookEditionRepo.findByTitle(title));
    }

    @Override
    public Set<Reviewer> findReviewerByName(String name) {
        return reviewerRepo.findByName(name);
    }

    @Override
    public Set<Book> getAllBooks() {
        return new HashSet<>(bookRepo.findAll());
    }

    @Override
    public Set<BookEdition> getAllEditions() {
        return new HashSet<>(bookEditionRepo.findAll());
    }

    @Override
    public Set<Author> getAllAuthors() {
        return new HashSet<>(authorRepo.findAll());
    }

    @Override
    public Set<Review> getAllReviewsForBook(Book book) {
        return reviewRepo.findByBook(book);
    }
}
