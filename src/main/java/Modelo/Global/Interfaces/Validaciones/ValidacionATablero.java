package Modelo.Global.Interfaces.Validaciones;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;

public interface ValidacionATablero {
    boolean validarCartaParaTablero(Carta cartaNueva, Carta topeDest);
}
