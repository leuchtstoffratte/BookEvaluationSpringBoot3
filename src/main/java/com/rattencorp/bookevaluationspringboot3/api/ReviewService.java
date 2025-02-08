package com.rattencorp.bookevaluationspringboot3.api;

import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.Review;

import java.util.List;

public interface ReviewService {


    void submitReview(Review review);


    void updateReview(Review review);

    /**
     * Get all review to a book regardless of edition
     * @param book
     * @return
     */
    List<Review> getReviewsForBook(Book book);


}
