package Model.Global.Interfaces.Validations;

import Model.Global.MainObjects.Universal.Card;

public interface CellValidation {
    boolean validateCardForCell(Card newCard, Card CellCard);
}
