package FreeCellTest;

import Modelo.Global.Constantes.*;
import Modelo.Global.ObjetosPrincipales.Abstactos.Tablero;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import Modelo.SolitarioFreeCell.JuegoFreeCell;
import Modelo.SolitarioFreeCell.ObjetosConcretos.TableroFreeCell;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;


public class FreeCellTest {
    private JuegoFreeCell juego;

    @Before
    public void setUp() {
        juego = new JuegoFreeCell();
    }

    @Test
    public void testCrearJuego() {
        //se testea partida vacia
        assertEquals(Generales.MAZOVACIO, juego.obtenerCartasTotales());

        Fundacion f = juego.obtenerFundacion();
        for (int i = 0; i < Klondlike.FUNDACIONES; i++) {
            assertEquals(Generales.FUNDACIONVACIA, f.cantCartas(i));
        }
        Tablero t = juego.obtenerTablero();
        for (int i = 0; i < Klondlike.PILASCARTAS; i++) {
            assertEquals(Generales.PILAVACIA, t.cantCartas(i));
        }
        assertTrue(juego.obtenerJuegoTerminado());

    }

    @Test
    public void testEstadoInicialPartida(){
        juego.prepararPartidaAleatoria();
        assertEquals(Generales.MAZOCOMPLETO, juego.obtenerCartasTotales());
        assertFalse(juego.obtenerJuegoTerminado());
        assertFalse(juego.esJuegoGanado());
    }

    @Test
    public void testPrepararPartida(){
        juego.prepararPartidaAleatoria();
        Fundacion f = juego.obtenerFundacion();
        Celda c = juego.obtenerCeldas();
        TableroFreeCell t = juego.obtenerTablero();

        for (Carta carta:c.getCeldas()){
            assertNull(carta);
        }
        for (ArrayList<Carta> fund:f.obtenerFundaciones()){
            assertTrue(fund.isEmpty());
        }
        ArrayList<Carta> pila;
        for (int i = 0; i<= Freecell.TOPEPILASPRIMERAS; i++){
            pila = t.obtenerPila(i);
            assertEquals(Freecell.CARTASPILASPRIMERAS,pila.size());
        }
        for (int i=Freecell.TOPEPILASPRIMERAS+1;i<=Freecell.TOPEPILASSEGUNDAS;i++){
            pila = t.obtenerPila(i);
            assertEquals(Freecell.CARTASPILASSEGUNDAS,pila.size());
        }
    }

