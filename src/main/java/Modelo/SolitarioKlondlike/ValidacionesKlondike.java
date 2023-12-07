package Modelo.SolitarioKlondlike;

import Modelo.Global.Constantes.Valores;
import Modelo.Global.Interfaces.Validaciones.ValidacionAFundacion;
import Modelo.Global.Interfaces.Validaciones.ValidacionATablero;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;

import java.io.Serializable;
import java.util.ArrayList;

public class ValidacionesKlondike implements ValidacionAFundacion, ValidacionATablero, Serializable {

    public boolean validarCartaParaFundacion(Carta cartaNueva, Carta cartaFundacion){
        if (cartaNueva == null || !cartaNueva.esVisible()){
            return false;
        }
        return (cartaFundacion == null && cartaNueva.obtenerValor() == Valores.AS) ||
                (cartaFundacion != null && cartaNueva.obtenerValor().getValue() == cartaFundacion.obtenerValor().getValue() + 1
                        && cartaNueva.obtenerPalo() == cartaFundacion.obtenerPalo());
    }

    public boolean validarCartaParaTablero(Carta cartaNueva, Carta topeDest) {
        if (cartaNueva == null || !cartaNueva.esVisible()){
            return false;
        }

        return (topeDest == null && cartaNueva.obtenerValor() == Valores.REY) ||
                (topeDest != null && cartaNueva.obtenerColor() != topeDest.obtenerColor() &&
                cartaNueva.obtenerValor().getValue() == topeDest.obtenerValor().getValue()-1);
    }

    public static boolean EsUltimaCarta(ArrayList<Carta> pila, int posCartaOrigen) {
        return posCartaOrigen == pila.size()-1;
    }
}


