package Modelo.Global.Interfaces.Validaciones;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;

public interface ValidacionAFundacion {
    boolean validarCartaParaFundacion(Carta cartaNueva, Carta cartaFundacion);
}
