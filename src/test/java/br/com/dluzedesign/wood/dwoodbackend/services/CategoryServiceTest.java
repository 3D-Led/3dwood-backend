package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.CategoryRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;

    @Test
    void findAllCategory_WithValidData_ReturnListCategoryResponseDTO() {
        List<Category> list = List.of(
                new Category("Category name Test","https://testedeimage.com.br"));
        when(repository.findAll()).thenReturn(list);

        var sut = service.findAll();
        assertEquals(1, sut.size());
        assertEquals(list.get(0).getId(), sut.get(0).id());
        assertEquals(list.get(0).getName(), sut.get(0).name());
        assertEquals(list.get(0).getImgCategoryUrl(), sut.get(0).imgCategoryUrl());
    }
    @Test
    void findOneCategory_WithValidData_ReturnCategoryResponseDTO() {
        Category category = new Category("Category name Test","https://testedeimage.com.br");
        when(repository.findById(1L)).thenReturn(Optional.of(category));

        var sut = service.findOne(1L);
        assertEquals(category.getId(), sut.id());
        assertEquals(category.getName(), sut.name());
        assertEquals(category.getImgCategoryUrl(), sut.imgCategoryUrl());
    }
    @Test
    void findOneCategory_WithInvalidData_ReturnThrowException() {
        when(repository.findById(1l)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> service.findOne(1L)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void insertCategory_WithValidData_ReturnCategoryResponseDTO() {
        Category category = new Category("Category name Test","https://testedeimage.com.br");
        CategoryRequestDTO request = new CategoryRequestDTO("Category name Test","https://testedeimage.com.br");
        when(repository.existsByName(request.name())).thenReturn(false);
        when(repository.save(category)).thenReturn(category);

        var sut = service.insert(request);
        assertEquals(category.getId(), sut.id());
        assertEquals(category.getName(), sut.name());
        assertEquals(category.getImgCategoryUrl(), sut.imgCategoryUrl());
    }
    @Test
    void insertCategory_WithInvalidData_ReturnThrowException() {
        CategoryRequestDTO request = new CategoryRequestDTO("Category name Test","https://testedeimage.com.br");
        when(repository.existsByName(request.name())).thenReturn(true);

        assertThatThrownBy(()-> service.insert(request)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void updateCategory_WithValidData_ReturnCategoryResponseDTO() {
        Category oldcategory = new Category("Category old name Test","https://testedeimage.com.br");
        CategoryRequestDTO request = new CategoryRequestDTO("Category name Test","https://testedeimage.com.br");
        when(repository.findById(1L)).thenReturn(Optional.of(oldcategory));
        when(repository.save(oldcategory)).thenReturn(oldcategory);

        var sut = service.update(1L, request);
        assertEquals(oldcategory.getId(), sut.id());
        assertEquals(oldcategory.getName(), sut.name());
        assertEquals(oldcategory.getImgCategoryUrl(), sut.imgCategoryUrl());
    }
    @Test
    void updateCategory_WithInvalidData_ReturnThrowException() {
        CategoryRequestDTO category = new CategoryRequestDTO("Category name Test","https://testedeimage.com.br");
        when(repository.findById(1l)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> service.update(1L,category)).isInstanceOf(IllegalArgumentException.class);
    }



}