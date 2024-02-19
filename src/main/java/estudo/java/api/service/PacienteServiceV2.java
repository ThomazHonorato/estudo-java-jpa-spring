package estudo.java.api.service;

import estudo.java.api.domain.entities.Paciente;
import estudo.java.api.domain.mappers.PacienteMapper;
import estudo.java.api.domain.request.PacienteRequest;
import estudo.java.api.domain.response.PacienteResponse;
import estudo.java.api.exceptions.PacienteNotFoundException;
import estudo.java.api.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteServiceV2 {

    private final PacienteRepository pacienteRepository;
    private final PessoaService pessoaService;

    private final PacienteMapper pacienteMapper;


    //Metodo para listar todos os pacientes
    public List<PacienteResponse> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(pacienteMapper::toResponse).toList();
    }

    //Metodo para resgatar um paciente pelo seu ID
    public PacienteResponse getPacienteById(UUID id) {
        return pacienteMapper.toResponse(getPaciente(id));
    }

    //Metodo para criar um paciente extendido de pessoa
    public PacienteResponse createPaciente(PacienteRequest pacienteRequest) {
        return pacienteMapper.toResponse(pacienteRepository.save(pacienteMapper.toEntity(pacienteRequest)));
    }

    public PacienteResponse updatePaciente(UUID id, PacienteRequest pacienteRequest) {
        Paciente paciente = getPaciente(id);
        pacienteMapper.toUpdateEntity(pacienteRequest, paciente);
        return pacienteMapper.toResponse(pacienteRepository.save(paciente));
    }

    private Paciente getPaciente(UUID id) {
        return pacienteRepository.findById(id)
                .orElseThrow(PacienteNotFoundException::new);
    }

    public void deletePaciente(UUID id) {
        pacienteRepository.deleteById(id);
    }
}