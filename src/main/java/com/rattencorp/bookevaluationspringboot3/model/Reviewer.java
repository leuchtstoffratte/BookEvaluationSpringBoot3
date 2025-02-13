package com.rattencorp.bookevaluationspringboot3.model;

import java.io.Serial;
import java.io.Serializable;

public class Reviewer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;
    private String name;


    public Reviewer(int id) {
        this.id = id;
    }

    public Reviewer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
