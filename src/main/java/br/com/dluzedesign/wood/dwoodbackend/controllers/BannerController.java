package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.services.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {
    private BannerService service;

    public BannerController(BannerService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<BannerResponseDTO>> getAll(){
        return ResponseEntity.ok(service.gelAll());
    }
}
