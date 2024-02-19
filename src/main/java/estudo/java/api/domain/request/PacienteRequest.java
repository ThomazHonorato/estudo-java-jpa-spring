package estudo.java.api.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    //@NotBlank(message = "Prontuario não pode ser vazio")
    private String prontuario;
    //@NotNull(message = "")
    private String historicoMedico;
    private @Valid PessoaRequest pessoa;
}
