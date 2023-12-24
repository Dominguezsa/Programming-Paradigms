package FreeCellTest;


import Model.Global.Constants.General;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.FreeCellSolitaire.ConcreteObjects.FreeCellTableau;
import Model.FreeCellSolitaire.FreeCellValidations;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class FreecellTableauTest {
    private FreeCellTableau tablero;
    private Card reyDiamante;
    private Card reinaTrebol;
    private Card jotaDiamante;
    private Deck deck;

    @Before
    public void setUp() {
        tablero = new FreeCellTableau(new FreeCellValidations());
        this.deck = new Deck();
        reyDiamante = new Card(Values.KING, Suits.DIAMOND);
        reinaTrebol = new Card(Values.QUEEN, Suits.CLUB);
        jotaDiamante = new Card(Values.JACK, Suits.DIAMOND);
        reyDiamante.changeVisibility(true);
        reinaTrebol.changeVisibility(true);
        jotaDiamante.changeVisibility(true);
    }

    @Test
    public void testPrepararPilas(){
        tablero.prepareStacks(this.deck); // Freecell comienza con 8 pilas
        assertEquals(General.COMPLETEDDECK, tablero.TotalCards()); // Freecell comienza con 52 (todas) cartas
        assertNotNull(tablero.seeCard(0, 0));
    }

    @Test
    public void testBorrarCartas() {
        tablero.prepareStacks(this.deck);
        tablero.drawCardsFromPosition(0, 6);
        assertEquals(6, tablero.cardsInStack(0));
    }

    @Test
    public void testAgregarUnicaCarta() {
        tablero.prepareStacks(this.deck);
        tablero.drawCardsFromPosition(0,0); //la pila 0 queda vacía, porque se sacan todas desde la posicion 0
        tablero.addCard(reyDiamante,0);
        tablero.addCard(reinaTrebol,0);
        assertEquals(2,tablero.cardsInStack(0));
        tablero.addCard(reinaTrebol,0);
        assertEquals(2,tablero.cardsInStack(0));
        tablero.drawCardsFromPosition(0,0);
        tablero.addCard(reinaTrebol,0);
        assertEquals(1,tablero.cardsInStack(0));
        assertTrue(tablero.seeCard(0,0).getValue() == Values.QUEEN && tablero.seeCard(0,0).getSuit() == Suits.CLUB);
    }

    @Test
    public void testObtenerPilas() {
        tablero.prepareStacks(this.deck);
        ArrayList<Card> pila2 = tablero.getStack(1);
        assertEquals(7, pila2.size());
        assertTrue(pila2.get(0).isVisible());
        assertTrue(pila2.get(1).isVisible());
    }

    @Test
    public void testMoverEntrePilas(){
        tablero.addCard(reyDiamante,0);
        tablero.addCard(reinaTrebol,1);
        tablero.addCard(jotaDiamante,1);
        assertTrue(tablero.seeCard(1,0).getValue() == Values.QUEEN && tablero.seeCard(1,0).getSuit() == Suits.CLUB);
        assertTrue(tablero.seeCard(1,1).getValue() == Values.JACK && tablero.seeCard(1,1).getSuit() == Suits.DIAMOND);
        assertTrue(tablero.seeCard(0,0).getValue() == Values.KING && tablero.seeCard(0,0).getSuit() == Suits.DIAMOND);
        assertTrue(tablero.moveBetweenStacks(1,0,0));
        assertEquals(General.EMPTY,tablero.cardsInStack(1));
        assertEquals(3,tablero.cardsInStack(0));
        assertTrue(tablero.seeCard(0,2).getValue() == Values.JACK && tablero.seeCard(0,2).getSuit() == Suits.DIAMOND);
        tablero.moveBetweenStacks(0,0,7);
        assertTrue(tablero.seeCard(7,2).getValue() == Values.JACK && tablero.seeCard(7,2).getSuit() == Suits.DIAMOND);
    }


}