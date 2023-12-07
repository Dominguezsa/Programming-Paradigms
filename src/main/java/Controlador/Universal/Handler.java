package Controlador.Universal;

import Modelo.Global.Constantes.TipoObjeto;
import Modelo.Global.ObjetosPrincipales.Abstactos.Stock;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Vista.Universal.CartaVista;
import Vista.Universal.StockVista;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.Serializable;

public class Handler implements Serializable {
    private final JuegoControlador controlador;

    public Handler(JuegoControlador k){
        this.controlador = k;
    }

    public void moverACelda(int posCelda, Celda celda, ImageView imgSeleccionar){
        if (controlador.obtenerCartaSeleccionada() != null) {
            controlador.realizarCambio(null, TipoObjeto.TIPOCELDA, posCelda);
        } else {
            Vista.Universal.CartaVista.AplicarFiltro(imgSeleccionar);
            controlador.establerCartaSeleccionada(celda.verCarta(posCelda));
        }
    }

    public void moverAFundacion(int posCelda, Fundacion fundacion, ImageView imgSeleccionar,boolean sePuedenSeleccionar){
        if (controlador.obtenerCartaSeleccionada() != null) {
            controlador.realizarCambio(null, TipoObjeto.TIPOFUNDACION, posCelda);
        } else if (sePuedenSeleccionar && fundacion.verPrimerCartaFundacion(posCelda) != null) {
            Vista.Universal.CartaVista.AplicarFiltro(imgSeleccionar);
            controlador.establerCartaSeleccionada(fundacion.verPrimerCartaFundacion(posCelda));
        }
    }

    public void moverATablero(int posCarta, int posPila, Carta carta, ImageView imgSeleccionar){
        if (carta == null && controlador.obtenerCartaSeleccionada() !=null) {
            controlador.realizarCambio(null, TipoObjeto.TIPOTABLERO,  posPila);
        }else if (carta != null){
            if (carta.esVisible() && controlador.obtenerCartaSeleccionada() ==null) {
                Vista.Universal.CartaVista.AplicarFiltro(imgSeleccionar);
                controlador.establerCartaSeleccionada(carta);
            }else if (carta.esVisible()){
                controlador.realizarCambio(carta, TipoObjeto.TIPOTABLERO,posCarta);
            }else{
                controlador.deseleccionarCarta();
                controlador.actualizarVista();
            }
        }
    }

    public void actualizarStock(boolean esStockNoVisible, Stock stock, StockVista stockVista,
                                GridPane root,Carta carta, ImageView imgSeleccionar){
        if (esStockNoVisible){
            stock.pasarTandaStock();
            controlador.deseleccionarCarta();// Se deselecciona la carta cuando se hace click en el stock
            controlador.actualizarVista();
            stockVista.establecerStockBox(root,this);
        } else {
            if (controlador.obtenerCartaSeleccionada() ==null) {
                CartaVista.AplicarFiltro(imgSeleccionar);
                controlador.establerCartaSeleccionada(carta);
            }else{
                CartaVista.DesaplicarFiltro(imgSeleccionar);
                controlador.deseleccionarCarta();
                controlador.actualizarVista();
            }
        }
    }
}
