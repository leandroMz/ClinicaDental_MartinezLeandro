package com.example.clase23.service;

import com.example.clase23.entity.Paciente;
import com.example.clase23.exception.ResourceNotFoundException;
import com.example.clase23.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    public Optional<Paciente> buscarPacientePorCorreo(String email){
        return pacienteRepository.findByEmail(email);
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    //public Optional<Paciente> buscarPaciente(Long id){return pacienteRepository.findById(id);}
    public List<Paciente> buscarTodosPacientes(){
        return pacienteRepository.findAll();
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id).isPresent()){
            pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("no se pudo eliminar el paciente");
        }
    }
    public Optional<Paciente> buscarPacientePorId(Long id){
        return pacienteRepository.findById(id);
    }
    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
}
