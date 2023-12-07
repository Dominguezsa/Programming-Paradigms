package ObjetosPrincipalesTest;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Persistencia;
import Modelo.SolitarioFreeCell.JuegoFreeCell;
import Modelo.SolitarioKlondlike.JuegoKlondike;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class PersistenciaTest {
    private Persistencia persist;
    @Before
    public void setUp(){
        this.persist = new Persistencia();
    }
    @Test
    public void testGuardarPartidaKlondike(){
        //guardar juego
        JuegoKlondike juego = new JuegoKlondike();
        juego.prepararPartidaAleatoria();
        juego.pasarCartasStock();
        int numero = juego.obtenerStock().obtenerCartasVisibles().size();
        assertEquals(1,numero);
        Carta cartaPila3 = juego.obtenerTablero().obtenerPila(3).get(3);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Persistencia.guardarPartida(baos, juego);
        //cargar juego
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        JuegoKlondike nuevo = (JuegoKlondike) Persistencia.cargarPartida(bais);
        
        int numero2 = nuevo.obtenerStock().obtenerCartasVisibles().size();
        assertEquals(1,numero2);
        Carta cartaPila3Nueva = nuevo.obtenerTablero().obtenerPila(3).get(3);
        assertTrue(cartaPila3.sonIguales(cartaPila3Nueva));
    }

    @Test
    public void testGuardarPartidaFreeCell(){
        //guardar juego
        JuegoFreeCell juego = new JuegoFreeCell();
        juego.prepararPartidaAleatoria();
        Carta cartaPila3 = juego.obtenerTablero().obtenerPila(3).get(3);
        Carta cartaPila4 = juego.obtenerTablero().obtenerPila(4).get(3);
        Carta cartaPila5 = juego.obtenerTablero().obtenerPila(5).get(3);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Persistencia.guardarPartida(baos, juego);
        //cargar juego
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        JuegoFreeCell nuevo = (JuegoFreeCell) Persistencia.cargarPartida(bais);

        Carta cartaPila3Nueva = nuevo.obtenerTablero().obtenerPila(3).get(3);
        assertTrue(cartaPila3.sonIguales(cartaPila3Nueva));

        Carta cartaPila4Nueva = nuevo.obtenerTablero().obtenerPila(4).get(3);
        assertTrue(cartaPila4.sonIguales(cartaPila4Nueva));

        Carta cartaPila5Nueva = nuevo.obtenerTablero().obtenerPila(5).get(3);
        assertTrue(cartaPila5.sonIguales(cartaPila5Nueva));
    }
}
