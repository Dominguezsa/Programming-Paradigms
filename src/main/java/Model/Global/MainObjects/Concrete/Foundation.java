package Model.Global.MainObjects.Concrete;

import Model.Global.Constants.Klondlike;
import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.AppendCards.CardsReceptor;
import Model.Global.Interfaces.Validations.FoundationValidation;
import Model.Global.MainObjects.Universal.Card;

import java.io.Serializable;
import java.util.ArrayList;


public class Foundation implements Serializable, CardsReceptor {
    private final ArrayList<ArrayList<Card>> foundations;
    private final FoundationValidation validation;

    public Foundation(FoundationValidation validation) {
        this.foundations = new ArrayList<>();
        for (int i = 0; i < Klondlike.FOUNDATIONS; i++) {
            foundations.add(new ArrayList<>());
        }
        this.validation = validation;
    }

    public void drawCard(int posFund) {
        ArrayList<Card> fund = this.foundations.get(posFund);
        if (fund.isEmpty()) {
            return;
        }
        fund.remove(fund.size() - 1);
    }

    public boolean addCard(Card newCard, int destinationColumn) {
        ArrayList<Card> fundDest = this.foundations.get(destinationColumn);
        Card cardFund = this.seeCard(destinationColumn);
        if (validation.validateCardForFoundation(newCard, cardFund)) {
            fundDest.add(newCard);
            newCard.changeObjectType(ObjectType.FOUNDATION);
            newCard.establishColumn(destinationColumn);
            newCard.establishPosition(this.foundations.get(destinationColumn).size());
            return true;
        }
        return false;
    }

    public Card seeCard(int posFund) {
        ArrayList<Card> fund = this.foundations.get(posFund);
        if (fund.isEmpty()) {
            return null;
        }
        return fund.get(fund.size() - 1);
    }

    public boolean moveBetweenFoundations(int sourcePosition, int destinationPosition) {
        Card cardOrig = this.seeCard(sourcePosition);
        Card cardDest = this.seeCard(destinationPosition);
        if (validation.validateCardForFoundation(cardOrig, cardDest)) {
            this.foundations.get(destinationPosition).add(cardOrig);
            this.drawCard(sourcePosition);
            cardOrig.establishColumn(destinationPosition);
            return true;
        }
        return false;
    }

    public int totalCards() {
        int i = 0;
        for (ArrayList<Card> stack : this.foundations) {
            i += stack.size();
        }
        return i;
    }

    public int cardsInColumn(int posFund) {
        return this.foundations.get(posFund).size();
    }

    public ArrayList<ArrayList<Card>> getFoundations() {
        return this.foundations;
    }

    public void prepareSpecificFoundations(ArrayList<ArrayList<Card>> foundations) {
        this.foundations.clear();
        this.foundations.addAll(foundations);
    }

}

