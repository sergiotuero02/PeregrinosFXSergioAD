package com.example.PeregrinosFX.bean;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class EnvioACasa {
    @Id
    @GeneratedValue
    public long id;
    public double peso;
    public int[] volumen;
    public boolean urgente = false;
    @Embedded
    public Direccion direccion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int[] getVolumen() {
        return volumen;
    }

    public void setVolumen(int[] volumen) {
        this.volumen = volumen;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }
}
