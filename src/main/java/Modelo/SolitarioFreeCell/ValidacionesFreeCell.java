package Modelo.SolitarioFreeCell;

import Modelo.Global.Constantes.Valores;
import Modelo.Global.Interfaces.Validaciones.ValidacionACelda;
import Modelo.Global.Interfaces.Validaciones.ValidacionAFundacion;
import Modelo.Global.Interfaces.Validaciones.ValidacionATablero;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioFreeCell.ObjetosConcretos.TableroFreeCell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidacionesFreeCell implements ValidacionATablero, ValidacionAFundacion, ValidacionACelda, Serializable {
    public boolean validarCartaParaFundacion(Carta cartaNueva, Carta cartaFundacion){
        //todas las cartas son siempre VISIBLES en todo el juego.
        if (cartaNueva == null) {
            return false;
        }
        return (cartaFundacion == null && cartaNueva.obtenerValor() == Valores.AS) ||
                (cartaFundacion != null && cartaNueva.obtenerValor().getValue() == cartaFundacion.obtenerValor().getValue() + 1
                        && cartaNueva.obtenerPalo() == cartaFundacion.obtenerPalo());
    }

    public boolean validarCartaParaTablero(Carta cartaNueva, Carta topeDest) {
        //todas las cartas son siempre VISIBLES en todo el juego.
        if (cartaNueva == null) {
            return false;
        }
        //se puede mover CUALQUIER carta a una pila vacia.
        return (topeDest == null) ||
                (cartaNueva.obtenerColor() != topeDest.obtenerColor() &&
                        cartaNueva.obtenerValor().getValue() == topeDest.obtenerValor().getValue()-1);
    }

    public boolean validarCartaParaCelda(Carta cartaNueva, Carta cartaVieja){
        return cartaNueva != null && cartaVieja == null;
    }

    private int obtenerCartasMover(TableroFreeCell tablero, Celda celdas, int posPilaDest){
        int numPilasVacias = 0;
        int numCeldasVacias = 0;
        var pilas = tablero.obtenerPilas();
        ArrayList<Carta> pila;
        for (int i = 0; i < pilas.size(); i++) {
            pila = pilas.get(i);
            if (pila.isEmpty() && i != posPilaDest){
                numPilasVacias +=1;
            }
        }
        for (Carta carta: celdas.getCeldas()){
            if (carta == null){
                numCeldasVacias +=1;
            }
        }
        //Formula para calculo de cantidad de cartas que se pueden mover a la vez.
        //http://www.solitairecentral.com/articles/FreecellPowerMovesExplained.html
        //(1 + number of empty freecells) * 2 ^ (number of empty columns) = maxima cantidad a mover.
        return (int) ((numCeldasVacias+1) * Math.pow(2,numPilasVacias));
    }

    public boolean ValidarMoverEntrePilas(TableroFreeCell tablero, Celda celdas,int posPilaOrig, int posCartaOrig,int posPilaDest){
        if (posCartaOrig >= tablero.obtenerPila(posPilaOrig).size()){
            return false;
        }
        int cantCartasMover = obtenerCartasMover(tablero,celdas,posPilaDest);
        List<Carta> subpila = tablero.CartasDesdePos(posPilaOrig,posCartaOrig);
        return subpila.size() <= cantCartasMover;
    }

    public static boolean es_ultima_carta(int posCartaOrigen, TableroFreeCell tablero, int posPilaOrig) {
        return posCartaOrigen == tablero.cantCartas(posPilaOrig) - 1;
    }
}