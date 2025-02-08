package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;

    private List<Book> books;
}
