package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.NewsLetterRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import br.com.dluzedesign.wood.dwoodbackend.services.NewsletterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "${api.prefix}/newsletter")
@RequiredArgsConstructor
public class NewsletterController {
    private final  NewsletterService service;
    @PostMapping()
    public ResponseEntity<?> insert(@RequestBody @Valid NewsLetterRequestDTO dto) {
        try {
            Newsletter saved = service.insert(dto);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", saved.getName() +": Inscrição realizada com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Erro ao salvar inscrição: " + e.getMessage()
            ));
        }
    }

}
