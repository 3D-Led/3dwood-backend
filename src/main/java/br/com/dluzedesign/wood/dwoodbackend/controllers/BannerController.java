package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.services.BannerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    ResponseEntity<?> insert(@RequestParam @Valid BannerRequestDTO bannerRequestDTO) {
        try {
            service.insert(bannerRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", " Salvo com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Erro ao salvar!: " + e.getMessage()
            ));
        }
    }
}
