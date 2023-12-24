package Model.FreeCellSolitaire.ConcreteObjects;

import Model.Global.Constants.Freecell;
import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.Validations.TableauValidation;
import Model.Global.MainObjects.Abstract.Tableau;
import Model.Global.MainObjects.Concrete.Cell;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.FreeCellSolitaire.FreeCellValidations;

import java.io.Serializable;
import java.util.ArrayList;

public class FreeCellTableau extends Tableau implements Serializable {

    public FreeCellTableau(TableauValidation validation) {
        super(validation);
        for (int i = 0; i < 8; i++) {
            super.stacks.add(new ArrayList<>());
        }
    }

    public void prepareStacks(Deck deck) {
        Card card;
        for (int i = 0; i <= Freecell.MAXFIRSTSTACKS; i++) {
            for (int j = 0; j < Freecell.FIRSTSTACKS; j++) {
                card = deck.drawCard();
                card.changeObjectType(ObjectType.TABLEAU);
                card.establishColumn(i);
                card.establishPosition(j);
                card.changeVisibility(true);
                super.stacks.get(i).add(card);
            }
        }
        for (int i = Freecell.MAXFIRSTSTACKS + 1; i <= Freecell.MAXSECONDSTACKS; i++) {
            for (int j = 0; j < Freecell.SECONDSTACKS; j++) {
                card = deck.drawCard();
                card.changeVisibility(true);
                card.changeObjectType(ObjectType.TABLEAU);
                card.establishColumn(i);
                card.establishPosition(j);
                super.stacks.get(i).add(card);
            }
        }
    }

    public boolean reviewCardsQuantity(Cell cells, int sourceColumn, int sourceCardPosition, int destinationColumn) {
        FreeCellValidations validation = (FreeCellValidations) this.validation;
        return validation.ValidateMovementBetweenStacks(this, cells, sourceColumn, sourceCardPosition, destinationColumn);
    }
}