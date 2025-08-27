package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    public List<CategoryResponseDTO> findAll() {
        List<Category> list = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getId))
                .toList();
        return list.stream()
                .map(c-> new CategoryResponseDTO(c.getId(), c.getName(),c.getImgCategoryUrl()))
                .limit(6)
                .collect(Collectors.toList());
    }
    public CategoryResponseDTO findOne(Long id) {
        var category = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id: " + id + "não encontrado;"));
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getImgCategoryUrl());
    }
    public CategoryResponseDTO insert(CategoryRequestDTO request) {
        if (repository.existsByName(request.name())) {
            throw new IllegalArgumentException(request.name() + "já existe outra categoria com esse nome!");
        }
        var newCategory = repository.save(new Category(request.name(), request.imgCategoryUrl()));
        return new CategoryResponseDTO(newCategory.getId(), newCategory.getName(), newCategory.getImgCategoryUrl());
    }
    public CategoryResponseDTO update(Long id, CategoryRequestDTO request) {
        var oldData = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Id: " + id + "não encontrado!"));
        oldData.setImgCategoryUrl(request.imgCategoryUrl());
        repository.save(oldData);
        return new CategoryResponseDTO(oldData.getId(), oldData.getName(), oldData.getImgCategoryUrl());
    }

}
