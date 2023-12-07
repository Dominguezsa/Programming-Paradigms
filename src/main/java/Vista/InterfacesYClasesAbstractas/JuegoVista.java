package Vista.InterfacesYClasesAbstractas;

import Controlador.Universal.Handler;
import Controlador.Universal.PresentacionControlador;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public abstract class JuegoVista implements Vista {

    protected final Stage primaryStage;
    protected final GridPane root;

    protected Handler acciones;

    public JuegoVista(Stage st){
        this.primaryStage = st;
        this.root = new GridPane();
    }
    public OutputStream obtenerOutputStreamGuardado(String ruta) {
        var file = new File(ruta);
        OutputStream os;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e){
            return null;
        }
        return os;
    }
    public static InputStream obtenerInputStreamGuardado(String ruta) {
        var file = new File(ruta);
        InputStream os;
        try {
            os = new FileInputStream(file);
        } catch (FileNotFoundException e){
            return null;
        }
        return os;
    }

    public void borrarPartida(String ruta){
        var file = new File(ruta);
        if (file.exists()){
            file.delete();
        }
    }

    public void mostrarPartidaGanada(){
        Alert.AlertType typeInfo = Alert.AlertType.CONFIRMATION;
        String title = "Partida ganada";
        String msg = "¿Desea volver al menu principal?";
        ButtonType btnSi = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnNo = new ButtonType("No, salir", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alerta = new Alert(typeInfo,msg,btnSi,btnNo);
        alerta.setHeaderText("Fin de la partida.");
        alerta.setTitle(title);

        Optional<ButtonType> respuesta = alerta.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
            Platform.exit(); // metodo para cerrar todo
        } else if (respuesta.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            new PresentacionControlador(primaryStage);
        }
    }

    public void setOnClose(EventHandler eventHandler, Stage primaryStage){
        primaryStage.setOnCloseRequest(eventHandler);
    }

    public void setAccionesVista(Handler a){
        this.acciones =a;
    }
}
