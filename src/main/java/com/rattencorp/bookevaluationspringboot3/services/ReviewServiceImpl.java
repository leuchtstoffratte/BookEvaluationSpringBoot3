package com.rattencorp.bookevaluationspringboot3.services;

import com.rattencorp.bookevaluationspringboot3.api.ReviewService;
import com.rattencorp.bookevaluationspringboot3.internal.PersistenceService;
import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.Review;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Log LOG = LogFactory.getLog(ReviewServiceImpl.class);


    private final PersistenceService persistenceService;


    @Autowired
    public ReviewServiceImpl(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }


    @Override
    public void submitReview(Review review) {
        persistenceService.persistReview(review);
    }

    @Override
    public void updateReview(Review review) {
        Optional<Review> old = persistenceService
                .getAllReviewsForBook(review.getBook().book())
                .stream()
                .filter(r -> r.getReviewer().equals(review.getReviewer()))
                .filter(r -> r.getBook().equals(review.getBook()))
                .findFirst();

        if (old.isPresent()) {
            old.get().updateReview(review);
        }else {
            LOG.error("Review by '%s' for book '%s' was submitted for update but did not exist. Submitting instead."
                    .formatted(review.getReviewer(), review.getBook()));

            submitReview(review);
        }

    }

    @Override
    public List<Review> getReviewsForBook(Book book) {
        return new ArrayList<>(persistenceService.getAllReviewsForBook(book));
    }
}
