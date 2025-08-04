package br.com.dluzedesign.wood.dwoodbackend.repositories;

import br.com.dluzedesign.wood.dwoodbackend.models.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
    boolean existsByEmail(String email);
}
