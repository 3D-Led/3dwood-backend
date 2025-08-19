package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.dtos.response.BannerResponseDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.Banner;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import br.com.dluzedesign.wood.dwoodbackend.repositories.BannerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BannerServiceTest {
    @InjectMocks
    private BannerService service;

    @Mock
    private BannerRepository repository;

    @Test
    void getAllByType_WithValidData_ReturnListBannerResponseDTO() {
        BannerType type = BannerType.BANNER1;
        List<Banner> list = List.of(
                new Banner(1L,"https://testedeimage.com.br",BannerType.BANNER1));
        when(repository.existsByType(type)).thenReturn(true);
        when(repository.findAll()).thenReturn(list);

        var sut = service.getAllByType(type);
        assertEquals(1, sut.size());
        assertEquals(list.get(0).getId(), sut.get(0).id());
        assertEquals(list.get(0).getImgUrl(), sut.get(0).imgUrl());
        assertEquals(list.get(0).getType(), sut.get(0).type());
    }
    @Test
    void getAllByType_WithInvalidData_ThrowException() {
        BannerType type = BannerType.BANNER1;
        when(repository.existsByType(type)).thenReturn(false);

        assertThatThrownBy(()-> service.getAllByType(type)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void insetBanner_WithValidData_ReturnBannerResponseDTO() {
        BannerRequestDTO request =  new BannerRequestDTO(null,"https://testedeimage.com.br",BannerType.CARROUSEL);
        Banner banner =  new Banner(null,"https://testedeimage.com.br",BannerType.CARROUSEL);
        when(repository.save(banner)).thenReturn(banner);

        BannerResponseDTO sut = service.insert(request);
        assertThat(sut.id()).isEqualTo(banner.getId());
        assertThat(sut.imgUrl()).isEqualTo(banner.getImgUrl());
        assertThat(sut.type()).isEqualTo(banner.getType());
    }
    @Test
    void insetBanner_WithInvalidData_ReturnThrowException() {
        BannerRequestDTO invalidIdRequest =  new BannerRequestDTO(2L,"https://testedeimage.com.br",BannerType.CARROUSEL);
        BannerRequestDTO invalidTypeRequest =  new BannerRequestDTO(null,"https://testedeimage.com.br",BannerType.BANNER2);

        assertThatThrownBy(()-> service.insert(invalidIdRequest)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()-> service.insert(invalidTypeRequest)).isInstanceOf(IllegalArgumentException.class);
    }


}