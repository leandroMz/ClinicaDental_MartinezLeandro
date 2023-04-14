package com.example.clase23.controlller;

import com.example.clase23.dto.TurnoDTO;
import com.example.clase23.entity.Odontologo;
import com.example.clase23.entity.Paciente;
import com.example.clase23.entity.Turno;
import com.example.clase23.exception.BadRequest;
import com.example.clase23.exception.ResourceNotFoundException;
import com.example.clase23.service.OdontologoService;
import com.example.clase23.service.PacienteService;
import com.example.clase23.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping("alta")
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnodto){
        ResponseEntity<TurnoDTO> response;
        Optional<Paciente>pacienteBuscado=pacienteService.buscarPacientePorId(turnodto.getPacienteDto());
        Optional<Odontologo>odontologoBuscado=odontologoService.buscarOdontologoPorId(turnodto.getOdontologoDto());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(turnoService.guardarTurno(turnodto));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        ResponseEntity<?> response = null;
        if (turnoBuscado.isPresent()) {
            response = ResponseEntity.ok(turnoBuscado.get());
        } else {
            response = ResponseEntity.badRequest().body("no se encontro");
        }
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("turno eliminado");
    }


    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turnodto) {
        Optional<?> turnoBuscado = turnoService.buscarTurno(turnodto.getId());

        if (turnoBuscado.isPresent()) {
            if (odontologoService.buscarOdontologoPorId(turnodto.getOdontologoDto()).isPresent()
                    && pacienteService.buscarPacientePorId(turnodto.getPacienteDto()).isPresent()) {
                turnoService.actualizarTurno(turnodto);
                return ResponseEntity.ok("turno actualizado");
            } else {
                return ResponseEntity.badRequest().body("no se puede actualizar el turno");
            }
        } else {
            return ResponseEntity.badRequest().body("turno no encontrado para modificar" + turnodto.getId());
        }
    }


}
