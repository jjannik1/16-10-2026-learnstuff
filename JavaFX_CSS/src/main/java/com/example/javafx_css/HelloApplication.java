package com.example.javafx_css;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema de Gestión Empresarial - JavaFX CSS & FXML");
        stage.setScene(scene);

        // Búsqueda de nodo mediante lookup() una vez mostrada la escena
        stage.setOnShown(e -> {
            Node deleteBtn = scene.lookup("#btn-delete");
            if (deleteBtn != null) {
                System.out.println("Nodo resuelto vía CSS lookup (#btn-delete): " + deleteBtn);
            }
        });


        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());

    }
}
