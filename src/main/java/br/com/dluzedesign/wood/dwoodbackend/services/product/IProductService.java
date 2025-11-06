package br.com.dluzedesign.wood.dwoodbackend.services.product;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;

public interface IProductService {
    ProductResponseDTO insert(ProductRequestDTO request);
    ProductResponseDTO update(Long id, ProductRequestDTO request);
}
