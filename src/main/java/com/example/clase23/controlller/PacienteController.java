package com.example.clase23.controlller;

import com.example.clase23.entity.Paciente;
import com.example.clase23.exception.ResourceNotFoundException;
import com.example.clase23.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
   @PostMapping("/alta")
    public ResponseEntity<?> registrarPaciente(@RequestBody Paciente paciente){
    Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorCorreo(paciente.getEmail());
    ResponseEntity<?> response = null;
    if (pacienteBuscado.isPresent()){
        response = ResponseEntity.badRequest().body("el correo ya posee paciente registrado");
    }else {
        response = ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    return response;
    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<?> pacienteBuscado = pacienteService.buscarPacientePorId(paciente.getId());
        ResponseEntity<?> response = null;
        if (pacienteBuscado.isPresent()){
            response = ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        }else{
            response = ResponseEntity.badRequest().body("no se pudo actualizar");
        }
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(id);
        ResponseEntity<?> response = null;
        if(pacienteBuscado.isPresent()){
            response = ResponseEntity.ok(pacienteBuscado.get());
        }else{
            response = ResponseEntity.badRequest().body("paciente no encontrado");
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacientePorId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("se elimino el paciente"+id);
        }
        return null;
    }
    @GetMapping
    public List<Paciente> buscarTodosPacientes(){
            return pacienteService.buscarTodosPacientes();
    }
    @GetMapping("/buscar/{email}")
    public ResponseEntity<?> buscarPacientePorCorreo(@PathVariable String email){
        Optional<Paciente> pacienteXMail = pacienteService.buscarPacientePorCorreo(email);
        ResponseEntity<?> response = null;
        if (pacienteXMail.isPresent()){
            response = ResponseEntity.ok(pacienteXMail.get());
        }else{
            response = ResponseEntity.badRequest().body("usuario de email"+email+ " no encontrado");
        }
        return response;
    }


}
