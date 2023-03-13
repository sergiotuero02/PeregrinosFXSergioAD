package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.Connections.ObjectDBConnect;
import com.example.PeregrinosFX.bean.Direccion;
import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.Connections.ObjectDBConnect.em;
import static com.example.PeregrinosFX.bean.Servicio.db;
import static com.example.PeregrinosFX.controller.AlojarseController.paradaEnvio;
import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class EnvioACasaController implements Initializable {
    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private Label direccionLBL;
    @FXML
    private Label localidadLBL;
    @FXML
    private Label alturaLBL;
    @FXML
    private Label anchoLBL;
    @FXML
    private Label profundoLBL;
    @FXML
    private Label pesoLBL;
    @FXML
    private TextField direccionTF;
    @FXML
    private TextField localidadTF;
    @FXML
    private TextField alturaTF;
    @FXML
    private TextField anchoTF;
    @FXML
    private TextField profundoTF;
    @FXML
    private TextField pesoTF;
    @FXML
    private CheckBox urgenteCB;
    @FXML
    private Button aceptarBTN;
    @FXML
    private Button cancelarBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void enviarACasa(ActionEvent event){
    if(direccionTF.equals("") || localidadTF.equals("") || alturaTF.equals("") || anchoTF.equals("") || profundoTF.equals("") || pesoTF.equals("")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Introduzca todos los campos");
        alert.show();
    }else {
        try {
            Direccion direccion = new Direccion();
            EnvioACasa envio = new EnvioACasa();
            double[]dimensiones = new double[3];
            dimensiones[0] = Double.parseDouble(alturaTF.getText());
            dimensiones[1] = Double.parseDouble(anchoTF.getText());
            dimensiones[2] = Double.parseDouble(profundoTF.getText());

            direccion.setDireccion(direccionTF.getText());
            direccion.setLocalidad(localidadTF.getText());

            envio.setVolumen(dimensiones);
            envio.setDireccion(direccion);
            envio.setPeso(Double.parseDouble(pesoTF.getText()));
            envio.setUrgente(urgenteCB.isSelected());
            envio.setIdParada(paradaEnvio.getIdParada());
            ObjectDBConnect.em.getTransaction().begin();
            ObjectDBConnect.em.persist(envio);
            ObjectDBConnect.em.getTransaction().commit();
            TypedQuery<EnvioACasa> query = em.createQuery("SELECT p FROM EnvioACasa p",
                    EnvioACasa.class);
            List<EnvioACasa> results = query.getResultList();
            if (results.isEmpty()) {
                System.out.println("La lista de resultados está vacía.");
            } else {

                for (EnvioACasa e : results) {
                    System.out.println("Envío: " + e);
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("El peso, la altura, el ancho y la profundidad deben ser un número");
            alert.show();
        }
    }}

    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
        if (rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if (rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }

    public Label getDireccionLBL() {
        return direccionLBL;
    }

    public void setDireccionLBL(Label direccionLBL) {
        this.direccionLBL = direccionLBL;
    }

    public Label getLocalidadLBL() {
        return localidadLBL;
    }

    public void setLocalidadLBL(Label localidadLBL) {
        this.localidadLBL = localidadLBL;
    }

    public Label getAlturaLBL() {
        return alturaLBL;
    }

    public void setAlturaLBL(Label alturaLBL) {
        this.alturaLBL = alturaLBL;
    }

    public Label getAnchoLBL() {
        return anchoLBL;
    }

    public void setAnchoLBL(Label anchoLBL) {
        this.anchoLBL = anchoLBL;
    }

    public Label getProfundoLBL() {
        return profundoLBL;
    }

    public void setProfundoLBL(Label profundoLBL) {
        this.profundoLBL = profundoLBL;
    }

    public Label getPesoLBL() {
        return pesoLBL;
    }

    public void setPesoLBL(Label pesoLBL) {
        this.pesoLBL = pesoLBL;
    }

    public TextField getDireccionTF() {
        return direccionTF;
    }

    public void setDireccionTF(TextField direccionTF) {
        this.direccionTF = direccionTF;
    }

    public TextField getLocalidadTF() {
        return localidadTF;
    }

    public void setLocalidadTF(TextField localidadTF) {
        this.localidadTF = localidadTF;
    }

    public TextField getAlturaTF() {
        return alturaTF;
    }

    public void setAlturaTF(TextField alturaTF) {
        this.alturaTF = alturaTF;
    }

    public TextField getAnchoTF() {
        return anchoTF;
    }

    public void setAnchoTF(TextField anchoTF) {
        this.anchoTF = anchoTF;
    }

    public TextField getProfundoTF() {
        return profundoTF;
    }

    public void setProfundoTF(TextField profundoTF) {
        this.profundoTF = profundoTF;
    }

    public TextField getPesoTF() {
        return pesoTF;
    }

    public void setPesoTF(TextField pesoTF) {
        this.pesoTF = pesoTF;
    }

    public CheckBox getUrgenteCB() {
        return urgenteCB;
    }

    public void setUrgenteCB(CheckBox urgenteCB) {
        this.urgenteCB = urgenteCB;
    }
}
