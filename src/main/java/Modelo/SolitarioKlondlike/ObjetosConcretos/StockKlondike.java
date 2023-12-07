package Modelo.SolitarioKlondlike.ObjetosConcretos;

import Modelo.Global.ObjetosPrincipales.Abstactos.Stock;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class StockKlondike extends Stock implements Serializable {

    private ArrayList<Carta> cartasVisibles;

    public StockKlondike(){
        super(); // Inicializa el stock no visible con una pila.
        this.cartasVisibles = new ArrayList<>();
    }
    public boolean pasarTandaStock() {
        if (!stockNovisible.isEmpty()) {
            Carta cartanueva = stockNovisible.pop();
            cartanueva.hacerVisible();
            cartasVisibles.add(cartanueva);
            return true;
        }
        //en caso de que el stock este vacio, paso todas las cartas visibles al stock no visible de nuevo.
        for (int i = cartasVisibles.size()-1; i >= 0; i--) {
            Carta carta =  cartasVisibles.remove(i);
            carta.hacerNoVisible();
            stockNovisible.add(carta);
        }
        return true;
    }

    public Carta sacarCarta()  {
        if (cartasVisibles.isEmpty()){
            //Si no hay cartas visibles, devuelvo null.
            return null;
        }
        return cartasVisibles.remove(cartasVisibles.size()-1);
    }

    public Carta verPrimerCarta(){
        //Devuelve la primer carta visible del stock. Si no hay ninguna, devuelve null.
        if (cartasVisibles.isEmpty()){
            return null;
        }
        return cartasVisibles.get(cartasVisibles.size()-1);
    }

    public int cartasTotales(){
        return this.cartasVisibles.size() + this.stockNovisible.size();
    }
    public Stack<Carta> obtenerStockNoVisible(){
        return this.stockNovisible;
    }
    public ArrayList<Carta> obtenerCartasVisibles(){
        return this.cartasVisibles;
    }

    public void prepararStockEspecifico(Stack<Carta> stockNovisiblee, ArrayList<Carta> stockVisiblee) {
        this.stockNovisible.clear();
        this.cartasVisibles.clear();
        this.stockNovisible = stockNovisiblee;
        this.cartasVisibles = stockVisiblee;
    }

    public void pasarVisiblesANoVisibles() {
        while (!cartasVisibles.isEmpty()) {
            Carta c = cartasVisibles.remove(cartasVisibles.size()-1);
            stockNovisible.add(c);
        }

    }

}