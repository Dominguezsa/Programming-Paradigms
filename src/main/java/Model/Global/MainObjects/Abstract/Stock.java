package Model.Global.MainObjects.Abstract;

import Model.Global.Constants.ObjectType;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;

import java.io.Serializable;
import java.util.Stack;

public abstract class Stock implements Serializable {

    protected Stack<Card> NonVisibleStock;
    public Stock() {
        this.NonVisibleStock = new Stack<>();
    }

    public void prepareStock(Deck deck) {
        Card card;
        while (deck.cantidad() > 0) {
            card = deck.drawCard();
            card.changeObjectType(ObjectType.STOCK);
            this.NonVisibleStock.add(card);
        }
    }

    public abstract boolean moveCard();

    public abstract Card seeFirstCard();

    public abstract int totalCards();

}