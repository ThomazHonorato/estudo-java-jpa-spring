package estudo.java.api.domain.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(builderMethodName = "MedicoResponseBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class MedicoResponse {
    private UUID id;
    private String crm;
    private String especialidade;
    private String nome;
    private String cpf;
}
