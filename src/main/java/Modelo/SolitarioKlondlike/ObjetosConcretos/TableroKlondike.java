package Modelo.SolitarioKlondlike.ObjetosConcretos;

import Modelo.Global.Constantes.Klondlike;
import Modelo.Global.Interfaces.Validaciones.ValidacionATablero;
import Modelo.Global.ObjetosPrincipales.Abstactos.Tablero;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;

import java.io.Serializable;
import java.util.ArrayList;

public class TableroKlondike extends Tablero implements Serializable {

    public TableroKlondike(ValidacionATablero validacion){
        super(validacion);
        for (int i = 0; i < Klondlike.PILASCARTAS; i++) {
            this.pilas.add(new ArrayList<>());
        }
    }

    public void prepararPilas(Mazo mazo){
        for (int i = 0; i < Klondlike.PILASCARTAS; i++) {
            ArrayList<Carta> pila = this.pilas.get(i);
            for (int j = 0; j < i + 1; j++) {
                Carta carta = mazo.sacarCarta();
                carta.ConvertirATablero();
                carta.establecerColumna(i);
                carta.establecerPos(j);
                pila.add(carta);
                if (j == i) {
                    carta.hacerVisible();
                }
            }
        }
    }

}