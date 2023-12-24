package ObjetosPrincipalesTest;

import Model.Global.Constants.General;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;
import Model.Global.MainObjects.Concrete.Cell;
import Model.Global.MainObjects.Universal.Card;
import Model.FreeCellSolitaire.FreeCellValidations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class CellTest {
    private Cell celdas;
    private Card asCorazon;
    private Card reyPica;
    private Card diezDiamante;

    private Card cincoTrebol;

    @Before
    public void setUp(){
        var validacion = new FreeCellValidations();
        this.celdas = new Cell(validacion);
        this.asCorazon = new Card(Values.ACE, Suits.HEART);
        this.reyPica = new Card(Values.KING, Suits.SPADE);
        this.diezDiamante = new Card(Values.TEN, Suits.DIAMOND);
        this.cincoTrebol =  new Card(Values.FIVE, Suits.CLUB);
    }
    @Test
    public void testCrearCelda(){
        for (Card card :this.celdas.getCells()){
            assertEquals(General.EMPTYCELL, card);
        }
    }

    @Test
    public void testAgregarCartaACelda(){
        assertTrue(this.celdas.addCard(this.asCorazon,0));
        assertTrue(this.celdas.addCard(this.reyPica,1));
        assertTrue(this.celdas.addCard(this.cincoTrebol,2));
        assertTrue(this.celdas.addCard(this.diezDiamante,3));
        assertEquals(4, celdas.totalCard());
    }

    @Test
    public void testMoverCartaCeldas(){
        this.celdas.addCard(this.reyPica,1);
        assertFalse(this.celdas.addCard(this.asCorazon,1));

        assertTrue(this.celdas.moveBetweenCells(1,3));
        assertEquals(General.EMPTYCELL,this.celdas.getCells()[1]);

        assertTrue(this.celdas.moveBetweenCells(3,0));
        assertEquals(General.EMPTYCELL,this.celdas.getCells()[3]);

        assertFalse(this.celdas.moveBetweenCells(1,2));
        assertNull(this.celdas.getCells()[1]);
        assertNull(this.celdas.getCells()[2]);
    }

    @Test
    public void testFuncionaCantidadySacarCartas() {
        this.celdas.addCard(this.reyPica,0);
        this.celdas.addCard(this.asCorazon,1);
        this.celdas.addCard(this.cincoTrebol,2);
        this.celdas.addCard(this.diezDiamante,3);
        this.celdas.drawCard(0);
        assertEquals(3,celdas.totalCard());
        this.celdas.drawCard(1);
        assertEquals(2,celdas.totalCard());
        this.celdas.drawCard(2);
        assertEquals(1,celdas.totalCard());
        this.celdas.drawCard(3);
        assertEquals(0,celdas.totalCard());

    }
}
