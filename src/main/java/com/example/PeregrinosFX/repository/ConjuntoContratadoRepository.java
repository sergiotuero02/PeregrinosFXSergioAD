package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.ConjuntoContratado;

import static com.example.PeregrinosFX.Connections.Db4oConnect.db;

public class ConjuntoContratadoRepository {
    public static void guardarConjunto(ConjuntoContratado cc){
        db.store(cc);
    }
}
