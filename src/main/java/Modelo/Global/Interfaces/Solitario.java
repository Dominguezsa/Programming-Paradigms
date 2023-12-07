package Modelo.Global.Interfaces;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;

import java.util.Random;

public interface Solitario {
    void prepararPartidaAleatoria();
    void prepararPartidaConSemilla(Random rnd);
    boolean esJuegoGanado();
    int obtenerCartasTotales();

    void moverUniversal(Carta cartaOrigen, Carta cartaDestino, int tipoObjetoDest, int pila);

}
