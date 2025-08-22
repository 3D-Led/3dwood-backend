package br.com.dluzedesign.wood.dwoodbackend.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProductRequestDTO(String descriptions,
                                @NotBlank String name,
                                Long sku,
                                Long ean,
                                String size,
                                String imgUrl,
                                List<String> imgs,
                                @NotBlank String categoryName) {
}
