package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Servicio;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.ParadaServiceImpl;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class NuevoServicioController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Lazy
    @Autowired
    private ParadaServiceImpl paradaServiceImpl;

    @FXML
    private javafx.scene.control.Label nombreLBL;

    @FXML
    private javafx.scene.control.Label precioLBL;

    @FXML
    private javafx.scene.control.TextField nombreTF;

    @FXML
    private javafx.scene.control.TextField precioTF;

    @FXML
    private javafx.scene.control.Button confirmarBTN;

    @FXML
    private javafx.scene.control.Button cancelarBTN;

    @FXML
    private TableView paradaTB;

    @FXML
    private TableColumn idParadaTC;

    @FXML
    private TableColumn nombreParadaTC;

    @FXML
    private ComboBox paradasCB;

    @FXML
    private javafx.scene.control.Button addBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Parada> paradas = new ArrayList<Parada>();
        paradas = (ArrayList<Parada>) paradaServiceImpl.findAll();
        for (Parada p : paradas) {
            paradasCB.getItems().add(p);
        }

    }

    @FXML
    private void addTabla(ActionEvent event) {
        try {

            idParadaTC.setCellValueFactory(new PropertyValueFactory<>("idParada"));
            nombreParadaTC.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            Parada p = (Parada) paradasCB.getSelectionModel().getSelectedItem();
            int contador = 0;

            for (int i = 0; i < paradaTB.getItems().size(); i++) {
                if (paradaTB.getItems().get(i) == p){
                    contador++;
                }
            }

            if (p == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Introduzca la parada");
                alert.show();
            }
            else if (contador == 0 && p != null) {
                paradaTB.getItems().add(p);
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setContentText("Parada ya seleccionada");
                alerta.show();
            }

        } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Introduzca todos los campos");
            alert.setContentText("Introduzca todos los campos");
            alert.show();


        }
    }

    @FXML
    private void nuevoServicio(ActionEvent event){
        Servicio s = new Servicio();
        s.setNombre(nombreTF.getText());
        s.setPrecio(Double.parseDouble(precioTF.getText()));
    }

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUSERVICIOS);
    }

    public javafx.scene.control.Label getNombreLBL() {
        return nombreLBL;
    }

    public void setNombreLBL(javafx.scene.control.Label nombreLBL) {
        this.nombreLBL = nombreLBL;
    }

    public javafx.scene.control.Label getPrecioLBL() {
        return precioLBL;
    }

    public void setPrecioLBL(javafx.scene.control.Label precioLBL) {
        this.precioLBL = precioLBL;
    }

    public javafx.scene.control.TextField getNombreTF() {
        return nombreTF;
    }

    public void setNombreTF(javafx.scene.control.TextField nombreTF) {
        this.nombreTF = nombreTF;
    }

    public javafx.scene.control.TextField getPrecioTF() {
        return precioTF;
    }

    public void setPrecioTF(javafx.scene.control.TextField precioTF) {
        this.precioTF = precioTF;
    }

    public javafx.scene.control.Button getConfirmarBTN() {
        return confirmarBTN;
    }

    public void setConfirmarBTN(javafx.scene.control.Button confirmarBTN) {
        this.confirmarBTN = confirmarBTN;
    }

    public javafx.scene.control.Button getCancelarBTN() {
        return cancelarBTN;
    }

    public void setCancelarBTN(javafx.scene.control.Button cancelarBTN) {
        this.cancelarBTN = cancelarBTN;
    }

    public TableView getParadaTB() {
        return paradaTB;
    }

    public void setParadaTB(TableView paradaTB) {
        this.paradaTB = paradaTB;
    }

    public TableColumn getIdParadaTC() {
        return idParadaTC;
    }

    public void setIdParadaTC(TableColumn idParadaTC) {
        this.idParadaTC = idParadaTC;
    }

    public TableColumn getNombreParadaTC() {
        return nombreParadaTC;
    }

    public void setNombreParadaTC(TableColumn nombreParadaTC) {
        this.nombreParadaTC = nombreParadaTC;
    }

    public ComboBox getParadasCB() {
        return paradasCB;
    }

    public void setParadasCB(ComboBox paradasCB) {
        this.paradasCB = paradasCB;
    }

    public javafx.scene.control.Button getAddBTN() {
        return addBTN;
    }

    public void setAddBTN(Button addBTN) {
        this.addBTN = addBTN;
    }


}
