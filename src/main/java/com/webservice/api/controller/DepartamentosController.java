/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.api.controller;

import com.webservice.api.exception.ResourceNotFoundException;
import com.webservice.api.model.Departamentos;
import com.webservice.api.model.Empleados;
import com.webservice.api.repository.DepartamentosRepository;
import com.webservice.api.repository.EmpleadosRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alberto
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DepartamentosController {
    
    @Autowired
    private DepartamentosRepository departamentosRepository;

    @GetMapping("/departamentos")
    public List<Departamentos> getAllDepartamentos() {
        return departamentosRepository.findAll();
    }

    @GetMapping("/departamentos/{id}")
    public ResponseEntity<Departamentos> getDepartamentosById(@PathVariable(value = "id") Long departamentoId) throws ResourceNotFoundException {
        Departamentos despartamentos = departamentosRepository.findById(departamentoId)
        .orElseThrow(() -> new ResourceNotFoundException("Departamentos not found for this id :: " + departamentoId));
        return ResponseEntity.ok().body(despartamentos);
    }

    @PostMapping("/departamentos")
    public Departamentos createDepartamentos(@Validated @RequestBody Departamentos departamentos) {
        return departamentosRepository.save(departamentos);
    }

    @PutMapping("/departamentos/{id}")
    public ResponseEntity<Departamentos> updateDepartamentos(@PathVariable(value = "id") Long departamentoId, @RequestBody Departamentos departamentosDetails) throws ResourceNotFoundException {
        Departamentos departamentos = departamentosRepository.findById(departamentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Departamentos not found for this id :: " + departamentoId));

        departamentos.setId(departamentosDetails.getId());
        departamentos.setNombre(departamentosDetails.getNombre());
        final Departamentos updatedDepartamentos = departamentosRepository.save(departamentos);
        return ResponseEntity.ok(updatedDepartamentos);
    }

    @DeleteMapping("/departamentos/{id}")
    public Map<String, Boolean> deleteDepartamentos(@PathVariable(value = "id") Long departamentosId) throws ResourceNotFoundException {
        departamentosRepository.deleteById(departamentosId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
}
