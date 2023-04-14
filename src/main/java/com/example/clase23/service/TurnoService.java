package com.example.clase23.service;


import com.example.clase23.dto.TurnoDTO;
import com.example.clase23.entity.Odontologo;
import com.example.clase23.entity.Paciente;
import com.example.clase23.entity.Turno;
import com.example.clase23.exception.ResourceNotFoundException;
import com.example.clase23.repository.OdontologoRepository;
import com.example.clase23.repository.PacienteRepository;
import com.example.clase23.repository.TurnoRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;

    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }

    public List<TurnoDTO> listarTurnos() {
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno turno : turnosEncontrados) {
            respuesta.add(turnoAturnoDTO(turno));
        }
        return respuesta;
    }

    public TurnoDTO guardarTurno(TurnoDTO turnoDTO){
       Turno turnoGuardado = turnoRepository.save(turnoDTOaTurno(turnoDTO));
       return turnoAturnoDTO(turnoGuardado);
    }

    public Optional<TurnoDTO> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            return Optional.of(turnoAturnoDTO(turnoBuscado.get()));
        }else{
            return Optional.empty();
        }
    }

    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurno(id).isPresent()){
            turnoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("no se pudo eliminar el turno");
        }
    }
    public void actualizarTurno(TurnoDTO turnoDTO){
        turnoRepository.save(turnoDTOaTurno(turnoDTO));
    }

    private TurnoDTO turnoAturnoDTO(Turno turno) {
        TurnoDTO respuesta = new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFechaTurno());
        respuesta.setPacienteDto(turno.getPaciente().getId());
        respuesta.setOdontologoDto(turno.getOdontologo().getId());
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnodto) {
        Turno respuesta = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        odontologo.setId(turnodto.getOdontologoDto());
        paciente.setId(turnodto.getPacienteDto());
        respuesta.setId(turnodto.getId());
        respuesta.setFechaTurno(turnodto.getFecha());
        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        return respuesta;
    }
}
