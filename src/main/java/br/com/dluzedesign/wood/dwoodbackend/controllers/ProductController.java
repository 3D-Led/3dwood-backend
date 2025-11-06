package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.services.product.ProductQueryService;
import br.com.dluzedesign.wood.dwoodbackend.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductQueryService queryService;


    @GetMapping
    ResponseEntity<List<ProductCategoryResponseDTO>> getAll(){
        return ResponseEntity.ok(queryService.getAll());
    }
    @GetMapping(value = "/category")
    ResponseEntity<List<ProductCategoryResponseDTO>> getProductByCategory(@RequestParam String category){
        return ResponseEntity.ok(queryService.getProductByCategory(category));
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(queryService.getProductById(id));
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
