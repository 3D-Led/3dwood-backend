package br.com.dluzedesign.wood.dwoodbackend.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank String name,
                                 String imgCategoryUrl) {
}
