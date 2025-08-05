package br.com.dluzedesign.wood.dwoodbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
@Entity
@Table(name = "WOOD_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    private String name;
    private Long sku;
    private Long ean;
    @Column(name = "size", columnDefinition = "TEXT")
    private String size;
    private String imgUrl;
    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> listImgs;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private Set<Category> categories;

}
