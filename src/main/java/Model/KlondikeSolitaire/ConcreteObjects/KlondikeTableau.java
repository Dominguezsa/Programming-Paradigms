package Model.KlondikeSolitaire.ConcreteObjects;

import Model.Global.Constants.Klondlike;
import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.Validations.TableauValidation;
import Model.Global.MainObjects.Abstract.Tableau;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;

import java.io.Serializable;
import java.util.ArrayList;

public class KlondikeTableau extends Tableau implements Serializable {

    public KlondikeTableau(TableauValidation validacion) {
        super(validacion);
        for (int i = 0; i < Klondlike.INITIALTABLEAUCOLUMNS; i++) {
            this.stacks.add(new ArrayList<>());
        }
    }

    public void prepareStacks(Deck deck) {
        for (int i = 0; i < Klondlike.INITIALTABLEAUCOLUMNS; i++) {
            ArrayList<Card> pila = this.stacks.get(i);
            for (int j = 0; j < i + 1; j++) {
                Card card = deck.drawCard();
                card.changeObjectType(ObjectType.TABLEAU);
                card.establishColumn(i);
                card.establishPosition(j);
                pila.add(card);
                if (j == i) {
                    card.changeVisibility(true);
                }
            }
        }
    }

}