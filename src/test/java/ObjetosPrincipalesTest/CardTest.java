package ObjetosPrincipalesTest;


import Model.Global.Constants.Colours;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Universal.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class CardTest {

    private Card card;

    @Before
    public void setUp() {
        card = new Card(Values.ACE, Suits.HEART);
    }

    @Test
    public void testObtenerValor() {
        assertEquals(Values.ACE, card.getValue());
    }

    @Test
    public void testObtenerPalo() {
        assertEquals(Suits.HEART, card.getSuit());
    }

    @Test
    public void testSonIguales() {
        Card otraCard = new Card(Values.ACE, Suits.HEART);
        assertTrue(card.getValue() == otraCard.getValue() && card.getSuit() == otraCard.getSuit());
    }

    @Test
    public void testObtenerColor() {
        assertEquals(Colours.RED, card.getColour());
    }

    @Test
    public void testHacerVisible() {
        card.changeVisibility(true);
        assertTrue(card.isVisible());
    }

    @Test
    public void testHacerNoVisible() {
        card.changeVisibility(false);
        assertFalse(card.isVisible());
    }

    @Test
    public void testToStringVisible() {
        card.changeVisibility(true);
        assertEquals("V-ACE-HEART", card.toString());
    }

    @Test
    public void testToStringNoVisible() {
        card.changeVisibility(false);
        assertEquals("***", card.toString());
    }

    @Test
    public void testCartasIguales() {
        //verifica que la funcion que devuelve si dos cartas son iguales funcione correctamente
        for (Suits palo : Suits.values()) {
            for (Values valor : Values.values()) {
                for (Colours color : Colours.values()) {
                    Card card1 = new Card(valor, palo);
                    Card card2 = new Card(valor, palo);
                    Boolean resultado = card1.getValue() == card2.getValue() && card1.getSuit() == card2.getSuit();
                    assertEquals(true, resultado);
                    assertEquals(card1.getColour(), card2.getColour());
                }
            }
        }
    }

}
