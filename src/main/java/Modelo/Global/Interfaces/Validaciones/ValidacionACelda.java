package Modelo.Global.Interfaces.Validaciones;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;

public interface ValidacionACelda {
    boolean validarCartaParaCelda(Carta cartaNueva, Carta cartaCelda);
}
