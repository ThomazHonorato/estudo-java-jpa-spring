package estudo.java.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    private UUID id;
    private String prontuario;
    private String historicoMedico;
    private PessoaRequest pessoa;
}
