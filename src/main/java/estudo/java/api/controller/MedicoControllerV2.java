package estudo.java.api.controller;

import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.response.MedicoResponse;
import estudo.java.api.domain.response.MedicoResponse;
import estudo.java.api.domain.validators.GrupoValidacao;
import estudo.java.api.service.MedicoServiceV2;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medicov2")
public class MedicoControllerV2 {

    @Autowired
    private MedicoServiceV2 medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos(){
        List<MedicoResponse> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoResponseById(@PathVariable UUID id) {
        MedicoResponse medicoResponse = medicoService.getMedicoById(id);
        if (medicoResponse != null) {
            return ResponseEntity.ok(medicoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> createMedico(@RequestBody @Valid MedicoRequest medicoRequest) {
        return ResponseEntity.ok(medicoService.createMedico(medicoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable UUID id, @RequestBody @Validated(GrupoValidacao.Atualizar.class) MedicoRequest medicoRequest) {
        return ResponseEntity.ok(medicoService.updateMedico(id, medicoRequest));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable UUID id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.ok().build();
    }

}
