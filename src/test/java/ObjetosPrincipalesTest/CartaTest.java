package ObjetosPrincipalesTest;


import Modelo.Global.Constantes.Colores;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class CartaTest {

    private Carta carta;

    @Before
    public void setUp() {
        carta = new Carta(Valores.AS, Palos.CORAZON);
    }

    @Test
    public void testObtenerValor() {
        assertEquals(Valores.AS, carta.obtenerValor());
    }

    @Test
    public void testObtenerPalo() {
        assertEquals(Palos.CORAZON, carta.obtenerPalo());
    }

    @Test
    public void testSonIguales() {
        Carta otraCarta = new Carta(Valores.AS, Palos.CORAZON);
        assertTrue(carta.sonIguales(otraCarta));
    }

    @Test
    public void testObtenerColor() {
        assertEquals(Colores.ROJO, carta.obtenerColor());
    }

    @Test
    public void testHacerVisible() {
        carta.hacerVisible();
        assertTrue(carta.esVisible());
    }

    @Test
    public void testHacerNoVisible() {
        carta.hacerNoVisible();
        assertFalse(carta.esVisible());
    }

    @Test
    public void testToStringVisible() {
        carta.hacerVisible();
        assertEquals("V-AS-CORAZON", carta.toString());
    }

    @Test
    public void testToStringNoVisible() {
        carta.hacerNoVisible();
        assertEquals("***", carta.toString());
    }

    @Test
    public void testCartasIguales() {
        //verifica que la funcion que devuelve si dos cartas son iguales funcione correctamente
        for (Palos palo : Palos.values()) {
            for (Valores valor : Valores.values()) {
                for (Colores color : Colores.values()) {
                    Carta carta1 = new Carta(valor, palo);
                    Carta carta2 = new Carta(valor, palo);
                    Boolean resultado = carta1.sonIguales(carta2);
                    assertEquals(true, resultado);
                    assertEquals(carta1.obtenerColor(), carta2.obtenerColor());
                }
            }
        }
    }

}
