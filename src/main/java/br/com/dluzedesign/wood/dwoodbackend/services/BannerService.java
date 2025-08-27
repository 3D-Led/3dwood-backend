package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Banner;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.repositories.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository repository;
    public List<BannerResponseDTO> getAllByType(BannerType type){
        if (!repository.existsByType(type)){
            throw new IllegalArgumentException("Typo " + type + " não encontrado!");
        }
        return repository.findAll().stream()
                .filter(b->b.getType().equals(type))
                .map(b-> new BannerResponseDTO(b.getId(),b.getImgUrl(),b.getType()))
                .toList();
    }
    public BannerResponseDTO insert(BannerRequestDTO request) {
        Banner banner;
        if (request.id() != null) {
            banner = repository.findById(request.id())
                    .orElseThrow(() -> new IllegalArgumentException("Banner não encontrado para id: " + request.id()));

            banner.setImgUrl(request.imgUrl());
        } else {
            if (request.type() != BannerType.CARROUSEL) {
                throw new IllegalArgumentException("Somente banners do tipo CARROUSEL podem ser inseridos.");
            }
            banner = new Banner(request);
        }
        Banner saved = repository.save(banner);
        return new BannerResponseDTO(
                saved.getId(),
                saved.getImgUrl(),
                saved.getType()
        );
    }
}
