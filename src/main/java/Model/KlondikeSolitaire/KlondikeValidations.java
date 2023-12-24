package Model.KlondikeSolitaire;

import Model.Global.Constants.Values;
import Model.Global.Interfaces.Validations.FoundationValidation;
import Model.Global.Interfaces.Validations.TableauValidation;
import Model.Global.MainObjects.Universal.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class KlondikeValidations implements FoundationValidation, TableauValidation, Serializable {

    public boolean validateCardForFoundation(Card newCard, Card foundationCard) {
        if (newCard == null || !newCard.isVisible()) {
            return false;
        }
        return (foundationCard == null && newCard.getValue() == Values.ACE) ||
                (foundationCard != null && newCard.getValue().getValue() == foundationCard.getValue().getValue() + 1
                        && newCard.getSuit() == foundationCard.getSuit());
    }

    public boolean validateCardForTableau(Card newCard, Card tableauCard) {
        if (newCard == null || !newCard.isVisible()) {
            return false;
        }
        return (tableauCard == null && newCard.getValue() == Values.KING) ||
                (tableauCard != null && newCard.getColour() != tableauCard.getColour() &&
                        newCard.getValue().getValue() == tableauCard.getValue().getValue() - 1);
    }

    public static boolean isLastCard(ArrayList<Card> stack, int cardPosition) {
        return cardPosition == stack.size() - 1;
    }
}


