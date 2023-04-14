package com.example.clase23.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fechaTurno;
    @ManyToOne
    @JoinColumn(name="paciente_id", referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;



    public Turno(LocalDate fechaTurno, Paciente paciente, Odontologo odontologo) {
        this.fechaTurno = fechaTurno;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno(Long id, LocalDate fechaTurno, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fechaTurno = fechaTurno;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
