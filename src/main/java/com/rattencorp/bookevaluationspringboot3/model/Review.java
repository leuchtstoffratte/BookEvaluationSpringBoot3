package com.rattencorp.bookevaluationspringboot3.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Review implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int reviewId;
    
    @ManyToOne
    private final Reviewer reviewer;
    @ManyToOne
    private final BookEdition book;

    //reviews might be adapted, hence these will be mutable
    private String text;
    private String title;
    private Rating rating;
    private LocalDate reviewDate;


    public Review(Reviewer reviewer, BookEdition book) {
        this.reviewer = reviewer;
        this.book = book;
    }

    public Review(int reviewId, Reviewer reviewer, String text, String title, Rating rating, LocalDate reviewDate, BookEdition book) {
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.text = text;
        this.title = title;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.book = book;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    
    public Reviewer getReviewer() {
        return reviewer;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public BookEdition getBook() {
        return book;
    }


    public void updateReview(Review review) {
        this.reviewDate = review.getReviewDate();
        this.rating = review.getRating();
        this.text = review.getText();
        this.title = review.getTitle();

    }




    public enum Rating{
        VERY_GOOD,
        GOOD,
        BAD,
        VERY_BAD;
    }

}
