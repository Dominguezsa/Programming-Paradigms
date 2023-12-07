package Modelo.Global.Interfaces.AgregarCartas;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;

public interface ReceptorDeCartas {
    boolean agregarCarta(Carta cartaNueva, int posDest);
}
