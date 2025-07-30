package br.com.dluzedesign.wood.dwoodbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Table(name = "WOOD_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer sku;
    private Long ean;
    private String tamanho;
    private String imgUrl;
    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
}
