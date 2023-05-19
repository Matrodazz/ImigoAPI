package imigo.api.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import imigo.api.models.Cliente;
import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByNomeContaining(String busca, Pageable pageable);
}