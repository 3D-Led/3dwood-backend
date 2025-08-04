package br.com.dluzedesign.wood.dwoodbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewsLetterRequestDTO(
        @NotBlank String name,
                                   @Email @NotBlank String email) {
}
