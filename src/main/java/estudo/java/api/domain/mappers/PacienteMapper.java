package estudo.java.api.domain.mappers;

import estudo.java.api.domain.entities.Paciente;
import estudo.java.api.domain.request.PacienteRequest;
import estudo.java.api.domain.response.PacienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    Paciente toEntity(PacienteRequest pacienteRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa.id", ignore = true)
    @Mapping(target = "consulta", ignore = true)
    default void toUpdateEntity(PacienteRequest pacienteRequest, @MappingTarget Paciente paciente) {
        if (pacienteRequest.getPessoa().getCpf() != null && paciente.getPessoa() != null) {
            paciente.getPessoa().setCpf(pacienteRequest.getPessoa().getCpf());
        }
        if (pacienteRequest.getHistoricoMedico() != null && paciente.getPessoa() != null) {
            paciente.setHistoricoMedico(pacienteRequest.getHistoricoMedico());
        }
        if (pacienteRequest.getPessoa().getNome() != null && paciente.getPessoa() != null) {
            paciente.getPessoa().setNome(pacienteRequest.getPessoa().getNome());
        }
    }

    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "cpf", source = "pessoa.cpf")
    PacienteResponse toResponse(Paciente paciente);
}
