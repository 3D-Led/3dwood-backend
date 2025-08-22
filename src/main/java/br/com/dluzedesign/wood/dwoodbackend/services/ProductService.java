package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import br.com.dluzedesign.wood.dwoodbackend.repositories.ProductRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRespository repository;
    private CategoryRepository categoryRepository;
    public List<ProductCategoryResponseDTO> getAll(){
        var list =  repository.findAll();
        return list.stream()
                .map(p-> new ProductCategoryResponseDTO(p.getId(),p.getName(),p.getImgUrl()))
                .toList();
    }
    public List<ProductCategoryResponseDTO> getProductByCategory(String category) {
        if(!categoryRepository.existsByName(category)) {
            throw new EntityNotFoundException("Categoria: "+ category + ", não encontrada");
        }
        var list   = repository.findByCategoria(category);
        return list.stream()
                .map(p -> new ProductCategoryResponseDTO(p.getId(),p.getName(),p.getImgUrl()))
                .toList();
    }
    public ProductResponseDTO getProductById(Long id) {
        return repository.findById(id)
                .map(product -> new ProductResponseDTO(
                        product.getDescription(),
                        product.getName(),
                        product.getSku(),
                        product.getEan(),
                        product.getSize(),
                        product.getImgUrl(),
                        product.getListImgs(),
                        repository.findFirstCategoryNameByProductId(product.getId())
                ))
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
    }

    public ProductResponseDTO insert(ProductRequestDTO request) {
        if (!categoryRepository.existsByName(request.categoryName())) {
            throw new EntityNotFoundException("Categoria: " + request.categoryName() + ", não encontrada");
        }

        var categoria = categoryRepository.findByName(request.categoryName());
        var product = new Product(
                null,
                request.descriptions(),
                request.name(),
                request.sku(),
                request.ean(),
                request.size(),
                request.imgUrl(),
                request.imgs(),
                Set.of(categoria)
        );

        product = repository.save(product);

        return new ProductResponseDTO(
                product.getDescription(),
                product.getName(),
                product.getSku(),
                product.getEan(),
                product.getSize(),
                product.getImgUrl(),
                product.getListImgs(),
                categoria.getName()
        );
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        var product = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto com id " + id + " não encontrado")
        );

        if (StringUtils.hasText(request.descriptions())) product.setDescription(request.descriptions());
        if (StringUtils.hasText(request.name())) product.setName(request.name());
        if (request.sku() != null) product.setSku(request.sku());
        if (request.ean() != null) product.setEan(request.ean());
        if (StringUtils.hasText(request.size())) product.setSize(request.size());
        if (StringUtils.hasText(request.imgUrl())) product.setImgUrl(request.imgUrl());
        if (request.imgs() != null && !request.imgs().isEmpty()) product.setListImgs(request.imgs());

        if (StringUtils.hasText(request.categoryName())) {
            if (!categoryRepository.existsByName(request.categoryName())) {
                throw new EntityNotFoundException("Categoria: " + request.categoryName() + ", não encontrada");
            }
            var categoria = categoryRepository.findByName(request.categoryName());
            product.setCategories(Set.of(categoria));
        }

        product = repository.save(product);

        String categoryName = product.getCategories().stream()
                .findFirst()
                .map(Category::getName)
                .orElse(null);

        return new ProductResponseDTO(
                product.getDescription(),
                product.getName(),
                product.getSku(),
                product.getEan(),
                product.getSize(),
                product.getImgUrl(),
                product.getListImgs(),
                categoryName
        );
    }
}

