package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serial;
import java.io.Serializable;


public class Publisher implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String publisherName;


    public Publisher(String name) {
        this.publisherName = name;
    }

    public String getPublisherName() {
        return publisherName;
    }


}
