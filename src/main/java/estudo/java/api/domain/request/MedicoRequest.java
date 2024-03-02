package estudo.java.api.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequest extends PessoaRequest{
    @Schema(description = "CRM do médico", example = "2322-AC")
    @NotBlank(message = "CRM não pode ser vazio")
    private String crm;

    @Schema(description = "Especialidade do médico", example = "Pediatria")
    @NotBlank(message = "ESPECIALIDADE não pode ser vazio")
    private String especialidade;
}
