package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.services.BannerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService service;
    @GetMapping("/{type}")
    ResponseEntity<List<BannerResponseDTO>> getAll(@PathVariable BannerType type) {
        return ResponseEntity.ok(service.getAllByType(type));
    }
    @PostMapping
    ResponseEntity<BannerResponseDTO> insert(@RequestBody @Valid BannerRequestDTO bannerRequestDTO) {
        return ResponseEntity.ok(service.insert(bannerRequestDTO));
    }
}
