package com.example.javafx_eventos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {

    // Consola centralizada de registro de eventos
    private TextArea txtConsola;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    // Variables de apoyo para el arrastre de nodos (Drag)
    private double dragStartX;
    private double dragStartY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // =========================================================================
        // ESTRUCTURA PRINCIPAL (BorderPane)
        // =========================================================================

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #1e1e2e; -fx-font-family: 'Segoe UI', Arial, sans-serif;");

        // Top: Cabecera y botón de limpiar consola
        HBox header = crearCabecera();
        root.setTop(header);

        // Center: TabPane con los 4 ejemplos de eventos
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.getTabs().addAll(
                crearTabActionEvent(),
                crearTabMouseEventClics(),
                crearTabMouseEventArrastre(),
                crearTabKeyEvent()
        );
        root.setCenter(tabPane);

        // Bottom: Consola/Log de eventos en tiempo real
        VBox panelConsola = crearPanelConsola();
        root.setBottom(panelConsola);

        // =========================================================================
        // ESCENA Y ESCENARIO
        // =========================================================================
        Scene scene = new Scene(root, 950, 720);

        // Aplicar estilos oscuros globales mediante CSS incrustado
        scene.getStylesheets().add("data:text/css," +
                ".root { -fx-base: #1e1e2e; -fx-background-color: #1e1e2e; }" +
                ".tab-pane .tab-header-area .tab-header-background { -fx-background-color: #181825; }" +
                ".tab { -fx-background-color: #313244; -fx-padding: 8 16; }" +
                ".tab:selected { -fx-background-color: #89b4fa; }" +
                ".tab:selected .tab-label { -fx-text-fill: #11111b; -fx-font-weight: bold; }" +
                ".tab .tab-label { -fx-text-fill: #cdd6f4; }" +
                ".button { -fx-background-color: #89b4fa; -fx-text-fill: #11111b; -fx-font-weight: bold; -fx-background-radius: 6; }" +
                ".button:hover { -fx-background-color: #b4befe; }" +
                ".text-field, .text-area { -fx-background-color: #313244; -fx-text-fill: #cdd6f4; -fx-highlight-fill: #585b70; -fx-background-radius: 6; }"
        );

        // Captura de Evento de Ventana (WindowEvent)
        primaryStage.setOnCloseRequest(event -> {
            registrarEvento("WINDOW_CLOSE_REQUEST", "El usuario ha solicitado cerrar la ventana.");
        });

        primaryStage.setTitle("Demostración Programática de Eventos JavaFX");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        registrarEvento("SISTEMA", "Aplicación iniciada. Seleccione una pestaña e interactúe con los elementos.");
    }

    // =============================================================================
    // 1. ACTION EVENT (Botones y Campos de Texto)
    // =============================================================================
    private Tab crearTabActionEvent() {
        Tab tab = new Tab("1. ActionEvent");

        VBox contenedor = new VBox(15);
        contenedor.setPadding(new Insets(20));
        contenedor.setAlignment(Pos.TOP_LEFT);

        Label lblTitulo = new Label("Eventos de Acción de Alto Nivel (ActionEvent)");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #89b4fa;");

        Label lblDesc = new Label("Se disparan cuando un control ejecuta su acción principal (clic en botón, Enter en TextField).");
        lblDesc.setStyle("-fx-text-fill: #a6adc8;");

        // Componentes instanciados por código
        Button btnAccion = new Button("▶ Ejecutar Acción de Botón");
        btnAccion.setPrefHeight(35);

        TextField txtEntrada = new TextField();
        txtEntrada.setPromptText("Escriba algo y presione ENTER...");
        txtEntrada.setPrefWidth(300);

        // ASOCIACIÓN DE EVENTOS MEDIANTE LAMBDAS Y LECTURA DE PROPIEDADES
        btnAccion.setOnAction(e -> {
            // Lectura de valores asociados al ActionEvent
            Object origen = e.getSource(); // Nodo que registra el evento
            Object objetivo = e.getTarget(); // Nodo destino original
            String tipoEvento = e.getEventType().getName();

            registrarEvento("ActionEvent [Button]", String.format(
                    "Tipo: %s | Origen: %s | Target: %s",
                    tipoEvento, origen.getClass().getSimpleName(), objetivo.getClass().getSimpleName()
            ));
        });

        txtEntrada.setOnAction(e -> {
            String textoIngresado = txtEntrada.getText();
            registrarEvento("ActionEvent [TextField]", String.format(
                    "Texto Confirmado con ENTER: '%s' | Target: %s",
                    textoIngresado, e.getTarget().getClass().getSimpleName()
            ));
        });

        HBox filaControles = new HBox(15, btnAccion, txtEntrada);
        filaControles.setAlignment(Pos.CENTER_LEFT);

        contenedor.getChildren().addAll(lblTitulo, lblDesc, filaControles);
        tab.setContent(contenedor);
        return tab;
    }

    // =============================================================================
    // 2. MOUSE EVENT - CLICS, COORDENADAS Y MODIFICADORES
    // =============================================================================
    private Tab crearTabMouseEventClics() {
        Tab tab = new Tab("2. MouseEvent (Clics y Posición)");

        VBox contenedor = new VBox(15);
        contenedor.setPadding(new Insets(20));

        Label lblTitulo = new Label("Eventos de Ratón: Clics, Coordenadas y Teclas Modificadoras");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #89b4fa;");

        Label lblDesc = new Label("Haga clic o mueva el ratón dentro del área interactiva inferior para inspeccionar sus valores.");
        lblDesc.setStyle("-fx-text-fill: #a6adc8;");

        // Panel interactivo sensible al ratón
        StackPane areaRaton = new StackPane();
        areaRaton.setPrefSize(800, 180);
        areaRaton.setStyle("-fx-background-color: #313244; -fx-border-color: #89b4fa; -fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label lblInstruccionArea = new Label("📍 Panel Táctil de Ratón\n[Haga Clic Izquierdo/Derecho, Doble Clic, o presione Ctrl/Shift/Alt]");
        lblInstruccionArea.setStyle("-fx-text-fill: #cdd6f4; -fx-font-size: 14px; -fx-text-alignment: center;");
        areaRaton.getChildren().add(lblInstruccionArea);

        // REGISTRO DE EVENTOS DE RATÓN Y LECTURA DE VALORES ASOCIADOS
        areaRaton.setOnMouseClicked((MouseEvent e) -> {
            // 1. Botón del ratón pulsado (PRIMARY = Izquierdo, SECONDARY = Derecho, MIDDLE = Central)
            MouseButton botonPulsado = e.getButton();

            // 2. Número de clics consecutivos (1 = simple, 2 = doble clic, etc.)
            int numeroClics = e.getClickCount();

            // 3. Coordenadas locales dentro del nodo (X, Y)
            double posX = e.getX();
            double posY = e.getY();

            // 4. Coordenadas absolutas en la Escena (SceneX, SceneY)
            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            // 5. Teclas modificadoras presionadas durante el clic
            boolean ctrlDown = e.isControlDown();
            boolean shiftDown = e.isShiftDown();
            boolean altDown = e.isAltDown();

            registrarEvento("MouseEvent [MOUSE_CLICKED]", String.format(
                    "Botón: %s | Clics: %d | Local: (%.1f, %.1f) | Escena: (%.1f, %.1f) | Modificadores: [Ctrl: %b, Shift: %b, Alt: %b]",
                    botonPulsado, numeroClics, posX, posY, sceneX, sceneY, ctrlDown, shiftDown, altDown
            ));
        });

        // Evento al mover el ratón dentro del área
        areaRaton.setOnMouseMoved((MouseEvent e) -> {
            // Actualización ligera
            if (e.getX() % 20 < 2 && e.getY() % 20 < 2) { // Filtrar frecuencia de log
                registrarEvento("MouseEvent [MOUSE_MOVED]", String.format(
                        "Posición actual del cursor -> Local: (X=%.1f, Y=%.1f)", e.getX(), e.getY()
                ));
            }
        });

        areaRaton.setOnMouseEntered(e -> areaRaton.setStyle("-fx-background-color: #45475a; -fx-border-color: #a6e3a1; -fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8;"));
        areaRaton.setOnMouseExited(e -> areaRaton.setStyle("-fx-background-color: #313244; -fx-border-color: #89b4fa; -fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8;"));

        contenedor.getChildren().addAll(lblTitulo, lblDesc, areaRaton);
        tab.setContent(contenedor);
        return tab;
    }

    // =============================================================================
    // 3. MOUSE EVENT - ARRASTRE DE NODOS (MOUSE_DRAGGED)
    // =============================================================================
    private Tab crearTabMouseEventArrastre() {
        Tab tab = new Tab("3. MouseEvent (Arrastre)");

        VBox contenedor = new VBox(15);
        contenedor.setPadding(new Insets(20));

        Label lblTitulo = new Label("Eventos de Arrastre de Ratón (MOUSE_DRAGGED / Drag & Drop)");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #89b4fa;");

        Label lblDesc = new Label("Haga clic sobre la figura circular y arrástrela libremente dentro del lienzo.");
        lblDesc.setStyle("-fx-text-fill: #a6adc8;");

        // Lienzo contenedor para arrastre libre
        Pane lienzoArrastre = new Pane();
        lienzoArrastre.setPrefSize(800, 200);
        lienzoArrastre.setStyle("-fx-background-color: #181825; -fx-border-color: #313244; -fx-border-radius: 8; -fx-background-radius: 8;");

        // Figura interactiva arrastrable (Círculo)
        Circle figuraArrastrable = new Circle(40, Color.web("#f38ba8"));
        figuraArrastrable.setCenterX(100);
        figuraArrastrable.setCenterY(100);
        figuraArrastrable.setStroke(Color.web("#f5e0dc"));
        figuraArrastrable.setStrokeWidth(3);

        Label lblEtiquetaNodo = new Label("Arrástrame");
        lblEtiquetaNodo.setStyle("-fx-text-fill: #11111b; -fx-font-weight: bold; -fx-font-size: 11px;");
        lblEtiquetaNodo.setLayoutX(70);
        lblEtiquetaNodo.setLayoutY(93);

        // 1. MOUSE_PRESSED: Guardar la posición inicial al presionar el ratón
        figuraArrastrable.setOnMousePressed((MouseEvent e) -> {
            dragStartX = e.getSceneX() - figuraArrastrable.getCenterX();
            dragStartY = e.getSceneY() - figuraArrastrable.getCenterY();

            registrarEvento("MouseEvent [MOUSE_PRESSED - Inicio Drag]", String.format(
                    "Nodo Seleccionado en Centro: (X=%.1f, Y=%.1f)",
                    figuraArrastrable.getCenterX(), figuraArrastrable.getCenterY()
            ));
        });

        // 2. MOUSE_DRAGGED: Recalcular posición dinámicamente durante el movimiento
        figuraArrastrable.setOnMouseDragged((MouseEvent e) -> {
            double nuevaX = e.getSceneX() - dragStartX;
            double nuevaY = e.getSceneY() - dragStartY;

            // Restricción dentro de los límites del lienzo
            if (nuevaX >= 40 && nuevaX <= lienzoArrastre.getWidth() - 40) {
                figuraArrastrable.setCenterX(nuevaX);
                lblEtiquetaNodo.setLayoutX(nuevaX - 30);
            }
            if (nuevaY >= 40 && nuevaY <= lienzoArrastre.getHeight() - 40) {
                figuraArrastrable.setCenterY(nuevaY);
                lblEtiquetaNodo.setLayoutY(nuevaY - 7);
            }

            registrarEvento("MouseEvent [MOUSE_DRAGGED]", String.format(
                    "Arrastrando a nueva posición -> Centro: (X=%.1f, Y=%.1f) | Delta Scene: (%.1f, %.1f)",
                    figuraArrastrable.getCenterX(), figuraArrastrable.getCenterY(), e.getSceneX(), e.getSceneY()
            ));
        });

        // 3. MOUSE_RELEASED: Notificar cuando el usuario suelta el ratón
        figuraArrastrable.setOnMouseReleased((MouseEvent e) -> {
            registrarEvento("MouseEvent [MOUSE_RELEASED - Fin Drag]", String.format(
                    "Arrastre completado en posición final: (X=%.1f, Y=%.1f)",
                    figuraArrastrable.getCenterX(), figuraArrastrable.getCenterY()
            ));
        });

        lienzoArrastre.getChildren().addAll(figuraArrastrable, lblEtiquetaNodo);
        contenedor.getChildren().addAll(lblTitulo, lblDesc, lienzoArrastre);
        tab.setContent(contenedor);
        return tab;
    }

    // =============================================================================
    // 4. KEY EVENT (Pulsación, Liberación y Escritura de Teclas)
    // =============================================================================
    private Tab crearTabKeyEvent() {
        Tab tab = new Tab("4. KeyEvent (Teclado)");

        VBox contenedor = new VBox(15);
        contenedor.setPadding(new Insets(20));

        Label lblTitulo = new Label("Eventos de Teclado (KeyEvent: Pressed, Released, Typed)");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #89b4fa;");

        Label lblDesc = new Label("Haga clic en el cuadro de texto inferior y presione cualquier tecla (letras, flechas, ESC, Espacio, etc.).");
        lblDesc.setStyle("-fx-text-fill: #a6adc8;");

        TextField txtCapturaTeclado = new TextField();
        txtCapturaTeclado.setPromptText("Haga clic aquí y presione teclas...");
        txtCapturaTeclado.setPrefHeight(40);

        // 1. KEY_PRESSED: Captura códigos físicos de teclas (KeyCode)
        txtCapturaTeclado.setOnKeyPressed((KeyEvent e) -> {
            KeyCode codigoTecla = e.getCode(); // Enum con la tecla física (ej. KeyCode.ENTER, KeyCode.SPACE)
            String textoTecla = e.getText();

            registrarEvento("KeyEvent [KEY_PRESSED]", String.format(
                    "Código Tecla: %s | Texto Asociado: '%s' | Modificadores: [Shift: %b, Ctrl: %b, Alt: %b]",
                    codigoTecla, textoTecla, e.isShiftDown(), e.isControlDown(), e.isAltDown()
            ));
        });

        // 2. KEY_TYPED: Captura caracteres Unicode reales generados
        txtCapturaTeclado.setOnKeyTyped((KeyEvent e) -> {
            String caracterUnicode = e.getCharacter();

            registrarEvento("KeyEvent [KEY_TYPED]", String.format(
                    "Carácter Insertado (Unicode): '%s' (Código ASCII/UTF: %d)",
                    caracterUnicode.replace("\r", "\\r").replace("\n", "\\n"),
                    caracterUnicode.isEmpty() ? 0 : (int) caracterUnicode.charAt(0)
            ));
        });

        // 3. KEY_RELEASED: Notifica cuando se suelta una tecla
        txtCapturaTeclado.setOnKeyReleased((KeyEvent e) -> {
            registrarEvento("KeyEvent [KEY_RELEASED]", String.format(
                    "Tecla Liberada: %s", e.getCode()
            ));
        });

        contenedor.getChildren().addAll(lblTitulo, lblDesc, txtCapturaTeclado);
        tab.setContent(contenedor);
        return tab;
    }

    // =============================================================================
    // COMPONENTES AUXILIARES Y LOGGING
    // =============================================================================
    private HBox crearCabecera() {
        Label lblHeader = new Label("⚡ Inspector Programático de Eventos JavaFX");
        lblHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #cdd6f4;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnLimpiar = new Button("🧹 Limpiar Consola");
        btnLimpiar.setOnAction(e -> {
            txtConsola.clear();
            registrarEvento("SISTEMA", "Consola de eventos limpiada.");
        });

        HBox header = new HBox(15, lblHeader, spacer, btnLimpiar);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 0, 15, 0));
        return header;
    }

    private VBox crearPanelConsola() {
        VBox panel = new VBox(8);
        panel.setPadding(new Insets(10, 0, 0, 0));

        Label lblConsola = new Label("📋 Registro de Propiedades del Evento (Console Log):");
        lblConsola.setStyle("-fx-font-weight: bold; -fx-text-fill: #a6e3a1; -fx-font-size: 12px;");

        txtConsola = new TextArea();
        txtConsola.setEditable(false);
        txtConsola.setPrefRowCount(7);
        txtConsola.setStyle("-fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 12px;");

        panel.getChildren().addAll(lblConsola, txtConsola);
        return panel;
    }

    private void registrarEvento(String Categoria, String detalles) {
        String timestamp = LocalTime.now().format(timeFormatter);
        String lineaLog = String.format("[%s] [%s] %s%n", timestamp, Categoria, detalles);

        if (txtConsola != null) {
            txtConsola.appendText(lineaLog);
        }
        System.out.print(lineaLog);
    }
}
