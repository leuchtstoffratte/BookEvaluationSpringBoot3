package com.rattencorp.bookevaluationspringboot3.web;

import com.rattencorp.bookevaluationspringboot3.api.LibraryService;
import com.rattencorp.bookevaluationspringboot3.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path="/web/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping("/enter")
    public String enterLibrary(@Autowired Model model) {

        return "library/library_browse.html";
    }


    @PostMapping(path="/listBooksByAuthor")
    public String listBooks(Model model, @ModelAttribute("author") Author author) {

        model.addAttribute("books", libraryService.getBooksByAuthor(author));

        return "library/library_browse.html";
    }

}
