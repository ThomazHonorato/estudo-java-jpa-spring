package estudo.java.api.controller;

import estudo.java.api.domain.request.PacienteRequest;
import estudo.java.api.domain.response.PacienteResponse;
import estudo.java.api.domain.validators.GrupoValidacao;
import estudo.java.api.service.PacienteServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pacienteV2")
public class PacienteControllerV2 {

    @Autowired
    private PacienteServiceV2 pacienteService;


    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getAllPacientes() {
        List<PacienteResponse> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteResponseById(@PathVariable UUID id) {
        PacienteResponse pacienteResponse = pacienteService.getPacienteById(id);
        if (pacienteResponse != null) {
            return ResponseEntity.ok(pacienteResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> createPaciente(@RequestBody @Validated(GrupoValidacao.Inserir.class) PacienteRequest pacienteRequest) {
        return ResponseEntity.ok(pacienteService.createPaciente(pacienteRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable UUID id, @RequestBody @Validated(GrupoValidacao.Atualizar.class) PacienteRequest pacienteRequest) {
        return ResponseEntity.ok(pacienteService.updatePaciente(id, pacienteRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable UUID id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok().build();
    }
}