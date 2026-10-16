package com.example.demoeventos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HelloController {


    @FXML
    private BorderPane bdPrincipal;
    @FXML
    private GridPane gdPrincipal;

    @FXML
    private void initialize() { // El inicial sirve para cuando se inicia la aplicacion que manejara cosas como conexiones u otros cosas como filtro

        gdPrincipal.addEventFilter(ActionEvent.ACTION, event -> {

            if (event.getTarget() instanceof Button) {
                Button boton = (Button) event.getTarget();
                String text = boton.getText();
                txtIntroducir.setText(text);

                /*if (!(text.equals("+"))) {
                    txtIntroducir.setText(txtIntroducir.getText().equals("0") ? text : txtIntroducir.getText() + text);
                }*/

                event.consume();
            }

        });

    }


    @FXML
    private Button btnTres;
    @FXML
    private Button btnResult;
    @FXML
    private Button btnCinco;
    @FXML
    private Button btnCuatro;
    @FXML
    private Button btnDivide;
    @FXML
    private Button btnUno;
    @FXML
    private Button btnNueve;
    @FXML
    private Button btnSeis;
    @FXML
    private Button btnMulti;
    @FXML
    private TextArea txtResultados;
    @FXML
    private Button btnSieben;
    @FXML
    private Button btnCero;
    @FXML
    private Button btnDos;
    @FXML
    private Button btnMinus;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnOcho;
    @FXML
    private TextField txtIntroducir;

    @FXML
    public void OnbtnDivide(ActionEvent actionEvent) {
    }

    @FXML
    public void OnbtnSupr(ActionEvent actionEvent) {
    }

    @FXML
    public void OnbtnMinus(ActionEvent actionEvent) {
    }

    @FXML
    public void OnbtnMulti(ActionEvent actionEvent) {
    }

    @FXML
    public void OnbtnPlus(ActionEvent actionEvent) {
    }

//    @FXML
//    public void OnbtnDos(ActionEvent actionEvent) {
//        txtIntroducir.appendText("2");
//    }
//
//    @FXML
//    public void OnbtnUno(ActionEvent actionEvent) {
//        txtIntroducir.appendText("1");
//    }
//
//    @FXML
//    public void OnbtnCuatro(ActionEvent actionEvent) {
//        txtIntroducir.appendText("4");
//
//    }
//
//    @FXML
//    public void OnbtnTres(ActionEvent actionEvent) {
//        txtIntroducir.appendText("3");
//
//    }
//
//    @FXML
//    public void btnCinco(ActionEvent actionEvent) {
//        txtIntroducir.appendText("5");
//
//    }
//
//    @FXML
//    public void OnbtnCero(ActionEvent actionEvent) {
//        txtIntroducir.appendText("0");
//
//    }
//
//    @FXML
//    public void OnbtnSeis(ActionEvent actionEvent) {
//        txtIntroducir.appendText("6");
//
//    }
//
//    @FXML
//    public void OnbtnSupr(ActionEvent actionEvent) {
//        //txtIntroducir.;
//
//    }
//
//    @FXML
//    public void OnbtnSiete(ActionEvent actionEvent) {
//        txtIntroducir.appendText("7");
//
//    }
//
//    @FXML
//    public void OnbtnNueve(ActionEvent actionEvent) {
//        txtIntroducir.appendText("9");
//
//    }
//
//    @FXML
//    public void OnbtnOcho(ActionEvent actionEvent) {
//        txtIntroducir.appendText("8");
//
//    }
//
//    @FXML
//    public void OnbtnDivide(ActionEvent actionEvent) {
//        txtIntroducir.appendText("/");
//
//    }
//
//    @FXML
//    public void OnbtnMinus(ActionEvent actionEvent) {
//        txtIntroducir.appendText("-");
//    }
//
//    @FXML
//    public void OnbtnMulti(ActionEvent actionEvent) {
//        txtIntroducir.appendText("*");
//    }
//
//    @FXML
//    public void OnbtnPlus(ActionEvent actionEvent) {
//        txtIntroducir.appendText("+");
//    }

    /*
    @Deprecated
    public void onBtnOK(ActionEvent actionEvent) {
        lblNombre.setText("Apellido");
    }
    */
}
