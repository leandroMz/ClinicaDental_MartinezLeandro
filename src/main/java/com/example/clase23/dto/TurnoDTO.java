package com.example.clase23.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Long pacienteDto;
    private Long odontologoDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteDto() {
        return pacienteDto;
    }

    public void setPacienteDto(Long pacienteDto) {
        this.pacienteDto = pacienteDto;
    }

    public Long getOdontologoDto() {
        return odontologoDto;
    }

    public void setOdontologoDto(Long odontologoDto) {
        this.odontologoDto = odontologoDto;
    }
}
