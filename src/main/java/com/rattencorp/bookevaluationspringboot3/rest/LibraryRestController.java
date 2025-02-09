package com.rattencorp.bookevaluationspringboot3.rest;


import com.rattencorp.bookevaluationspringboot3.api.AuthorsService;
import com.rattencorp.bookevaluationspringboot3.api.LibraryService;
import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/rest/library")
public class LibraryRestController {

    private static final Log LOG = LogFactory.getLog(LibraryRestController.class);

    private final LibraryService libraryService;
    private final AuthorsService authorsService;


    @Autowired
    public LibraryRestController(LibraryService libraryService, AuthorsService authorsService) {
        this.libraryService = libraryService;
        this.authorsService = authorsService;
    }



    @PostMapping(path="addBook")
    @ResponseBody
    public String addBookEdition(@RequestBody BookEdition book) {
        LOG.debug("addBookEdition '%s".formatted(book));

        libraryService.addBook(book);
        return "Added Book '%s'".formatted(book);
    }



    @GetMapping(path="listBooks")
    @ResponseBody
    public List<BookEdition> listBooks(@RequestBody Author author) {
        LOG.debug("listBooks for author '%s'".formatted(author));

        return libraryService.getBooksByAuthor(author).stream().toList();
    }


    @GetMapping(path="listAuthors")
    @ResponseBody
    public List<Author> listAuthors() {
        LOG.debug("listAuthors");
        return authorsService.getAuthors();
    }

}
