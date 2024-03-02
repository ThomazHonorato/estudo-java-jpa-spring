package estudo.java.api.domain.request;

import estudo.java.api.domain.validators.GrupoValidacao;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Prontuário do paciente", example = "Aqui retornaria uma serie de informações de exames e etc.")
    @NotBlank(message = "Prontuario não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String prontuario;

    @Schema(description = "Histórico do paciente", example = "Aqui retornaria uma serie informações de atendimento")
    @NotNull(message = "Historico medico não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String historicoMedico;
    private @Valid PessoaRequest pessoa;
}
