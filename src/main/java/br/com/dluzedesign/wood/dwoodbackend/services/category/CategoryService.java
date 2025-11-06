package br.com.dluzedesign.wood.dwoodbackend.services.category;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.exceptions.IllegalArgumentException;
import br.com.dluzedesign.wood.dwoodbackend.mapper.CategoryMapper;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.CATEGORY_ALREADY_EXISTS;
import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService{
    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    @Override
    public CategoryResponseDTO insert(CategoryRequestDTO request) {
        log.debug("Try to method to insert category in repository following DATA: {}", request);
        repository.findByName(request.name())
                .ifPresent(exists -> {
                    throw new IllegalArgumentException(CATEGORY_ALREADY_EXISTS.params(request.name()).getMessage());
                });
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }
    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO request) {
        log.debug("Try to method to update category with ID: {} following new data: {}", id, request);
        var oldData = repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(CATEGORY_NOT_FOUND.params(id.toString()).getMessage()));
        oldData.setImgCategoryUrl(request.imgCategoryUrl());
        return mapper.toResponse(repository.save(oldData));
    }

}
