package com.example.PeregrinosFX.controller;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Servicio;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.ParadaServiceImpl;
import com.example.PeregrinosFX.service.impl.ServicioServiceImpl;
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
import java.util.*;

import static com.example.PeregrinosFX.bean.Servicio.db;

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
        paradaServiceImpl.cargarCB(paradasCB, paradaServiceImpl);
    }

    @FXML
    private void addTabla(ActionEvent event) {
        Parada p = (Parada) paradasCB.getSelectionModel().getSelectedItem();
        ParadaServiceImpl.addParadaTabla(paradaTB, idParadaTC, nombreParadaTC, p);
    }

    @FXML
    private void nuevoServicio(ActionEvent event) {
        ServicioServiceImpl.addServicio(nombreTF, precioTF, paradaTB);

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
