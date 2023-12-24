package ObjetosPrincipalesTest;


import Model.Global.Constants.Klondlike;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.KlondikeSolitaire.KlondikeValidations;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FoundationTest {
    private Foundation fund;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @Before
    public void setUp() {
        fund = new Foundation(new KlondikeValidations());
        ArrayList<ArrayList<Card>>  fundacionesArr = new ArrayList<ArrayList<Card>>();
        for (int i = 0; i< Klondlike.FOUNDATIONS; i++){
            fundacionesArr.add(new ArrayList<Card>());
        }
        fund.prepareSpecificFoundations(fundacionesArr);
        card1 = new Card(Values.ACE, Suits.DIAMOND);
        card2 = new Card(Values.TWO, Suits.DIAMOND);
        card3 = new Card(Values.TWO, Suits.DIAMOND);
        card4 = new Card(Values.ACE, Suits.HEART);
    }

    @Test
    public void testAgregarCarta() {
        card1.changeVisibility(true);
        assertTrue(fund.addCard(card1, 0));
        assertEquals(1, fund.cardsInColumn(0));
    }

    @Test
    public void testNoAgregarCarta() {
        assertFalse(fund.addCard(card1, 0));
        assertEquals(0, fund.cardsInColumn(0));
    }

    @Test
    public void testAgregarCartaNoValida() {
        // Intentar agregar una carta que no es un As a una fundación vacía
        Card card = new Card(Values.KING, Suits.CLUB);
        assertFalse(fund.addCard(card, 1));
        assertEquals(0, fund.cardsInColumn(1));
    }

    @Test
    public void testSacarCartaFundacion() {
        card4.changeVisibility(true);
        fund.addCard(card4, 0);
        Card cardSacada = fund.seeCard(0);
        fund.drawCard(0);
        assertNotNull(cardSacada);
        assertEquals(card4, cardSacada);
    }

    @Test
    public void testSacarCartaFundacionVacia() {
        Card cardSacada = fund.seeCard(3);
        fund.drawCard(0);
        assertNull(cardSacada);
    }


    @Test
    public void testAgregarAs() {
        card1.changeVisibility(true);
        card2.changeVisibility(true);
        card3.changeVisibility(true);
        card4.changeVisibility(false);
        assertTrue(fund.addCard(card1, 0));
        assertTrue(fund.addCard(card2, 0));
        assertFalse(fund.addCard(card3, 2)); // False porque no es un AS
        assertFalse(fund.addCard(card4, 3)); // False porque no es visible
    }

    @Test
    public void testCambio() {
        card1.changeVisibility(true);
        card2.changeVisibility(true);
        card3.changeVisibility(true);
        card4.changeVisibility(false);
        assertTrue(fund.addCard(card1, 0));
        assertTrue(fund.moveBetweenFoundations(0,1));
        assertFalse(fund.moveBetweenFoundations(2, 3)); // False porque no es un AS
        assertTrue(fund.moveBetweenFoundations(1, 3)); // False porque no es visible
    }

    @Test
    public void testObtenerFundaciones() {
        ArrayList<ArrayList<Card>> fundaciones = fund.getFoundations();
        assertEquals(Klondlike.FOUNDATIONS, fundaciones.size());
        for (ArrayList<Card> fundacion : fundaciones) {
            assertEquals(0, fundacion.size()); // Todas las fundaciones deben estar vacías inicialmente
        }
        card1.changeVisibility(true);
        card2.changeVisibility(true);
        card3.changeVisibility(true);
        card4.changeVisibility(true);
        fund.addCard(card1, 0);
        fund.addCard(card2, 1);
        fund.addCard(card3, 2);
        fund.addCard(card4, 3);
        assertEquals(1, fund.cardsInColumn(0));
        assertEquals(0, fund.cardsInColumn(1));
        assertEquals(0, fund.cardsInColumn(2));
        assertEquals(1, fund.cardsInColumn(3));
    }

    @Test
    public void testCantidad() {
        card1.changeVisibility(true);
        card2.changeVisibility(true);
        card3.changeVisibility(true);
        card4.changeVisibility(true);
        assertTrue(fund.addCard(card1, 0));
        assertEquals(1,fund.totalCards());
        assertTrue(fund.addCard(card4, 1));
        assertEquals(2,fund.totalCards());
        //chequeo de cada fundacion particularmente
        assertEquals(1,fund.cardsInColumn(0));
        assertEquals(1,fund.cardsInColumn(1));
        assertEquals(0,fund.cardsInColumn(2));
        assertEquals(0,fund.cardsInColumn(3));
    }

    @Test
    public void SacarCarta() {
        card1.changeVisibility(true);
        card2.changeVisibility(true);
        card3.changeVisibility(true);
        card4.changeVisibility(true);
        assertTrue(fund.addCard(card1, 0));
        assertTrue(fund.addCard(card4, 1));
        Card cartanue = fund.seeCard(0);
        fund.drawCard(0);
        assertEquals(cartanue, card1);
        assertNotEquals(cartanue, card2);
        assertEquals(0,fund.cardsInColumn(0));
        fund.drawCard(1);
        assertEquals(0,fund.totalCards());
    }
}
