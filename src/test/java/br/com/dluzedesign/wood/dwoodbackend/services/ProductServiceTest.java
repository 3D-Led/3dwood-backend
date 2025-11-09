package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.ProductRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import br.com.dluzedesign.wood.dwoodbackend.repositories.CategoryRepository;
import br.com.dluzedesign.wood.dwoodbackend.repositories.ProductRespository;
import br.com.dluzedesign.wood.dwoodbackend.services.product.ProductQueryService;
import br.com.dluzedesign.wood.dwoodbackend.services.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService service;
    @InjectMocks
    private ProductQueryService queryService;
    @Mock
    private ProductRespository repository;
    @Mock
    private CategoryRepository categoryRepository;
    @Test
    void getAll_WithValidData_ReturnListProductCategoryResponseDTO() {
        List<Product> products = List.of(
                new Product(1L, "desc test", "Product Test", 123L, 456L,
                        "M", "http://image.com/img.png", List.of("img1", "img2"), Set.of())
        );
        when(repository.findAll()).thenReturn(products);

        var sut = queryService.getAll();
        assertEquals(1, sut.size());
        assertEquals(products.get(0).getId(), sut.get(0).id());
        assertEquals(products.get(0).getName(), sut.get(0).name());
        assertEquals(products.get(0).getImgUrl(), sut.get(0).imgUrl());
    }

    @Test
    void getProductByCategory_WithValidData_ReturnListProductCategoryResponseDTO() {
        String categoryName = "Category Test";
        Category category = new Category(categoryName, "http://img.com");

        List<Product> products = List.of(
                new Product(1L, "desc test", "Product Test", 123L, 456L,
                        "M", "http://image.com/img.png", List.of("img1"), Set.of(category))
        );

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(category));
        when(repository.findByCategoria(categoryName)).thenReturn(products);

        var sut = queryService.getProductByCategory(categoryName);
        assertEquals(1, sut.size());
        assertEquals(products.get(0).getId(), sut.get(0).id());
        assertEquals(products.get(0).getName(), sut.get(0).name());
        assertEquals(products.get(0).getImgUrl(), sut.get(0).imgUrl());
    }

    @Test
    void getProductByCategory_WithInvalidCategory_ThrowsException() {
        when(categoryRepository.findByName("invalid")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> queryService.getProductByCategory("invalid"))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void getProductById_WithValidData_ReturnProductResponseDTO() {
        Category category = new Category("Category Test", "http://img.com");
        Product product = new Product(1L, "desc test", "Product Test", 123L, 456L,
                "M", "http://image.com/img.png", List.of("img1", "img2"), Set.of(category));

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.findFirstCategoryNameByProductId(1L)).thenReturn(category.getName());

        var sut = queryService.getProductById(1L);
        assertEquals(product.getDescription(), sut.descriptions());
        assertEquals(product.getName(), sut.name());
        assertEquals(product.getSku(), sut.sku());
        assertEquals(product.getEan(), sut.ean());
        assertEquals(product.getSize(), sut.size());
        assertEquals(product.getImgUrl(), sut.imgUrl());
        assertEquals(product.getListImgs(), sut.imgs());
        assertEquals(category.getName(), sut.categoryName());
    }

    @Test
    void getProductById_WithInvalidData_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> queryService.getProductById(1L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void insertProduct_WithValidData_ReturnProductResponseDTO() {
        // Arrange
        Category category = new Category("Category Test", "http://img.com");
        ProductRequestDTO request = new ProductRequestDTO(
                "desc test", "Product Test", 123L, 456L,
                "M", "http://image.com/img.png", List.of("img1", "img2"), category.getName()
        );

        when(categoryRepository.findByName(request.categoryName())).thenReturn(Optional.of(category));
        when(repository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        var sut = service.insert(request);

        assertEquals(request.descriptions(), sut.descriptions());
        assertEquals(request.name(), sut.name());
        assertEquals(request.sku(), sut.sku());
        assertEquals(request.ean(), sut.ean());
        assertEquals(request.size(), sut.size());
        assertEquals(request.imgUrl(), sut.imgUrl());
        assertEquals(request.imgs(), sut.imgs());
        assertEquals(category.getName(), sut.categoryName());
    }


    @Test
    void insertProduct_WithInvalidCategory_ThrowsException() {
        ProductRequestDTO request = new ProductRequestDTO(
                "desc test", "Product Test", 123L, 456L,
                "M", "http://image.com/img.png", List.of("img1"), "Invalid"
        );

        when(categoryRepository.findByName(request.categoryName())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.insert(request))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void updateProduct_WithValidData_ReturnProductResponseDTO() {
        Category category = new Category("Category Test", "http://img.com");
        Product oldProduct = new Product(1L, "old desc", "Old Product", 111L, 222L,
                "S", "http://oldimage.com/img.png", List.of("oldImg"), Set.of(category));

        ProductRequestDTO request = new ProductRequestDTO(
                "new desc", "New Product", 333L, 444L,
                "L", "http://newimage.com/img.png", List.of("newImg"), category.getName()
        );

        when(repository.findById(1L)).thenReturn(Optional.of(oldProduct));
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
        when(repository.save(oldProduct)).thenReturn(oldProduct);

        var sut = service.update(1L, request);
        assertEquals(request.descriptions(), sut.descriptions());
        assertEquals(request.name(), sut.name());
        assertEquals(request.sku(), sut.sku());
        assertEquals(request.ean(), sut.ean());
        assertEquals(request.size(), sut.size());
        assertEquals(request.imgUrl(), sut.imgUrl());
        assertEquals(request.imgs(), sut.imgs());
        assertEquals(request.categoryName(), sut.categoryName());
    }

    @Test
    void updateProduct_WithInvalidId_ThrowsException() {
        ProductRequestDTO request = new ProductRequestDTO(
                "desc test", "Product Test", 123L, 456L,
                "M", "http://image.com/img.png", List.of("img1"), "Category Test"
        );

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(1L, request))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
