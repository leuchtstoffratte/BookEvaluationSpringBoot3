package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private Author author;
    private String isbn;
    private Publisher publisher;


}
