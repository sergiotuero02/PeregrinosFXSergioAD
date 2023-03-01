package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Servicio;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static com.example.PeregrinosFX.bean.Servicio.db;

public class ServicioServiceImpl {

public static void cargarServicios(ComboBox serviciosCB){
    Servicio servicio = new Servicio();
    for (Object s : db.queryByExample(servicio)) {
        serviciosCB.getItems().add((Servicio)s);
    }
}


    public static void addServicio(TextField nombreTF, TextField precioTF, TableView paradaTB){

        Servicio s = new Servicio();
        int contador = 0;
        ArrayList<Servicio> servicios = new ArrayList<Servicio>();
        for (Object o : db.queryByExample(s)){
            servicios.add((Servicio) o);
        }
        for (Servicio s1 : servicios){
            if (nombreTF.getText().equalsIgnoreCase(s1.getNombre())) {
                contador++;
            }
        }

        ArrayList<Parada> paradasSer = new ArrayList<Parada>();
        for (int i = 0; i < paradaTB.getItems().size(); i++) {
            paradasSer.add((Parada) paradaTB.getItems().get(i));
        }

        if(contador != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("El servicio ya existe");
            alert.show();
        }
        else if (nombreTF.getText().equals("") || precioTF.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Introduzca el nombre y el precio");
            alert.show();
            db.close();
        } else {
            try {
                s.setPrecio(Double.parseDouble(precioTF.getText()));
                s.setNombre(nombreTF.getText());

                s.setParadas(paradasSer);
                db.store(s);
                db.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SERVICIO AÑADIDO");
                alert.setContentText("SERVICIO AÑADIDO EXITOSAMENTE");
                alert.show();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Introduzca un número en el precio");
                alert.show();
            }


        }

    }
}
