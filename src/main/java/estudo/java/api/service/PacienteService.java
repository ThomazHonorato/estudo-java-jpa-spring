package estudo.java.api.service;

import estudo.java.api.domain.entities.Paciente;
import estudo.java.api.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente getPacienteById(UUID id) {
        return pacienteRepository.findById(id).stream().findFirst().orElse(null);
    }

    public Paciente createPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(UUID id, Paciente paciente) {
        Paciente pacienteId = getPacienteById(id);
        if(pacienteId != null){
            paciente.setId(pacienteId.getId());
        }
        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(UUID id) {
        pacienteRepository.deleteById(id);
    }
}
