package br.com.dluzedesign.wood.dwoodbackend.services.product;

import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;

import java.util.List;

public interface IProductQueryService {
    List<ProductCategoryResponseDTO> getAll();
    List<ProductCategoryResponseDTO> getProductByCategory(String category);
    ProductResponseDTO getProductById(Long id);
}
