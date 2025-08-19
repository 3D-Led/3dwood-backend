package br.com.dluzedesign.wood.dwoodbackend.dtos.response;

import java.util.List;

public record ProductResponseDTO(String descriptions,
                                String name,

                                 Long sku,
                                 Long ean,
                                 String size,
                                 String imgUrl,
                                 List<String> imgs,
                                 String categoryName) {
}
