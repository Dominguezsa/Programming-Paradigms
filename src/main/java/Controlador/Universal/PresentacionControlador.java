package Controlador.Universal;

import Modelo.Global.ObjetosPrincipales.Universales.Persistencia;
import Modelo.SolitarioFreeCell.JuegoFreeCell;
import Modelo.SolitarioKlondlike.JuegoKlondike;
import Vista.Juegos.FreeCellVista;
import Vista.Juegos.KlondikeVista;
import Vista.Universal.PresentacionVista;
import javafx.stage.Stage;

public class PresentacionControlador {

    private final Stage st;
    public PresentacionControlador(Stage primaryStage) {
        PresentacionVista vista = new PresentacionVista(primaryStage, this);

        vista.setButtonKlondikeClick(event -> {
            vista.aplicarEstiloBoton(vista.obtenerBoton(0));
            iniciarJuegoKlondike();
        });

        vista.setButtonFreeCellClick(event -> {
            vista.aplicarEstiloBoton(vista.obtenerBoton(1));
            iniciarJuegoFreeCell();
        });

        this.st = primaryStage;
        vista.mostrar();
    }

    public void

    iniciarJuegoKlondike() {
        var is = KlondikeVista.obtenerInputStreamGuardado();
        JuegoKlondike j;
        KlondikeVista v;
        JuegoControlador controlador;
        if (is != null) {
            j = (JuegoKlondike) Persistencia.cargarPartida(is);
        } else {
            j = new JuegoKlondike();
            j.prepararPartidaAleatoria();
        }
        v = new KlondikeVista(j,st);
        controlador = new JuegoControlador(st, j, v);
        v.setAccionesVista(new Handler(controlador));
        controlador.iniciar();
    }

    public void iniciarJuegoFreeCell() {
        var is = FreeCellVista.obtenerInputStreamGuardado();
        JuegoFreeCell j;
        FreeCellVista v;
        JuegoControlador controlador;
        if (is != null) {
            j = (JuegoFreeCell) Persistencia.cargarPartida(is);
        } else {
            j = new JuegoFreeCell();
            j.prepararPartidaAleatoria();
        }
        v = new FreeCellVista(j,st);
        controlador = new JuegoControlador(st, j, v);
        v.setAccionesVista(new Handler(controlador));
        controlador.iniciar();
    }
}