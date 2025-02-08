package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    private Reviewer reviewer;
    private String text;
    private String title;
    private Rating rating;
    private LocalDate reviewDate;





    public enum Rating{
        VERY_GOOD,
        GOOD,
        BAD,
        VERY_BAD;
    }

}
