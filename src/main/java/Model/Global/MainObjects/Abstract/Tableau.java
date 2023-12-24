package Model.Global.MainObjects.Abstract;

import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.AppendCards.CardsReceptor;
import Model.Global.Interfaces.Validations.TableauValidation;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tableau implements Serializable, CardsReceptor {

    protected final ArrayList<ArrayList<Card>> stacks;

    protected final TableauValidation validation;

    public Tableau(TableauValidation validation) {
        this.validation = validation;
        this.stacks = new ArrayList<ArrayList<Card>>();
    }

    public abstract void prepareStacks(Deck deck);

    public void prepareSpecificStacks(ArrayList<ArrayList<Card>> tableau) {
        this.stacks.clear();
        this.stacks.addAll(tableau);
    }

    public Card seeCard(int stackPosition, int cardPosition) {
        ArrayList<Card> stack = this.stacks.get(stackPosition);
        if (stack.isEmpty() || cardPosition >= stack.size() || !stack.get(cardPosition).isVisible()) {
            return null;
        }
        return stack.get(cardPosition);
    }

    public boolean addCard(Card newCard, int destinationColumn) {
        ArrayList<Card> stack = this.stacks.get(destinationColumn);
        Card lastCardDestination = this.seeCard(destinationColumn, stack.size() - 1);
        if (this.validation.validateCardForTableau(newCard, lastCardDestination)) {
            stack.add(newCard);
            newCard.changeObjectType(ObjectType.TABLEAU);
            newCard.establishColumn(destinationColumn);
            newCard.establishPosition(stacks.get(destinationColumn).size() - 1);
            return true;
        }
        return false;
    }

    public boolean moveBetweenStacks(int sourceStack, int sourceCardPosition, int destinationStack) {
        if (sourceCardPosition >= this.stacks.get(sourceStack).size()) {
            return false;
        }
        List<Card> littleStack = this.cardsFromPosition(sourceStack, sourceCardPosition);
        ArrayList<Card> pilaDest = this.stacks.get(destinationStack);
        Card cardDest = this.seeCard(destinationStack, pilaDest.size() - 1);
        Card cardOrig = littleStack.get(0);
        int pilaSize = pilaDest.size();
        if (this.validation.validateCardForTableau(cardOrig, cardDest)) {
            for (Card card : littleStack) {
                pilaDest.add(card);
                card.establishPosition(pilaSize);
                card.establishColumn(destinationStack);
                pilaSize++;
            }
            this.drawCardsFromPosition(sourceStack, sourceCardPosition);
            return true;
        }
        return false;
    }

    public List<Card> cardsFromPosition(int stackPosition, int cardPosition) {
        ArrayList<Card> stack = this.stacks.get(stackPosition);
        return stack.subList(cardPosition, stack.size());
    }

    public void drawCardsFromPosition(int stackPosition, int cardPosition) {
        ArrayList<Card> sourceStack = this.stacks.get(stackPosition);
        if (sourceStack.size() > cardPosition) {
            sourceStack.subList(cardPosition, sourceStack.size()).clear();
        }
        if (!sourceStack.isEmpty()) {
            Card lastCard = sourceStack.get(sourceStack.size() - 1);
            lastCard.changeVisibility(true);
        }
    }

    public int TotalCards() {
        int i = 0;
        for (ArrayList<Card> stack : this.stacks) {
            i += stack.size();
        }
        return i;
    }

    public int cardsInStack(int stackPosition) {
        return this.stacks.get(stackPosition).size();
    }

    public ArrayList<ArrayList<Card>> getStacks() {
        return this.stacks;
    }

    public int getStacksQuantity() {
        return this.stacks.size();
    }

    public ArrayList<Card> getStack(int stackPosition) {
        return this.stacks.get(stackPosition);
    }
}