package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
