package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.ParadaServiceImpl;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.Connections.ObjectDBConnect.em;
import static com.example.PeregrinosFX.controller.LoginController.rol;
import static com.example.PeregrinosFX.controller.LoginController.u;

@Controller
public class VerEnviosController implements Initializable {
    @Lazy
    @Autowired
    private StageManager stageManager;
    @Lazy
    @Autowired
    private ParadaServiceImpl paradaServiceImpl;
    @FXML
    private Label paradaLBL;
    @FXML
    private ComboBox paradaCB;
    @FXML
    private Label envioLBL;
    @FXML
    private TextArea enviosTA;
    @FXML
    private Button buscarBTN;
    @FXML
    private Button inicioBTN;

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        if (rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if (rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }

    @FXML
    private void verEnvio(ActionEvent event) {
        enviosTA.setText("");
        TypedQuery<EnvioACasa> query = em.createQuery("SELECT p FROM EnvioACasa p", EnvioACasa.class);
        List<EnvioACasa> results = query.getResultList();
        ArrayList<EnvioACasa> enviosParada = new ArrayList<EnvioACasa>();
        Parada parada = (Parada) paradaCB.getSelectionModel().getSelectedItem();

        if (results.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No hay ningún envíos");
            alerta.setContentText("No hay ningún envío para la parada seleccionada");
            alerta.show();
        } else {
            for (EnvioACasa envio : results) {
                if (envio.getIdParada() == parada.getIdParada()) {
                    double[] volumen = envio.getVolumen();
                    String vol = String.valueOf(volumen[0]) + " " + String.valueOf(volumen[1]) + " " + String.valueOf(volumen[2]);
                    String envioTXT = envio.getId() + " Peso: " + envio.getPeso() + " Volumen: " + vol + " Direccion: " + envio.getDireccion().getDireccion() + " Localidad: " + envio.getDireccion().getLocalidad() + "\n";
                    enviosTA.setText(enviosTA.getText() + envioTXT);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (rol == 2) {
            paradaCB.setValue(u.getParada());
        } else {
            ArrayList<Parada> paradas = new ArrayList<>();
            paradas = (ArrayList<Parada>) paradaServiceImpl.findAll();
            for (Parada p : paradas) {
                paradaCB.getItems().add(p);
            }
        }
    }

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public ParadaServiceImpl getParadaServiceImpl() {
        return paradaServiceImpl;
    }

    public void setParadaServiceImpl(ParadaServiceImpl paradaServiceImpl) {
        this.paradaServiceImpl = paradaServiceImpl;
    }

    public Label getParadaLBL() {
        return paradaLBL;
    }

    public void setParadaLBL(Label paradaLBL) {
        this.paradaLBL = paradaLBL;
    }

    public ComboBox getParadaCB() {
        return paradaCB;
    }

    public void setParadaCB(ComboBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    public Label getEnvioLBL() {
        return envioLBL;
    }

    public void setEnvioLBL(Label envioLBL) {
        this.envioLBL = envioLBL;
    }

    public Button getBuscarBTN() {
        return buscarBTN;
    }

    public void setBuscarBTN(Button buscarBTN) {
        this.buscarBTN = buscarBTN;
    }

    public Button getInicioBTN() {
        return inicioBTN;
    }

    public void setInicioBTN(Button inicioBTN) {
        this.inicioBTN = inicioBTN;
    }
}
