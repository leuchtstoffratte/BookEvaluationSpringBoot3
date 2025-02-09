package com.rattencorp.bookevaluationspringboot3.services;

import com.rattencorp.bookevaluationspringboot3.api.AuthorsService;
import com.rattencorp.bookevaluationspringboot3.internal.PersistenceService;
import com.rattencorp.bookevaluationspringboot3.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final PersistenceService persistenceService;


    @Autowired
    public AuthorsServiceImpl(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }


    @Override
    public List<Author> getAuthors() {
        return persistenceService.getAllAuthors().stream().toList();
    }

    @Override
    public void addAuthor(Author author) {
        persistenceService.persistAuthor(author);
    }

    @Override
    public List<Author> getAuthorsByName(String surname) {
        return persistenceService.getAllAuthors()
                .stream()
                .filter(a -> a.getSurname().equals(surname))
                .toList();
    }
}
