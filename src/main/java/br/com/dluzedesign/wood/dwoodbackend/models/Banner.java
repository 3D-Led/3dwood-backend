package br.com.dluzedesign.wood.dwoodbackend.models;

import br.com.dluzedesign.wood.dwoodbackend.dtos.request.BannerRequestDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.enums.BannerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WOOD_BANNERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BannerType type;

    public Banner(BannerRequestDTO banner) {
        this.id = banner.id();
        this.imgUrl = banner.imgUrl();
        this.type = banner.type();
    }
}
