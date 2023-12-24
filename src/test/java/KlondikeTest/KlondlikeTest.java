package KlondikeTest;


import Model.Global.Constants.General;
import Model.Global.Constants.Klondlike;
import Model.Global.MainObjects.Abstract.Stock;
import Model.Global.MainObjects.Abstract.Tableau;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.KlondikeSolitaire.KlondikeGame;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import Model.KlondikeSolitaire.ConcreteObjects.KlondikeTableau;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.*;

public class KlondlikeTest {
    private KlondikeGame juego;
    @Before
    public void setUp() {
        juego = new KlondikeGame();
    }


    @Test
    public void testEstadoInicialJuego() {
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
        Stock s = juego.getStock();
        assertEquals(General.EMPTY, s.totalCards());
        assertTrue(juego.isGameFinished());
        juego.prepareRandomGame();
        assertEquals(General.COMPLETEDDECK, juego.countAllCards());
        assertFalse(juego.isGameFinished());
        assertFalse(juego.gameFinished());
    }

    @Test
    public void testPrepararPartida() {
        //se verifica que las cantidades iniciales esten bien
        juego.prepareRandomGame();
        assertFalse(juego.isGameFinished());
        Foundation f = juego.getFoundations();
        for (int i = 0; i < Klondlike.FOUNDATIONS; i++) {
            assertEquals(General.EMPTY, f.cardsInColumn(i));
        }
        Tableau t = juego.getTableau();
        for (int i = 0; i < Klondlike.INITIALTABLEAUCOLUMNS; i++) {
            ArrayList<Card> pila = t.getStacks().get(i);
            assertEquals(i + 1, pila.size());
            for (int j = 0; j < pila.size() - 2; j++) {
                Card card = pila.get(j);
                assertFalse(card.isVisible());
            }
            Card ultimaCard = pila.get(i);
            assertTrue(ultimaCard.isVisible());
        }
        assertEquals(Klondlike.INITIALSTOCK, juego.getStock().totalCards());
        assertFalse(juego.isGameFinished());
        assertFalse(juego.gameFinished());
    }


    @Test
    public void testVolumen(){
        Foundation f = juego.getFoundations();
        StockKlondike s = juego.getStock();
        KlondikeTableau t = juego.getTableau();
        Deck deck = new Deck();
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
        juego.prepareGameWithSeed(rnd);
        juego.moveCardStock();
        assertFalse(juego.moveStockToFoundation(0));
        //return this.value == otra.getValue() && suit == otra.getSuit();
        assertTrue(s.seeFirstCard().isVisible());
        juego.moveCardStock();
        assertTrue(juego.moveStockToFoundation(0));
        juego.moveCardStock();
        juego.moveStockToTableau(6);
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveStockToTableau(6);
        juego.moveCardStock();
        juego.moveStockToTableau(0);
        juego.moveCardStock();
        juego.moveStockToTableau(2);
        juego.moveCardStock();
        juego.moveStockToTableau(3);
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveStockToTableau(6);
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveBetweenFoundations(0,3);
        juego.moveStockToFoundation(2);
        juego.moveFoundationToTableau(3,6);
        assertFalse(juego.moveTableauToFoundation(6,t.cardsInStack(6)-1,2));
        assertTrue(juego.moveTableauToFoundation(6,t.cardsInStack(6)-1,3));
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveStockToTableau(0);
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveStockToTableau(3);
        juego.moveStockToTableau(5);
        juego.moveCardStock();
        juego.moveStockToTableau(3);
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveCardStock();
        juego.moveStockToFoundation(1);
        juego.moveCardStock();
        juego.moveCardStock();
        assertEquals(0,s.getNonVisibleStock().size());
        assertEquals(11,s.getVisibleStock().size());
        juego.moveCardStock();
        assertEquals(11,s.getNonVisibleStock().size());
        assertEquals(0,s.getVisibleStock().size());
        for (Card card :s.getNonVisibleStock()){
            assertFalse(card.isVisible());
        }
        juego.moveCardStock();
        juego.moveStockToTableau(5);
        juego.moveBetweenTableau(6,6,5);
        assertTrue(t.seeCard(6,t.cardsInStack(6)-1).isVisible());
        assertFalse(juego.isGameFinished());
        assertFalse(juego.gameFinished());
    }
    @Test
    public void testPartidaGanada(){
        //Testeo tener 51 cartas en fundaciones, mover la ultima y que la partida esté ganada.
        //Inicio el estado de la partida.
        ArrayList<ArrayList<Card>> tablero = new ArrayList<>();
        ArrayList<ArrayList<Card>> fundaciones = new ArrayList<>();
        ArrayList<Card> stockVisible = new ArrayList<>();
        Stack<Card> stockNovisible= new Stack<>();
        for (int i = 0; i<Klondlike.INITIALTABLEAUCOLUMNS; i++){
            tablero.add(new ArrayList<>());
        }
        for (int i = 0; i<Klondlike.FOUNDATIONS; i++){
            fundaciones.add(new ArrayList<>());
        }
        Deck deck = new Deck();
        ArrayList<Card> cards = deck.obtenerCartas();
        ArrayList<Card> fund;
        Card card;
        for(int i = 0; i< General.COMPLETEDDECK -1; i++){
            card = cards.get(i);
            card.changeVisibility(true);
            fund = fundaciones.get(i / General.CARDSBYSUIT);
            fund.add(cards.get(i));
        }
        card = cards.get(General.COMPLETEDDECK -1);
        card.changeVisibility(true);
        tablero.get(0).add(card);
        //Preparo la partida con las fundaciones casi completas y una carta en la primer pila.
        juego.prepareSpecificGame(stockNovisible,stockVisible,fundaciones,tablero);
        //Realizo la validacion.
        assertFalse(juego.gameFinished());
        juego.moveTableauToFoundation(0,0,3);
        assertTrue(juego.gameFinished());
    }
}
