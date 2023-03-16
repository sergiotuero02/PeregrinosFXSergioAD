package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.EnvioRepository;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static com.example.PeregrinosFX.Connections.ObjectDBConnect.em;

@Service
public class EnvioServiceImpl {

    @Autowired
    public EnvioRepository envioRepository;

    public void verEnvios(ListView enviosTA, Parada parada){
        enviosTA.getItems().clear();
        List<EnvioACasa> envios = envioRepository.verEnvios();
        if (envios.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No hay ningún envíos");
            alerta.setContentText("No hay ningún envío para la parada seleccionada");
            alerta.show();
        } else {
            for (EnvioACasa envio : envios) {
                if (envio.getIdParada() == parada.getIdParada()) {
                    double[] volumen = envio.getVolumen();
                    String vol = String.valueOf(volumen[0]) + " " + String.valueOf(volumen[1]) + " " + String.valueOf(volumen[2]);
                    String envioTXT = envio.getId() + " Peso: " + envio.getPeso() + " Volumen: " + vol + " Direccion: " + envio.getDireccion().getDireccion() + " Localidad: " + envio.getDireccion().getLocalidad() + "\n";
                    enviosTA.getItems().add(envioTXT);
                }
            }
        }}
}
