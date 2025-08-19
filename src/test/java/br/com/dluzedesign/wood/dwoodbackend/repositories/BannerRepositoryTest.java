package br.com.dluzedesign.wood.dwoodbackend.repositories;

import br.com.dluzedesign.wood.dwoodbackend.models.Banner;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType.CARROUSEL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
class BannerRepositoryTest {
    @Autowired
    private BannerRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void createBanner_WithValidData_ReturnsBanner() {
        Banner banner = repository.save(new Banner(
                null,
                "https://exemplodeurl.com.br/imagem",
                CARROUSEL));

        Banner sut = testEntityManager.find(Banner.class, banner.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getImgUrl()).isEqualTo(banner.getImgUrl());
    }
    @Test
    void createBanner_WithInvalidData_ThrownException() {
        Banner banner = new Banner(
                null,
                "https://exemplodeurl.com.br/imagem",
                null);

        assertThatThrownBy(()->repository.save(banner)).isInstanceOf(Exception.class);
    }
    @Test
    void verifyExistingBanner_WithNotExistsData_ReturnFalse() {
         BannerType bannerType = BannerType.BANNER1;
        boolean exists = repository.existsByType(bannerType);
        assertThat(exists).isFalse();
    }
    @Test
    void verifyExistingBanner_WithExistsData_ReturnTrue() {
        BannerType bannerType = BannerType.BANNER1;
        repository.save(new Banner(null,"teste", bannerType));
        boolean exists = repository.existsByType(bannerType);
        assertThat(exists).isTrue();
    }

}