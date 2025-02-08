package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serializable;
import java.util.List;

public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Book> books;
    private List<Author> authors;

}
