package Model.Global.Interfaces.Validations;

import Model.Global.MainObjects.Universal.Card;

public interface TableauValidation {
    boolean validateCardForTableau(Card newCard, Card tableauCard);
}
