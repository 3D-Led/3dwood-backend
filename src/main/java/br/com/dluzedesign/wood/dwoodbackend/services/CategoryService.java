package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    public List<CategoryResponseDTO> findAll() {
        List<Category> list = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getId))
                .collect(Collectors.toList());
        return list.stream()
                .map(c-> new CategoryResponseDTO(c.getName(),c.getImgCategoryUrl()))
                .limit(6)
                .collect(Collectors.toList());
    }
}
