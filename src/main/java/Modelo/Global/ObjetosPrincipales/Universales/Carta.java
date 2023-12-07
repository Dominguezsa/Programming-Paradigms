package Modelo.Global.ObjetosPrincipales.Universales;

import Modelo.Global.Constantes.Colores;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.TipoObjeto;
import Modelo.Global.Constantes.Valores;

import java.io.Serializable;

public class Carta implements Serializable {
    private final Valores valor;
    private final Palos palo;
    private boolean visible;
    private int tipoContenedor;

    private int columna;
    private int posicion;


    public Carta(Valores numero, Palos palo) {
        this.palo = palo;
        this.valor = numero;
        this.visible = false; //carta esta boca arriba o boca abajo
        this.tipoContenedor = -1;
    }

    public Boolean sonIguales(Carta otra) {
        return this.valor == otra.obtenerValor() && palo == otra.obtenerPalo();
    }

    public Valores obtenerValor() {
        return valor;
    }

    public Palos obtenerPalo() {
        return palo;
    }

    public Colores obtenerColor() {
        return palo.getColor();
    }

    public void hacerVisible(){
        this.visible = true;
    }
    public void hacerNoVisible(){
        this.visible = false;
    }
    public String toString() {
        return this.visible ? "V-" + valor + "-" + palo.toString() : "***";
    }
    /*public String toString(){
        return this.visible ? "V-" + valor.getValue() + "-" + palo.toString().substring(0,4) : "NV-"+valor.getValue() + "-" + palo.toString().substring(0,4);
    }*/
    public boolean esVisible() {
        return this.visible;
    }

    public void ConvertirACelda() {
        this.tipoContenedor = TipoObjeto.TIPOCELDA;
    }

    public void ConvertirAFundacion() {
        this.tipoContenedor = TipoObjeto.TIPOFUNDACION;
    }

    public void ConvertirATablero() {
        this.tipoContenedor = TipoObjeto.TIPOTABLERO;
    }

    public void ConvertirAStock() {
        this.tipoContenedor = TipoObjeto.TIPOSTOCK;
    }

    public boolean esStock() {
        return this.tipoContenedor == TipoObjeto.TIPOSTOCK;
    }
    public boolean esFundacion() {
        return this.tipoContenedor == TipoObjeto.TIPOFUNDACION;
    }
    public boolean esTablero() {
        return this.tipoContenedor == TipoObjeto.TIPOTABLERO;
    }
    public boolean esCelda() {
        return this.tipoContenedor == TipoObjeto.TIPOCELDA;
    }

    public int obtenerColumna() {
        return this.columna;
    }

    public int obtenerPosicion() {
        return this.posicion;
    }

    public void establecerColumna(int n){
        this.columna = n;
    }

    public void establecerPos(int n){
        this.posicion = n;
    }



}
