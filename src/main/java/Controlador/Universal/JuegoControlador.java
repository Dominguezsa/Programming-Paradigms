package Controlador.Universal;

import Modelo.Global.Interfaces.Solitario;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Persistencia;
import Vista.InterfacesYClasesAbstractas.JuegoVista;
import javafx.stage.Stage;

public class JuegoControlador {

    protected final Stage primarystage;
    protected final Solitario modeloJuego;
    protected final JuegoVista vistaJuego;

    protected Carta cartaSeleccionada;

    public JuegoControlador(Stage s, Solitario j, JuegoVista v){
        this.primarystage = s;
        this.modeloJuego = j;
        this.vistaJuego = v;
    }

    public void iniciar() {
        vistaJuego.mostrarJuego(primarystage);
        vistaJuego.setOnClose(Event -> {
            if (!modeloJuego.esJuegoGanado()) {
                Persistencia.guardarPartida(vistaJuego.obtenerOutputStreamGuardado(),modeloJuego);
            } else {
                vistaJuego.borrarPartida();
            }
        }, this.primarystage);
    }
    public void deseleccionarCarta() {
        cartaSeleccionada = null;
    }

    public Carta obtenerCartaSeleccionada(){
        return this.cartaSeleccionada;
    }

    public void establerCartaSeleccionada(Carta carta) {
        if (this.cartaSeleccionada==null){
            this.cartaSeleccionada = carta;
        }
    }
    public void realizarCambio(Carta carta2, int tipofundacion, int finalPosFund) {
        modeloJuego.moverUniversal(cartaSeleccionada, carta2, tipofundacion, finalPosFund);
        deseleccionarCarta();
        actualizarVista();
        if (modeloJuego.esJuegoGanado()){
            vistaJuego.mostrarPartidaGanada();
        }
    }

    public void actualizarVista(){
        vistaJuego.actualizarVista();
    }
}
