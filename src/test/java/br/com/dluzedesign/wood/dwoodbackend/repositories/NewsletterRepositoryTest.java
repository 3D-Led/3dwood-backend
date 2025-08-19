package br.com.dluzedesign.wood.dwoodbackend.repositories;

import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
class NewsletterRepositoryTest {
    @Autowired
    private NewsletterRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void createNewsletters_WithValidData_ReturnsNewsletter() {
        Newsletter newsletter = repository.save(new Newsletter(
                null,
                "joao",
                "joao@joao.com.br"));

        Newsletter sut = testEntityManager.find(Newsletter.class, newsletter.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(newsletter.getName());
        assertThat(sut.getEmail()).isEqualTo(newsletter.getEmail());
    }
    @Test
    void createNewsletters_WithInvalidData() {
        Newsletter emptyData = new Newsletter();
        Newsletter invalidData = new Newsletter(null, "","");

       assertThatThrownBy(()->repository.save(invalidData)).isInstanceOf(RuntimeException.class);
       assertThatThrownBy(()->repository.save(emptyData)).isInstanceOf(RuntimeException.class);
    }
    @Test
    void createNewsletters_WithExistsData_ThrowsExceptions() {
        Newsletter newsletter = testEntityManager.persistFlushFind(new Newsletter(
                null,
                "joao",
                "joao@joao.com.br"));
        testEntityManager.detach(newsletter);
        newsletter.setId(null);

        assertThatThrownBy(()->repository.save(newsletter)).isInstanceOf(RuntimeException.class);
    }
}