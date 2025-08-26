package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.services.ProductService;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<List<ProductCategoryResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping(value = "/category")
    ResponseEntity<List<ProductCategoryResponseDTO>> getProductByCategory(@RequestParam String category){
        return ResponseEntity.ok(service.getProductByCategory(category));
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(service.getProductById(id));
    }
    @PostMapping
    ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO request) {
        ProductResponseDTO createdProduct = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO request) {
        ProductResponseDTO updatedProduct = service.update(id, request);
        return ResponseEntity.ok(updatedProduct);
    }
}
