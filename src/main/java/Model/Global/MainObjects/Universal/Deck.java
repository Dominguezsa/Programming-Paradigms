package Model.Global.MainObjects.Universal;

import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck implements Serializable {
    private final ArrayList<Card> mazo;

    public Deck() {
        ArrayList<Card> arr = new ArrayList<>();

        for (Suits palo : Suits.values()) {
            for (Values valor : Values.values()) {
                Card card = new Card(valor, palo);
                arr.add(card);
            }
        }
        this.mazo = arr;
    }

    public int cantidad() {
        return mazo.size();
    }

    public Card drawCard() {
        return mazo.remove(mazo.size() - 1);
    }

    public ArrayList<Card> obtenerCartas() {
        return new ArrayList<>(this.mazo);
    }

    public void desorder() {
        Collections.shuffle(mazo);
    }

    public void desorder(Random rnd) {
        Collections.shuffle(mazo, rnd);
    }
}
