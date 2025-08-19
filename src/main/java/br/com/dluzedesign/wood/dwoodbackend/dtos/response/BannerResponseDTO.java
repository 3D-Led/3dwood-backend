package br.com.dluzedesign.wood.dwoodbackend.dtos.response;

import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;

public record BannerResponseDTO(Long id, String imgUrl, BannerType type) {
}
