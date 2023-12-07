package Vista.Juegos;

import Modelo.SolitarioFreeCell.JuegoFreeCell;
import Vista.Constantes.Generales;
import Vista.InterfacesYClasesAbstractas.JuegoVista;
import Vista.Universal.CeldaVista;
import Vista.Universal.FundacionVista;
import Vista.Universal.TableroVista;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;

public class FreeCellVista extends JuegoVista {
    private CeldaVista vistaCelda;
    private FundacionVista vistaFundaciones;
    private TableroVista vistaTablero;

    private final JuegoFreeCell juegoModelo;
    public FreeCellVista(JuegoFreeCell j, Stage st){
        super(st);
        this.juegoModelo=j;
    }

    public void mostrarJuego(Stage primaryStage){
        Scene scene = new Scene(root, Generales.ANCHOFREECELL, Generales.ALTOFREECELL);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FreeCell");
        root.setStyle("-fx-background-color: green;");

        // CELDAS
        vistaCelda = new CeldaVista(juegoModelo.obtenerCeldas());
        vistaCelda.obtenerCeldaBox(root,this.acciones);

        // FUNDACIONES
        vistaFundaciones = new FundacionVista(juegoModelo.obtenerFundacion());
        vistaFundaciones.establecerFundacionesBox(root,4, false,this.acciones);

        // TABLERO
        vistaTablero = new TableroVista(juegoModelo.obtenerTablero());
        vistaTablero.establecerTableroBox(root,this.acciones);

        primaryStage.setWidth(Generales.ANCHOFREECELL);
        primaryStage.setHeight(Generales.ALTOFREECELL);
        primaryStage.show();

        //Espaciado del contenido del gridpane con bordes de este.
        // Recordar que el gridpane tiene el mismo tamaño que la ventana.
        root.setPadding(new Insets(20,0,0,30)); // top, right, bottom, and left
    }

    public void actualizarVista(){
        this.vistaCelda.obtenerCeldaBox(root,this.acciones);
        this.vistaFundaciones.establecerFundacionesBox(root,4, false,this.acciones);
        this.vistaTablero.clearTablero();
        this.vistaTablero.establecerTableroBox(root,this.acciones);
    }

    public OutputStream obtenerOutputStreamGuardado() {
        return super.obtenerOutputStreamGuardado(Generales.RUTAFREECELLGUARDAR);
    }

    public static InputStream obtenerInputStreamGuardado() {
        return obtenerInputStreamGuardado(Generales.RUTAFREECELLGUARDAR);
    }

    public void borrarPartida(){
        super.borrarPartida(Generales.RUTAFREECELLGUARDAR);
    }


}
