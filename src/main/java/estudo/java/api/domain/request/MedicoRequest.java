package estudo.java.api.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequest extends PessoaRequest{
    @NotBlank(message = "CRM não pode ser vazio")
    private String crm;
    @NotBlank(message = "ESPECIALIDADE não pode ser vazio")
    private String especialidade;
}
