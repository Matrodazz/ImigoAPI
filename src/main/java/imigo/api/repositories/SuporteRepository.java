package imigo.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import imigo.api.models.Suporte;
import java.util.List;
import java.util.Optional;


public interface SuporteRepository extends JpaRepository<Suporte, Long> {
    Optional<Suporte> findById(Long id);
    Page<Suporte> findByDescricaoContaining(String descricao, Pageable pageable);
}