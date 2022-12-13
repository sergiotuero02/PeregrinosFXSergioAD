package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.EstanciaRepository;
import com.example.PeregrinosFX.service.EstanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstanciaServiceImpl implements EstanciaService {


    @Autowired
    private EstanciaRepository estanciaRepository;

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
        return null;
    }

    @Override
    public ArrayList<Estancia> findByParada(Parada parada) {
        return estanciaRepository.findByParada(parada);
    }

}
