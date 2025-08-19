package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Banner;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.repositories.BannerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BannerService {
    private BannerRepository repository;
    public List<BannerResponseDTO> getAllByType(BannerType type){
        return repository.findAll().stream()
                .filter(b->b.getType().equals(type))
                .map(b-> new BannerResponseDTO(b.getImgUrl()))
                .toList();
    }
    public boolean insert (BannerRequestDTO banner) {
        var result = false;
        if(banner.id() == null && banner.type() == BannerType.CARROUSEL){
            repository.save(new Banner(banner));
            result = true;
        } else {
            var olddata = repository.findById(banner.id()).get();
            olddata.setImgUrl(banner.imgUrl());
            repository.save(olddata);
            result = true;
        }
        return result;
    }


}
