package Modelo.Global.ObjetosPrincipales.Universales;

import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo implements Serializable {
    private final ArrayList<Carta> mazo;

    public Mazo(){
        ArrayList<Carta> arr = new ArrayList<>();

        for (Palos palo : Palos.values()) {
            for (Valores valor: Valores.values()){
                Carta card = new Carta(valor, palo);
                arr.add(card);
            }
        }
        this.mazo = arr;
    }

    public int cantidad() {
        return mazo.size();
    }

    public Carta sacarCarta() {
        return mazo.remove(mazo.size()-1);
    }

    public ArrayList<Carta> obtenerCartas(){
        return new ArrayList<>(this.mazo);
    }
    public void desordenarMazo() {
        Collections.shuffle(mazo);
    }

    public void desordenarMazo(Random rnd) {
        Collections.shuffle(mazo,rnd);
    }

    public Carta verCarta(Palos palo, Valores valor){
        Carta cartaRes = null;
        for (Carta carta:mazo){
            if (carta.obtenerValor()==valor && carta.obtenerPalo()==palo){
                cartaRes = carta;
                break;
            }
        }
        return cartaRes;
    }
}
