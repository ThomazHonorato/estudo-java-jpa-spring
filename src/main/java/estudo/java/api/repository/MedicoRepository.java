package estudo.java.api.repository;

import estudo.java.api.domain.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {
}
