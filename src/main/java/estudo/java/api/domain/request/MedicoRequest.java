package estudo.java.api.domain.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequest extends PessoaRequest{
    private String nome;
    private String crm;
    private String especialidade;
}
