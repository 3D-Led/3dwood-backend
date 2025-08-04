package br.com.dluzedesign.wood.dwoodbackend.dtos;

import java.util.List;

public record ProductResponseDTO(String name,
                                 Integer sku,
                                 Long ean,
                                 String size,
                                 String imgUrl,
                                 List<String> imgs,
                                 String categoryName) {
}
