/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.api.repository;

import com.webservice.api.model.Departamentos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alberto
 */
public interface DepartamentosRepository extends JpaRepository<Departamentos, Long>{
    
}
