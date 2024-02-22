package estudo.java.api.service;

import estudo.java.api.domain.entities.Medico;
import estudo.java.api.domain.mappers.MedicoMapper;
import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.response.MedicoResponse;
import estudo.java.api.exceptions.MedicoNotFoundException;
import estudo.java.api.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoServiceV2 {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;
    public List<MedicoResponse> getAllMedicos(){
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream().map(medicoMapper::toResponse).toList();
    }

    public MedicoResponse getMedicoById(UUID id) {
        return medicoMapper.toResponse(getMedico(id));
    }

    public MedicoResponse createMedico(MedicoRequest medicoRequest) {
        return medicoMapper.toResponse(medicoRepository.save(medicoMapper.toEntity(medicoRequest)));
    }

    public MedicoResponse updateMedico(UUID id, MedicoRequest medicoRequest) {
        Medico medico = getMedico(id);
        medicoMapper.toUpdateEntity(medicoRequest, medico);
        return medicoMapper.toResponse(medicoRepository.save(medico));
    }

    private Medico getMedico(UUID id) {
        return medicoRepository.findById(id)
                .orElseThrow(MedicoNotFoundException::new);
    }

    public void deleteMedico(UUID id) {
        medicoRepository.deleteById(id);
    }
}
