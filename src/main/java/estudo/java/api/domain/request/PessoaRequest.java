package estudo.java.api.domain.request;

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
    private String nome;
    private String cpf;
    private LocalDateTime dataNascimento;
    private String tipo;
}
