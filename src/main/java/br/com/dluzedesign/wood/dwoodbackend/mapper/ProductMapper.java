package br.com.dluzedesign.wood.dwoodbackend.mapper;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {
    Product toEntity(ProductRequestDTO request);
    ProductResponseDTO toResponse(Product product);
    ProductCategoryResponseDTO toProductCategoryResponse(Product product);

    @Mapping(target = "description", qualifiedByName = "emptyToNull")
    @Mapping(target = "name", qualifiedByName = "emptyToNull")
    void update(@MappingTarget Product product, ProductRequestDTO dto);

    @Named("emptyToNull")
    default String emptyToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }
}
