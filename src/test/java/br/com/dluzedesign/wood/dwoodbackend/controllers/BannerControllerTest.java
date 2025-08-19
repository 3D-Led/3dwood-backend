package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.services.BannerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BannerController.class)
class BannerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private BannerService service;
    @Test
    void createBanner_WithValidData_ReturnOk() throws Exception {
        BannerRequestDTO request = new BannerRequestDTO(
                null,
                "https://testedeimage.com.br",
                BannerType.BANNER1);
        when(service.insert(request)).thenReturn(new BannerResponseDTO(request.id(),request.imgUrl(),request.type()));
        mockMvc.perform(
                        post("/banner").content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(request.id()))
                .andExpect(jsonPath("$.imgUrl").value(request.imgUrl()))
                .andExpect(jsonPath("$.type").value(equalTo(request.type().name())));
    }
    @Test
    void createBanner_WithInvalidData_ReturnBadRequest() throws Exception {
        BannerRequestDTO emptyRequest = new BannerRequestDTO(null,"",null);
        mockMvc.perform(
                        post("/banner").content(objectMapper.writeValueAsString(emptyRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void getAllBannerByType_WithValidData_ReturnOk() throws Exception {
        BannerType param = BannerType.BANNER1;
        List<BannerResponseDTO> list = new ArrayList<>(
                List.of(new BannerResponseDTO(1L, "https://testedeimage.com.br", BannerType.BANNER1))
        );

        when(service.getAllByType(param)).thenReturn(list);
        mockMvc.perform(get("/banner/{type}", param.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(list.get(0).id()))
                .andExpect(jsonPath("$[0].imgUrl").value(list.get(0).imgUrl()))
                .andExpect(jsonPath("$[0].type").value(equalTo(list.get(0).type().name())));
    }
    @Test
    void getAllBannerByType_WithInvalidData_ReturnBadRequest() throws Exception {
        var invalidParam = "inexistentebanner";
        var emptyParam = "";
        mockMvc.perform(
                        get("/banner/{type}",invalidParam))
                .andExpect(status().isBadRequest());
        mockMvc.perform(
                        get("/banner/{type}",emptyParam))
                .andExpect(status().isNotFound());
    }

}