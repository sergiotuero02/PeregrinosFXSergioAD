package com.example.PeregrinosFX.bean;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Servicio implements Serializable {

    @Serial
    private static final long serialVersionUID = -3114560015446897422L;

    public static final ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "peregrinos.db4o");
    private String nombre;
    private double precio;
    private ArrayList<Parada> paradas;

    public Servicio(){

    }

    public ArrayList<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(ArrayList<Parada> paradas) {
        this.paradas = paradas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return
               nombre + " " + precio +
                "€ " + paradas;
    }
}
