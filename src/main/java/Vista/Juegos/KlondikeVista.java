package Vista.Juegos;

import Modelo.SolitarioKlondlike.JuegoKlondike;
import Vista.Constantes.Generales;
import Vista.InterfacesYClasesAbstractas.JuegoVista;
import Vista.Universal.FundacionVista;
import Vista.Universal.StockVista;
import Vista.Universal.TableroVista;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;

public class KlondikeVista extends JuegoVista {
    StockVista vistaStock;
    FundacionVista vistaFundaciones;
    TableroVista vistaTablero;


    private final JuegoKlondike juegoModelo;

    public KlondikeVista(JuegoKlondike j, Stage st){
        super(st);
        this.juegoModelo=j;
    }

    public void mostrarJuego(Stage primaryStage) {
        Scene sc = new Scene(root, Generales.ANCHOKLONDIKE, Generales.ALTOKLONDIKE);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Klondike");
        root.setStyle("-fx-background-color: green;");

        // STOCK
        vistaStock = new StockVista(juegoModelo.obtenerStock());
        vistaStock.establecerStockBox(root,this.acciones);

        // FUNDACIONES
        vistaFundaciones = new FundacionVista(juegoModelo.obtenerFundaciones());
        vistaFundaciones.establecerFundacionesBox(root,3, true,this.acciones);

        // TABLERO
        vistaTablero = new TableroVista(juegoModelo.obtenerTablero());
        vistaTablero.establecerTableroBox(root,this.acciones);

        primaryStage.setWidth(Generales.ANCHOKLONDIKE);
        primaryStage.setHeight(Generales.ALTOKLONDIKE);
        primaryStage.show();
        //Espaciado del contenido del gridpane con bordes de este.
        // Recordar que el gridpane tiene el mismo tamaño que la ventana.
        root.setPadding(new Insets(20,0,0,30)); // top, right, bottom, and left
    }

    public void actualizarVista(){
        this.vistaStock.establecerStockBox(root,this.acciones);
        this.vistaFundaciones.establecerFundacionesBox(root,3, true,this.acciones);
        this.vistaTablero.clearTablero();
        this.vistaTablero.establecerTableroBox(root,this.acciones);
    }


    public OutputStream obtenerOutputStreamGuardado() {
        return super.obtenerOutputStreamGuardado(Generales.RUTAKLONDIKEGUARDAR);
    }

    public static InputStream obtenerInputStreamGuardado() {
        return obtenerInputStreamGuardado(Generales.RUTAKLONDIKEGUARDAR);
    }

    public void borrarPartida(){
        super.borrarPartida(Generales.RUTAKLONDIKEGUARDAR);
    }
}
