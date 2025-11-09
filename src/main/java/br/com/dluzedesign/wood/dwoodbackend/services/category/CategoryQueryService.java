package br.com.dluzedesign.wood.dwoodbackend.services.category;

import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.exceptions.NotFoundException;
import br.com.dluzedesign.wood.dwoodbackend.mapper.CategoryMapper;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.CATEGORY_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryQueryService implements ICategoryQueryService{
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryResponseDTO> findAll() {
        log.debug("Try to find all category in repository");
        List<Category> list = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getId))
                .limit(6)
                .toList();
        log.debug("Try to transform category list into CategoryResponseDTO list");
        return list.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO findOne(Long id) {
        log.debug("Try to find category by ID following ID: {}", id);
        var category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.params(id.toString()).getMessage()));
        log.debug("Try to transform category into CategoryResponseDTO");
        return mapper.toResponse(category);
    }
}
