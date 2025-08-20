package br.com.dluzedesign.wood.dwoodbackend.repositories;

import br.com.dluzedesign.wood.dwoodbackend.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void createCategory_WithValidData_ReturnsCategory() {
        Category category = repository.save(new Category(
                "Categoria de teste",
                "https://exemplodeurl.com.br/imagem"));

        Category sut = testEntityManager.find(Category.class, category.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(category.getName());
        assertThat(sut.getImgCategoryUrl()).isEqualTo(category.getImgCategoryUrl());
    }
    @Test
    void createCategory_WithInvalidData_ReturnsThrowException() {
        Category category = new Category("","");
        Category emptyCategory = new Category();

        assertThatThrownBy(()->repository.save(category)).isInstanceOf(Exception.class);
        assertThatThrownBy(()->repository.save(emptyCategory)).isInstanceOf(Exception.class);
    }
    @Test
    void verifyExistingCategory_WithExistsData_ReturnTrue() {
        Category category = repository.save(new Category(
                "Categoria de teste",
                "https://exemplodeurl.com.br/imagem"));
        repository.save(category);
        boolean exists = repository.existsByName(category.getName());
        assertThat(exists).isTrue();
    }
    @Test
    void getAllCategory_WithValidData_ReturnsListCategory() {
        List<Category> list = List.of(new Category(
                "Categoria de teste",
                "https://exemplodeurl.com.br/imagem"),
                new Category(
                        "Categoria de teste1",
                        "https://exemplodeurl.com.br/imagem"),
                new Category(
                        "Categoria de teste2",
                        "https://exemplodeurl.com.br/imagem"));
        repository.saveAll(list);
        List<Category> sut = testEntityManager.
                getEntityManager()
                .createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();

        assertThat(sut).isNotNull();
        assertEquals(3, sut.size());
        assertThat(sut.get(0).getName()).isEqualTo(list.get(0).getName());
        assertThat(sut.get(0).getImgCategoryUrl()).isEqualTo(list.get(0).getImgCategoryUrl());
        assertThat(sut.get(1).getName()).isEqualTo(list.get(1).getName());
        assertThat(sut.get(1).getImgCategoryUrl()).isEqualTo(list.get(1).getImgCategoryUrl());
        assertThat(sut.get(2).getName()).isEqualTo(list.get(2).getName());
        assertThat(sut.get(2).getImgCategoryUrl()).isEqualTo(list.get(2).getImgCategoryUrl());
    }
    @Test
    void getOneCategory_WithValidData_ReturnsCategory() {
        Category category = repository.save( new Category(
                        "Categoria de teste",
                        "https://exemplodeurl.com.br/imagem"));
        Category sut = testEntityManager.find(Category.class, category.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(category.getName());
        assertThat(sut.getImgCategoryUrl()).isEqualTo(category.getImgCategoryUrl());

    }

}