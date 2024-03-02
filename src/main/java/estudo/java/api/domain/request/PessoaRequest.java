package estudo.java.api.domain.request;

import estudo.java.api.domain.validators.GrupoValidacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequest {
    @Schema(description = "Nome do cadastro", example = "Thomaz Honorato Soares")
    @NotBlank(message = "O Nome não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String nome;

    @Schema(description = "CPF cadastrado", example = "12345678910")
    @NotBlank(message="CPF não pode ser vazio", groups = {GrupoValidacao.Inserir.class})
    private String cpf;

    @Schema(description = "Data de nascimento", example = "13/04/1991")
    @NotNull(message = "ERRO data vazia", groups = GrupoValidacao.Inserir.class)
    private LocalDateTime dataNascimento;

    @Schema(description = "Tipo de cadastro Médico ou Paciente", example = "Médico")
    @NotBlank(message = "Tipo não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String tipo;
}
