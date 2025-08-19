package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@AllArgsConstructor
public class CategoryController {
    private CategoryService service;
    @GetMapping
    ResponseEntity<List<CategoryResponseDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> getOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findOne(id));
    }
    @PostMapping
    ResponseEntity<CategoryResponseDTO> insert(@RequestBody @Valid CategoryRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(request));
    }@PutMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> getAll(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }
}
