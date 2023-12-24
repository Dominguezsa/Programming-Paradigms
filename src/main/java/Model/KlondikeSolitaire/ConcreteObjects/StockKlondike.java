package Model.KlondikeSolitaire.ConcreteObjects;

import Model.Global.MainObjects.Abstract.Stock;
import Model.Global.MainObjects.Universal.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class StockKlondike extends Stock implements Serializable {

    private ArrayList<Card> visibleStock;

    public StockKlondike() {
        super(); // Initialize NonVisibleCards
        this.visibleStock = new ArrayList<>();
    }

    public boolean moveCard() {
        if (!NonVisibleStock.isEmpty()) {
            Card newCard = NonVisibleStock.pop();
            newCard.changeVisibility(true);
            visibleStock.add(newCard);
        }else{
            for (int i = visibleStock.size() - 1; i >= 0; i--) {
                Card card = visibleStock.remove(i);
                card.changeVisibility(false);
                NonVisibleStock.add(card);
            }
        }
        return true;
    }

    public Card drawACard() {
        if (visibleStock.isEmpty()) {
            return null;
        }
        return visibleStock.remove(visibleStock.size() - 1);
    }

    public Card seeFirstCard() {
        if (visibleStock.isEmpty()) {
            return null;
        }
        return visibleStock.get(visibleStock.size() - 1);
    }

    public int totalCards() {
        return this.visibleStock.size() + this.NonVisibleStock.size();
    }

    public Stack<Card> getNonVisibleStock() {
        return this.NonVisibleStock;
    }

    public ArrayList<Card> getVisibleStock() {
        return this.visibleStock;
    }

    public void prepareSpecificStock(Stack<Card> nonVisibleStock, ArrayList<Card> visibleStock) {
        this.NonVisibleStock.clear();
        this.visibleStock.clear();
        this.NonVisibleStock = nonVisibleStock;
        this.visibleStock = visibleStock;
    }
}