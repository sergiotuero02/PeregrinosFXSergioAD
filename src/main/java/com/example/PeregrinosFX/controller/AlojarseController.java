package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.CarnetService;
import com.example.PeregrinosFX.service.ParadaService;
import com.example.PeregrinosFX.service.PeregrinoService;
import com.example.PeregrinosFX.service.impl.EstanciaServiceImpl;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;
import static com.example.PeregrinosFX.controller.LoginController.u;

@Controller
public class AlojarseController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;


    @Autowired
    private ParadaService paradaService;

    @Autowired
    private CarnetService carnetService;


    @Autowired
    private PeregrinoService peregrinoService;


    @Autowired
    private EstanciaServiceImpl estanciaServiceImpl;

    @FXML
    private Label paradaLBL;

    @FXML
    private Label peregrinoLBL;

    @FXML
    private Label estanciaLBL;

    @FXML
    private Label vipLBL;

    @FXML
    private CheckBox estanciaCheck;

    @FXML
    private ComboBox paradaCB;

    @FXML
    private ComboBox peregrinoCB;

    @FXML
    private CheckBox vipCB;

    @FXML
    private Button alojarseBTN;

    @FXML
    private Button cancelarBTN;

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public Label getParadaLBL() {
        return paradaLBL;
    }

    public void setParadaLBL(Label paradaLBL) {
        this.paradaLBL = paradaLBL;
    }

    public Label getPeregrinoLBL() {
        return peregrinoLBL;
    }

    public void setPeregrinoLBL(Label peregrinoLBL) {
        this.peregrinoLBL = peregrinoLBL;
    }

    public Label getEstanciaLBL() {
        return estanciaLBL;
    }

    public void setEstanciaLBL(Label estanciaLBL) {
        this.estanciaLBL = estanciaLBL;
    }

    public Label getVipLBL() {
        return vipLBL;
    }

    public void setVipLBL(Label vipLBL) {
        this.vipLBL = vipLBL;
    }

    public CheckBox getEstanciaCheck() {
        return estanciaCheck;
    }

    public void setEstanciaCheck(CheckBox estanciaCheck) {
        this.estanciaCheck = estanciaCheck;
    }

    public ComboBox getParadaCB() {
        return paradaCB;
    }

    public void setParadaCB(ComboBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    public ComboBox getPeregrinoCB() {
        return peregrinoCB;
    }

    public void setPeregrinoCB(ComboBox peregrinoCB) {
        this.peregrinoCB = peregrinoCB;
    }

    public Button getAlojarseBTN() {
        return alojarseBTN;
    }

    public void setAlojarseBTN(Button alojarseBTN) {
        this.alojarseBTN = alojarseBTN;
    }

    public Button getCancelarBTN() {
        return cancelarBTN;
    }

    public void setCancelarBTN(Button cancelarBTN) {
        this.cancelarBTN = cancelarBTN;
    }

    @FXML
    private void alojar(ActionEvent event) throws IOException {
        try {


            Peregrino peregrino = (Peregrino) peregrinoCB.getSelectionModel().getSelectedItem();
            Parada parada = (Parada) paradaCB.getSelectionModel().getSelectedItem();
            //Llamamos al m??todo alojarse que nos va a??adir un campo a la tabla peregrino_parada con el peregrino y la parada seleccionados
            //!Este m??todo nos permite a??adir varias veces la misma combinaci??n peregrino parada! por lo que un peregrino puede realizar la misma parada varias veces
            estanciaServiceImpl.alojarse((Peregrino) peregrinoCB.getSelectionModel().getSelectedItem(), (Parada) paradaCB.getSelectionModel().getSelectedItem());

            //Si hay una estancia la a??adiremos, diferenciando si es vip o no
            if (estanciaCheck.isSelected()) {

                if (vipCB.isSelected()) {

                    peregrino.getCarnet().setNumVips(peregrino.getCarnet().getNumVips() + 1);
                    Estancia e = new Estancia();
                    e.setVip(true);
                    e.setFecha(LocalDate.now());
                    e.setParada(parada);
                    e.setPeregrino(peregrino);
                    estanciaServiceImpl.addEstancia(e);
                    //En ambos casos, actualizaremos el carnet para que se guarde la nueva distancia total y se actualice el n??mero de vips
                    carnetService.addCarnet(peregrino.getCarnet());
                }
                if (!vipCB.isSelected()) {
                    Estancia e = new Estancia();
                    e.setVip(false);
                    e.setFecha(LocalDate.now());
                    e.setParada(parada);
                    e.setPeregrino(peregrino);
                    estanciaServiceImpl.addEstancia(e);
                    carnetService.addCarnet(peregrino.getCarnet());
                }

                //Mostramos un mensaje informando del ??xito de la operaci??n
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Peregrino alojado");
                alert.setHeaderText(null);
                alert.setContentText("El peregrino " + ((Peregrino) peregrinoCB.getSelectionModel().getSelectedItem()).getNombre() + " ha realizado la parada correctamente.");
                alert.showAndWait();

                //En funci??n del perfil/rol redirigiremos al usuario hacia el men?? correspondiente
                if (rol == 2) {
                    stageManager.switchScene(FxmlView.MENUADMINPARADA);
                }
                if (rol == 3) {
                    stageManager.switchScene(FxmlView.MENUADMINGENERAL);
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Introduzca todos los campos");
            alert.setHeaderText(null);
            alert.setContentText("Introduzca todos los campos");
            alert.showAndWait();
        }
    }

    //M??todo que activa o desactiva el campo vip en funci??n de si hay estancia o no
    @FXML
    private void estanciaClick(ActionEvent event) throws IOException {
        if (estanciaCheck.isSelected()) {
            vipLBL.setTextFill(Paint.valueOf("45322e"));
            vipCB.setDisable(false);

        } else {
            vipLBL.setTextFill(Paint.valueOf("45322e70"));
            vipCB.setDisable(true);

        }

    }

    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
        if (rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if (rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Cargamos los combo box, si el perfil/ rol es peregrino o parada solo cargaremos el valor correspondiente
        //a ese peregrino o parada, pero si es un admin general los cargaremos todos
        ArrayList<Peregrino> peregrinos = new ArrayList<>();
        peregrinos = (ArrayList<Peregrino>) peregrinoService.findAll();
        for (Peregrino p1 : peregrinos) {
            peregrinoCB.getItems().add(p1);

        }
        if (rol == 2) {
            paradaCB.setValue(u.getParada());
        } else {
            ArrayList<Parada> paradas = new ArrayList<>();
            paradas = (ArrayList<Parada>) paradaService.findAll();
            for (Parada p : paradas) {
                paradaCB.getItems().add(p);
            }
        }

    }
}
