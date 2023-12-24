package Model.Global.MainObjects.Concrete;

import Model.Global.Constants.Freecell;
import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.AppendCards.CardsReceptor;
import Model.Global.Interfaces.Validations.CellValidation;
import Model.Global.MainObjects.Universal.Card;

import java.io.Serializable;

public class Cell implements CardsReceptor, Serializable {
    private final Card[] cells;
    private final CellValidation validation;

    public Cell(CellValidation validation) {
        this.validation = validation;
        this.cells = new Card[4];
    }


    public boolean addCard(Card newCard, int destinationColumn) {
        Card cellCard = this.cells[destinationColumn];
        if (validation.validateCardForCell(newCard, cellCard)) {
            this.cells[destinationColumn] = newCard;
            newCard.changeObjectType(ObjectType.CELL);
            newCard.establishColumn(destinationColumn);
            return true;
        }
        return false;
    }

    public void drawCard(int columnPosition) {
        this.cells[columnPosition] = null;
    }

    public boolean moveBetweenCells(int sourceColumnPosition, int destinationColumn) {
        Card cardOrig = seeCard(sourceColumnPosition);
        Card cardDest = seeCard(destinationColumn);
        if (validation.validateCardForCell(cardOrig, cardDest)) {
            this.cells[destinationColumn] = cardOrig;
            this.cells[sourceColumnPosition] = null;
            cardOrig.establishColumn(destinationColumn);
            return true;
        }
        return false;
    }

    public Card seeCard(int columnPosition) {
        return this.cells[columnPosition];
    }

    public Card[] getCells() {
        return this.cells;
    }

    public int totalCard() {
        int i = 0;
        for (Card c : this.cells) {
            if (c != null) {
                i += 1;
            }
        }
        return i;
    }

    public void prepareSpecificCells(Card[] cards) {
        System.arraycopy(cards, 0, this.cells, 0, Freecell.CELLS);
    }
}
