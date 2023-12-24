package KlondikeTest;

import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.KlondikeSolitaire.ConcreteObjects.KlondikeTableau;
import Model.KlondikeSolitaire.KlondikeValidations;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class KlondikeTableauTest {
    private KlondikeTableau tablero;
    private Card rey;
    private Card reinaTrebol;
    private Card ochoCorazon;
    private Card sieteTrebol;
    private Card nuevePica;

    @Before
    public void setUp() {
        tablero = new KlondikeTableau(new KlondikeValidations());
        Deck deck = new Deck();
        tablero.prepareStacks(deck); // Klondike comienza con 7 pilas
        rey = new Card(Values.KING, Suits.DIAMOND);
        reinaTrebol = new Card(Values.QUEEN, Suits.CLUB);
        ochoCorazon = new Card(Values.EIGHT, Suits.HEART);
        sieteTrebol = new Card(Values.SEVEN, Suits.CLUB);
        nuevePica = new Card(Values.NINE, Suits.SPADE);
    }

    @Test
    public void testPrepararPilas() {
        assertEquals(28, tablero.TotalCards()); // Klondike comienza con 28 cartas
        assertNotNull(tablero.seeCard(0, 0));
    }

    @Test
    public void testBorrarCartas() {
        tablero.drawCardsFromPosition(0, 0);
        assertEquals(0, tablero.cardsInStack(0));
    }

    @Test
    public void testVerCartaNoVisible() {
        Card card = tablero.seeCard(2,0);
        assertNull(card);
    }

    @Test
    public void testAgregarUnicaCarta() {
        tablero.drawCardsFromPosition(0,0);
        rey.changeVisibility(true);
        reinaTrebol.changeVisibility(true);
        tablero.addCard(rey,0);
        tablero.addCard(reinaTrebol,0);
        assertEquals(2,tablero.cardsInStack(0));
        tablero.addCard(reinaTrebol,0);
        assertEquals(2,tablero.cardsInStack(0));
    }

    @Test
    public void testAgregarCartas() {
        ArrayList<ArrayList<Card>> tablerito = new ArrayList<>();
        ArrayList<Card> pila1 = new ArrayList<>();
        ArrayList<Card> pila2 = new ArrayList<>();
        pila1.add(rey);
        ochoCorazon.changeVisibility(true);
        sieteTrebol.changeVisibility(true);
        nuevePica.changeVisibility(true);
        pila1.add(ochoCorazon);
        pila1.add(sieteTrebol);
        pila2.add(nuevePica);
        tablerito.add(pila1);
        tablerito.add(pila2);
        tablero.prepareSpecificStacks(tablerito);
        assertTrue(tablero.moveBetweenStacks(0,1,1));
        pila1 = tablero.getStack(0);
        pila2 = tablero.getStack(1);
        assertEquals(1, pila1.size());
        assertEquals(3, pila2.size());
        assertTrue(pila1.get(0).isVisible());
    }

    @Test
    public void testObtenerPilas() {
        ArrayList<Card> pila2 = tablero.getStack(1);
        assertEquals(2, pila2.size());
        assertFalse(pila2.get(0).isVisible());
        assertTrue(pila2.get(1).isVisible());
    }

    @Test
    public void testSeHaceVisible() {
        tablero.drawCardsFromPosition(2,2);
        ArrayList<Card> pila3 = tablero.getStack(2);
        assertEquals(2, pila3.size());
        assertFalse(pila3.get(0).isVisible());
        assertTrue(pila3.get(1).isVisible());
    }
    @Test
    public void testBorrarCarta(){
        ArrayList<ArrayList<Card>> pilas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<Card> pila = new ArrayList<>();
            pilas.add(pila);
        }
        tablero.prepareSpecificStacks(pilas);
        tablero.drawCardsFromPosition(0,0);
    }
}
