package estudo.java.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequest extends PessoaRequest{
    private String crm;
    private String especialidade;
}
