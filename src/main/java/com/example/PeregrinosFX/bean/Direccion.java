package com.example.PeregrinosFX.bean;

import jakarta.persistence.Embeddable;

@Embeddable
public class Direccion {

    public String direccion;
    public String localidad;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
