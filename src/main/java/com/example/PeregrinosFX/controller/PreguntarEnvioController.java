package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class PreguntarEnvioController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;
    @FXML
    private Button siBTN;
    @FXML
    private Button noBTN;
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
    private void envio(ActionEvent event) throws IOException{

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
