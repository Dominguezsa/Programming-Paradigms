package Modelo.SolitarioFreeCell.ObjetosConcretos;

import Modelo.Global.Constantes.Freecell;
import Modelo.Global.Interfaces.Validaciones.ValidacionATablero;
import Modelo.Global.ObjetosPrincipales.Abstactos.Tablero;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioFreeCell.ValidacionesFreeCell;

import java.io.Serializable;
import java.util.ArrayList;

public class TableroFreeCell extends Tablero implements Serializable {
    public TableroFreeCell(ValidacionATablero validacion){
        super(validacion);
        for (int i=0;i<8;i++){
            super.pilas.add(new ArrayList<Carta>());
        }
    }
    public void prepararPilas(Mazo mazo) {
        //Agrego 7 cartas a las primeras 4 pilas y 6 a las segundas 4 pilas.
        Carta carta;
        for (int i = 0; i <= Freecell.TOPEPILASPRIMERAS; i++) {
            for (int j = 0; j < Freecell.CARTASPILASPRIMERAS; j++) {
                carta = mazo.sacarCarta();
                carta.ConvertirATablero();
                carta.establecerColumna(i);
                carta.establecerPos(j);
                carta.hacerVisible();
                super.pilas.get(i).add(carta);
            }
        }
        for (int i = Freecell.TOPEPILASPRIMERAS+1; i <= Freecell.TOPEPILASSEGUNDAS; i++) {
            for (int j = 0; j < Freecell.CARTASPILASSEGUNDAS; j++) {
                carta = mazo.sacarCarta();
                carta.hacerVisible();
                carta.ConvertirATablero();
                carta.establecerColumna(i);
                carta.establecerPos(j);
                super.pilas.get(i).add(carta);
            }
        }
    }
    public boolean revisarCantidadCartas(Celda celdas, int posPilaOrig, int posCartaOrig, int posPilaDest){
        ValidacionesFreeCell validacion = (ValidacionesFreeCell) this.validacion;
        return validacion.ValidarMoverEntrePilas(this,celdas,posPilaOrig,posCartaOrig,posPilaDest);
    }
}