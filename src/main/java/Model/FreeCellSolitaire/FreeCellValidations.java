package Model.FreeCellSolitaire;

import Model.FreeCellSolitaire.ConcreteObjects.FreeCellTableau;
import Model.Global.Constants.Values;
import Model.Global.Interfaces.Validations.CellValidation;
import Model.Global.Interfaces.Validations.FoundationValidation;
import Model.Global.Interfaces.Validations.TableauValidation;
import Model.Global.MainObjects.Concrete.Cell;
import Model.Global.MainObjects.Universal.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FreeCellValidations implements TableauValidation, FoundationValidation, CellValidation, Serializable {

    public boolean validateCardForFoundation(Card newCard, Card foundationCard) {
        if (newCard == null) {
            return false;
        }
        return (foundationCard == null && newCard.getValue() == Values.ACE) ||
                (foundationCard != null && newCard.getValue().getValue() == foundationCard.getValue().getValue() + 1
                        && newCard.getSuit() == foundationCard.getSuit());
    }

    public boolean validateCardForTableau(Card newCard, Card tableauCard) {
        if (newCard == null) {
            return false;
        }
        //se puede mover CUALQUIER carta a una pila vacia.
        return (tableauCard == null) ||
                (newCard.getColour() != tableauCard.getColour() &&
                        newCard.getValue().getValue() == tableauCard.getValue().getValue() - 1);
    }

    public boolean validateCardForCell(Card newCard, Card CellCard) {
        return newCard != null && CellCard == null;
    }

    private int getCardsToMove(FreeCellTableau tableau, Cell cells, int destinationColumn) {
        int emptyStacks = 0;
        int emptyCells = 0;
        var stacks = tableau.getStacks();
        ArrayList<Card> stack;
        for (int i = 0; i < stacks.size(); i++) {
            stack = stacks.get(i);
            if (stack.isEmpty() && i != destinationColumn) {
                emptyStacks += 1;
            }
        }
        for (Card card : cells.getCells()) {
            if (card == null) {
                emptyCells += 1;
            }
        }
        //http://www.solitairecentral.com/articles/FreecellPowerMovesExplained.html
        //(1 + number of empty freecells) * 2 ^ (number of empty columns) = max cards to move.
        return (int) ((emptyCells + 1) * Math.pow(2, emptyStacks));
    }

    public boolean ValidateMovementBetweenStacks(FreeCellTableau tableau, Cell cells, int sourceColumn, int sourceCardPosition, int destinationColumn) {
        if (sourceCardPosition >= tableau.getStack(sourceColumn).size()) {
            return false;
        }
        int numberOfCards = getCardsToMove(tableau, cells, destinationColumn);
        List<Card> littleStack = tableau.cardsFromPosition(sourceColumn, sourceCardPosition);
        return littleStack.size() <= numberOfCards;
    }

    public static boolean isNotLastCard(int posCartaOrigen, FreeCellTableau tableau, int sourceColumn) {
        return posCartaOrigen != tableau.cardsInStack(sourceColumn) - 1;
    }
}