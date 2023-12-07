package Vista.Universal;

import Controlador.Universal.PresentacionControlador;
import Vista.Constantes.Generales;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PresentacionVista {
    private final Stage primaryStage;
    private final PresentacionControlador controlador;

    private final ArrayList<Button> buttons;

    public PresentacionVista(Stage primaryStage, PresentacionControlador controlador) {
        this.primaryStage = primaryStage;
        this.controlador = controlador;
        this.buttons = new ArrayList<Button>();
        menuPrincipal();
    }

    private void menuPrincipal() {
        // Crear una VBox para organizar elementos en el centro vertical
        VBox vbox = new VBox(20); // Espacio entre elementos
        vbox.setAlignment(Pos.CENTER);

        Text title = new Text("Elija la versión de solitario:");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox buttonContainer = crearButtonContainer();

        vbox.getChildren().addAll(title, buttonContainer);

        // Establecer el fondo en verde
        vbox.setStyle("-fx-background-color: #0096ff;");

        Scene scene = new Scene(vbox, Generales.ANCHOPRESENTACION, Generales.ALTOPRESENTACION);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Juego Solitario");

        // Establecer el tamaño de la ventana
        primaryStage.setWidth(Generales.ANCHOPRESENTACION);
        primaryStage.setHeight(Generales.ALTOPRESENTACION);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private HBox crearButtonContainer() {
        Button buttonKlondike = new Button("Klondike");
        Button buttonFreeCell = new Button("FreeCell");

        // Establecer un estilo para los botones
        buttonKlondike.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");
        buttonFreeCell.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        // Aplicar estilo al botón cuando se presiona
        buttonKlondike.setOnMousePressed(event -> buttonKlondike.setStyle("-fx-background-color: #6d6d6d; -fx-text-fill: white; -fx-font-size: 16px;"));
        buttonFreeCell.setOnMousePressed(event -> buttonFreeCell.setStyle("-fx-background-color: #6d6d6d; -fx-text-fill: white; -fx-font-size: 16px;"));

        buttons.add(buttonKlondike);
        buttons.add(buttonFreeCell);
        // Crear un HBox para organizar los botones horizontalmente
        HBox buttonContainer = new HBox(10); // Espacio entre elementos
        buttonContainer.getChildren().addAll(buttonKlondike, buttonFreeCell);
        buttonContainer.setAlignment(Pos.CENTER); // Centrar horizontalmente
        return buttonContainer;
    }

    public void mostrar() {
        primaryStage.show();
    }

    public void setButtonKlondikeClick(EventHandler eventHandler){
        buttons.get(0).setOnAction(eventHandler);
    }

    public void setButtonFreeCellClick(EventHandler eventHandler){
        buttons.get(1).setOnAction(eventHandler);
    }

    public void aplicarEstiloBoton(Button btn){
        btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px;");
    }

    public Button obtenerBoton(int n){
        return this.buttons.get(n);
    }
}
