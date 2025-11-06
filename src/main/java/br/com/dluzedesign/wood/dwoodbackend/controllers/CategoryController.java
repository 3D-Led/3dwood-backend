package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.services.category.CategoryQueryService;
import br.com.dluzedesign.wood.dwoodbackend.services.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService service;
    private final CategoryQueryService queryService;
    @GetMapping
    ResponseEntity<List<CategoryResponseDTO>> getAll(){
        log.info("Processing request to obtain all category without parameter");
        return ResponseEntity.status(HttpStatus.OK).body(queryService.findAll());
    }
    @GetMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> getOne(@PathVariable Long id){
        log.info("Processing request to obtain category by ID");
        return ResponseEntity.status(HttpStatus.OK).body(queryService.findOne(id));
    }
    @PostMapping
    ResponseEntity<CategoryResponseDTO> insert(@RequestBody @Valid CategoryRequestDTO request){
        log.info("Processing request to insert new category");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(request));
    }
    @PutMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO request){
        log.info("Processing request to update category by ID");
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }
}
