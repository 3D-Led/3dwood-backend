package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.NewsLetterRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import br.com.dluzedesign.wood.dwoodbackend.repositories.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsletterService {
    private final NewsletterRepository repository;
    public Newsletter insert(NewsLetterRequestDTO dto) {
        if (!repository.existsByEmail(dto.email())){
            return repository.save(new Newsletter(dto));
        }
        return null;
    }
}
