package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.services.BannerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/banner")
@AllArgsConstructor
public class BannerController {
    private BannerService service;
    @GetMapping("/{type}")
    ResponseEntity<List<BannerResponseDTO>> getAll(@PathVariable BannerType type) {
        return ResponseEntity.ok(service.getAllByType(type));
    }
    @PostMapping
    ResponseEntity<BannerResponseDTO> insert(@RequestBody @Valid BannerRequestDTO bannerRequestDTO) {
        return ResponseEntity.ok(service.insert(bannerRequestDTO));
    }
}
