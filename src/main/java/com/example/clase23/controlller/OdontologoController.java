package com.example.clase23.controlller;

import com.example.clase23.entity.Odontologo;
import com.example.clase23.exception.ResourceNotFoundException;
import com.example.clase23.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @PostMapping("/alta")
    public ResponseEntity<?> altaOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(odontologo.getNumeroMatricula());
        ResponseEntity<?> response = null;
        if (odontologoBuscado.isPresent()) {
            response = ResponseEntity.badRequest().body("La matricula ya esta asociada");
        } else {
            response = ResponseEntity.ok(odontologoService.altaOdontologo(odontologo));
        }
        return response;
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<?> odontologoBuscado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        ResponseEntity<?> response = null;
        if (odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } else {
            response = ResponseEntity.badRequest().body("Imposible actualizar el odontologo");
        }
        return response;
    }
    @GetMapping("/buscar/{matricula}")
    public ResponseEntity<?> buscarOdontologoPorEmail(@PathVariable String matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        ResponseEntity<?> response = null;
        if (odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(odontologoBuscado.get());
        } else {
            response = ResponseEntity.badRequest().body("Odontologo no encontrado");
        }
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se elimino el odontologo"+id);
    }

}
