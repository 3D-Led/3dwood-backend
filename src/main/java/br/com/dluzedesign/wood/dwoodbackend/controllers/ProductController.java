package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import br.com.dluzedesign.wood.dwoodbackend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping(value = "/category")
    ResponseEntity<List<ProductCategoryResponseDTO>> getProductByCategory(@RequestParam String category){
        return ResponseEntity.ok(service.getProductByCategory(category));
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(service.getProductById(id));
    }

}
