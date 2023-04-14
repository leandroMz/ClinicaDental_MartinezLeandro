package com.example.clase23.entity;

import javax.persistence.*;

@Entity
@Table(name="domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String calle;
    @Column
    private String numeroCalle;
    @Column
    private String localidad;
    @Column
    private String provincia;

    public Domicilio(String calle, String numeroCalle, String localidad, String provincia) {
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio(Long id, String calle, String numeroCalle, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio() {
    }
    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numeroCalle=" + numeroCalle +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
