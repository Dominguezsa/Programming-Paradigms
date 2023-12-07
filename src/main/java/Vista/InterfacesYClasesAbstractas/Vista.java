package Vista.InterfacesYClasesAbstractas;

import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.OutputStream;

public interface Vista {
    void mostrarJuego(Stage st);

    void setOnClose(EventHandler eventHandler, Stage primaryStage);

    void borrarPartida();

    void mostrarPartidaGanada();

    void actualizarVista();

    OutputStream obtenerOutputStreamGuardado();
}
