package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Review implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Reviewer reviewer;
    private String text;
    private String title;
    private Rating rating;
    private LocalDate reviewDate;
    private final BookEdition book;


    public Review(Reviewer reviewer, BookEdition book) {
        this.reviewer = reviewer;
        this.book = book;
    }

    public Review(Reviewer reviewer, String text, String title, Rating rating, LocalDate reviewDate, BookEdition book) {
        this.reviewer = reviewer;
        this.text = text;
        this.title = title;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.book = book;
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

    public enum Rating{
        VERY_GOOD,
        GOOD,
        BAD,
        VERY_BAD;
    }

}
