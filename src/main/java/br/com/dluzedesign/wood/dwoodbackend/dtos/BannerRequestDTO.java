package br.com.dluzedesign.wood.dwoodbackend.dtos;

import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import jakarta.validation.constraints.NotNull;

public record BannerRequestDTO(Long id, String imgUrl, @NotNull BannerType type) {
}
