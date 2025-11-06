package br.com.dluzedesign.wood.dwoodbackend.services.product;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.exceptions.NotFoundException;
import br.com.dluzedesign.wood.dwoodbackend.mapper.ProductMapper;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import br.com.dluzedesign.wood.dwoodbackend.repositories.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.CATEGORY_NOT_FOUND;
import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {
    private final ProductRespository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;


    @Override
    public ProductResponseDTO insert(ProductRequestDTO request) {
        log.debug("Try to save product in repository");
        return categoryRepository.findByName(request.categoryName())
                .map(exists -> {
                    var data = mapper.toEntity(request);
                    data.setCategories(Set.of(exists));
                    return mapper.toResponse(repository.save(data));
                }).orElseThrow(()-> new NotFoundException(CATEGORY_NOT_FOUND.params(request.categoryName()).getMessage()));
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        log.debug("Try to update product in repository");
        var product = repository.findById(id).orElseThrow(
                () -> new NotFoundException(PRODUCT_NOT_FOUND.params(id.toString()).getMessage())
        );
        return categoryRepository.findByName(request.categoryName())
                .map(exists -> {
                    mapper.update(product, request);
                    return mapper.toResponse(repository.save(product));
                })
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));

    }
}

