package estudo.java.api.service;

import estudo.java.api.domain.entities.Medico;
import estudo.java.api.domain.entities.Pessoa;
import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.response.MedicoResponse;
import estudo.java.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.micrometer.common.util.StringUtils.isNotBlank;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final PessoaService pessoaService;

    public List<MedicoResponse> getAllMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream().map(medico -> MedicoResponse.MedicoResponseBuilder()
                        .id(medico.getId())
                        .nome(medico.getPessoa().getNome())
                        .cpf(medico.getPessoa().getCpf())
                        .crm(medico.getCrm())
                        .especialidade(medico.getEspecialidade())
                        .build())
                .collect(Collectors.toList());
    }

    public MedicoResponse getMedicoById(UUID id) {
        Medico medico = medicoRepository.findById(id).stream().findFirst().orElse(null);
        if (nonNull(medico)) {
            return MedicoResponse.MedicoResponseBuilder()
                    .id(medico.getId())
                    .nome(medico.getPessoa().getNome())
                    .cpf(medico.getPessoa().getCpf())
                    .crm(medico.getCrm())
                    .especialidade(medico.getEspecialidade())
                    .build();
        }
        return null;
    }

    @Transactional
    public MedicoResponse createMedico(MedicoRequest medicoRequest) {
        Pessoa pessoa = Pessoa.builder()
                .nome(medicoRequest.getNome())
                .cpf(medicoRequest.getCpf())
                .dataNascimento(medicoRequest.getDataNascimento())
                .tipo(medicoRequest.getTipo())
                .build();
        pessoaService.createPessoa(pessoa);

        Medico medico = Medico.builder()
                .pessoa(pessoa)
                .crm(medicoRequest.getCrm())
                .especialidade(medicoRequest.getEspecialidade())
                .build();
        medico = medicoRepository.save(medico);

        return MedicoResponse.MedicoResponseBuilder()
                .id(medico.getId())
                .nome(medico.getPessoa().getNome())
                .cpf(medico.getPessoa().getCpf())
                .crm(medico.getCrm())
                .especialidade(medico.getEspecialidade())
                .build();
    }

    @Transactional
    public MedicoResponse updateMedico(UUID id, MedicoRequest medicoRequest){
        Optional<Medico> optionalMedico = medicoRepository.findById(id).stream().findFirst();
        if(optionalMedico.isPresent()){
            Medico medico = optionalMedico.get();
            Pessoa pessoa = medico.getPessoa();
            pessoa.setNome(medicoRequest.getNome());
            pessoa.setDataNascimento(medicoRequest.getDataNascimento());
            pessoa.setTipo(medicoRequest.getTipo());

            pessoaService.updatePessoa(medico.getPessoa().getId(),pessoa);

            medico.setEspecialidade(getFieldString(medicoRequest.getEspecialidade(),medico.getEspecialidade()));
            medico.setCrm(getFieldString(medicoRequest.getCrm(),medico.getCrm()));

            medicoRepository.save(medico);

            return MedicoResponse.MedicoResponseBuilder()
                    .id(medico.getId())
                    .nome(medico.getPessoa().getNome())
                    .cpf(medico.getPessoa().getCpf())
                    .crm(medico.getCrm())
                    .especialidade(medico.getEspecialidade())
                    .build();
        }
        return MedicoResponse.MedicoResponseBuilder().build();
    }

    private static String getFieldString(String stringRequest, String stringField){
        return isNotBlank(stringRequest) ? stringRequest : stringField;
    }

    public void deleteMedico(UUID id){medicoRepository.deleteById(id);}
}
