package br.com.dluzedesign.wood.dwoodbackend.controllers;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductCategoryResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.ProductResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService service;

    @Test
    void getAllProducts_ReturnOk() throws Exception {
        List<ProductCategoryResponseDTO> list = List.of(
                new ProductCategoryResponseDTO(1L, "Produto Teste", "http://img.com/prod.png")
        );

        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(list.get(0).id()))
                .andExpect(jsonPath("$[0].name").value(list.get(0).name()))
                .andExpect(jsonPath("$[0].imgUrl").value(list.get(0).imgUrl()));
    }

    @Test
    void getProductByCategory_ReturnOk() throws Exception {
        String category = "Categoria Teste";
        List<ProductCategoryResponseDTO> list = List.of(
                new ProductCategoryResponseDTO(1L, "Produto Categoria", "http://img.com/cat.png")
        );

        when(service.getProductByCategory(category)).thenReturn(list);

        mockMvc.perform(get("/products/category")
                        .param("category", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(list.get(0).id()))
                .andExpect(jsonPath("$[0].name").value(list.get(0).name()))
                .andExpect(jsonPath("$[0].imgUrl").value(list.get(0).imgUrl()));
    }

    @Test
    void getProductById_ReturnOk() throws Exception {
        Long id = 1L;
        ProductResponseDTO response = new ProductResponseDTO(
                "Descrição", "Produto Teste", 123L, 456L,
                "M", "http://img.com/prod.png",
                List.of("img1.png", "img2.png"),
                "Categoria Teste"
        );

        when(service.getProductById(id)).thenReturn(response);

        mockMvc.perform(get("/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descriptions").value(response.descriptions()))
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.sku").value(response.sku()))
                .andExpect(jsonPath("$.ean").value(response.ean()))
                .andExpect(jsonPath("$.size").value(response.size()))
                .andExpect(jsonPath("$.imgUrl").value(response.imgUrl()))
                .andExpect(jsonPath("$.categoryName").value(response.categoryName()));
    }

    @Test
    void insertProduct_WithValidData_ReturnCreated() throws Exception {
        ProductRequestDTO request = new ProductRequestDTO(
                "Descrição Insert", "Produto Insert", 111L, 222L,
                "G", "http://img.com/new.png", List.of("img1.png"), "Categoria Teste"
        );

        ProductResponseDTO response = new ProductResponseDTO(
                request.descriptions(),
                request.name(),
                request.sku(),
                request.ean(),
                request.size(),
                request.imgUrl(),
                request.imgs(),
                request.categoryName()
        );

        when(service.insert(any(ProductRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/products")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.sku").value(response.sku()))
                .andExpect(jsonPath("$.categoryName").value(response.categoryName()));
    }

    @Test
    void updateProduct_WithValidData_ReturnOk() throws Exception {
        Long id = 1L;
        ProductRequestDTO request = new ProductRequestDTO(
                "Nova Descrição", "Produto Atualizado", 333L, 444L,
                "P", "http://img.com/update.png", List.of("imgU1.png"), "Categoria Atualizada"
        );

        ProductResponseDTO response = new ProductResponseDTO(
                request.descriptions(),
                request.name(),
                request.sku(),
                request.ean(),
                request.size(),
                request.imgUrl(),
                request.imgs(),
                request.categoryName()
        );

        when(service.update(eq(id), any(ProductRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/products/{id}", id)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.sku").value(response.sku()))
                .andExpect(jsonPath("$.categoryName").value(response.categoryName()));
    }
}
