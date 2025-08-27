package br.com.dluzedesign.wood.dwoodbackend.models;

import br.com.dluzedesign.wood.dwoodbackend.models.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_USERS")
@Data
@EqualsAndHashCode
@ToString
public class User implements UserDetails {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ToString.Include
    private String email;

    @ToString.Include
    private String password;

    private UserRoles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role ==UserRoles.ADMIN)return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
        return email;
    }


}
