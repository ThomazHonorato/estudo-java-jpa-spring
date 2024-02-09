package estudo.java.api.domain.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
    private UUID id;
    private String nome;
    private String cpf;
    private String historicoMedico;

}
