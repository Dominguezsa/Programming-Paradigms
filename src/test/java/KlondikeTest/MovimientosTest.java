package KlondikeTest;


import Modelo.Global.Constantes.Generales;
import Modelo.Global.Constantes.Klondlike;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioKlondlike.JuegoKlondike;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;
import Modelo.SolitarioKlondlike.ObjetosConcretos.TableroKlondike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class MovimientosTest {
        private Fundacion fundaciones;
        private TableroKlondike tablero;
        private StockKlondike stock;
        private JuegoKlondike juego;

        private Carta asDiamante;
        private Carta asPica;
        private Carta asTrebol;
        private Carta asCorazon;
        private Carta reyDiamante;
        private Carta dosPica;
        private Carta dosTrebol;
        private Carta seisPica;
        private Carta cincoDiamante;
        private Carta cuatroTrebol;
        private Carta tresCorazon;
        private Carta sieteDiamante;
        private Carta reyPica;
        private Carta seisTrebol;
        private Carta cincoCorazon;
        private Carta dosDiamante;
        private Carta tresDiamante;
        private Carta cuatroDiamante;
        @Before
        public void setUp(){
            juego = new JuegoKlondike();
            fundaciones = juego.obtenerFundaciones();
            stock = juego.obtenerStock();
            tablero = juego.obtenerTablero();

            //Creo varias cartas.
            asCorazon = new Carta(Valores.AS, Palos.CORAZON);
            asDiamante = new Carta(Valores.AS, Palos.DIAMANTE);
            asPica = new Carta(Valores.AS, Palos.PICA);
            asTrebol = new Carta(Valores.AS, Palos.TREBOL);
            dosDiamante = new Carta(Valores.DOS,Palos.DIAMANTE);
            dosPica = new Carta(Valores.DOS, Palos.PICA);
            dosTrebol = new Carta(Valores.DOS, Palos.TREBOL);
            tresCorazon = new Carta (Valores.TRES,Palos.CORAZON);
            tresDiamante = new Carta(Valores.TRES,Palos.DIAMANTE);
            cuatroDiamante = new Carta(Valores.CUATRO,Palos.DIAMANTE);
            cuatroTrebol = new Carta (Valores.CUATRO,Palos.TREBOL);
            cincoCorazon =  new Carta(Valores.CINCO,Palos.CORAZON);
            cincoDiamante = new Carta (Valores.CINCO,Palos.DIAMANTE);
            seisPica = new Carta(Valores.SEIS,Palos.PICA);
            seisTrebol =  new Carta(Valores.SEIS,Palos.TREBOL);
            sieteDiamante = new Carta(Valores.SIETE,Palos.DIAMANTE);
            reyDiamante = new Carta(Valores.REY, Palos.DIAMANTE);
            reyPica = new Carta(Valores.REY,Palos.PICA);
        }
        @Test
        public void testMoverDesdeTableroAFundacion(){
            //En esta prueba solo hay una carta y se mueve entre fundaciones
            asDiamante.hacerVisible();
            tablero.obtenerPila(0).add(asDiamante);

            //Test mover a fundacion::
            assertTrue(juego.moverTableroAFundacion(0,0,2));
            Carta cartaPrueba = new Carta(Valores.AS, Palos.DIAMANTE);
            int i = 0;
            for (ArrayList<Carta> fund : fundaciones.obtenerFundaciones()){
                if (i ==2){
                    assertFalse(fund.isEmpty());
                    assertTrue(cartaPrueba.sonIguales(fund.get((0))));
                }
                i = i+1;
            }
            for (ArrayList<Carta> pila:tablero.obtenerPilas()){
                assertEquals(0, pila.size());
            }
        }
        @Test
        public void testMoverDesdeTableroAFundacionInvalido(){
            //Prueba mover una carta que no es un as de una pila a una fundacion
            //Inicio el estado del juego:

            reyDiamante.hacerVisible();
            tablero.obtenerPila(0).add(reyDiamante);

            Carta cartaPrueba = new Carta(Valores.REY, Palos.DIAMANTE);
            //Trato de mover a una fundacion desde el tablero y verifico que nunca se pueda y todo se mantenga ok.
            for(int cuentaFund = 0; cuentaFund< Klondlike.FUNDACIONES; cuentaFund++){
                assertFalse(juego.moverTableroAFundacion(0,cuentaFund,0));
                assertEquals(Generales.FUNDACIONVACIA,fundaciones.cantCartas(cuentaFund));
                ArrayList<Carta> pila = tablero.obtenerPila(0);
                assertEquals(1,pila.size());
                assertTrue(cartaPrueba.sonIguales(pila.get(0)));
            }
        }

        @Test
        public void testMoverDesdeTableroATablero(){
            seisPica.hacerVisible();
            cincoDiamante.hacerVisible();
            cuatroTrebol.hacerVisible();
            tresCorazon.hacerVisible();

            ArrayList<Carta> pila0 = tablero.obtenerPila(0);
            ArrayList<Carta> pila1 = tablero.obtenerPila(1);
            pila0.add(reyPica);
            pila0.add(seisPica);
            pila0.add(cincoDiamante);
            pila0.add(cuatroTrebol);
            pila0.add(tresCorazon);
            pila1.add(sieteDiamante);

            //Trato de mover pero no hace nada.
            assertFalse(juego.moverTableroATablero(0,1,1));
            assertEquals(5,tablero.cantCartas(0));
            assertEquals(1,tablero.cantCartas(1));
            assertEquals(tresCorazon,tablero.verCartaPos(0,pila0.size()-1));
            assertNull(tablero.verCartaPos(1,pila1.size()-1)); //la carta no es visible.
            sieteDiamante.hacerVisible();

            //Muevo desde 6 de la pila 0 a la pila 1.
            assertTrue(juego.moverTableroATablero(0,1,1));

            assertEquals(1,tablero.cantCartas(0));
            assertEquals(reyPica, tablero.verCartaPos(0,0));
            assertEquals(5,tablero.cantCartas(1));
            assertEquals(tresCorazon,tablero.verCartaPos(1,pila1.size()-1));
        }
        @Test
        public void testMoverDesdeTableroATablero2(){
            //Inicio el estado de 2 pilas.
            seisPica.hacerVisible();
            cincoDiamante.hacerVisible();
            cuatroTrebol.hacerVisible();
            seisTrebol.hacerVisible();
            cincoCorazon.hacerVisible();

            ArrayList<Carta> pila0 = tablero.obtenerPila(0);
            ArrayList<Carta> pila1 = tablero.obtenerPila(1);
            pila0.add(reyPica);
            pila0.add(seisPica);
            pila0.add(cincoDiamante);
            pila0.add(cuatroTrebol);
            pila1.add(seisTrebol);
            pila1.add(cincoCorazon);

            //Muevo el cuatro de trebol de la pila 0 a la pila 1
            assertTrue(juego.moverTableroATablero(0,3,1));
            assertNull(tablero.verCartaPos(0,3));
            assertEquals(cincoDiamante,tablero.verCartaPos(0,2));
            assertEquals(cuatroTrebol,tablero.verCartaPos(1,2));
    }

        @Test
        public void testMoverDesdeStockAFundacionInvalido(){
            //Prueba mover una carta que no es un as de una pila a una fundacion
            //Inicio el estado del juego:

            dosDiamante.hacerVisible();
            tresDiamante.hacerVisible();
            cuatroDiamante.hacerVisible();
            stock.obtenerCartasVisibles().add(reyDiamante);
            stock.obtenerCartasVisibles().add(dosDiamante);
            stock.obtenerCartasVisibles().add(tresDiamante);
            stock.obtenerCartasVisibles().add(cuatroDiamante);
            ArrayList<Carta> stockVisible = this.stock.obtenerCartasVisibles();
            Stack<Carta> stockNoVisible = this.stock.obtenerStockNoVisible();
            //Trato de mover a una fundacion desde el stock y verifico que nunca se pueda y todo se mantenga ok.
            for(int cuentaFund = 0; cuentaFund< Klondlike.FUNDACIONES; cuentaFund++){
                assertFalse(juego.moverStockAFundacion(cuentaFund));
                assertEquals(Generales.FUNDACIONVACIA,fundaciones.cantCartas(cuentaFund));
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
            ArrayList<Carta> stockVisible = this.stock.obtenerCartasVisibles();
            Stack<Carta> stockNoVisible = this.stock.obtenerStockNoVisible();

            asDiamante.hacerVisible();
            asCorazon.hacerVisible();
            asPica.hacerVisible();
            asTrebol.hacerVisible();

            stockVisible.add(asDiamante);
            stockVisible.add(asCorazon);
            stockVisible.add(asPica);
            stockVisible.add(asTrebol);
            stockNoVisible.add(dosPica);
            stockNoVisible.add(dosTrebol);
            //Empiezo a mover los ases a fundaciones.
            assertTrue(juego.moverStockAFundacion(0));
            assertEquals(2,stockNoVisible.size());
            assertEquals(3,stockVisible.size());
            assertEquals(asPica,stockVisible.get(2));
            assertEquals(asCorazon,stockVisible.get(1));
            assertEquals(asDiamante,stockVisible.get(0));
            for (Carta cartaVis:stockVisible){
                assertTrue(cartaVis.esVisible());
            }

            assertTrue(juego.pasarCartasStock());
            assertEquals(dosTrebol,stock.verPrimerCarta());
            //Las cartas de stock visible siempre quedan en estado visible, por mas que no sean las primeras 3
            assertTrue(stockVisible.get(0).esVisible());
            assertEquals(asDiamante,stockVisible.get(0));
            assertTrue(juego.moverStockAFundacion(0));
            //Luego de mover, se hace visible nuevamente el As de diamante
            assertEquals(asDiamante,stockVisible.get(0));
            assertTrue(stockVisible.get(0).esVisible());
        }
        @Test
        public void testMoverDesdeStockATablero(){

            dosDiamante.hacerVisible();
            tresDiamante.hacerVisible();
            cuatroDiamante.hacerVisible();
            stock.obtenerStockNoVisible().add(reyDiamante);
            stock.obtenerCartasVisibles().add(dosDiamante);
            stock.obtenerCartasVisibles().add(tresDiamante);
            stock.obtenerCartasVisibles().add(cuatroDiamante);
            ArrayList<Carta> stockVisible = this.stock.obtenerCartasVisibles();
            Stack<Carta> stockNoVisible = this.stock.obtenerStockNoVisible();

            //Intento mover a un tablero vacio: no es posible.
            assertFalse(juego.moverStockATablero(3));
            assertEquals(cuatroDiamante,stock.verPrimerCarta());

            //Muevo el rey
            assertTrue(juego.pasarCartasStock());
            assertTrue(juego.moverStockATablero(0));
            assertEquals(0,stockNoVisible.size());
            assertEquals(3,stockVisible.size());
            assertEquals(cuatroDiamante,stock.verPrimerCarta());
            assertEquals(reyDiamante,tablero.verCartaPos(0,0));
        }
        @Test
        public void testMoverDesdeFundacionAFundacion(){
            //Test mover entre fundaciones:
            ArrayList<Carta> fund2 = fundaciones.obtenerFundaciones().get(2);
            Carta cartaPrueba = new Carta(Valores.AS,Palos.DIAMANTE);
            fund2.add(cartaPrueba);
            assertFalse(juego.moverFundacionAFundacion(2,0));
            assertEquals(cartaPrueba,fund2.get(0));
            cartaPrueba.hacerVisible();
            assertTrue(juego.moverFundacionAFundacion(2,0));
            int i=0;
            for (ArrayList<Carta> fund:fundaciones.obtenerFundaciones()){
                if (i!=0){
                    assertEquals(Generales.FUNDACIONVACIA,fund.size());
                } else {
                    assertEquals(1,fund.size());
                    assertEquals(cartaPrueba,fund.get(0));
                }
                i+=1;
            }

            assertTrue(juego.moverFundacionAFundacion(0,3));
            i=0;
            for (ArrayList<Carta> fund:fundaciones.obtenerFundaciones()){
                if (i!=3){
                    assertEquals(Generales.FUNDACIONVACIA,fund.size());
                } else {
                    assertEquals(1,fund.size());
                    assertTrue(cartaPrueba.sonIguales(fund.get(0)));
                }
                i+=1;
            }
            assertTrue(juego.moverFundacionAFundacion(3,1));
            i=0;
            for (ArrayList<Carta> fund:fundaciones.obtenerFundaciones()){
                if (i!=1){
                    assertEquals(Generales.FUNDACIONVACIA,fund.size());
                } else {
                    assertEquals(1,fund.size());
                    assertTrue(cartaPrueba.sonIguales(fund.get(0)));
                }
                i+=1;
            }

            //Mover a una fundacion invalida.
            Carta cartaPrueba2= new Carta(Valores.AS,Palos.CORAZON);
            cartaPrueba2.hacerVisible();
            ArrayList<Carta> fund0 = fundaciones.obtenerFundaciones().get(0);
            fund0.add(cartaPrueba2);
            assertFalse(juego.moverFundacionAFundacion(1,0));
            assertEquals(cartaPrueba2,fund0.get(0));
        }
        @Test
        public void testMoverDesdeFundacionATablero(){
            asPica.hacerVisible();
            dosPica.hacerVisible();
            asCorazon.hacerVisible();
            assertTrue(fundaciones.agregarCarta(asPica,0));
            assertTrue(fundaciones.agregarCarta(dosPica,0));
            assertTrue(fundaciones.agregarCarta(asCorazon,1));

            Carta seisPica = new Carta(Valores.SEIS,Palos.PICA);
            Carta cincoDiamante = new Carta (Valores.CINCO,Palos.DIAMANTE);
            Carta cuatroTrebol = new Carta (Valores.CUATRO,Palos.TREBOL);
            Carta tresCorazon = new Carta (Valores.TRES,Palos.CORAZON);

            cincoDiamante.hacerVisible();
            cuatroTrebol.hacerVisible();
            tresCorazon.hacerVisible();

            ArrayList<Carta> pila0 = tablero.obtenerPila(0);
            pila0.add(seisPica);
            pila0.add(cincoDiamante);
            pila0.add(cuatroTrebol);
            pila0.add(tresCorazon);
            //Termino de preparar el estado del juego.

            //Muevo a el tablero y valido.
            assertTrue(juego.moverFundacionATablero(0,0));
            assertFalse(juego.moverFundacionATablero(0,0));
            assertTrue(juego.moverFundacionATablero(1,0));

            assertEquals(1,fundaciones.cantCartas(0));
            assertEquals(Generales.FUNDACIONVACIA,fundaciones.cantCartas(1));
            assertEquals(asPica,fundaciones.verPrimerCartaFundacion(0));
            assertEquals(6,tablero.cantCartas(0));
            assertEquals(asCorazon,tablero.verCartaPos(0,pila0.size()-1));
        }

        @Test
        public void testTableroAFundacion () {
            //test funciona bien visibilidad de cartas de tablero a fundacion
            asDiamante.hacerVisible();
            ArrayList<ArrayList<Carta>> pilas = this.tablero.obtenerPilas();
            Stack<Carta> noVisibles = this.stock.obtenerStockNoVisible();
            ArrayList<Carta> visibles = this.stock.obtenerCartasVisibles();
            pilas.get(0).add(cuatroTrebol);
            pilas.get(0).add(tresDiamante);
            pilas.get(0).add(dosDiamante);
            pilas.get(0).add(asDiamante);
            JuegoKlondike juego = new JuegoKlondike();
            juego.prepararPartidaEspecifica(noVisibles, visibles, fundaciones.obtenerFundaciones(), pilas);
            assertTrue(juego.moverTableroAFundacion(0,3,0));
            assertTrue(dosDiamante.esVisible());
            assertFalse(tresDiamante.esVisible());
            assertFalse(cuatroTrebol.esVisible());
            assertTrue(juego.moverTableroAFundacion(0,2,0));
            assertTrue(tresDiamante.esVisible());
            assertFalse(cuatroTrebol.esVisible());
            assertFalse(juego.moverTableroAFundacion(4,0,0));
            assertTrue(juego.moverTableroAFundacion(0,1,0));
        }
}

