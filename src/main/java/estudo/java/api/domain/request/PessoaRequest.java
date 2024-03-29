package estudo.java.api.domain.request;

import estudo.java.api.domain.validators.GrupoValidacao;
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
    @NotBlank(message = "O Nome não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String nome;
    @NotBlank(message="CPF não pode ser vazio", groups = {GrupoValidacao.Inserir.class})
    private String cpf;
    @NotNull(message = "ERRO data vazia", groups = GrupoValidacao.Inserir.class)
    private LocalDateTime dataNascimento;
    @NotBlank(message = "Tipo não pode ser vazio", groups = GrupoValidacao.Inserir.class)
    private String tipo;
}
