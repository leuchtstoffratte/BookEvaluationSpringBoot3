/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rattencorp.bookevaluationspringboot3.model.persistence;

import com.rattencorp.bookevaluationspringboot3.model.BookEdition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author <intentionally left blank>
 */
public interface BookEditionRepository extends JpaRepository<BookEdition, String>{
    
}
