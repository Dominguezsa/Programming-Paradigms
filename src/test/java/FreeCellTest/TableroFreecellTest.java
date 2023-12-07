package FreeCellTest;


import Modelo.Global.Constantes.Generales;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioFreeCell.ObjetosConcretos.TableroFreeCell;
import Modelo.SolitarioFreeCell.ValidacionesFreeCell;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class TableroFreecellTest {
    private TableroFreeCell tablero;
    private Carta reyDiamante;
    private Carta reinaTrebol;
    private Carta jotaDiamante;
    private Mazo mazo;

    @Before
    public void setUp() {
        tablero = new TableroFreeCell(new ValidacionesFreeCell());
        this.mazo = new Mazo();
        reyDiamante = new Carta(Valores.REY, Palos.DIAMANTE);
        reinaTrebol = new Carta(Valores.REINA, Palos.TREBOL);
        jotaDiamante = new Carta(Valores.JOTA,Palos.DIAMANTE);
        reyDiamante.hacerVisible();
        reinaTrebol.hacerVisible();
        jotaDiamante.hacerVisible();
    }

    @Test
    public void testPrepararPilas(){
        tablero.prepararPilas(this.mazo); // Freecell comienza con 8 pilas
        assertEquals(Generales.MAZOCOMPLETO, tablero.cartasTotales()); // Freecell comienza con 52 (todas) cartas
        assertNotNull(tablero.verCartaPos(0, 0));
    }

    @Test
    public void testBorrarCartas() {
        tablero.prepararPilas(this.mazo);
        tablero.sacarCartas(0, 6);
        assertEquals(6, tablero.cantCartas(0));
    }

    @Test
    public void testAgregarUnicaCarta() {
        tablero.prepararPilas(this.mazo);
        tablero.sacarCartas(0,0); //la pila 0 queda vacía, porque se sacan todas desde la posicion 0
        tablero.agregarCarta(reyDiamante,0);
        tablero.agregarCarta(reinaTrebol,0);
        assertEquals(2,tablero.cantCartas(0));
        tablero.agregarCarta(reinaTrebol,0);
        assertEquals(2,tablero.cantCartas(0));
        tablero.sacarCartas(0,0);
        tablero.agregarCarta(reinaTrebol,0);
        assertEquals(1,tablero.cantCartas(0));
        assertTrue(reinaTrebol.sonIguales(tablero.verCartaPos(0,0)));
    }

    @Test
    public void testObtenerPilas() {
        tablero.prepararPilas(this.mazo);
        ArrayList<Carta> pila2 = tablero.obtenerPila(1);
        assertEquals(7, pila2.size());
        assertTrue(pila2.get(0).esVisible());
        assertTrue(pila2.get(1).esVisible());
    }

    @Test
    public void testMoverEntrePilas(){
        tablero.agregarCarta(reyDiamante,0);
        tablero.agregarCarta(reinaTrebol,1);
        tablero.agregarCarta(jotaDiamante,1);
        assertTrue(reinaTrebol.sonIguales(tablero.verCartaPos(1,0)));
        assertTrue(jotaDiamante.sonIguales(tablero.verCartaPos(1,1)));
        assertTrue(reyDiamante.sonIguales(tablero.verCartaPos(0,0)));

        assertTrue(tablero.moverEntrePilas(1,0,0));
        assertEquals(Generales.PILAVACIA,tablero.cantCartas(1));
        assertEquals(3,tablero.cantCartas(0));
        assertTrue(jotaDiamante.sonIguales(tablero.verCartaPos(0,2)));

        tablero.moverEntrePilas(0,0,7);
        assertTrue(jotaDiamante.sonIguales(tablero.verCartaPos(7,2)));
    }


}