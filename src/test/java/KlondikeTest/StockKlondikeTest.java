package KlondikeTest;

import Modelo.Global.Constantes.Generales;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class StockKlondikeTest {
    private StockKlondike stock;
    private Carta asDiamante;
    private Carta dosDiamante;
    private Carta tresPica;
    private Carta asCorazon;

    @Before
    public void setUp() {
        stock = new StockKlondike();
        ArrayList<Carta> Visibles = new ArrayList<Carta>();
        Stack<Carta> NoVisible = new Stack<>();
        asDiamante = new Carta(Valores.AS, Palos.DIAMANTE);
        dosDiamante = new Carta(Valores.DOS, Palos.DIAMANTE);
        tresPica = new Carta(Valores.TRES, Palos.PICA);
        asCorazon = new Carta(Valores.AS, Palos.CORAZON);
        NoVisible.add(asDiamante);
        NoVisible.add(dosDiamante);
        NoVisible.add(tresPica);
        NoVisible.add(asCorazon);
        stock.prepararStockEspecifico(NoVisible,Visibles);
    }

    @Test
    public void testPrepararStock() {
        assertEquals(4, stock.cartasTotales());
        assertNull(stock.verPrimerCarta());
    }

    @Test
    public void testPasarTandaStock() {
        stock.pasarTandaStock();
        assertNotNull(stock.verPrimerCarta());
    }

    @Test
    public void testSacarCarta() {
        stock.pasarTandaStock();
        Carta cartaSacada = stock.sacarCarta();
        assertNotNull(cartaSacada);
        assertEquals(asCorazon, cartaSacada);
    }
    @Test
    public void sacarCartas() {
        //se evalua que funciona pasarStock() y cantidad()
        Carta carta = stock.sacarCarta();
        assertNull(carta);
        assertEquals(4,stock.cartasTotales());
        stock.pasarTandaStock();
        carta = stock.sacarCarta();
        assertEquals(3,stock.cartasTotales());
        assertEquals(asCorazon,carta);
        assertTrue(carta.esVisible());
        stock.pasarTandaStock();
        carta = stock.sacarCarta();
        assertEquals(2,stock.cartasTotales());
        assertEquals(tresPica,carta);
        assertTrue(carta.esVisible());
        stock.pasarTandaStock();
        carta = stock.sacarCarta();
        assertEquals(1,stock.cartasTotales());
        assertEquals(dosDiamante,carta);
        assertTrue(carta.esVisible());
        stock.pasarTandaStock();
        carta = stock.sacarCarta();
        assertEquals(0,stock.cartasTotales());
        assertEquals(asDiamante,carta);
        assertTrue(carta.esVisible());
    }
    @Test
    public void stockVacio(){
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        stock.pasarTandaStock();

        stock.sacarCarta();
        stock.sacarCarta();
        stock.sacarCarta();
        stock.sacarCarta();

        assertEquals(Generales.STOCKVACIO,stock.cartasTotales());
        assertNull(stock.sacarCarta());
    }
    @Test
    public void verPrimeraCarta() {
        Carta carta = stock.sacarCarta();
        assertNull(carta);
        stock.pasarTandaStock();
        carta = stock.verPrimerCarta();
        assertEquals(asCorazon,carta);
        assertTrue(carta.esVisible());
        stock.sacarCarta();
        carta = stock.verPrimerCarta();
        assertNull(carta);
    }

    @Test
    public void SeRespetanVisibilidades() {
        List<Carta> visibles = stock.obtenerCartasVisibles();
        Stack<Carta> noVisible = stock.obtenerStockNoVisible();
        assertEquals(0, visibles.size());
        assertEquals(4, noVisible.size());
        for (Carta carta : noVisible) {
            assertFalse(carta.esVisible());
        }
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        assertEquals(2,visibles.size());
        assertEquals(2,noVisible.size());
        for (Carta carta : visibles) {
            assertTrue(carta.esVisible());
        }
        for (Carta carta : noVisible) {
            assertFalse(carta.esVisible());
        }
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        assertEquals(4, visibles.size());
        assertEquals(0, noVisible.size());
        //La cuarta carta no deberia ser visible, se evalua.
        for(int i=0;i<4;i++){
            assertTrue(visibles.get(i).esVisible());
        }
    }

    @Test
    public void testSePasaPorTodasLasCartas(){

        List<Carta> visibles = stock.obtenerCartasVisibles();
        Stack<Carta> noVisible = stock.obtenerStockNoVisible();
        //Paso las 4 cuartas a la parte visible
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        stock.pasarTandaStock();
        //Vuelven a ser todas NoVisibles
        stock.pasarTandaStock();
        //Voy sacando y reviso que esten en orden, con la visibilidad correcta.
        stock.pasarTandaStock();
        assertEquals(3,noVisible.size());
        assertEquals(1,visibles.size());
        assertEquals(asCorazon,visibles.get(visibles.size()-1));
        assertTrue(asCorazon.esVisible());

        stock.pasarTandaStock();
        assertEquals(tresPica,visibles.get(visibles.size()-1));
        assertTrue(tresPica.esVisible());
        assertEquals(2,noVisible.size());
        assertEquals(2,visibles.size());

        stock.pasarTandaStock();
        assertEquals(dosDiamante,visibles.get(visibles.size()-1));
        assertTrue(dosDiamante.esVisible());
        assertEquals(1,noVisible.size());
        assertEquals(3,visibles.size());

        stock.pasarTandaStock();
        assertEquals(asDiamante,visibles.get(visibles.size()-1));
        assertTrue(asDiamante.esVisible());
        assertEquals(0,noVisible.size());
        assertEquals(4,visibles.size());
    }
}