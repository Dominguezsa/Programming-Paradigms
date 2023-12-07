package Modelo.Global.ObjetosPrincipales.Abstactos;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;

import java.io.Serializable;
import java.util.Stack;

public abstract class Stock implements Serializable {

    protected Stack<Carta> stockNovisible;


    public Stock(){
        this.stockNovisible = new Stack<>();
    }

    public void prepararStock(Mazo mazo){
        Carta carta;
        while (mazo.cantidad()>0) {
            carta = mazo.sacarCarta();
            carta.ConvertirAStock();
            this.stockNovisible.add(carta);
        }
    }
    public abstract boolean pasarTandaStock();

    public abstract Carta verPrimerCarta();

    public abstract int cartasTotales();

}