package com.rattencorp.bookevaluationspringboot3.rest;


import com.rattencorp.bookevaluationspringboot3.api.LibraryService;
import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/rest/library")
public class LibraryRestController {

    private final LibraryService libraryService;


    @Autowired
    public LibraryRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }



    @PostMapping(path="addBook")
    @ResponseBody
    public String addBookEdition(@RequestBody BookEdition book) {
        libraryService.addBook(book);
        return "Added Book '%s'".formatted(book);
    }



    @GetMapping(path="listBooks")
    @ResponseBody
    public List<BookEdition> listBooks(@RequestBody Author author) {
        return libraryService.getBooksByAuthor(author).stream().toList();
    }




}
