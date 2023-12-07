package ObjetosPrincipalesTest;


import Modelo.Global.Constantes.Klondlike;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioKlondlike.ValidacionesKlondike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FundacionTest {
    private Fundacion fund;
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta4;

    @Before
    public void setUp() {
        fund = new Fundacion(new ValidacionesKlondike());
        ArrayList<ArrayList<Carta>>  fundacionesArr = new ArrayList<ArrayList<Carta>>();
        for (int i = 0; i< Klondlike.FUNDACIONES; i++){
            fundacionesArr.add(new ArrayList<Carta>());
        }
        fund.prepararFundacionesEspecificas(fundacionesArr);
        carta1 = new Carta(Valores.AS, Palos.DIAMANTE);
        carta2 = new Carta(Valores.DOS, Palos.DIAMANTE);
        carta3 = new Carta(Valores.DOS, Palos.DIAMANTE);
        carta4 = new Carta(Valores.AS, Palos.CORAZON);
    }

    @Test
    public void testAgregarCarta() {
        carta1.hacerVisible();
        assertTrue(fund.agregarCarta(carta1, 0));
        assertEquals(1, fund.cantCartas(0));
    }

    @Test
    public void testNoAgregarCarta() {
        assertFalse(fund.agregarCarta(carta1, 0));
        assertEquals(0, fund.cantCartas(0));
    }

    @Test
    public void testAgregarCartaNoValida() {
        // Intentar agregar una carta que no es un As a una fundación vacía
        Carta carta = new Carta(Valores.REY, Palos.TREBOL);
        assertFalse(fund.agregarCarta(carta, 1));
        assertEquals(0, fund.cantCartas(1));
    }

    @Test
    public void testSacarCartaFundacion() {
        carta4.hacerVisible();
        fund.agregarCarta(carta4, 0);
        Carta cartaSacada = fund.verPrimerCartaFundacion(0);
        fund.sacarCartas(0);
        assertNotNull(cartaSacada);
        assertEquals(carta4, cartaSacada);
    }

    @Test
    public void testSacarCartaFundacionVacia() {
        Carta cartaSacada = fund.verPrimerCartaFundacion(3);
        fund.sacarCartas(0);
        assertNull(cartaSacada);
    }


    @Test
    public void testAgregarAs() {
        carta1.hacerVisible();
        carta2.hacerVisible();
        carta3.hacerVisible();
        carta4.hacerNoVisible();
        assertTrue(fund.agregarCarta(carta1, 0));
        assertTrue(fund.agregarCarta(carta2, 0));
        assertFalse(fund.agregarCarta(carta3, 2)); // False porque no es un AS
        assertFalse(fund.agregarCarta(carta4, 3)); // False porque no es visible
    }

    @Test
    public void testCambio() {
        carta1.hacerVisible();
        carta2.hacerVisible();
        carta3.hacerVisible();
        carta4.hacerNoVisible();
        assertTrue(fund.agregarCarta(carta1, 0));
        assertTrue(fund.moverEntreFundaciones(0,1));
        assertFalse(fund.moverEntreFundaciones(2, 3)); // False porque no es un AS
        assertTrue(fund.moverEntreFundaciones(1, 3)); // False porque no es visible
    }

    @Test
    public void testObtenerFundaciones() {
        ArrayList<ArrayList<Carta>> fundaciones = fund.obtenerFundaciones();
        assertEquals(Klondlike.FUNDACIONES, fundaciones.size());
        for (ArrayList<Carta> fundacion : fundaciones) {
            assertEquals(0, fundacion.size()); // Todas las fundaciones deben estar vacías inicialmente
        }
        carta1.hacerVisible();
        carta2.hacerVisible();
        carta3.hacerVisible();
        carta4.hacerVisible();
        fund.agregarCarta(carta1, 0);
        fund.agregarCarta(carta2, 1);
        fund.agregarCarta(carta3, 2);
        fund.agregarCarta(carta4, 3);
        assertEquals(1, fund.cantCartas(0));
        assertEquals(0, fund.cantCartas(1));
        assertEquals(0, fund.cantCartas(2));
        assertEquals(1, fund.cantCartas(3));
    }

    @Test
    public void testCantidad() {
        carta1.hacerVisible();
        carta2.hacerVisible();
        carta3.hacerVisible();
        carta4.hacerVisible();
        assertTrue(fund.agregarCarta(carta1, 0));
        assertEquals(1,fund.cartasTotales());
        assertTrue(fund.agregarCarta(carta4, 1));
        assertEquals(2,fund.cartasTotales());
        //chequeo de cada fundacion particularmente
        assertEquals(1,fund.cantCartas(0));
        assertEquals(1,fund.cantCartas(1));
        assertEquals(0,fund.cantCartas(2));
        assertEquals(0,fund.cantCartas(3));
    }

    @Test
    public void SacarCarta() {
        carta1.hacerVisible();
        carta2.hacerVisible();
        carta3.hacerVisible();
        carta4.hacerVisible();
        assertTrue(fund.agregarCarta(carta1, 0));
        assertTrue(fund.agregarCarta(carta4, 1));
        Carta cartanue = fund.verPrimerCartaFundacion(0);
        fund.sacarCartas(0);
        assertEquals(cartanue, carta1);
        assertNotEquals(cartanue, carta2);
        assertEquals(0,fund.cantCartas(0));
        fund.sacarCartas(1);
        assertEquals(0,fund.cartasTotales());
    }
}
