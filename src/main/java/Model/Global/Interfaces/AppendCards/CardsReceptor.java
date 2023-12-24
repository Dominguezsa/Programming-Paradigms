package Model.Global.Interfaces.AppendCards;

import Model.Global.MainObjects.Universal.Card;

public interface CardsReceptor {
    boolean addCard(Card newCard, int destinationColumn);
}
