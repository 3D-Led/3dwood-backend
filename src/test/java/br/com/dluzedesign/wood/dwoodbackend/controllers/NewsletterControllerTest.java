package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.NewsLetterRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import br.com.dluzedesign.wood.dwoodbackend.services.NewsletterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsletterController.class)
class NewsletterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private NewsletterService newsletterService;
    @Test
    void createNewsletter_WithValidData_ReturnCreated() throws Exception {
        NewsLetterRequestDTO newsLetterRequestDTO = new NewsLetterRequestDTO(
                "joao",
                "joao@joao.com.br");
        when(newsletterService.insert(newsLetterRequestDTO)).thenReturn(new Newsletter(newsLetterRequestDTO));
        mockMvc.perform(
                    post("/newsletter").content(objectMapper.writeValueAsString(newsLetterRequestDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(newsLetterRequestDTO.name() +": Inscrição realizada com sucesso!"));
    }

}