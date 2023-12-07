package Modelo.SolitarioFreeCell;

import Modelo.Global.ObjetosPrincipales.Concretos.Agregado;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioFreeCell.ObjetosConcretos.TableroFreeCell;

public class Movimientos {

    public static boolean moverTableroATablero(TableroFreeCell tablero, Celda celdas, int posPilaOrig, int posCartaOrig, int posPilaDest) {
        if (tablero.revisarCantidadCartas(celdas,posPilaOrig,posCartaOrig,posPilaDest)){
            return tablero.moverEntrePilas(posPilaOrig, posCartaOrig, posPilaDest);
        }
        return false;
    }

    public static boolean moverTableroAFundacion(TableroFreeCell tablero, Fundacion fundaciones, int posCartaOrigen, int posPilaOrig, int posFundDest) {
        if (!ValidacionesFreeCell.es_ultima_carta(posCartaOrigen, tablero, posPilaOrig)) {
            return false;
        }
        Carta cartaInicial = tablero.verCartaPos(posPilaOrig,posCartaOrigen);
        return Agregado.AgregarTableroAOtro(tablero, cartaInicial, posPilaOrig, posFundDest, fundaciones);
    }

    public static boolean moverTableroACelda(TableroFreeCell tablero, int posPilaOrig, int posCartaOrigen, Celda celdas, int posCeldaDestino){
        if (!ValidacionesFreeCell.es_ultima_carta(posCartaOrigen, tablero, posPilaOrig)) {
            return false;
        }
        Carta cartaInicial = tablero.verCartaPos(posPilaOrig,posCartaOrigen);
        return Agregado.AgregarTableroAOtro(tablero, cartaInicial, posPilaOrig, posCeldaDestino, celdas);
    }

    public static boolean moverCeldaACelda(Celda celdas, int posCeldaOrig, int posCeldaDest){
        return celdas.moverEntreCeldas(posCeldaOrig,posCeldaDest);
    }

    public static boolean moverCeldaATablero(Celda celdas, int posCeldaOrig, TableroFreeCell tablero, int posPilaDest){
        Carta carta = celdas.verCarta(posCeldaOrig);
        return Agregado.AgregarCeldaAOtro(celdas, carta, posCeldaOrig, posPilaDest, tablero);
    }

    public static boolean moverCeldaAFundacion(Celda celdas,int posCeldaOrig,Fundacion fundaciones, int posDestino){
        Carta carta = celdas.verCarta(posCeldaOrig);
        return Agregado.AgregarCeldaAOtro(celdas, carta, posCeldaOrig, posDestino, fundaciones);
    }
}
