package com.rattencorp.bookevaluationspringboot3.services;

import com.rattencorp.bookevaluationspringboot3.model.Author;
import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

class PersistenceServiceImplTest {


    private static final String AUTHOR_NAME = "Author";
    private static final String AUTHOR_SURNAME = "McAuthorson";
    private static final String ISBN = "ISBN";
    private static final String BOOK_TITLE = "Wonderful world of testing";

    private PersistenceServiceImpl testObj;


    @BeforeEach
    void setUp() {
        testObj = new PersistenceServiceImpl();
    }

    @Test
    void persistedEdition_shouldAllowRetrievalOfEdition_whenPersistedEdition() {
        //GIVEN
        final BookEdition inEdition = defaultBookEdition();

        testObj.persistEdition(inEdition);

        //WHEN
        final Set<BookEdition> result = testObj.getAllEditions();

        //THEN
        org.junit.jupiter.api.Assertions.assertAll(
                "When one edition is persisted in a clean store, exactly that edition should be retrievable",
                ()->Assertions.assertThat(result).contains(inEdition),
                ()->Assertions.assertThat(result).hasSize(1)
        );

    }


    @Test
    void persistedEdition_shouldPersistParentBookOfEdition_whenPersistedEdition() {
        //GIVEN
        final BookEdition inEdition = defaultBookEdition();

        testObj.persistEdition(inEdition);

        //WHEN
        final Set<Book> result = testObj.getAllBooks();

        //THEN
        org.junit.jupiter.api.Assertions.assertAll(
                "When one edition is persisted in a clean store, exactly that editions parent book should be retrievable",
                ()->Assertions.assertThat(result).contains(inEdition.book()),
                ()->Assertions.assertThat(result).hasSize(1)
        );

    }

    @Test
    void findBookByTitle_shouldProvidePersistedBook_whenPersistingEdition() {
        //GIVEN
        final BookEdition inEdition = defaultBookEdition();

        testObj.persistEdition(inEdition);

        //WHEN
        final Book result = testObj.findBookByTitle(inEdition.book().title());

        //THEN
        Assertions.assertThat(result).isEqualTo(inEdition.book());

    }

    @Test
    void findEditionByIsbn_shouldProvidePersistedEdition_whenPersistingEdition() {
        //GIVEN
        final BookEdition inEdition = defaultBookEdition();

        testObj.persistEdition(inEdition);

        //WHEN
        final BookEdition result = testObj.findBookByIsbn(inEdition.isbn());

        //THEN
        Assertions.assertThat(result).isEqualTo(inEdition);

    }



    private Author defaultAuthor(){
        Author author = new Author(12) ;
        author.setName(AUTHOR_NAME);
        author.setSurname(AUTHOR_SURNAME);
        return author;
    }

    private Book defaultBook(){
        return new Book(BOOK_TITLE, defaultAuthor());
    }

    private BookEdition defaultBookEdition(){
        return new BookEdition(defaultBook(), ISBN,
                LocalDate.of(2025, 5, 12),1,
                BookEdition.BookForm.HARDCOVER );
    }

}