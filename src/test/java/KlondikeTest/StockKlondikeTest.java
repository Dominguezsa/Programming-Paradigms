package KlondikeTest;

import Model.Global.Constants.General;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Universal.Card;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class StockKlondikeTest {
    private StockKlondike stock;
    private Card asDiamante;
    private Card dosDiamante;
    private Card tresPica;
    private Card asCorazon;

    @Before
    public void setUp() {
        stock = new StockKlondike();
        ArrayList<Card> Visibles = new ArrayList<Card>();
        Stack<Card> NoVisible = new Stack<>();
        asDiamante = new Card(Values.ACE, Suits.DIAMOND);
        dosDiamante = new Card(Values.TWO, Suits.DIAMOND);
        tresPica = new Card(Values.THREE, Suits.SPADE);
        asCorazon = new Card(Values.ACE, Suits.HEART);
        NoVisible.add(asDiamante);
        NoVisible.add(dosDiamante);
        NoVisible.add(tresPica);
        NoVisible.add(asCorazon);
        stock.prepareSpecificStock(NoVisible,Visibles);
    }

    @Test
    public void testPrepararStock() {
        assertEquals(4, stock.totalCards());
        assertNull(stock.seeFirstCard());
    }

    @Test
    public void testPasarTandaStock() {
        stock.moveCard();
        assertNotNull(stock.seeFirstCard());
    }

    @Test
    public void testSacarCarta() {
        stock.moveCard();
        Card cardSacada = stock.drawACard();
        assertNotNull(cardSacada);
        assertEquals(asCorazon, cardSacada);
    }
    @Test
    public void sacarCartas() {
        //se evalua que funciona pasarStock() y cantidad()
        Card card = stock.drawACard();
        assertNull(card);
        assertEquals(4,stock.totalCards());
        stock.moveCard();
        card = stock.drawACard();
        assertEquals(3,stock.totalCards());
        assertEquals(asCorazon, card);
        assertTrue(card.isVisible());
        stock.moveCard();
        card = stock.drawACard();
        assertEquals(2,stock.totalCards());
        assertEquals(tresPica, card);
        assertTrue(card.isVisible());
        stock.moveCard();
        card = stock.drawACard();
        assertEquals(1,stock.totalCards());
        assertEquals(dosDiamante, card);
        assertTrue(card.isVisible());
        stock.moveCard();
        card = stock.drawACard();
        assertEquals(0,stock.totalCards());
        assertEquals(asDiamante, card);
        assertTrue(card.isVisible());
    }
    @Test
    public void stockVacio(){
        stock.moveCard();
        stock.moveCard();
        stock.moveCard();
        stock.moveCard();

        stock.drawACard();
        stock.drawACard();
        stock.drawACard();
        stock.drawACard();

        assertEquals(General.EMPTY,stock.totalCards());
        assertNull(stock.drawACard());
    }
    @Test
    public void verPrimeraCarta() {
        Card card = stock.drawACard();
        assertNull(card);
        stock.moveCard();
        card = stock.seeFirstCard();
        assertEquals(asCorazon, card);
        assertTrue(card.isVisible());
        stock.drawACard();
        card = stock.seeFirstCard();
        assertNull(card);
    }

    @Test
    public void SeRespetanVisibilidades() {
        List<Card> visibles = stock.getVisibleStock();
        Stack<Card> noVisible = stock.getNonVisibleStock();
        assertEquals(0, visibles.size());
        assertEquals(4, noVisible.size());
        for (Card card : noVisible) {
            assertFalse(card.isVisible());
        }
        stock.moveCard();
        stock.moveCard();
        assertEquals(2,visibles.size());
        assertEquals(2,noVisible.size());
        for (Card card : visibles) {
            assertTrue(card.isVisible());
        }
        for (Card card : noVisible) {
            assertFalse(card.isVisible());
        }
        stock.moveCard();
        stock.moveCard();
        assertEquals(4, visibles.size());
        assertEquals(0, noVisible.size());
        //La cuarta carta no deberia ser visible, se evalua.
        for(int i=0;i<4;i++){
            assertTrue(visibles.get(i).isVisible());
        }
    }

    @Test
    public void testSePasaPorTodasLasCartas(){

        List<Card> visibles = stock.getVisibleStock();
        Stack<Card> noVisible = stock.getNonVisibleStock();
        //Paso las 4 cuartas a la parte visible
        stock.moveCard();
        stock.moveCard();
        stock.moveCard();
        stock.moveCard();
        //Vuelven a ser todas NoVisibles
        stock.moveCard();
        //Voy sacando y reviso que esten en orden, con la visibilidad correcta.
        stock.moveCard();
        assertEquals(3,noVisible.size());
        assertEquals(1,visibles.size());
        assertEquals(asCorazon,visibles.get(visibles.size()-1));
        assertTrue(asCorazon.isVisible());

        stock.moveCard();
        assertEquals(tresPica,visibles.get(visibles.size()-1));
        assertTrue(tresPica.isVisible());
        assertEquals(2,noVisible.size());
        assertEquals(2,visibles.size());

        stock.moveCard();
        assertEquals(dosDiamante,visibles.get(visibles.size()-1));
        assertTrue(dosDiamante.isVisible());
        assertEquals(1,noVisible.size());
        assertEquals(3,visibles.size());

        stock.moveCard();
        assertEquals(asDiamante,visibles.get(visibles.size()-1));
        assertTrue(asDiamante.isVisible());
        assertEquals(0,noVisible.size());
        assertEquals(4,visibles.size());
    }
}