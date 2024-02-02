package estudo.java.api.controller;

import estudo.java.api.domain.entities.Paciente;
import estudo.java.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable UUID id) {
        return ResponseEntity.ok(pacienteService.getPacienteById(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.createPaciente(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable UUID id, @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.updatePaciente(id, paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable UUID id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok().build();
    }
}