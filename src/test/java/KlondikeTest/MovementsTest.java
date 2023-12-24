package KlondikeTest;


import Model.Global.Constants.General;
import Model.Global.Constants.Klondlike;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.KlondikeSolitaire.KlondikeGame;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import Model.KlondikeSolitaire.ConcreteObjects.KlondikeTableau;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class MovementsTest {
        private Foundation fundaciones;
        private KlondikeTableau tablero;
        private StockKlondike stock;
        private KlondikeGame juego;

        private Card asDiamante;
        private Card asPica;
        private Card asTrebol;
        private Card asCorazon;
        private Card reyDiamante;
        private Card dosPica;
        private Card dosTrebol;
        private Card seisPica;
        private Card cincoDiamante;
        private Card cuatroTrebol;
        private Card tresCorazon;
        private Card sieteDiamante;
        private Card reyPica;
        private Card seisTrebol;
        private Card cincoCorazon;
        private Card dosDiamante;
        private Card tresDiamante;
        private Card cuatroDiamante;
        @Before
        public void setUp(){
            juego = new KlondikeGame();
            fundaciones = juego.getFoundations();
            stock = juego.getStock();
            tablero = juego.getTableau();

            //Creo varias cartas.
            asCorazon = new Card(Values.ACE, Suits.HEART);
            asDiamante = new Card(Values.ACE, Suits.DIAMOND);
            asPica = new Card(Values.ACE, Suits.SPADE);
            asTrebol = new Card(Values.ACE, Suits.CLUB);
            dosDiamante = new Card(Values.TWO, Suits.DIAMOND);
            dosPica = new Card(Values.TWO, Suits.SPADE);
            dosTrebol = new Card(Values.TWO, Suits.CLUB);
            tresCorazon = new Card(Values.THREE, Suits.HEART);
            tresDiamante = new Card(Values.THREE, Suits.DIAMOND);
            cuatroDiamante = new Card(Values.FOUR, Suits.DIAMOND);
            cuatroTrebol = new Card(Values.FOUR, Suits.CLUB);
            cincoCorazon =  new Card(Values.FIVE, Suits.HEART);
            cincoDiamante = new Card(Values.FIVE, Suits.DIAMOND);
            seisPica = new Card(Values.SIX, Suits.SPADE);
            seisTrebol =  new Card(Values.SIX, Suits.CLUB);
            sieteDiamante = new Card(Values.SEVEN, Suits.DIAMOND);
            reyDiamante = new Card(Values.KING, Suits.DIAMOND);
            reyPica = new Card(Values.KING, Suits.SPADE);
        }
        @Test
        public void testMoverDesdeTableroAFundacion(){
            //En esta prueba solo hay una carta y se mueve entre fundaciones
            asDiamante.changeVisibility(true);
            tablero.getStack(0).add(asDiamante);

            //Test mover a fundacion::
            assertTrue(juego.moveTableauToFoundation(0,0,2));
            Card cardPrueba = new Card(Values.ACE, Suits.DIAMOND);
            int i = 0;
            for (ArrayList<Card> fund : fundaciones.getFoundations()){
                if (i ==2){
                    assertFalse(fund.isEmpty());
                }
                i = i+1;
            }
            for (ArrayList<Card> pila:tablero.getStacks()){
                assertEquals(0, pila.size());
            }
        }
        @Test
        public void testMoverDesdeTableroAFundacionInvalido(){
            //Prueba mover una carta que no es un as de una pila a una fundacion
            //Inicio el estado del juego:

            reyDiamante.changeVisibility(true);
            tablero.getStack(0).add(reyDiamante);

            Card cardPrueba = new Card(Values.KING, Suits.DIAMOND);
            //Trato de mover a una fundacion desde el tablero y verifico que nunca se pueda y todo se mantenga ok.
            for(int cuentaFund = 0; cuentaFund< Klondlike.FOUNDATIONS; cuentaFund++){
                assertFalse(juego.moveTableauToFoundation(0,cuentaFund,0));
                assertEquals(General.EMPTY,fundaciones.cardsInColumn(cuentaFund));
                ArrayList<Card> pila = tablero.getStack(0);
                assertEquals(1,pila.size());
            }
        }

        @Test
        public void testMoverDesdeTableroATablero(){
            seisPica.changeVisibility(true);
            cincoDiamante.changeVisibility(true);
            cuatroTrebol.changeVisibility(true);
            tresCorazon.changeVisibility(true);

            ArrayList<Card> pila0 = tablero.getStack(0);
            ArrayList<Card> pila1 = tablero.getStack(1);
            pila0.add(reyPica);
            pila0.add(seisPica);
            pila0.add(cincoDiamante);
            pila0.add(cuatroTrebol);
            pila0.add(tresCorazon);
            pila1.add(sieteDiamante);

            //Trato de mover pero no hace nada.
            assertFalse(juego.moveBetweenTableau(0,1,1));
            assertEquals(5,tablero.cardsInStack(0));
            assertEquals(1,tablero.cardsInStack(1));
            assertEquals(tresCorazon,tablero.seeCard(0,pila0.size()-1));
            assertNull(tablero.seeCard(1,pila1.size()-1)); //la carta no es visible.
            sieteDiamante.changeVisibility(true);

            //Muevo desde 6 de la pila 0 a la pila 1.
            assertTrue(juego.moveBetweenTableau(0,1,1));

            assertEquals(1,tablero.cardsInStack(0));
            assertEquals(reyPica, tablero.seeCard(0,0));
            assertEquals(5,tablero.cardsInStack(1));
            assertEquals(tresCorazon,tablero.seeCard(1,pila1.size()-1));
        }
        @Test
        public void testMoverDesdeTableroATablero2(){
            //Inicio el estado de 2 pilas.
            seisPica.changeVisibility(true);
            cincoDiamante.changeVisibility(true);
            cuatroTrebol.changeVisibility(true);
            seisTrebol.changeVisibility(true);
            cincoCorazon.changeVisibility(true);

            ArrayList<Card> pila0 = tablero.getStack(0);
            ArrayList<Card> pila1 = tablero.getStack(1);
            pila0.add(reyPica);
            pila0.add(seisPica);
            pila0.add(cincoDiamante);
            pila0.add(cuatroTrebol);
            pila1.add(seisTrebol);
            pila1.add(cincoCorazon);

            //Muevo el cuatro de trebol de la pila 0 a la pila 1
            assertTrue(juego.moveBetweenTableau(0,3,1));
            assertNull(tablero.seeCard(0,3));
            assertEquals(cincoDiamante,tablero.seeCard(0,2));
            assertEquals(cuatroTrebol,tablero.seeCard(1,2));
    }

        @Test
        public void testMoverDesdeStockAFundacionInvalido(){
            //Prueba mover una carta que no es un as de una pila a una fundacion
            //Inicio el estado del juego:

            dosDiamante.changeVisibility(true);
            tresDiamante.changeVisibility(true);
            cuatroDiamante.changeVisibility(true);
            stock.getVisibleStock().add(reyDiamante);
            stock.getVisibleStock().add(dosDiamante);
            stock.getVisibleStock().add(tresDiamante);
            stock.getVisibleStock().add(cuatroDiamante);
            ArrayList<Card> stockVisible = this.stock.getVisibleStock();
            Stack<Card> stockNoVisible = this.stock.getNonVisibleStock();
            //Trato de mover a una fundacion desde el stock y verifico que nunca se pueda y todo se mantenga ok.
            for(int cuentaFund = 0; cuentaFund< Klondlike.FOUNDATIONS; cuentaFund++){
                assertFalse(juego.moveStockToFoundation(cuentaFund));
                assertEquals(General.EMPTY,fundaciones.cardsInColumn(cuentaFund));
                assertEquals(cuatroDiamante,stockVisible.get(stockVisible.size()-1));
                assertEquals(tresDiamante,stockVisible.get(stockVisible.size()-2));
                assertEquals(dosDiamante,stockVisible.get(stockVisible.size()-3));
                assertEquals(reyDiamante,stockVisible.get(stockVisible.size()-4));
                assertEquals(0,stockNoVisible.size());
                assertEquals(4,stockVisible.size());
            }
        }
        @Test
        public void testMoverDesdeStockAFundacion(){
            //Preparo el stock:
            ArrayList<Card> stockVisible = this.stock.getVisibleStock();
            Stack<Card> stockNoVisible = this.stock.getNonVisibleStock();

            asDiamante.changeVisibility(true);
            asCorazon.changeVisibility(true);
            asPica.changeVisibility(true);
            asTrebol.changeVisibility(true);

            stockVisible.add(asDiamante);
            stockVisible.add(asCorazon);
            stockVisible.add(asPica);
            stockVisible.add(asTrebol);
            stockNoVisible.add(dosPica);
            stockNoVisible.add(dosTrebol);
            //Empiezo a mover los ases a fundaciones.
            assertTrue(juego.moveStockToFoundation(0));
            assertEquals(2,stockNoVisible.size());
            assertEquals(3,stockVisible.size());
            assertEquals(asPica,stockVisible.get(2));
            assertEquals(asCorazon,stockVisible.get(1));
            assertEquals(asDiamante,stockVisible.get(0));
            for (Card cardVis :stockVisible){
                assertTrue(cardVis.isVisible());
            }

            assertTrue(juego.moveCardStock());
            assertEquals(dosTrebol,stock.seeFirstCard());
            //Las cartas de stock visible siempre quedan en estado visible, por mas que no sean las primeras 3
            assertTrue(stockVisible.get(0).isVisible());
            assertEquals(asDiamante,stockVisible.get(0));
            assertTrue(juego.moveStockToFoundation(0));
            //Luego de mover, se hace visible nuevamente el As de diamante
            assertEquals(asDiamante,stockVisible.get(0));
            assertTrue(stockVisible.get(0).isVisible());
        }
        @Test
        public void testMoverDesdeStockATablero(){

            dosDiamante.changeVisibility(true);
            tresDiamante.changeVisibility(true);
            cuatroDiamante.changeVisibility(true);
            stock.getNonVisibleStock().add(reyDiamante);
            stock.getVisibleStock().add(dosDiamante);
            stock.getVisibleStock().add(tresDiamante);
            stock.getVisibleStock().add(cuatroDiamante);
            ArrayList<Card> stockVisible = this.stock.getVisibleStock();
            Stack<Card> stockNoVisible = this.stock.getNonVisibleStock();

            //Intento mover a un tablero vacio: no es posible.
            assertFalse(juego.moveStockToTableau(3));
            assertEquals(cuatroDiamante,stock.seeFirstCard());

            //Muevo el rey
            assertTrue(juego.moveCardStock());
            assertTrue(juego.moveStockToTableau(0));
            assertEquals(0,stockNoVisible.size());
            assertEquals(3,stockVisible.size());
            assertEquals(cuatroDiamante,stock.seeFirstCard());
            assertEquals(reyDiamante,tablero.seeCard(0,0));
        }
        @Test
        public void testMoverDesdeFundacionAFundacion(){
            //Test mover entre fundaciones:
            ArrayList<Card> fund2 = fundaciones.getFoundations().get(2);
            Card cardPrueba = new Card(Values.ACE, Suits.DIAMOND);
            fund2.add(cardPrueba);
            assertFalse(juego.moveBetweenFoundations(2,0));
            assertEquals(cardPrueba,fund2.get(0));
            cardPrueba.changeVisibility(true);
            assertTrue(juego.moveBetweenFoundations(2,0));
            int i=0;
            for (ArrayList<Card> fund:fundaciones.getFoundations()){
                if (i!=0){
                    assertEquals(General.EMPTY,fund.size());
                } else {
                    assertEquals(1,fund.size());
                    assertEquals(cardPrueba,fund.get(0));
                }
                i+=1;
            }

            assertTrue(juego.moveBetweenFoundations(0,3));
            i=0;
            for (ArrayList<Card> fund:fundaciones.getFoundations()){
                if (i!=3){
                    assertEquals(General.EMPTY,fund.size());
                } else {
                    assertEquals(1,fund.size());
                }
                i+=1;
            }
            assertTrue(juego.moveBetweenFoundations(3,1));
            i=0;
            for (ArrayList<Card> fund:fundaciones.getFoundations()){
                if (i!=1){
                    assertEquals(General.EMPTY,fund.size());
                } else {
                    assertEquals(1,fund.size());
                }
                i+=1;
            }

            //Mover a una fundacion invalida.
            Card cardPrueba2 = new Card(Values.ACE, Suits.HEART);
            cardPrueba2.changeVisibility(true);
            ArrayList<Card> fund0 = fundaciones.getFoundations().get(0);
            fund0.add(cardPrueba2);
            assertFalse(juego.moveBetweenFoundations(1,0));
            assertEquals(cardPrueba2,fund0.get(0));
        }
        @Test
        public void testMoverDesdeFundacionATablero(){
            asPica.changeVisibility(true);
            dosPica.changeVisibility(true);
            asCorazon.changeVisibility(true);
            assertTrue(fundaciones.addCard(asPica,0));
            assertTrue(fundaciones.addCard(dosPica,0));
            assertTrue(fundaciones.addCard(asCorazon,1));

            Card seisPica = new Card(Values.SIX, Suits.SPADE);
            Card cincoDiamante = new Card(Values.FIVE, Suits.DIAMOND);
            Card cuatroTrebol = new Card(Values.FOUR, Suits.CLUB);
            Card tresCorazon = new Card(Values.THREE, Suits.HEART);

            cincoDiamante.changeVisibility(true);
            cuatroTrebol.changeVisibility(true);
            tresCorazon.changeVisibility(true);

            ArrayList<Card> pila0 = tablero.getStack(0);
            pila0.add(seisPica);
            pila0.add(cincoDiamante);
            pila0.add(cuatroTrebol);
            pila0.add(tresCorazon);
            //Termino de preparar el estado del juego.

            //Muevo a el tablero y valido.
            assertTrue(juego.moveFoundationToTableau(0,0));
            assertFalse(juego.moveFoundationToTableau(0,0));
            assertTrue(juego.moveFoundationToTableau(1,0));

            assertEquals(1,fundaciones.cardsInColumn(0));
            assertEquals(General.EMPTY,fundaciones.cardsInColumn(1));
            assertEquals(asPica,fundaciones.seeCard(0));
            assertEquals(6,tablero.cardsInStack(0));
            assertEquals(asCorazon,tablero.seeCard(0,pila0.size()-1));
        }

        @Test
        public void testTableroAFundacion () {
            //test funciona bien visibilidad de cartas de tablero a fundacion
            asDiamante.changeVisibility(true);
            ArrayList<ArrayList<Card>> pilas = this.tablero.getStacks();
            Stack<Card> noVisibles = this.stock.getNonVisibleStock();
            ArrayList<Card> visibles = this.stock.getVisibleStock();
            pilas.get(0).add(cuatroTrebol);
            pilas.get(0).add(tresDiamante);
            pilas.get(0).add(dosDiamante);
            pilas.get(0).add(asDiamante);
            KlondikeGame juego = new KlondikeGame();
            juego.prepareSpecificGame(noVisibles, visibles, fundaciones.getFoundations(), pilas);
            assertTrue(juego.moveTableauToFoundation(0,3,0));
            assertTrue(dosDiamante.isVisible());
            assertFalse(tresDiamante.isVisible());
            assertFalse(cuatroTrebol.isVisible());
            assertTrue(juego.moveTableauToFoundation(0,2,0));
            assertTrue(tresDiamante.isVisible());
            assertFalse(cuatroTrebol.isVisible());
            assertFalse(juego.moveTableauToFoundation(4,0,0));
            assertTrue(juego.moveTableauToFoundation(0,1,0));
        }
}

