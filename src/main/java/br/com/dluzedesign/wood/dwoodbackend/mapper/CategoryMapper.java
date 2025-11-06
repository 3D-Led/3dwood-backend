package br.com.dluzedesign.wood.dwoodbackend.mapper;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryRequestDTO requestDTO);
    CategoryResponseDTO toResponse(Category category);
}
