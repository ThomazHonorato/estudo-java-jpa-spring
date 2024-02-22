package estudo.java.api.domain.request;

import estudo.java.api.domain.validators.GrupoValidacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    @NotBlank(message = "Prontuario não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String prontuario;
    @NotNull(message = "Historico medico não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String historicoMedico;
    private @Valid PessoaRequest pessoa;
}
