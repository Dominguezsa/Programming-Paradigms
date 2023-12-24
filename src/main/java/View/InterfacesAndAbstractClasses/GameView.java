package View.InterfacesAndAbstractClasses;

import Controller.Handler;
import Controller.PresentationController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class GameView implements View {

    protected final Stage primaryStage;
    protected final GridPane root;

    protected Handler acciones;

    public GameView(Stage st) {
        this.primaryStage = st;
        this.root = new GridPane();
    }

    public void showWinMessage() {
        Alert.AlertType typeInfo = Alert.AlertType.CONFIRMATION;
        String title = "Partida ganada";
        String msg = "¿Desea volver al menu principal?";
        ButtonType btnSi = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnNo = new ButtonType("No, salir", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alerta = new Alert(typeInfo, msg, btnSi, btnNo);
        alerta.setHeaderText("Fin de la partida.");
        alerta.setTitle(title);

        Optional<ButtonType> respuesta = alerta.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            Platform.exit(); // metodo para cerrar todo
        } else if (respuesta.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            new PresentationController(primaryStage);
        }
    }

    public void setOnClose(EventHandler eventHandler, Stage primaryStage) {
        primaryStage.setOnCloseRequest(eventHandler);
    }

    public void setViewActions(Handler a) {
        this.acciones = a;
    }
}
