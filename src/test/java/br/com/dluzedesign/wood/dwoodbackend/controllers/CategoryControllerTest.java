package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.CategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private CategoryService service;
    @Test
    void createCategory_WithValidData_ReturnCreated() throws Exception {
        CategoryRequestDTO request = new CategoryRequestDTO(
                "Teste1",
                "https://testedeimage.com.br");
        when(service.insert(request)).thenReturn(new CategoryResponseDTO(1L,request.name(),request.imgCategoryUrl()));
        mockMvc.perform(
                        post("/category").content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.imgCategoryUrl").value(request.imgCategoryUrl()));
    }
    @Test
    void createCategory_WithInvalidData_ReturnBadRequest() throws Exception {
        CategoryRequestDTO invalidRequest = new CategoryRequestDTO(null, null);
        CategoryRequestDTO emptyRequest = new CategoryRequestDTO("", "");
        mockMvc.perform(
                        post("/category").content(objectMapper.writeValueAsString(emptyRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        mockMvc.perform(
                        post("/category").content(objectMapper.writeValueAsString(invalidRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void updateCategory_WithValidData_ReturnOk() throws Exception {
        CategoryRequestDTO request = new CategoryRequestDTO(
                "Teste1",
                "https://testedeimage.com.br");
        when(service.update(1L, request)).thenReturn(new CategoryResponseDTO(1L,request.name(),request.imgCategoryUrl()));
        mockMvc.perform(
                        put("/category/{id}",1L).content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.imgCategoryUrl").value(request.imgCategoryUrl()));
    }
    @Test
    void updateCategory_WithInvalidData_ReturnThrowException() throws Exception {
        CategoryRequestDTO invalidRequest = new CategoryRequestDTO(null, null);
        CategoryRequestDTO emptyRequest = new CategoryRequestDTO("", "");
        mockMvc.perform(
                        put("/category/{id}",1L).content(objectMapper.writeValueAsString(emptyRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        mockMvc.perform(
                        put("/category/{id}",1L).content(objectMapper.writeValueAsString(invalidRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void getCategory_ReturnOk() throws Exception {
        Category category = new Category("Teste1","https://testedeimage.com.br");
        when(service.findOne(1L)).thenReturn(new CategoryResponseDTO(1L, category.getName(), category.getImgCategoryUrl()));
        mockMvc.perform(
                        get("/category/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(category.getName()))
                .andExpect(jsonPath("$.imgCategoryUrl").value(category.getImgCategoryUrl()));
    }
    @Test
    void getAllCategory_ReturnOk() throws Exception {
        List<Category> list = List.of(new Category("Teste1","https://testedeimage.com.br"));
        when(service.findAll()).thenReturn(List.of(new CategoryResponseDTO(1L, "Teste1","https://testedeimage.com.br")));
        mockMvc.perform(
                        get("/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value(list.get(0).getName()))
                .andExpect(jsonPath("$[0].imgCategoryUrl").value(list.get(0).getImgCategoryUrl()));
    }

}