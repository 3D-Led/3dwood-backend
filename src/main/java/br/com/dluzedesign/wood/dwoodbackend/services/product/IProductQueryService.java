package br.com.dluzedesign.wood.dwoodbackend.services.product;

import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface IProductQueryService {
    List<ProductCategoryResponseDTO> getAll(int page, int numbersItems);
    List<ProductCategoryResponseDTO> getProductByCategory(String category);
    ProductResponseDTO getProductById(Long id);
}
