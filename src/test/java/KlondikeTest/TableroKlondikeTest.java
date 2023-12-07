package KlondikeTest;

import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioKlondlike.ObjetosConcretos.TableroKlondike;
import Modelo.SolitarioKlondlike.ValidacionesKlondike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TableroKlondikeTest {
    private TableroKlondike tablero;
    private Carta rey;
    private Carta reinaTrebol;
    private Carta ochoCorazon;
    private Carta sieteTrebol;
    private Carta nuevePica;

    @Before
    public void setUp() {
        tablero = new TableroKlondike(new ValidacionesKlondike());
        Mazo mazo = new Mazo();
        tablero.prepararPilas(mazo); // Klondike comienza con 7 pilas
        rey = new Carta(Valores.REY, Palos.DIAMANTE);
        reinaTrebol = new Carta(Valores.REINA, Palos.TREBOL);
        ochoCorazon = new Carta(Valores.OCHO, Palos.CORAZON);
        sieteTrebol = new Carta(Valores.SIETE, Palos.TREBOL);
        nuevePica = new Carta(Valores.NUEVE, Palos.PICA);
    }

    @Test
    public void testPrepararPilas() {
        assertEquals(28, tablero.cartasTotales()); // Klondike comienza con 28 cartas
        assertNotNull(tablero.verCartaPos(0, 0));
    }

    @Test
    public void testBorrarCartas() {
        tablero.sacarCartas(0, 0);
        assertEquals(0, tablero.cantCartas(0));
    }

    @Test
    public void testVerCartaNoVisible() {
        Carta carta = tablero.verCartaPos(2,0);
        assertNull(carta);
    }

    @Test
    public void testAgregarUnicaCarta() {
        tablero.sacarCartas(0,0);
        rey.hacerVisible();
        reinaTrebol.hacerVisible();
        tablero.agregarCarta(rey,0);
        tablero.agregarCarta(reinaTrebol,0);
        assertEquals(2,tablero.cantCartas(0));
        tablero.agregarCarta(reinaTrebol,0);
        assertEquals(2,tablero.cantCartas(0));
    }

    @Test
    public void testAgregarCartas() {
        ArrayList<ArrayList<Carta>> tablerito = new ArrayList<>();
        ArrayList<Carta> pila1 = new ArrayList<>();
        ArrayList<Carta> pila2 = new ArrayList<>();
        pila1.add(rey);
        ochoCorazon.hacerVisible();
        sieteTrebol.hacerVisible();
        nuevePica.hacerVisible();
        pila1.add(ochoCorazon);
        pila1.add(sieteTrebol);
        pila2.add(nuevePica);
        tablerito.add(pila1);
        tablerito.add(pila2);
        tablero.prepararPilasEspecificas(tablerito);
        assertTrue(tablero.moverEntrePilas(0,1,1));
        pila1 = tablero.obtenerPila(0);
        pila2 = tablero.obtenerPila(1);
        assertEquals(1, pila1.size());
        assertEquals(3, pila2.size());
        assertTrue(pila1.get(0).esVisible());
    }

    @Test
    public void testObtenerPilas() {
        ArrayList<Carta> pila2 = tablero.obtenerPila(1);
        assertEquals(2, pila2.size());
        assertFalse(pila2.get(0).esVisible());
        assertTrue(pila2.get(1).esVisible());
    }

    @Test
    public void testSeHaceVisible() {
        tablero.sacarCartas(2,2);
        ArrayList<Carta> pila3 = tablero.obtenerPila(2);
        assertEquals(2, pila3.size());
        assertFalse(pila3.get(0).esVisible());
        assertTrue(pila3.get(1).esVisible());
    }
    @Test
    public void testBorrarCarta(){
        ArrayList<ArrayList<Carta>> pilas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<Carta> pila = new ArrayList<>();
            pilas.add(pila);
        }
        tablero.prepararPilasEspecificas(pilas);
        tablero.sacarCartas(0,0);
    }
}
