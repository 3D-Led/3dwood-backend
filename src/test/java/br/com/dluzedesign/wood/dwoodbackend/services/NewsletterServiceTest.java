package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.NewsLetterRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import br.com.dluzedesign.wood.dwoodbackend.repositories.NewsletterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsletterServiceTest {

    @InjectMocks
    private NewsletterService service;
    @Mock
    private NewsletterRepository repository;
    @Test
    void createNewsletters_WithValidDate_ReturnsNewsletter() {
        Newsletter newsletter = new Newsletter(
                null,
                "joao",
                "joao@joao.com.br");
        NewsLetterRequestDTO newsLetterRequestDTO = new NewsLetterRequestDTO(
                "joao",
                "joao@joao.com.br");
        when(repository.existsByEmail(newsLetterRequestDTO.email())).thenReturn(false);
        when(repository.save(any(Newsletter.class))).thenReturn(newsletter);

        Newsletter sut = service.insert(newsLetterRequestDTO);

        assertThat(sut).isEqualTo(newsletter);
    }
    @Test
    void createNewsletters_WithDuplicateDate_ReturnsNull() {
        NewsLetterRequestDTO newsLetterRequestDTO = new NewsLetterRequestDTO(
                "joao",
                "joao@joao.com.br");

        when(repository.existsByEmail(newsLetterRequestDTO.email())).thenReturn(true);

        Newsletter sut = service.insert(newsLetterRequestDTO);

        assertThat(sut).isNull();
        verify(repository, never()).save(any());
    }
}