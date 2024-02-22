package estudo.java.api.domain.mappers;

import estudo.java.api.domain.entities.Medico;
import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.response.MedicoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    @Mapping(target = "pessoa.nome", source = "nome")
    @Mapping(target = "pessoa.cpf", source = "cpf")
    @Mapping(target = "pessoa.dataNascimento", source = "dataNascimento")
    @Mapping(target = "pessoa.tipo", source = "tipo")
    Medico toEntity(MedicoRequest medicoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa.id", ignore = true)
    @Mapping(target = "consulta", ignore = true)
    default void toUpdateEntity(MedicoRequest medicoRequest, @MappingTarget Medico medico) {
        if (medicoRequest.getCpf() != null) {
            medico.getPessoa().setCpf(medicoRequest.getCpf());
        }

        if (medicoRequest.getNome() != null && medico.getPessoa() != null) {
            medico.getPessoa().setNome(medicoRequest.getNome());
        }

        if (medicoRequest.getEspecialidade() != null && medico.getPessoa() != null){
            medico.setEspecialidade(medicoRequest.getEspecialidade());
        }
    }

    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "cpf", source = "pessoa.cpf")
    MedicoResponse toResponse(Medico medico);
}
