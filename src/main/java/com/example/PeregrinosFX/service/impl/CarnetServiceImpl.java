package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Carnet;
import com.example.PeregrinosFX.bean.User;
import com.example.PeregrinosFX.repository.CarnetRepository;
import com.example.PeregrinosFX.repository.UserRespository;
import com.example.PeregrinosFX.service.CarnetService;
import com.example.PeregrinosFX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarnetServiceImpl implements CarnetService {

    @Autowired
    private CarnetRepository carnetRespository;

    @Override
    public Carnet save(Carnet entity) {
        return null;
    }

    @Override
    public Carnet update(Carnet entity) {
        return null;
    }

    @Override
    public void delete(Carnet entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Carnet> entities) {

    }

    @Override
    public Carnet find(Long id) {
        return null;
    }

    @Override
    public List<Carnet> findAll() {
        return null;
    }

    @Override
    public Carnet addCarnet(Carnet carnet) {
        return carnetRespository.save(carnet);
    }
}
