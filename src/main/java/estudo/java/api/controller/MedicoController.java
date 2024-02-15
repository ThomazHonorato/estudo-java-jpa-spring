package estudo.java.api.controller;

import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.response.MedicoResponse;
import estudo.java.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos() {
        List<MedicoResponse> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicoResponseById(@PathVariable UUID id) {
        MedicoResponse medicoResponse = medicoService.getMedicoById(id);
        if (medicoResponse != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(medicoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> createMedico(@RequestBody MedicoRequest medicoRequest) {
        MedicoResponse medicoResponse = medicoService.createMedico(medicoRequest);
        if (medicoResponse != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(medicoResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable UUID id, @RequestBody MedicoRequest medicoRequest){
        MedicoResponse medicoResponse = medicoService.updateMedico(id, medicoRequest);
        if(medicoResponse != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicoResponse);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(medicoResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable UUID id){
        medicoService.deleteMedico(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
