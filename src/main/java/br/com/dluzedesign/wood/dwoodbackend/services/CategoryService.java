package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    public List<Category> findAll() {
        return repository.findAll();
    }
}
