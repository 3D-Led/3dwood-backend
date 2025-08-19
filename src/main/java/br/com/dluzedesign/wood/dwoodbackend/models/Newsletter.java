package br.com.dluzedesign.wood.dwoodbackend.models;

import br.com.dluzedesign.wood.dwoodbackend.dtos.NewsLetterRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WOOD_LEEDS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(unique = true)
    private String email;


    public Newsletter(NewsLetterRequestDTO newsletter) {
        this.name = newsletter.name();
        this.email = newsletter.email();
    }
}
