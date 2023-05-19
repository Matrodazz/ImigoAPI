package imigo.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import imigo.api.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findByCep(String busca, Pageable pageable);

}