package ObjetosPrincipalesTest;

import Modelo.Global.Constantes.Generales;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioFreeCell.ValidacionesFreeCell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class CeldaTest {
    private Celda celdas;
    private Carta asCorazon;
    private Carta reyPica;
    private Carta diezDiamante;

    private Carta cincoTrebol;

    @Before
    public void setUp(){
        var validacion = new ValidacionesFreeCell();
        this.celdas = new Celda(validacion);
        this.asCorazon = new Carta(Valores.AS,Palos.CORAZON);
        this.reyPica = new Carta(Valores.REY,Palos.PICA);
        this.diezDiamante = new Carta(Valores.DIEZ,Palos.DIAMANTE);
        this.cincoTrebol =  new Carta(Valores.CINCO,Palos.TREBOL);
    }
    @Test
    public void testCrearCelda(){
        for (Carta carta:this.celdas.getCeldas()){
            assertEquals(Generales.CELDAVACIA,carta);
        }
    }

    @Test
    public void testAgregarCartaACelda(){
        assertTrue(this.celdas.agregarCarta(this.asCorazon,0));
        assertTrue(this.celdas.agregarCarta(this.reyPica,1));
        assertTrue(this.celdas.agregarCarta(this.cincoTrebol,2));
        assertTrue(this.celdas.agregarCarta(this.diezDiamante,3));

        assertTrue(this.asCorazon.sonIguales(this.celdas.getCeldas()[0]));
        assertTrue(this.reyPica.sonIguales(this.celdas.getCeldas()[1]));
        assertTrue(this.cincoTrebol.sonIguales(this.celdas.getCeldas()[2]));
        assertTrue(this.diezDiamante.sonIguales(this.celdas.getCeldas()[3]));
        assertEquals(4, celdas.cartasTotales());
    }

    @Test
    public void testMoverCartaCeldas(){
        this.celdas.agregarCarta(this.reyPica,1);
        assertFalse(this.celdas.agregarCarta(this.asCorazon,1));
        assertTrue(this.reyPica.sonIguales(this.celdas.getCeldas()[1]));

        assertTrue(this.celdas.moverEntreCeldas(1,3));
        assertEquals(Generales.CELDAVACIA,this.celdas.getCeldas()[1]);
        assertTrue(this.reyPica.sonIguales(this.celdas.getCeldas()[3]));

        assertTrue(this.celdas.moverEntreCeldas(3,0));
        assertEquals(Generales.CELDAVACIA,this.celdas.getCeldas()[3]);
        assertTrue(this.reyPica.sonIguales(this.celdas.getCeldas()[0]));

        assertFalse(this.celdas.moverEntreCeldas(1,2));
        assertNull(this.celdas.getCeldas()[1]);
        assertNull(this.celdas.getCeldas()[2]);
    }

    @Test
    public void testFuncionaCantidadySacarCartas() {
        this.celdas.agregarCarta(this.reyPica,0);
        this.celdas.agregarCarta(this.asCorazon,1);
        this.celdas.agregarCarta(this.cincoTrebol,2);
        this.celdas.agregarCarta(this.diezDiamante,3);
        this.celdas.sacarCarta(0);
        assertEquals(3,celdas.cartasTotales());
        this.celdas.sacarCarta(1);
        assertEquals(2,celdas.cartasTotales());
        this.celdas.sacarCarta(2);
        assertEquals(1,celdas.cartasTotales());
        this.celdas.sacarCarta(3);
        assertEquals(0,celdas.cartasTotales());

    }
}
