package Modelo.Global.ObjetosPrincipales.Concretos;

import Modelo.Global.Constantes.Freecell;
import Modelo.Global.Interfaces.AgregarCartas.ReceptorDeCartas;
import Modelo.Global.Interfaces.Validaciones.ValidacionACelda;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;

import java.io.Serializable;

public class Celda implements ReceptorDeCartas, Serializable {
    private final Carta[] celdas;
    private final ValidacionACelda validacion;
    public Celda (ValidacionACelda validacion){
        this.validacion = validacion;
        this.celdas = new Carta[4];
    }


    public boolean agregarCarta(Carta cartaNueva, int posCelda){
        Carta cartaCelda = this.celdas[posCelda];
        if (validacion.validarCartaParaCelda(cartaNueva,cartaCelda)){
            this.celdas[posCelda] = cartaNueva;
            cartaNueva.ConvertirACelda();
            cartaNueva.establecerColumna(posCelda);
            return true;
        }
        return false;
    }
    public void sacarCarta(int posCelda){
        this.celdas[posCelda] = null;
    }

    public boolean moverEntreCeldas(int posCeldaOrig,int posCeldaDest){
        Carta cartaOrig = verCarta(posCeldaOrig);
        Carta cartaDest = verCarta(posCeldaDest);
        if (validacion.validarCartaParaCelda(cartaOrig,cartaDest)){
            this.celdas[posCeldaDest] = cartaOrig;
            this.celdas[posCeldaOrig] = null;
            cartaOrig.establecerColumna(posCeldaDest);
            return true;
        }
        return false;
    }
    public Carta verCarta(int posCelda){
        return this.celdas[posCelda];
    }

    public Carta[] getCeldas(){
        return this.celdas;
    }

    public int cartasTotales() {
        int numero = 0;
        for (Carta c : this.celdas) {
            if (c != null) {
                numero += 1;
            }
        }
        return numero;
    }

    public void prepararCeldasEspecificas(Carta[] cartas){
        System.arraycopy(cartas, 0, this.celdas, 0, Freecell.CELDAS);
    }
}
