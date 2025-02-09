package com.rattencorp.bookevaluationspringboot3.rest;

import com.rattencorp.bookevaluationspringboot3.api.ReviewService;
import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/rest/review")
public class ReviewRestController {

    private final ReviewService reviewService;


    @Autowired
    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping(path="/listReviewForBook")
    @ResponseBody
    public List<Review> getReviewForBook(@RequestParam Book book) {
        return reviewService.getReviewsForBook(book);
    }

    @PostMapping(path="/submitReview")
    public void submitReview(@RequestBody Review review) {
        reviewService.submitReview(review);
    }


}
