package estudo.java.api.controller;

import estudo.java.api.domain.request.MedicoRequest;
import estudo.java.api.domain.response.MedicoResponse;
import estudo.java.api.domain.validators.GrupoValidacao;
import estudo.java.api.exceptions.MedicoNotFoundException;
import estudo.java.api.service.MedicoServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "API V2")
@RestController
@RequestMapping("/api/medicov2")
public class MedicoControllerV2 {

    @Autowired
    private MedicoServiceV2 medicoService;

    @Operation(
            summary = "Obtém uma lista de médicos cadastrados",
            description = "Endpoint para listar todos os médicos cadastrados")
    @ApiResponse(
            responseCode = "200",
            description = "Registros encontrados",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedicoResponse.class))
            }
    )
    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos() {
        List<MedicoResponse> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @Operation(
            summary = "Obtém um médico pelo ID",
            description = "Endpoint para buscar um médico cadastrado pelo seu ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Registro encontrados",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedicoResponse.class))
            }
    )
    @ApiResponse(
            responseCode = "404",
            description = "Registro não encontrado",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedicoNotFoundException.class))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoResponseById(@PathVariable UUID id) {
        MedicoResponse medicoResponse = medicoService.getMedicoById(id);
        if (medicoResponse != null) {
            return ResponseEntity.ok(medicoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(
            summary = "Cadastra um médico",
            description = "Endpoint para cadastrar um médico")
    @PostMapping
    public ResponseEntity<MedicoResponse> createMedico(@RequestBody @Valid MedicoRequest medicoRequest) {
        return ResponseEntity.ok(medicoService.createMedico(medicoRequest));
    }

    @Operation(
            summary = "Altera um médico",
            description = "Endpoint para alterar um médico cadastrado")
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable UUID id, @RequestBody @Validated(GrupoValidacao.Atualizar.class) MedicoRequest medicoRequest) {
        return ResponseEntity.ok(medicoService.updateMedico(id, medicoRequest));
    }

    @Operation(
            summary = "Deleta um médico cadastrado",
            description = "Endpoint Deletar um médico cadastrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable UUID id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.ok().build();
    }

}
