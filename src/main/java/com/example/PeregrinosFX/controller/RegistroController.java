package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.*;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.CarnetService;
import com.example.PeregrinosFX.service.ParadaService;
import com.example.PeregrinosFX.service.PeregrinoService;
import com.example.PeregrinosFX.service.UserService;
import com.example.PeregrinosFX.view.FxmlView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class RegistroController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private ParadaService paradaService;

    @Lazy
    @Autowired
    private PeregrinoService peregrinoService;

    @Lazy
    @Autowired
    private CarnetService carnetService;

    @FXML
    private Label nombreLBL;

    @FXML
    private Label usuarioLBL;

    @FXML
    private Label contrasenaLBL;

    @FXML
    private Label confirmcontraLBL;

    @FXML
    private Label paradaLBL;

    @FXML
    private Label nacionalidadLBL;

    @FXML
    private TextField nombreTF;

    @FXML
    private TextField usuarioTF;

    @FXML
    private TextField contrasenaTF;

    @FXML
    private TextField confirmcontraTF;

    @FXML
    private ComboBox paradaCB;

    @FXML
    private ComboBox nacionalidadCB;

    @FXML
    private Button aceptarBTN;

    @FXML
    private Button cancelarBTN;

    public static User userCreado;

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Label getNombreLBL() {
        return nombreLBL;
    }

    public void setNombreLBL(Label nombreLBL) {
        this.nombreLBL = nombreLBL;
    }

    public Label getUsuarioLBL() {
        return usuarioLBL;
    }

    public void setUsuarioLBL(Label usuarioLBL) {
        this.usuarioLBL = usuarioLBL;
    }

    public Label getContrasenaLBL() {
        return contrasenaLBL;
    }

    public void setContrasenaLBL(Label contrasenaLBL) {
        this.contrasenaLBL = contrasenaLBL;
    }

    public Label getConfirmcontraLBL() {
        return confirmcontraLBL;
    }

    public void setConfirmcontraLBL(Label confirmcontraLBL) {
        this.confirmcontraLBL = confirmcontraLBL;
    }

    public Label getParadaLBL() {
        return paradaLBL;
    }

    public void setParadaLBL(Label paradaLBL) {
        this.paradaLBL = paradaLBL;
    }

    public Label getNacionalidadLBL() {
        return nacionalidadLBL;
    }

    public void setNacionalidadLBL(Label nacionalidadLBL) {
        this.nacionalidadLBL = nacionalidadLBL;
    }

    public String getNombreTF() {
        return nombreTF.getText();
    }

    public void setNombreTF(TextField nombreTF) {
        this.nombreTF = nombreTF;
    }

    public String getUsuarioTF() {
        return usuarioTF.getText();
    }

    public void setUsuarioTF(TextField usuarioTF) {
        this.usuarioTF = usuarioTF;
    }

    public String getContrasenaTF() {
        return contrasenaTF.getText();
    }

    public void setContrasenaTF(TextField contrasenaTF) {
        this.contrasenaTF = contrasenaTF;
    }

    public TextField getConfirmcontraTF() {
        return confirmcontraTF;
    }

    public void setConfirmcontraTF(TextField confirmcontraTF) {
        this.confirmcontraTF = confirmcontraTF;
    }

    public ComboBox getParadaCB() {
        return paradaCB;
    }

    public void setParadaCB(ComboBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    public ComboBox getNacionalidadCB() {
        return nacionalidadCB;
    }

    public void setNacionalidadCB(ComboBox nacionalidadCB) {
        this.nacionalidadCB = nacionalidadCB;
    }

    public Button getAceptarBTN() {
        return aceptarBTN;
    }

    public void setAceptarBTN(Button aceptarBTN) {
        this.aceptarBTN = aceptarBTN;
    }

    public Button getCancelarBTN() {
        return cancelarBTN;
    }

    public void setCancelarBTN(Button cancelarBTN) {
        this.cancelarBTN = cancelarBTN;
    }

    @FXML
    private void updateAlert(User user) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuario registrado");
        alert.setHeaderText(null);
        alert.setContentText("El usuario " + getUsuarioTF() + " se ha registrado correctamente.");
        alert.showAndWait();
    }


    @FXML
    private void registro(ActionEvent event) throws IOException {
        if (userService.userDisponible(getUsuarioTF())) {
            usuarioLBL.setText("NO DISPONIBLE");
            usuarioLBL.setTextFill(Paint.valueOf("#FF0000"));
        } else if (confirmcontraTF.equals("") || contrasenaTF.equals("") || usuarioTF.equals("") || nombreTF.equals("") || paradaCB.getSelectionModel().getSelectedItem() == null || nacionalidadCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campos no introducidos");
            alert.setHeaderText(null);
            alert.setContentText("Introduzca todos los campos.");
            alert.showAndWait();
        }
        else if (confirmcontraTF != contrasenaTF) {
            contrasenaLBL.setText("NO COINCIDEN");
            contrasenaLBL.setTextFill(Paint.valueOf("#FF0000"));
            confirmcontraLBL.setText("NO COINCIDEN");
            confirmcontraLBL.setTextFill(Paint.valueOf("#FF0000"));
        }  else {
            userCreado = new User();
            Peregrino peregrino = new Peregrino();
            Carnet carnet = new Carnet();
            Parada parada = new Parada();
            Perfil perfil = new Perfil();
            Carnet carnetAux = new Carnet();
            parada = (Parada) paradaCB.getSelectionModel().getSelectedItem();
            carnetAux.getIdCarnet();
            perfil.setIdPerfil(1L);
            userCreado.setContrasenia(getContrasenaTF());
            userCreado.setUsuario(getUsuarioTF());
            userCreado.setPerfil(perfil);
            carnet.setDistancia(0.0);
            carnet.setFechaExp(LocalDate.now());
            carnet.setNumVips(0);
            carnet.setParadaInicial(parada);
            Carnet newCarnet = carnetService.addCarnet(carnet);
            newCarnet.getIdCarnet();
            peregrino.setNombre(getNombreTF());
            peregrino.setNacionalidad((String) nacionalidadCB.getValue());
            peregrino.setCarnet(newCarnet);
            userCreado.setPeregrino(peregrino);
            Peregrino newPeregrino = peregrinoService.addPeregrino(peregrino);
            User newUser = userService.addUser(userCreado);
            updateAlert(userCreado);
            stageManager.switchScene(FxmlView.USUARIOCREADO);

        }

    }

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUPRINCIPAL);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nacionalidadCB.getItems().addAll(
                "España",
                "Alemania",
                "Rusia",
                "Rumanía",
                "Italia",
                "Francia"

        );
        ArrayList<Parada> paradas = new ArrayList<>();
        paradas = (ArrayList<Parada>) paradaService.findAll();
        for (Parada p : paradas) {
            paradaCB.getItems().add(p);
        }
    }
}
