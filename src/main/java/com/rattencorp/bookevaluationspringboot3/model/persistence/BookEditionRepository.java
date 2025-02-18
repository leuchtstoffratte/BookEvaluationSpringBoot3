/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rattencorp.bookevaluationspringboot3.model.persistence;

import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.BookEdition;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author <intentionally left blank>
 */
public interface BookEditionRepository extends JpaRepository<BookEdition, String>{

    List<BookEdition> findByNumberIsbn(String numberIsbn);
    
    
    @Query(value = "Select be From BookEdition be where be.book in (SELECT b FROM Book b WHERE b.title LIKE :title ) ")
    List<BookEdition> findByTitle(String title);

    Set<BookEdition> findByBook(Book book);

}
