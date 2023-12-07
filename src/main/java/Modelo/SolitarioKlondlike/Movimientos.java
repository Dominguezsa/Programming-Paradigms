package Modelo.SolitarioKlondlike;

import Modelo.Global.ObjetosPrincipales.Concretos.Agregado;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;
import Modelo.SolitarioKlondlike.ObjetosConcretos.TableroKlondike;

import java.io.Serializable;
import java.util.ArrayList;


public class Movimientos implements Serializable {
    //la idea es que todos los movimientos devuelvan true o false de acuerdo si se hizo el movimiento o no

    public static boolean moverStockAFundacion(StockKlondike stock, Fundacion fundaciones, int posFund) {
        Carta carta = stock.verPrimerCarta();
        return Agregado.AgregarStockoAOtro(stock, carta, posFund, fundaciones);
    }

    public static boolean moverStockATablero(TableroKlondike tablero, StockKlondike stock, int posPilaDest) {
        Carta carta = stock.verPrimerCarta();
        return Agregado.AgregarStockoAOtro(stock, carta, posPilaDest, tablero);
    }

    public static boolean moverFundacionATablero(Fundacion fundaciones, TableroKlondike tablero,int posFundOrig, int posPilaDest) {
        Carta carta = fundaciones.verPrimerCartaFundacion(posFundOrig);
        return Agregado.AgregarFundacionAOtro(fundaciones, carta, posFundOrig, posPilaDest, tablero);
    }


    public static boolean moverFundacionAFundacion(Fundacion fundaciones, int posFundOrig, int posFundDest) {
        return fundaciones.moverEntreFundaciones(posFundOrig,posFundDest);
    }

    public static boolean moverTableroATablero(TableroKlondike tablero, int posPilaOrig, int posCartaOrig, int posPilaDest) {
        return tablero.moverEntrePilas(posPilaOrig, posCartaOrig, posPilaDest);
    }

    public static boolean moverTableroAFundacion(TableroKlondike tablero, Fundacion fundaciones,int posCartaOrigen, int posPilaOrig, int posFundDest) {
        ArrayList<Carta> pila = tablero.obtenerPila(posPilaOrig);
        if (ValidacionesKlondike.EsUltimaCarta(pila, posCartaOrigen)) {
            Carta cartaInicial = tablero.verCartaPos(posPilaOrig, posCartaOrigen);
            return Agregado.AgregarTableroAOtro(tablero, cartaInicial, posPilaOrig, posFundDest, fundaciones);
        }
        return false;
    }
}
