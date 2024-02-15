package estudo.java.api.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    private UUID id;
    @NotBlank
    private String prontuario;
    private String historicoMedico;
    private PessoaRequest pessoa;
}
