package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.NewsLetterRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import br.com.dluzedesign.wood.dwoodbackend.repositories.NewsletterRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {
    private NewsletterRepository repository;

    public NewsletterService(NewsletterRepository repository) {
        this.repository = repository;
    }

    public Newsletter insert(NewsLetterRequestDTO dto) {
        if (!repository.existsByEmail(dto.email())){
            return repository.save(new Newsletter(dto));
        }
        return null;

    }
}
