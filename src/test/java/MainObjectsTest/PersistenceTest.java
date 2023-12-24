package MainObjectsTest;

import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Persistence;
import Model.KlondikeSolitaire.KlondikeGame;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class PersistenceTest {
    private Persistence persist;
    @Before
    public void setUp(){
        this.persist = new Persistence();
    }
    @Test
    public void testGuardarPartidaKlondike(){
        //guardar juego
        KlondikeGame juego = new KlondikeGame();
        juego.prepareRandomGame();
        juego.moveCardStock();
        int numero = juego.getStock().getVisibleStock().size();
        assertEquals(1,numero);
        Card cardPila3 = juego.getTableau().getStack(3).get(3);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Persistence.saveGame(baos, juego);
        //cargar juego
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        KlondikeGame nuevo = (KlondikeGame) Persistence.loadGame(bais);
        
        int numero2 = nuevo.getStock().getVisibleStock().size();
        assertEquals(1,numero2);
        Card cardPila3Nueva = nuevo.getTableau().getStack(3).get(3);
    }
}
