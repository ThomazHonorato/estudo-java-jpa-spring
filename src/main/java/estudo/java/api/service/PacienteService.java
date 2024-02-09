package estudo.java.api.service;

import estudo.java.api.domain.entities.Paciente;
import estudo.java.api.domain.entities.Pessoa;
import estudo.java.api.domain.request.PacienteRequest;
import estudo.java.api.domain.request.PessoaRequest;
import estudo.java.api.domain.response.PacienteResponse;
import estudo.java.api.repository.PacienteRepository;
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
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PessoaService pessoaService;


    //Metodo para listar todos os pacientes
    public List<PacienteResponse> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(paciente -> PacienteResponse.builder()
                        .id(paciente.getId())
                        .nome(paciente.getPessoa().getNome())
                        .cpf(paciente.getPessoa().getCpf())
                        .historicoMedico(paciente.getHistoricoMedico())
                        .build())
                .collect(Collectors.toList());
    }

    //Metodo para resgatar um paciente pelo seu ID
    public PacienteResponse getPacienteById(UUID id) {
        Paciente paciente = pacienteRepository.findById(id).stream().findFirst().orElse(null);

        if (nonNull(paciente)) {
            return PacienteResponse.builder()
                    .id(paciente.getId())
                    .nome(paciente.getPessoa().getNome())
                    .cpf(paciente.getPessoa().getCpf())
                    .historicoMedico(paciente.getHistoricoMedico())
                    .build();
        }
        return PacienteResponse.builder().build();
    }

    //Metodo para criar um paciente extendido de pessoa
    @Transactional
    public PacienteResponse createPaciente(PacienteRequest pacienteRequest) {
        PessoaRequest pessoaRequest = pacienteRequest.getPessoa();
        Pessoa pessoa = Pessoa.builder()
                .nome(pessoaRequest.getNome())
                .cpf(pessoaRequest.getCpf())
                .dataNascimento(pessoaRequest.getDataNascimento())
                .tipo(pessoaRequest.getTipo())
                .build();
        pessoa = pessoaService.createPessoa(pessoa);

        Paciente paciente = Paciente.builder()
                .historicoMedico(pacienteRequest.getHistoricoMedico())
                .prontuario(pacienteRequest.getProntuario())
                .pessoa(pessoa)
                .build();

        paciente = pacienteRepository.save(paciente);

        return PacienteResponse.builder()
                .id(paciente.getId())
                .nome(paciente.getPessoa().getNome())
                .cpf(pessoa.getCpf())
                .historicoMedico(paciente.getHistoricoMedico())
                .build();

    }

    //Metodo para fazer update em um paciente.
    /*public Paciente updatePaciente(UUID id, PacienteRequest pacienteRequest) {
        Paciente pacienteId = getPacienteById(id);
        if (pacienteId != null) {
            pacienteRequest.setId(pacienteId.getId());
        }
        return pacienteRepository.save(paciente);
    }*/

    @Transactional
    public PacienteResponse updatePaciente(UUID id, PacienteRequest pacienteRequest) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id).stream().findFirst();

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            PessoaRequest pessoaRequest = pacienteRequest.getPessoa();
            Pessoa pessoa = paciente.getPessoa();
            pessoa.setNome(getFieldString(pessoaRequest.getNome(), paciente.getPessoa().getNome()));
            pessoa.setDataNascimento(pessoaRequest.getDataNascimento());
            pessoa.setTipo(getFieldString(pessoaRequest.getTipo(), paciente.getPessoa().getTipo()));

            pessoaService.updatePessoa(paciente.getPessoa().getId(), pessoa);

            paciente.setHistoricoMedico(getFieldString(pacienteRequest.getHistoricoMedico(), paciente.getHistoricoMedico()));
            paciente.setProntuario(getFieldString(pacienteRequest.getProntuario(), paciente.getProntuario()));

            pacienteRepository.save(paciente);

            return PacienteResponse.builder()
                    .id(paciente.getId())
                    .nome(paciente.getPessoa().getNome())
                    .cpf(paciente.getPessoa().getCpf())
                    .historicoMedico(paciente.getHistoricoMedico())
                    .build();
        }
        return PacienteResponse.builder().build();

    }

    private static String getFieldString(String stringRequest, String stringField) {
        return isNotBlank(stringRequest) ?
                stringRequest :
                stringField;
    }

    public void deletePaciente(UUID id) {
        pacienteRepository.deleteById(id);
    }
}
