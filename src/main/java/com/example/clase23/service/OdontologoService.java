package com.example.clase23.service;

import com.example.clase23.entity.Odontologo;
import com.example.clase23.exception.ResourceNotFoundException;
import com.example.clase23.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Odontologo altaOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarPorMatricula(String matricula) {
        return odontologoRepository.findByNumeroMatricula(matricula);
    }
    public List<Odontologo> buscarOdontologos(){
        return odontologoRepository.findAll();
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoEliminar = buscarOdontologoPorId(id);
        if (odontologoEliminar.isPresent()){
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar");
        }
    }
    public Optional<Odontologo> buscarOdontologoPorId(Long id) {
        return odontologoRepository.findById(id);
    }

}
