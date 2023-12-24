package ObjetosPrincipalesTest;

import Model.Global.Constants.General;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck;
    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testCantidadInicial() {
        assertEquals(General.COMPLETEDDECK, deck.cantidad());
    }

    @Test
    public void testSacarCarta() {
        int cantidadInicial = deck.cantidad();
        Card card = deck.drawCard();
        assertNotNull(card);
        assertEquals(cantidadInicial - 1, deck.cantidad());
    }

    @Test
    public void testDesordenarMaz() {
        ArrayList<Card> cartasAntes = deck.obtenerCartas();
        deck.desorder();
        ArrayList<Card> cartasDesordenadas = deck.obtenerCartas();
        assertNotEquals(cartasAntes, cartasDesordenadas);
    }

    @Test
    public void testDesordenarMazoConRnd() {
        ArrayList<Card> cartasAntes = deck.obtenerCartas();
        Random rnd = new Random();
        deck.desorder(rnd);
        ArrayList<Card> cartasDesordenadas = deck.obtenerCartas();
        assertNotEquals(cartasAntes, cartasDesordenadas);
    }


    @Test
    public void testMazoInicial() {
        //se verifica que haya creado la cantidad correcta de cartas
        Deck deck1 = new Deck();
        int resultado = deck1.cantidad();
        assertEquals(General.COMPLETEDDECK,resultado);
    }
    @Test
    public void testCartasEnMazoOk(){
        //se verifica que que esten todas las cartas
        Deck deck_prueba = new Deck();
        var dict = new HashMap<Suits,HashSet<Values>>();
        for (Suits palo: Suits.values()){
            dict.put(palo,new HashSet<>(List.of(Values.values())));
        }
        for (Card cart: deck_prueba.obtenerCartas()){
            Suits palo = cart.getSuit();
            assertTrue(dict.containsKey(palo));

            Values valor = cart.getValue();
            HashSet<Values> valuesRestantes = dict.get(palo);
            assertTrue(valuesRestantes.contains(valor));

            valuesRestantes.remove(valor);
        }
        for (Suits palo: Suits.values()){
            assertTrue(dict.get(palo).isEmpty());
        }
    }

    @Test
    public void testCartasPorPaloOk(){
        //se verifica que el mazo haya creado 13 cartas de cada palo
        Deck deck_prueba = new Deck();
        var dict = new HashMap<Suits, Integer>();
        for (Suits palo: Suits.values()){
            dict.put(palo,0);
        }
        for (Card card : deck_prueba.obtenerCartas()){
            Suits palo = card.getSuit();
            int valorActual = dict.get(palo);
            valorActual +=1;
            dict.put(palo, valorActual);

        }
        for (Suits palo: Suits.values()){
            int actual = dict.get(palo);
            assertEquals(General.CARDSBYSUIT, actual);
        }
    }
}

