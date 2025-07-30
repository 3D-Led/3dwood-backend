package br.com.dluzedesign.wood.dwoodbackend.repositories;

import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends JpaRepository<Product,Long> {
}
