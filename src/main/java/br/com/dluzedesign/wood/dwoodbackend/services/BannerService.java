package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.repositories.BannerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BannerService {
    private BannerRepository repository;

    public BannerService(BannerRepository repository) {
        this.repository = repository;
    }
    public List<BannerResponseDTO> gelAll(){
        return repository.findAll().stream()
                .map(b-> new BannerResponseDTO(b.getImgUrl()))
                .toList();
    }
}
