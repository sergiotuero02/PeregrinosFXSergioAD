package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.ParadaRepository;
import com.example.PeregrinosFX.service.EstanciaService;
import com.example.PeregrinosFX.service.ParadaService;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ParadaServiceImpl implements ParadaService {
    @Autowired
    private ParadaRepository paradaRepository;

    @Autowired
    private EstanciaService estanciaService;

    /*
     * Método que recibe como parámetros dos fechas y una parada y devuelve una lista de estancias
     * @return una List de estancias
     * */
    public List<Estancia> datosParada(LocalDate fechaInicial, LocalDate fechaFinal, Parada paradaCB) {

//Creamos la lista de estancias que después cargaremos
        List<Estancia> estanciasBD = null;

        //Comprobamos que las fechas sean válidas
        if (fechaInicial.isEqual(fechaFinal)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fechas incorrectas");
            alert.setHeaderText(null);
            alert.setContentText("Las fechas son iguales.");
            alert.showAndWait();
        }

        if (fechaInicial.isAfter(fechaFinal)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fechas incorrectas");
            alert.setHeaderText(null);
            alert.setContentText("La fecha final es antes que la inicial.");
            alert.showAndWait();

        }

        //Si las fechas son correctas llamamos al método previamente creado que busca todas las estancias para una parada concreta
        if (fechaInicial.isBefore(fechaFinal)) {
            estanciasBD = estanciaService.findByParada(paradaCB);

        }
        return estanciasBD;

    }



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
