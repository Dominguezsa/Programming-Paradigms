package ObjetosPrincipalesTest;

import Modelo.Global.Constantes.Generales;
import Modelo.Global.Constantes.Palos;
import Modelo.Global.Constantes.Valores;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.Global.ObjetosPrincipales.Universales.Mazo;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MazoTest {

    private Mazo mazo;
    @Before
    public void setUp() {
        mazo = new Mazo();
    }

    @Test
    public void testCantidadInicial() {
        assertEquals(Generales.MAZOCOMPLETO, mazo.cantidad());
    }

    @Test
    public void testSacarCarta() {
        int cantidadInicial = mazo.cantidad();
        Carta carta = mazo.sacarCarta();
        assertNotNull(carta);
        assertEquals(cantidadInicial - 1, mazo.cantidad());
    }

    @Test
    public void testDesordenarMaz() {
        ArrayList<Carta> cartasAntes = mazo.obtenerCartas();
        mazo.desordenarMazo();
        ArrayList<Carta> cartasDesordenadas = mazo.obtenerCartas();
        assertNotEquals(cartasAntes, cartasDesordenadas);
    }

    @Test
    public void testDesordenarMazoConRnd() {
        ArrayList<Carta> cartasAntes = mazo.obtenerCartas();
        Random rnd = new Random();
        mazo.desordenarMazo(rnd);
        ArrayList<Carta> cartasDesordenadas = mazo.obtenerCartas();
        assertNotEquals(cartasAntes, cartasDesordenadas);
    }

    @Test
    public void testVerCarta() {
        Palos palo = Palos.CORAZON;
        Valores valor = Valores.AS;
        Carta carta = mazo.verCarta(palo, valor);

        assertNotNull(carta);
        assertEquals(palo, carta.obtenerPalo());
        assertEquals(valor, carta.obtenerValor());
    }


    @Test
    public void testMazoInicial() {
        //se verifica que haya creado la cantidad correcta de cartas
        Mazo mazo1 = new Mazo();
        int resultado = mazo1.cantidad();
        assertEquals(Generales.MAZOCOMPLETO,resultado);
    }
    @Test
    public void testCartasEnMazoOk(){
        //se verifica que que esten todas las cartas
        Mazo mazo_prueba = new Mazo();
        var dict = new HashMap<Palos,HashSet<Valores>>();
        for (Palos palo: Palos.values()){
            dict.put(palo,new HashSet<>(List.of(Valores.values())));
        }
        for (Carta cart: mazo_prueba.obtenerCartas()){
            Palos palo = cart.obtenerPalo();
            assertTrue(dict.containsKey(palo));

            Valores valor = cart.obtenerValor();
            HashSet<Valores> valoresRestantes = dict.get(palo);
            assertTrue(valoresRestantes.contains(valor));

            valoresRestantes.remove(valor);
        }
        for (Palos palo: Palos.values()){
            assertTrue(dict.get(palo).isEmpty());
        }
    }

    @Test
    public void testCartasPorPaloOk(){
        //se verifica que el mazo haya creado 13 cartas de cada palo
        Mazo mazo_prueba = new Mazo();
        var dict = new HashMap<Palos, Integer>();
        for (Palos palo: Palos.values()){
            dict.put(palo,0);
        }
        for (Carta carta: mazo_prueba.obtenerCartas()){
            Palos palo = carta.obtenerPalo();
            int valorActual = dict.get(palo);
            valorActual +=1;
            dict.put(palo, valorActual);

        }
        for (Palos palo: Palos.values()){
            int actual = dict.get(palo);
            assertEquals(Generales.CARTASPORPALO, actual);
        }
    }

    @Test
    public void testDesordenarMazo(){
        Mazo mazo = new Mazo();
        ArrayList<Carta> cartasInicial = mazo.obtenerCartas();
        mazo.desordenarMazo();
        ArrayList<Carta> cartasDespues = mazo.obtenerCartas();

        assertEquals(cartasDespues.size(),cartasInicial.size());
        Carta cartaI;
        Carta cartaD;
        boolean estadoDesordenado = false;
        for (int i=0;i<cartasInicial.size();i++){
            cartaI = cartasInicial.get(i);
            cartaD = cartasDespues.get(i);
            if (!cartaI.sonIguales(cartaD)){
                estadoDesordenado = true;
                break;
            }
        }
        assertTrue(estadoDesordenado);
    }
}

