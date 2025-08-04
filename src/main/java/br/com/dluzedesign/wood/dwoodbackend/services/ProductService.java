package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import br.com.dluzedesign.wood.dwoodbackend.repositories.ProductRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRespository repository;
    public ProductService(ProductRespository repository) {
        this.repository = repository;
    }
    public List<ProductCategoryResponseDTO> getAll(){
        var list =  repository.findAll();
        return list.stream()
                .map(p-> new ProductCategoryResponseDTO(p.getId(),p.getName(),p.getImgUrl()))
                .toList();
    }
    public List<ProductCategoryResponseDTO> getProductByCategory(String category) {
        var list   = repository.findByCategoria(category);
        return list.stream()
                .map(p -> new ProductCategoryResponseDTO(p.getId(),p.getName(),p.getImgUrl()))
                .toList();
    }
    public ProductResponseDTO getProductById(Long id) {
        return repository.findById(id)
                .map(product -> new ProductResponseDTO(
                        product.getName(),
                        product.getSku(),
                        product.getEan(),
                        product.getSize(),
                        product.getImgUrl(),
                        repository.findFirstCategoryNameByProductId(product.getId())
                ))
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
    }

}
