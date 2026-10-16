package com.example.javafx_instanciado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // ===================================================================
        // 1. CONTENEDOR RAÍZ (BorderPane)
        // ===================================================================
        BorderPane root = new BorderPane();

        // ===================================================================
        // 2. CABECERA: MENÚ DE BARRAS Y TOOLBAR (Top)
        // ===================================================================
        MenuBar menuBar = new MenuBar();
        Menu menuArchivo = new Menu("Archivo");
        MenuItem itemGuardar = new MenuItem("Guardar");
        MenuItem itemSalir = new MenuItem("Salir");
        menuArchivo.getItems().addAll(itemGuardar, new SeparatorMenuItem(), itemSalir);
        menuBar.getMenus().addAll(menuArchivo, new Menu("Editar"), new Menu("Ayuda"));

        Button btnNuevo = new Button("📄 Nuevo");
        Button btnExportar = new Button("📤 Exportar");
        TextField txtBuscar = new TextField();
        txtBuscar.setPromptText("Buscar registro...");
        ToolBar toolBar = new ToolBar(btnNuevo, btnExportar, new Separator(), new Label("Filtro:"), txtBuscar);

        VBox topContainer = new VBox(menuBar, toolBar);
        root.setTop(topContainer);

        // ===================================================================
        // 3. BARRA LATERAL DE NAVEGACIÓN (Left)
        // ===================================================================
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(15));
        sidebar.setPrefWidth(180);
        sidebar.setStyle("-fx-background-color: #2c3e50;"); // Estilo CSS embebido en Java

        Label lblNav = new Label("NAVEGACIÓN");
        lblNav.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Button btnUsuarios = new Button("👥 Usuarios");
        btnUsuarios.setMaxWidth(Double.MAX_VALUE);
        Button btnInformes = new Button("📊 Informes");
        btnInformes.setMaxWidth(Double.MAX_VALUE);
        Button btnAjustes = new Button("⚙️ Ajustes");
        btnAjustes.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(lblNav, btnUsuarios, btnInformes, new Separator(), btnAjustes);
        root.setLeft(sidebar);

        // ===================================================================
        // 4. ÁREA CENTRAL: PESTAÑAS Y FORMULARIO GRID (Center)
        // ===================================================================
        TabPane tabPane = new TabPane();

        // Pestaña 1: Formulario de Alta
        Tab tabForm = new Tab("Alta de Usuario");
        tabForm.setClosable(false);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(20));

        Label lblTitulo = new Label("Registro de Nuevo Usuario");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        grid.add(lblTitulo, 0, 0, 2, 1);

        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();
        grid.add(lblNombre, 0, 1);
        grid.add(txtNombre, 1, 1);

        Label lblEmail = new Label("Correo electrónico:");
        TextField txtEmail = new TextField();
        grid.add(lblEmail, 0, 2);
        grid.add(txtEmail, 1, 2);

        Label lblRol = new Label("Rol:");
        ComboBox<String> cbRol = new ComboBox<>();
        cbRol.getItems().addAll("Administrador", "Operador", "Invitado");
        cbRol.getSelectionModel().selectFirst();
        grid.add(lblRol, 0, 3);
        grid.add(cbRol, 1, 3);

        Label lblBio = new Label("Observaciones:");
        TextArea txtBio = new TextArea();
        txtBio.setPrefRowCount(3);
        grid.add(lblBio, 0, 4);
        grid.add(txtBio, 1, 4);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnLimpiar = new Button("Limpiar");

        HBox boxBotones = new HBox(10, btnGuardar, btnLimpiar);
        boxBotones.setAlignment(Pos.CENTER_RIGHT);
        grid.add(boxBotones, 1, 5);

        tabForm.setContent(grid);

        // Pestaña 2: Listado Tabular
        Tab tabTabla = new Tab("Listado de Usuarios");
        tabTabla.setClosable(false);
        TableView<String> tabla = new TableView<>();
        TableColumn<String, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<String, String> colEmail = new TableColumn<>("Correo Electrónico");
        tabla.getColumns().addAll(colNombre, colEmail);
        tabTabla.setContent(tabla);


        // Nueva Pestaña prueba
        Tab tabimpagados = new Tab ("Impagados");
        tabimpagados.setClosable(false);

        // Para meter las tabs en el panel para que sea visible
        tabPane.getTabs().addAll(tabForm, tabTabla, tabimpagados);
        root.setCenter(tabPane);

        // ===================================================================
        // 5. BARRA DE ESTADO INFERIOR (Bottom)
        // ===================================================================
        HBox statusBar = new HBox(10);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        statusBar.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7;");

        Label lblStatus = new Label("🟢 Estado: Listo");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label lblInfo = new Label("v1.0.0 | Servidor: Conectado");

        statusBar.getChildren().addAll(lblStatus, spacer, lblInfo);
        root.setBottom(statusBar);

        // ===================================================================
        // 6. LÓGICA DE NEGOCIO Y EVENTOS (Mezclada con la UI)
        // ===================================================================
        btnGuardar.setOnAction(e -> {
            if (txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty()) {
                lblStatus.setText("❌ Error: Nombre y Email son obligatorios");
                lblStatus.setStyle("-fx-text-fill: red;");
            } else {
                lblStatus.setText("✅ Usuario registrado: " + txtNombre.getText());
                lblStatus.setStyle("-fx-text-fill: green;");
                txtNombre.clear();
                txtEmail.clear();
                txtBio.clear();
            }
        });

        btnLimpiar.setOnAction(e -> {
            txtNombre.clear();
            txtEmail.clear();
            txtBio.clear();
            lblStatus.setText("🟢 Estado: Listo");
            lblStatus.setStyle("-fx-text-fill: black;");
        });

        itemSalir.setOnAction(e -> primaryStage.close());

        // MOSTRAR LA ESCENA
        Scene scene = new Scene(root, 800, 550);
        primaryStage.setTitle("Aplicación Monolítica Imperativa (Sin MVC / FXML)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
