package com.example.PeregrinosFX.repository;

import com.db4o.ObjectSet;
import com.example.PeregrinosFX.bean.Servicio;

import java.util.ArrayList;

import static com.example.PeregrinosFX.Connections.Db4oConnect.db;

public class ServicioRepository
{
    public static ObjectSet<Servicio> buscarServicios(){
        Servicio servicio = new Servicio();
        return db.queryByExample(servicio);
    }
}
