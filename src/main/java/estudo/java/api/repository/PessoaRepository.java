package estudo.java.api.repository;

import estudo.java.api.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

}
