package Model.Global.Interfaces.Validations;

import Model.Global.MainObjects.Universal.Card;

public interface FoundationValidation {
    boolean validateCardForFoundation(Card newCard, Card foundationCard);
}
