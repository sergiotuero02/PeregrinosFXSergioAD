package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.repository.EstanciaRepository;
import com.example.PeregrinosFX.service.EstanciaService;
import com.example.PeregrinosFX.service.PeregrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstanciaServiceImpl implements EstanciaService {


    @Autowired
    private EstanciaRepository estanciaRepository;

    @Autowired
    private EstanciaService estanciaService;

    @Autowired
    private PeregrinoService peregrinoService;


    public void alojarse(Peregrino peregrinoCB, Parada paradaCB) {
            List<Parada> paradasPeregrino = peregrinoCB.getParadas();
            paradasPeregrino.add(paradaCB);
            peregrinoCB.setParadas(paradasPeregrino);
            peregrinoService.addPeregrino(peregrinoCB);
            peregrinoCB.getCarnet().setDistancia(peregrinoCB.getCarnet().getDistancia()+100);

    }

    @Override
    public Estancia save(Estancia entity) {
        return null;
    }

    @Override
    public Estancia update(Estancia entity) {
        return null;
    }

    @Override
    public void delete(Estancia entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Estancia> entities) {

    }

    @Override
    public Estancia find(Long id) {
        return null;
    }

    @Override
    public List<Estancia> findAll() {
        return estanciaRepository.findAll();
    }

    @Override
    public Estancia addEstancia(Estancia estancia) {
        return estanciaRepository.save(estancia);
    }

    @Override
    public ArrayList<Estancia> findByParada(Parada parada) {
        return estanciaRepository.findByParada(parada);
    }

}