    @Test
    public void testConSemilla() {
        Random rnd = new Random(1);
        juego.prepararPartidaConSemilla(rnd);
        Fundacion f = juego.obtenerFundacion();
        TableroFreeCell t = juego.obtenerTablero();
        Celda c = juego.obtenerCeldas();
        Mazo m = new Mazo();

        /*
        0-[[V-CINCO-DIAMANTE, V-JOTA-PICA, V-NUEVE-CORAZON, V-AS-PICA, V-DOS-DIAMANTE, V-REY-PICA, V-CINCO-PICA],
        1-[V-DOS-PICA, V-NUEVE-TREBOL, V-NUEVE-DIAMANTE, V-SIETE-DIAMANTE, V-SEIS-TREBOL, V-REINA-TREBOL, V-SEIS-DIAMANTE],
        2-[V-CINCO-CORAZON, V-DOS-TREBOL, V-OCHO-DIAMANTE, V-OCHO-PICA, V-DIEZ-DIAMANTE, V-TRES-PICA, V-OCHO-CORAZON],
        3-[V-CUATRO-PICA, V-REINA-CORAZON, V-NUEVE-PICA, V-REY-TREBOL, V-REY-CORAZON, V-JOTA-TREBOL, V-CINCO-TREBOL],
        4-[V-CUATRO-CORAZON, V-JOTA-CORAZON, V-AS-DIAMANTE, V-JOTA-DIAMANTE, V-DIEZ-CORAZON, V-TRES-DIAMANTE],
        5-[V-SEIS-PICA, V-SIETE-CORAZON, V-SIETE-PICA, V-TRES-CORAZON, V-DIEZ-TREBOL, V-REINA-PICA],
        6-[V-AS-CORAZON, V-DIEZ-PICA, V-DOS-CORAZON, V-REY-DIAMANTE, V-OCHO-TREBOL, V-REINA-DIAMANTE],
        7-[V-CUATRO-TREBOL, V-TRES-TREBOL, V-SIETE-TREBOL, V-CUATRO-DIAMANTE, V-AS-TREBOL, V-SEIS-CORAZON]]*/
        //Notacion: inicial objeto|n°pila|-|n°carta| a |inicial objeto|n°pila|-|n°carta
        assertTrue(juego.moverTableroATablero(0,6,1));//T0-5Pica a T1-6Diam
        assertTrue(juego.moverTableroACelda(7,5,0)); //T7-6cora a C0-vacia
        juego.moverTableroAFundacion(7,4,1);//T7-1treb a F1-fund

        juego.moverTableroATablero(7,3,3);//T7-4diam a T1-5treb
        juego.moverTableroATablero(7,2,2);//T7-7treb a T2-8Cora
        juego.moverTableroACelda(7,1,0);//T7-3treb a C0-6cora - no se puede
        juego.moverTableroACelda(7,1,1);//T7-3treb a C1-vacia
        juego.moverTableroACelda(7,0,2);//T7-4treb a C2-vacia

        assertEquals(3,c.cartasTotales());
        assertEquals(Generales.PILAVACIA,t.obtenerPila(7).size());
        assertTrue(m.verCarta(Palos.PICA, Valores.REY).sonIguales(t.verCartaPos(0,5)));
        assertEquals(Generales.PILAVACIA,t.cantCartas(7));
        assertEquals(1,f.cartasTotales());
        assertTrue(juego.moverCeldaATablero(1,3));//C1-3treb a T1-4diam
        assertEquals(Generales.CELDAVACIA,c.verCarta(1));
        assertTrue(juego.moverTableroATablero(3,7,1));//T4-diam a T1-5Pica
        assertEquals(7,t.cantCartas(3));
        assertEquals(10,t.cantCartas(1));
        juego.moverCeldaATablero(0,2);//C0-6cora a T2-7treb
        juego.moverTableroACelda(0,5,0);//T0-13Pica a C0-vacio
        juego.moverTableroATablero(0,4,1);//T0-2Diam a T1-3treb
        assertFalse(juego.moverTableroAFundacion(0,3,1));//T0-1Pica a F1-1Treb - no se puede
        juego.moverTableroAFundacion(0,3,0);//T0-1Pica a F0-vacio
        assertEquals(Generales.PILAVACIA,t.cantCartas(7));
        assertFalse(juego.moverTableroATablero(1,6,7));//T1-6Diam a T7-vacio - muevo 5 cartas. No se puede.
        // tengo 2 celdas vacias nada mas, por lo que son (1 + 2) * 2 ** (0) = 3. No cuenta la pila vacia ya que ahi es justamente donde estoy moviendo las cartas.
        assertEquals(Generales.PILAVACIA,t.cantCartas(7));
        juego.moverCeldaACelda(0,3);//C0-13pica a C3-vacio
        juego.moverCeldaATablero(3,7);//C3-13pica a T7-vacio
        juego.moverTableroATablero(6,5,7);//T6-12diam a T7-13pica
        juego.moverTableroATablero(3,6,2);//T3-5Treb a T2-6cora
        juego.moverTableroATablero(3,5,7);//T3-11treb a T7-12Diam
        assertTrue(m.verCarta(Palos.TREBOL,Valores.JOTA).sonIguales(t.verCartaPos(7,2)));
        juego.moverTableroATablero(5,5,3);//T5-12Pica a T3-13Cora
        assertTrue(juego.moverTableroATablero(0,2,5));//T0-9cora a T5-10treb
        juego.moverTableroATablero(0,1,3);//T0-11Pica
        juego.moverTableroACelda(6,4,0);//T6-8treb a C0-vacio
        juego.moverTableroACelda(6,3,1);//T6-13diam a C1-vacio
        juego.moverCeldaATablero(0,5);//C0-8treb a T5-9cora
        juego.moverTableroACelda(6,2,0);//T6-2cora a C0-vacio
        juego.moverTableroACelda(6,2,1);//T6-10pica a C1-vacio
        juego.moverTableroACelda(6,1,3);//T6-1cora a F2-vacio
        assertTrue(juego.moverTableroAFundacion(6,0,2));//C0-2cora a F2-1cora
        assertTrue(juego.moverCeldaAFundacion(0,2));
        assertTrue(m.verCarta(Palos.CORAZON,Valores.DOS).sonIguales(f.verPrimerCartaFundacion(2)));
    }


}