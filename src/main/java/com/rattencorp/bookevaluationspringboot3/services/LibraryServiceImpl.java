package com.rattencorp.bookevaluationspringboot3.services;

import com.rattencorp.bookevaluationspringboot3.api.LibraryService;
import com.rattencorp.bookevaluationspringboot3.api.PersistenceService;
import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final PersistenceService persistenceService;


    @Autowired
    public LibraryServiceImpl(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public Set<BookEdition> getBooksByAuthor(Author author) {
        return Set.of();
    }

    @Override
    public BookEdition getBookById(int id) {
        return null;
    }

    @Override
    public void addBook(BookEdition bookEdition) {
        persistenceService.persistBook(bookEdition.book());
    }

    @Override
    public Book getBookByTitle(String title) {
        return null;
    }

    @Override
    public Set<BookEdition> getEditionsByTitle(String title) {
        return Set.of();
    }
}
