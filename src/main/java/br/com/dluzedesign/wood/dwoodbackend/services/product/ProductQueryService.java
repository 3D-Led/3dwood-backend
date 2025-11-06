package br.com.dluzedesign.wood.dwoodbackend.services.product;

import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.exceptions.NotFoundException;
import br.com.dluzedesign.wood.dwoodbackend.mapper.ProductMapper;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import br.com.dluzedesign.wood.dwoodbackend.repositories.ProductRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.CATEGORY_NOT_FOUND;
import static br.com.dluzedesign.wood.dwoodbackend.exceptions.BaseErrorMessage.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductQueryService implements IProductQueryService {
    private final ProductRespository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;
    @Override
    public List<ProductCategoryResponseDTO> getAll(){
        var list =  repository.findAll();
        return list.stream()
                .map(mapper::toProductCategoryResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductCategoryResponseDTO> getProductByCategory(String category) {
        return categoryRepository.findByName(category)
                .map(exists -> repository.findByCategoria(category).stream()
                        .map(mapper::toProductCategoryResponse)
                        .collect(Collectors.toList())
                ).orElseThrow(()-> new NotFoundException(CATEGORY_NOT_FOUND.params(category).getMessage()));

    }
    @Override
    public ProductResponseDTO getProductById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.params(id.toString()).getMessage()));
    }
}

