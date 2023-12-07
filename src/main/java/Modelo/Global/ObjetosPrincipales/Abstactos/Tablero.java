package Modelo.Global.ObjetosPrincipales.Abstactos;

import Modelo.Global.Interfaces.AgregarCartas.ReceptorDeCartas;
import Modelo.Global.Interfaces.Validaciones.ValidacionATablero;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tablero implements Serializable, ReceptorDeCartas {
    protected final ArrayList<ArrayList<Carta>> pilas;

    protected final ValidacionATablero validacion;

    public Tablero(ValidacionATablero validacion){
        this.validacion = validacion;
        this.pilas = new ArrayList<ArrayList<Carta>>();
    }

    public abstract void prepararPilas(Mazo mazo);

    public void prepararPilasEspecificas(ArrayList<ArrayList<Carta>> tablero) {
        this.pilas.clear();
        this.pilas.addAll(tablero);
    }
    public Carta verCartaPos(int posPila,int posCarta){
        //Ve la carta en a posicion de una pila, segun los parametros indicados.
        //Si la  pila esta vacia, la posicion no es valida o la carta no es visible, devuelve null.
        //La posicion cero es la carta mas al fondo de la pila.
        ArrayList<Carta> pilaElegida = this.pilas.get(posPila);
        if (pilaElegida.isEmpty() || posCarta >= pilaElegida.size()  ||
                !pilaElegida.get(posCarta).esVisible()){
            return null;
        }
        return pilaElegida.get(posCarta);
    }
    public boolean agregarCarta(Carta carta, int posPilaDest)
    {
        ArrayList<Carta> pila = this.pilas.get(posPilaDest);
        Carta cartaTope = this.verCartaPos(posPilaDest,pila.size()-1);
        if (this.validacion.validarCartaParaTablero(carta, cartaTope)) {
            pila.add(carta);
            carta.ConvertirATablero();
            carta.establecerColumna(posPilaDest);
            carta.establecerPos(pilas.get(posPilaDest).size()-1);
            return true;
        }
        return false;
    }

    public boolean moverEntrePilas(int posPilaOrig, int posCartaOrig, int posPilaDest)
    {
        // Si la posicion de la primer carta visible seleccionada (el resto se obtiene a partir de esa posicion)
        // no es valida, no realiza el movimiento.
        if (posCartaOrig >= this.pilas.get(posPilaOrig).size()){
            return false;
        }
        List<Carta> subPila = this.CartasDesdePos(posPilaOrig,posCartaOrig);
        ArrayList<Carta> pilaDest = this.pilas.get(posPilaDest);
        Carta cartaDest = this.verCartaPos(posPilaDest,pilaDest.size()-1);
        Carta cartaOrig = subPila.get(0);
        int pilaSize = pilaDest.size();
        if (this.validacion.validarCartaParaTablero(cartaOrig,cartaDest)){
            for (Carta carta : subPila) {
                pilaDest.add(carta);
                carta.establecerPos(pilaSize);
                carta.establecerColumna(posPilaDest);
                pilaSize++;
            }
            this.sacarCartas(posPilaOrig, posCartaOrig);
            return true;
        }
        return false;
    }
    public List<Carta> CartasDesdePos(int posPila, int posCarta){
        ArrayList<Carta> pila = this.pilas.get(posPila);
        return pila.subList(posCarta,pila.size());
    }
    public void sacarCartas(int posPila, int posCarta) {
        //Saca todas las cartas a partir de una posicion del arreglo que representa la pila.
        ArrayList<Carta> pilaOrigen = this.pilas.get(posPila);
        if (pilaOrigen.size() > posCarta) {
            pilaOrigen.subList(posCarta, pilaOrigen.size()).clear();
        }
        if (!pilaOrigen.isEmpty()) {
            Carta cartaTope = pilaOrigen.get(pilaOrigen.size() - 1);
            cartaTope.hacerVisible();
        }
    }
    public int cartasTotales() {
        int numero = 0;
        for (ArrayList<Carta> pila : this.pilas) {
            numero += pila.size();
        }
        return numero;
    }
    public int cantCartas(int posPila){
        return this.pilas.get(posPila).size();
    }

    public ArrayList<ArrayList<Carta>> obtenerPilas() {
        return this.pilas;
    }

    public int obtenerCantPilas() {
        return this.pilas.size();
    }

    public ArrayList<Carta> obtenerPila(int posPila){
        return this.pilas.get(posPila);
    }
}