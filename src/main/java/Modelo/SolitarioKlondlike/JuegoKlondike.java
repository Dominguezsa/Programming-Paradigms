package Modelo.SolitarioKlondlike;


import Modelo.Global.Constantes.TipoObjeto;
import Modelo.Global.Interfaces.Solitario;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;
import Modelo.SolitarioKlondlike.ObjetosConcretos.TableroKlondike;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class JuegoKlondike implements Solitario, Serializable {
    private final Fundacion fundaciones;
    private final StockKlondike stock;
    private final TableroKlondike tablero;
    private boolean juegoTerminado;

    public JuegoKlondike() {
        ValidacionesKlondike validaciones = new ValidacionesKlondike();
        this.fundaciones = new Fundacion(validaciones);
        this.stock = new StockKlondike();
        this.tablero = new TableroKlondike(validaciones);
        this.juegoTerminado = true;
    }

    public void prepararPartidaAleatoria() {
        Mazo mazo = new Mazo();
        mazo.desordenarMazo();
        this.juegoTerminado = false;
        this.tablero.prepararPilas(mazo);
        this.stock.prepararStock(mazo);
    }

    public void prepararPartidaConSemilla(Random rnd) {
        Mazo mazo = new Mazo();
        mazo.desordenarMazo(rnd);
        this.juegoTerminado = false;
        this.tablero.prepararPilas(mazo);
        this.stock.prepararStock(mazo);
    }

    public void prepararPartidaEspecifica(Stack<Carta> stockNovisible,
                                          ArrayList<Carta> stockVisible, ArrayList<ArrayList<Carta>> fundaciones,
                                          ArrayList<ArrayList<Carta>> pilas) {
        this.juegoTerminado = false;
        this.tablero.prepararPilasEspecificas(pilas);
        this.stock.prepararStockEspecifico(stockNovisible, stockVisible);
        this.fundaciones.prepararFundacionesEspecificas(fundaciones);
    }

    public boolean pasarCartasStock() {
        //Modela hacer un click en el stock no visible y agregar una carta a la pila visible del stock.
        return this.stock.pasarTandaStock();
    }


    //TODOS LOS MOVIMIENTOS DEVUELVEN TRUE O FALSE DE ACUERDO A SI SE HIZO EL CAMBIO O NO.
    public boolean moverStockAFundacion(int posPilaDest) {
        return Movimientos.moverStockAFundacion(this.stock, this.fundaciones, posPilaDest);
    }

    public boolean moverStockATablero(int posPilaDest) {
        return Movimientos.moverStockATablero(this.tablero, this.stock, posPilaDest);
    }

    public boolean moverFundacionATablero(int posPilaOrig, int posPilaDest) {
        return Movimientos.moverFundacionATablero(this.fundaciones, this.tablero, posPilaOrig, posPilaDest);
    }

    public boolean moverFundacionAFundacion(int posFundOrig, int posFundDest) {
        return Movimientos.moverFundacionAFundacion(this.fundaciones, posFundOrig, posFundDest);
    }

    public boolean moverTableroATablero(int posPilaOrig, int posCartaOrig, int posPilaDest) {
        return Movimientos.moverTableroATablero(this.tablero, posPilaOrig, posCartaOrig, posPilaDest);
    }

    public boolean moverTableroAFundacion(int posPilaOrig, int posCartaOrigen, int posPilaDest) {
        return Movimientos.moverTableroAFundacion(this.tablero, this.fundaciones, posCartaOrigen, posPilaOrig, posPilaDest);
    }

    public boolean obtenerJuegoTerminado() {
        return juegoTerminado;
    }

    public Fundacion obtenerFundaciones() {
        return this.fundaciones;
    }

    public StockKlondike obtenerStock() {
        return this.stock;
    }

    public TableroKlondike obtenerTablero() {
        return this.tablero;
    }

    public int obtenerCartasTotales() {
        return this.fundaciones.cartasTotales() + this.tablero.cartasTotales() + this.stock.cartasTotales();
    }

    public boolean esJuegoGanado() {
        //Si hay 52 cartas en las fundaciones el juego se ganó,(suponiendo que estan ordenadas,
        //porque no podrian ingresar de otra forma);
        return this.fundaciones.cartasTotales() == 52;
    }

    public void moverUniversal(Carta cartaOrigen, Carta cartaDestino, int tipoObjetoDest,int pila) {
        //carta destino es una pila vacia! Ya sea fundacion o tablero.
        if (cartaDestino == null){
            if (tipoObjetoDest == TipoObjeto.TIPOFUNDACION) {
                if (cartaOrigen.esStock()) {
                    moverStockAFundacion(pila);
                }
                if (cartaOrigen.esTablero()) {
                    moverTableroAFundacion(cartaOrigen.obtenerColumna(),cartaOrigen.obtenerPosicion(), pila);
                }
                if (cartaOrigen.esFundacion()) {
                    moverFundacionAFundacion(cartaOrigen.obtenerColumna(), pila);
                }
            } else {
                if (cartaOrigen.esFundacion()) {
                    moverFundacionATablero(cartaOrigen.obtenerColumna(), pila);
                }
                if (cartaOrigen.esStock()) {
                    moverStockATablero(pila);
                }
                if (cartaOrigen.esTablero()) {
                    moverTableroATablero(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), pila);
                }
            }
        }else{
            if (cartaOrigen.esTablero() && cartaDestino.esTablero()) {
                moverTableroATablero(cartaOrigen.obtenerColumna(), cartaOrigen.obtenerPosicion(), cartaDestino.obtenerColumna());
            }
            if (cartaOrigen.esStock() && cartaDestino.esTablero()) {
                moverStockATablero(cartaDestino.obtenerColumna());
            }
            if (cartaOrigen.esFundacion() && cartaDestino.esTablero()) {
                moverFundacionATablero(cartaOrigen.obtenerColumna(), cartaDestino.obtenerColumna());
            }
        }
    }
}