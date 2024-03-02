package estudo.java.api.controller;

import estudo.java.api.domain.request.PacienteRequest;
import estudo.java.api.domain.response.PacienteResponse;
import estudo.java.api.domain.validators.GrupoValidacao;
import estudo.java.api.service.PacienteServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Obtém uma lista de pacientes cadastrados",
            description = "Endpoint para listar pacientes cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Busca efetuada com sucessso")
    @ApiResponse(responseCode = "400", description = "Erro ao processar busca")
    @ApiResponse(responseCode = "404", description = "Cadastros não encontrados")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getAllPacientes() {
        List<PacienteResponse> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(
            summary = "Obtém um paciente pelo ID",
            description = "Endpoint para buscar um paciente cadastrado pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteResponseById(@PathVariable UUID id) {
        PacienteResponse pacienteResponse = pacienteService.getPacienteById(id);
        if (pacienteResponse != null) {
            return ResponseEntity.ok(pacienteResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(
            summary = "Cadastra um paciente",
            description = "Endpoint para cadastrar um paciente")
    @PostMapping
    public ResponseEntity<PacienteResponse> createPaciente(@RequestBody @Validated(GrupoValidacao.Inserir.class) PacienteRequest pacienteRequest) {
        return ResponseEntity.ok(pacienteService.createPaciente(pacienteRequest));
    }

    @Operation(
            summary = "Altera um paciente cadastrado",
            description = "Endpoint para alterar um paciente cadastrado")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable UUID id, @RequestBody @Validated(GrupoValidacao.Atualizar.class) PacienteRequest pacienteRequest) {
        return ResponseEntity.ok(pacienteService.updatePaciente(id, pacienteRequest));
    }

    @Operation(
            summary = "Deleta um paciente cadastrado",
            description = "Endpoint deletar um paciente cadastrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable UUID id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok().build();
    }
}