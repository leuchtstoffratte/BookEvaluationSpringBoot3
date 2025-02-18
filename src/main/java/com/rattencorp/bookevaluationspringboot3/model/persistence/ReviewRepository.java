/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rattencorp.bookevaluationspringboot3.model.persistence;

import com.rattencorp.bookevaluationspringboot3.model.Book;
import com.rattencorp.bookevaluationspringboot3.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 *
 * @author <intentionally left blank>
 */
public interface ReviewRepository extends JpaRepository<Review, Integer>{


    Set<Review> findByBook(Book book);

}
