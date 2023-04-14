package com.example.clase23;
import com.example.clase23.entity.Domicilio;
import com.example.clase23.entity.Odontologo;
import com.example.clase23.entity.Paciente;
import com.example.clase23.entity.Turno;
import com.example.clase23.repository.OdontologoRepository;
import com.example.clase23.repository.PacienteRepository;
import com.example.clase23.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class ClinicaDentalLM {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private OdontologoRepository odontologoRepository;

	@Autowired
	private TurnoRepository turnoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClinicaDentalLM.class, args);
	}

	@PostConstruct
	public void init() {

		Odontologo odontologo1 = new Odontologo();
		odontologo1.setNumeroMatricula("1010");
		odontologo1.setNombre("Jorgito");
		odontologo1.setApellido("Profe");
		odontologoRepository.save(odontologo1);

		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("Junin");
		domicilio.setNumeroCalle("1233");
		domicilio.setLocalidad("Ctes.");
		domicilio.setProvincia("Corrientes");

		Paciente paciente = new Paciente();
		paciente.setApellido("Leandro");
		paciente.setNombre("Martinez");
		paciente.setDocumento("5435435");
		paciente.setFechaIngreso(LocalDate.now());
		paciente.setEmail("leo@gmail.com");
		paciente.setDomicilio(domicilio);
		pacienteRepository.save(paciente);

		Turno turno = new Turno();
		turno.setFechaTurno(LocalDate.now());
		turno.setPaciente(paciente);
		turno.setOdontologo(odontologo1);
		turnoRepository.save(turno);
	}

}