package Modelo.Global.ObjetosPrincipales.Concretos;

import Modelo.Global.Interfaces.AgregarCartas.ReceptorDeCartas;
import Modelo.Global.ObjetosPrincipales.Abstactos.Tablero;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;

import java.io.Serializable;

public class Agregado implements Serializable {

    public static boolean AgregarTableroAOtro(Tablero tablero, Carta cartaInicial, int posPilaOrig, int posDestino, ReceptorDeCartas objeto){
        if(objeto.agregarCarta(cartaInicial, posDestino)){
            int posUltimaCarta = tablero.cantCartas(posPilaOrig)-1;
            tablero.sacarCartas(posPilaOrig,posUltimaCarta);
            return true;
        }
        return false;
    }

    public static boolean AgregarStockoAOtro(StockKlondike stock, Carta carta, int posDestino, ReceptorDeCartas objeto){
        if(objeto.agregarCarta(carta, posDestino)){
            stock.sacarCarta();
            return true;
        }
        return false;
    }

    public static boolean AgregarFundacionAOtro(Fundacion fund, Carta cartaInicial, int posPilaOrig, int posDestino, ReceptorDeCartas objeto){
        if(objeto.agregarCarta(cartaInicial, posDestino)){
            fund.sacarCartas(posPilaOrig);
            return true;
        }
        return false;
    }

    public static boolean AgregarCeldaAOtro(Celda sacar, Carta cartaInicial, int posPilaOrig, int posDestino, ReceptorDeCartas objeto){
        if(objeto.agregarCarta(cartaInicial, posDestino)){
            sacar.sacarCarta(posPilaOrig);
            return true;
        }
        return false;
    }


}
