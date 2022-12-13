package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.ParadaRepository;
import com.example.PeregrinosFX.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaServiceImpl implements ParadaService {
    @Autowired
    private ParadaRepository paradaRepository;

    @Override
    public Parada save(Parada entity) {
        return null;
    }

    @Override
    public Parada update(Parada entity) {
        return null;
    }

    @Override
    public void delete(Parada entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Parada> entities) {

    }

    @Override
    public Parada find(Long id) {
        return null;
    }

    @Override
    public Parada findByIdParada(Long id) {
        return paradaRepository.findByIdParada(id);
    }

    @Override
    public List<Parada> findAll() {
        return paradaRepository.findAll();
    }

    @Override
    public Parada addParada(Parada parada) {
        return null;
    }

}
