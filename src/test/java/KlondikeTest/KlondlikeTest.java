package KlondikeTest;


import Modelo.Global.Constantes.Generales;
import Modelo.Global.Constantes.Klondlike;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Abstactos.Stock;
import Modelo.Global.ObjetosPrincipales.Abstactos.Tablero;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioKlondlike.JuegoKlondike;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;
import Modelo.SolitarioKlondlike.ObjetosConcretos.TableroKlondike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.*;

public class KlondlikeTest {
    private JuegoKlondike juego;
    @Before
    public void setUp() {
        juego = new JuegoKlondike();
    }


    @Test
    public void testEstadoInicialJuego() {
        //se testea partida vacia
        assertEquals(Generales.MAZOVACIO, juego.obtenerCartasTotales());
        Fundacion f = juego.obtenerFundaciones();
        for (int i = 0; i < Klondlike.FUNDACIONES; i++) {
            assertEquals(Generales.FUNDACIONVACIA, f.cantCartas(i));
        }
        Tablero t = juego.obtenerTablero();
        for (int i = 0; i < Klondlike.PILASCARTAS; i++) {
            assertEquals(Generales.PILAVACIA, t.cantCartas(i));
        }
        Stock s = juego.obtenerStock();
        assertEquals(Generales.STOCKVACIO, s.cartasTotales());
        assertTrue(juego.obtenerJuegoTerminado());
        juego.prepararPartidaAleatoria();
        assertEquals(Generales.MAZOCOMPLETO, juego.obtenerCartasTotales());
        assertFalse(juego.obtenerJuegoTerminado());
        assertFalse(juego.esJuegoGanado());
    }

    @Test
    public void testPrepararPartida() {
        //se verifica que las cantidades iniciales esten bien
        juego.prepararPartidaAleatoria();
        assertFalse(juego.obtenerJuegoTerminado());
        Fundacion f = juego.obtenerFundaciones();
        for (int i = 0; i < Klondlike.FUNDACIONES; i++) {
            assertEquals(Generales.FUNDACIONVACIA, f.cantCartas(i));
        }
        Tablero t = juego.obtenerTablero();
        for (int i = 0; i < Klondlike.PILASCARTAS; i++) {
            ArrayList<Carta> pila = t.obtenerPilas().get(i);
            assertEquals(i + 1, pila.size());
            for (int j = 0; j < pila.size() - 2; j++) {
                Carta carta = pila.get(j);
                assertFalse(carta.esVisible());
            }
            Carta ultimaCarta = pila.get(i);
            assertTrue(ultimaCarta.esVisible());
        }
        assertEquals(Klondlike.STOCKINICIO, juego.obtenerStock().cartasTotales());
        assertFalse(juego.obtenerJuegoTerminado());
        assertFalse(juego.esJuegoGanado());
    }


