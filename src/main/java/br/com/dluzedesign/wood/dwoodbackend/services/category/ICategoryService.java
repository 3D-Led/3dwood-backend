package br.com.dluzedesign.wood.dwoodbackend.services.category;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {
    CategoryResponseDTO insert(CategoryRequestDTO request);
    CategoryResponseDTO update(Long id, CategoryRequestDTO request);
}
