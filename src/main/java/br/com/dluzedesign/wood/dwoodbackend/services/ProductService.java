package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import br.com.dluzedesign.wood.dwoodbackend.repositories.ProductRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRespository repository;
    public ProductService(ProductRespository repository) {
        this.repository = repository;
    }
    public List<Product> getAll(){
        return repository.findAll();
    }
    public List<Product> getProductByCategory(String category) {
        var list = repository.findAll();
        return list.stream().
                filter(p -> p.getCategories().stream()
                        .anyMatch(c->c.getName().equals(category.toLowerCase())))
                .toList();
    }
    public Product getProductById(Long id) {
        return repository.findById(id).get();
    }

}
