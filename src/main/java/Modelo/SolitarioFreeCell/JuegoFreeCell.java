package Modelo.SolitarioFreeCell;

import Modelo.Global.Constantes.TipoObjeto;
import Modelo.Global.Interfaces.Solitario;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioFreeCell.ObjetosConcretos.TableroFreeCell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class JuegoFreeCell implements Solitario,Serializable {
    private Celda celda;
    private Fundacion fundacion;
    private TableroFreeCell tablero;
    private boolean juegoTerminado;

    public JuegoFreeCell(){
        InstanciaCero();
        this.juegoTerminado = true;
    }

    public void prepararPartidaAleatoria() {
        Mazo mazo = new Mazo();
        mazo.desordenarMazo();
        this.juegoTerminado = false;
        this.tablero.prepararPilas(mazo);
    }

    public void prepararPartidaConSemilla(Random rnd) {
        Mazo mazo = new Mazo();
        mazo.desordenarMazo(rnd);
        this.juegoTerminado = false;
        this.tablero.prepararPilas(mazo);
    }
    public void prepararPartidaEspecifica(Carta[] celdarecibida, ArrayList<ArrayList<Carta>> fundaciones,
                                          ArrayList<ArrayList<Carta>> pilas) {
        this.juegoTerminado = false;
        this.tablero.prepararPilasEspecificas(pilas);
        this.celda.prepararCeldasEspecificas(celdarecibida);
        this.fundacion.prepararFundacionesEspecificas(fundaciones);
    }


    public boolean moverTableroATablero(int posPilaOrig, int posCartaOrig, int posPilaDest) {
        return Movimientos.moverTableroATablero(tablero,celda, posPilaOrig,posCartaOrig,posPilaDest);
    }

    public boolean moverTableroAFundacion(int posPilaOrig, int posCartaOrigen, int posPilaDest) {
        return Movimientos.moverTableroAFundacion(tablero, fundacion, posCartaOrigen, posPilaOrig, posPilaDest);
    }

    public boolean moverTableroACelda(int posPilaOrig, int posCartaOrigen, int posCeldaDestino){
        return Movimientos.moverTableroACelda(tablero, posPilaOrig, posCartaOrigen, celda, posCeldaDestino);
    }

    public boolean moverCeldaACelda(int posCeldaOrig, int posCeldaDest){
        return Movimientos.moverCeldaACelda(celda, posCeldaOrig, posCeldaDest);
    }

    public boolean moverCeldaATablero(int posCeldaOrig, int posPilaDest){
        return Movimientos.moverCeldaATablero(celda, posCeldaOrig, tablero, posPilaDest);
    }

    public boolean moverCeldaAFundacion(int posCeldaOrig, int posDestino){
        return Movimientos.moverCeldaAFundacion(celda, posCeldaOrig, fundacion, posDestino);
    }
    public boolean obtenerJuegoTerminado() {
        return juegoTerminado;
    }

    public Celda obtenerCeldas() {
        return this.celda;
    }

    public TableroFreeCell obtenerTablero() {
        return this.tablero;
    }

    public Fundacion obtenerFundacion() { return this.fundacion;}

    public boolean esJuegoGanado() {
        return this.tablero.cartasTotales() == 0 && this.celda.cartasTotales() ==0;
    }

    public int obtenerCartasTotales() {
        return this.fundacion.cartasTotales() + this.tablero.cartasTotales() + this.celda.cartasTotales();
    }

    public void InstanciaCero() {
        ValidacionesFreeCell validaciones= new ValidacionesFreeCell();
        this.celda = new Celda(validaciones);
        this.fundacion = new Fundacion(validaciones);
        this.tablero = new TableroFreeCell(validaciones);
    }

    public void moverUniversal(Carta cartaOrigen, Carta cartaDestino, int tipoObjetoDest, int pila) {
        //carta destino es una pila vacia! Ya sea fundacion o tablero.
        if (cartaDestino == null) {
            switch (tipoObjetoDest) {
                case TipoObjeto.TIPOFUNDACION:
                    if (cartaOrigen.esTablero()) {
                        moverTableroAFundacion(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), pila);
                    }
                    if (cartaOrigen.esCelda()) {
                        moverCeldaAFundacion(cartaOrigen.obtenerColumna(), pila);
                    }
                    break;
                case TipoObjeto.TIPOCELDA:
                    if (cartaOrigen.esTablero()) {
                        moverTableroACelda(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), pila);
                    } else if (cartaOrigen.esCelda()) {
                        moverCeldaACelda(cartaOrigen.obtenerColumna(), pila);
                    }
                    break;
                case TipoObjeto.TIPOTABLERO:
                    if (cartaOrigen.esTablero()) {
                        moverTableroATablero(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), pila);
                    } else if (cartaOrigen.esCelda()) {
                        moverCeldaATablero(cartaOrigen.obtenerColumna(), pila);
                    }
                    break;
            }
        }else{
            if (cartaOrigen.esTablero()) {
                if (cartaDestino.esTablero()) {
                    moverTableroATablero(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), cartaDestino.obtenerColumna());
                }else if (cartaDestino.esFundacion()) {
                    moverTableroAFundacion(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), cartaDestino.obtenerColumna());
                }else if (cartaDestino.esCelda()) {
                    moverTableroACelda(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), cartaDestino.obtenerColumna());
                }
            }else if (cartaOrigen.esCelda()) {
                if (cartaDestino.esFundacion()) {
                    moverCeldaAFundacion(cartaOrigen.obtenerColumna(), cartaDestino.obtenerColumna());
                }else if (cartaDestino.esTablero()) {
                    moverCeldaATablero(cartaOrigen.obtenerColumna(), cartaDestino.obtenerColumna());
                }else if (cartaDestino.esCelda()) {
                    moverCeldaACelda(cartaOrigen.obtenerColumna(), cartaDestino.obtenerColumna());
                }
            }
        }
    }
}
