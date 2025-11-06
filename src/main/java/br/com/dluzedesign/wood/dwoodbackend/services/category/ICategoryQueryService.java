package br.com.dluzedesign.wood.dwoodbackend.services.category;

import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;

import java.util.List;

public interface ICategoryQueryService {
    List<CategoryResponseDTO> findAll();
    CategoryResponseDTO findOne(Long id);
}
