package br.com.dluzedesign.wood.dwoodbackend.dtos;

public record ProductResponseDTO( String name,
        Integer sku,
        Long ean,
        String size,
        String imgUrl,
        String categoryName) {
}