    @Test
    public void testVolumen(){
        Fundacion f = juego.obtenerFundaciones();
        StockKlondike s = juego.obtenerStock();
        TableroKlondike t = juego.obtenerTablero();
        Mazo mazo = new Mazo();
        Random rnd = new Random(1);
        /*
        Estado inicial de la partida generad.
        Notacion:
        V/NV = No Si la carta es visible o no lo es.
        Numero = Valor de la carta. 11 es jota, 12 reina y 13 rey.
        ----------
        Fundaciones:
        0-[]
        1-[]
        2-[]
        3-[]

        Stocks (invertidos para visualizar el tope a la izquierda del arreglo).
        Stock visible: []
        Stock no visible: [NV-6-CORA, NV-1-TREB, NV-4-DIAM, NV-7-TREB, NV-3-TREB, NV-4-TREB, NV-12-DIAM, NV-8-TREB, NV-13-DIAM, NV-2-CORA, NV-10-PICA, NV-1-CORA, NV-12-PICA, NV-10-TREB, NV-3-CORA, NV-7-PICA, NV-7-CORA, NV-6-PICA, NV-3-DIAM, NV-10-CORA, NV-11-DIAM, NV-1-DIAM, NV-11-CORA, NV-4-CORA]

        Pilas:
        0-[V-5-DIAM]
        1-[NV-11-PICA, V-9-CORA]
        2-[NV-1-PICA, NV-2-DIAM, V-13-PICA]
        3-[NV-5-PICA, NV-2-PICA, NV-9-TREB, V-9-DIAM]
        4-[NV-7-DIAM, NV-6-TREB, NV-12-TREB, NV-6-DIAM, V-5-CORA]
        5-[NV-2-TREB, NV-8-DIAM, NV-8-PICA, NV-10-DIAM, NV-3-PICA, V-8-CORA]
        6-[NV-4-PICA, NV-12-CORA, NV-9-PICA, NV-13-TREB, NV-13-CORA, NV-11-TREB, V-5-TREB]
       */
        juego.prepararPartidaConSemilla(rnd);
        juego.pasarCartasStock();
        assertFalse(juego.moverStockAFundacion(0));
        assertTrue(mazo.verCarta(Palos.CORAZON, Valores.SEIS).sonIguales(s.verPrimerCarta()));
        assertTrue(s.verPrimerCarta().esVisible());
        juego.pasarCartasStock();
        assertTrue(juego.moverStockAFundacion(0));
        assertTrue(mazo.verCarta(Palos.CORAZON,Valores.SEIS).sonIguales(s.verPrimerCarta()));
        assertTrue(mazo.verCarta(Palos.TREBOL,Valores.AS).sonIguales(f.verPrimerCartaFundacion(0)));
        juego.pasarCartasStock();
        juego.moverStockATablero(6);
        assertTrue(mazo.verCarta(Palos.DIAMANTE,Valores.CUATRO).sonIguales(t.verCartaPos(6,7)));
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.moverStockATablero(6);
        juego.pasarCartasStock();
        juego.moverStockATablero(0);
        juego.pasarCartasStock();
        juego.moverStockATablero(2);
        juego.pasarCartasStock();
        juego.moverStockATablero(3);
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.moverStockATablero(6);
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.moverFundacionAFundacion(0,3);
        juego.moverStockAFundacion(2);
        juego.moverFundacionATablero(3,6);
        assertFalse(juego.moverTableroAFundacion(6,t.cantCartas(6)-1,2));
        assertTrue(juego.moverTableroAFundacion(6,t.cantCartas(6)-1,3));
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.moverStockATablero(0);
        assertTrue(mazo.verCarta(Palos.CORAZON,Valores.TRES).sonIguales(t.verCartaPos(0,2)));
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.moverStockATablero(3);
        juego.moverStockATablero(5);
        assertTrue(mazo.verCarta(Palos.PICA,Valores.SIETE).sonIguales(t.verCartaPos(5,t.cantCartas(5)-1)));
        assertTrue(mazo.verCarta(Palos.CORAZON,Valores.SIETE).sonIguales(t.verCartaPos(3,t.cantCartas(3)-1)));
        juego.pasarCartasStock();
        juego.moverStockATablero(3);
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        juego.moverStockAFundacion(1);
        assertTrue(mazo.verCarta(Palos.DIAMANTE,Valores.AS).sonIguales(f.verPrimerCartaFundacion(1)));
        juego.pasarCartasStock();
        juego.pasarCartasStock();
        assertEquals(0,s.obtenerStockNoVisible().size());
        assertEquals(11,s.obtenerCartasVisibles().size());
        juego.pasarCartasStock();
        assertEquals(11,s.obtenerStockNoVisible().size());
        assertEquals(0,s.obtenerCartasVisibles().size());
        for (Carta carta:s.obtenerStockNoVisible()){
            assertFalse(carta.esVisible());
        }
        juego.pasarCartasStock();
        juego.moverStockATablero(5);
        juego.moverTableroATablero(6,6,5);
        assertTrue(mazo.verCarta(Palos.TREBOL,Valores.JOTA).sonIguales(t.verCartaPos(6,t.cantCartas(6)-1)));
        assertTrue(t.verCartaPos(6,t.cantCartas(6)-1).esVisible());
        assertFalse(juego.obtenerJuegoTerminado());
        assertFalse(juego.esJuegoGanado());
    }
    @Test
    public void testPartidaGanada(){
        //Testeo tener 51 cartas en fundaciones, mover la ultima y que la partida esté ganada.
        //Inicio el estado de la partida.
        ArrayList<ArrayList<Carta>> tablero = new ArrayList<>();
        ArrayList<ArrayList<Carta>> fundaciones = new ArrayList<>();
        ArrayList<Carta> stockVisible = new ArrayList<>();
        Stack<Carta> stockNovisible= new Stack<>();
        for (int i=0;i<Klondlike.PILASCARTAS;i++){
            tablero.add(new ArrayList<>());
        }
        for (int i=0;i<Klondlike.FUNDACIONES;i++){
            fundaciones.add(new ArrayList<>());
        }
        Mazo mazo = new Mazo();
        ArrayList<Carta> cartas = mazo.obtenerCartas();
        ArrayList<Carta> fund;
        Carta carta;
        for(int i=0;i<Generales.MAZOCOMPLETO-1;i++){
            carta = cartas.get(i);
            carta.hacerVisible();
            fund = fundaciones.get(i / Generales.CARTASPORPALO);
            fund.add(cartas.get(i));
        }
        carta = cartas.get(Generales.MAZOCOMPLETO-1);
        carta.hacerVisible();
        tablero.get(0).add(carta);
        //Preparo la partida con las fundaciones casi completas y una carta en la primer pila.
        juego.prepararPartidaEspecifica(stockNovisible,stockVisible,fundaciones,tablero);
        //Realizo la validacion.
        assertFalse(juego.esJuegoGanado());
        juego.moverTableroAFundacion(0,0,3);
        assertTrue(juego.esJuegoGanado());
    }
}
