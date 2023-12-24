package FreeCellTest;

import Model.Global.Constants.*;
import Model.Global.MainObjects.Abstract.Tableau;
import Model.Global.MainObjects.Concrete.Cell;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.FreeCellSolitaire.FreeCellGame;
import Model.FreeCellSolitaire.ConcreteObjects.FreeCellTableau;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;


public class FreeCellTest {
    private FreeCellGame juego;

    @Before
    public void setUp() {
        juego = new FreeCellGame();
    }

    @Test
    public void testCrearJuego() {
        //se testea partida vacia
        assertEquals(General.EMPTY, juego.countAllCards());

        Foundation f = juego.getFoundations();
        for (int i = 0; i < Klondlike.FOUNDATIONS; i++) {
            assertEquals(General.EMPTY, f.cardsInColumn(i));
        }
        Tableau t = juego.getTableau();
        for (int i = 0; i < Klondlike.INITIALTABLEAUCOLUMNS; i++) {
            assertEquals(General.EMPTY, t.cardsInStack(i));
        }
        assertTrue(juego.isGameOver());

    }

    @Test
    public void testEstadoInicialPartida(){
        juego.prepareRandomGame();
        assertEquals(General.COMPLETEDDECK, juego.countAllCards());
        assertFalse(juego.isGameOver());
        assertFalse(juego.gameFinished());
    }

    @Test
    public void testPrepararPartida(){
        juego.prepareRandomGame();
        Foundation f = juego.getFoundations();
        Cell c = juego.getCells();
        FreeCellTableau t = juego.getTableau();

        for (Card card :c.getCells()){
            assertNull(card);
        }
        for (ArrayList<Card> fund:f.getFoundations()){
            assertTrue(fund.isEmpty());
        }
        ArrayList<Card> pila;
        for (int i = 0; i<= Freecell.MAXFIRSTSTACKS; i++){
            pila = t.getStack(i);
            assertEquals(Freecell.FIRSTSTACKS,pila.size());
        }
        for (int i = Freecell.MAXFIRSTSTACKS +1; i<=Freecell.MAXSECONDSTACKS; i++){
            pila = t.getStack(i);
            assertEquals(Freecell.SECONDSTACKS,pila.size());
        }
    }

    @Test
    public void testConSemilla() {
        Random rnd = new Random(1);
        juego.prepareGameWithSeed(rnd);
        Foundation f = juego.getFoundations();
        FreeCellTableau t = juego.getTableau();
        Cell c = juego.getCells();
        Deck m = new Deck();

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
        assertTrue(juego.moveTableauToTableau(0,6,1));//T0-5Pica a T1-6Diam
        assertTrue(juego.moveTableauToCell(7,5,0)); //T7-6cora a C0-vacia
        juego.moveTableauToFoundation(7,4,1);//T7-1treb a F1-fund

        juego.moveTableauToTableau(7,3,3);//T7-4diam a T1-5treb
        juego.moveTableauToTableau(7,2,2);//T7-7treb a T2-8Cora
        juego.moveTableauToCell(7,1,0);//T7-3treb a C0-6cora - no se puede
        juego.moveTableauToCell(7,1,1);//T7-3treb a C1-vacia
        juego.moveTableauToCell(7,0,2);//T7-4treb a C2-vacia

        assertEquals(3,c.totalCard());
        assertEquals(General.EMPTY,t.getStack(7).size());
        assertEquals(General.EMPTY,t.cardsInStack(7));
        assertEquals(1,f.totalCards());
        assertTrue(juego.moverCellToTableau(1,3));//C1-3treb a T1-4diam
        assertEquals(General.EMPTYCELL,c.seeCard(1));
        assertTrue(juego.moveTableauToTableau(3,7,1));//T4-diam a T1-5Pica
        assertEquals(7,t.cardsInStack(3));
        assertEquals(10,t.cardsInStack(1));
        juego.moverCellToTableau(0,2);//C0-6cora a T2-7treb
        juego.moveTableauToCell(0,5,0);//T0-13Pica a C0-vacio
        juego.moveTableauToTableau(0,4,1);//T0-2Diam a T1-3treb
        assertFalse(juego.moveTableauToFoundation(0,3,1));//T0-1Pica a F1-1Treb - no se puede
        juego.moveTableauToFoundation(0,3,0);//T0-1Pica a F0-vacio
        assertEquals(General.EMPTY,t.cardsInStack(7));
        assertFalse(juego.moveTableauToTableau(1,6,7));//T1-6Diam a T7-vacio - muevo 5 cartas. No se puede.
        // tengo 2 celdas vacias nada mas, por lo que son (1 + 2) * 2 ** (0) = 3. No cuenta la pila vacia ya que ahi es justamente donde estoy moviendo las cartas.
        assertEquals(General.EMPTY,t.cardsInStack(7));
        juego.moveBetweenCells(0,3);//C0-13pica a C3-vacio
        juego.moverCellToTableau(3,7);//C3-13pica a T7-vacio
        juego.moveTableauToTableau(6,5,7);//T6-12diam a T7-13pica
        juego.moveTableauToTableau(3,6,2);//T3-5Treb a T2-6cora
        juego.moveTableauToTableau(3,5,7);//T3-11treb a T7-12Diam
        juego.moveTableauToTableau(5,5,3);//T5-12Pica a T3-13Cora
        assertTrue(juego.moveTableauToTableau(0,2,5));//T0-9cora a T5-10treb
        juego.moveTableauToTableau(0,1,3);//T0-11Pica
        juego.moveTableauToCell(6,4,0);//T6-8treb a C0-vacio
        juego.moveTableauToCell(6,3,1);//T6-13diam a C1-vacio
        juego.moverCellToTableau(0,5);//C0-8treb a T5-9cora
        juego.moveTableauToCell(6,2,0);//T6-2cora a C0-vacio
        juego.moveTableauToCell(6,2,1);//T6-10pica a C1-vacio
        juego.moveTableauToCell(6,1,3);//T6-1cora a F2-vacio
        assertTrue(juego.moveTableauToFoundation(6,0,2));//C0-2cora a F2-1cora
        assertTrue(juego.moveCellToFoundation(0,2));
    }


}