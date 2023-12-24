package Model.Global.MainObjects.Concrete;

import Model.Global.Interfaces.AppendCards.CardsReceptor;
import Model.Global.MainObjects.Abstract.Tableau;
import Model.Global.MainObjects.Universal.Card;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import java.io.Serializable;

public class AppendClass implements Serializable {

    public static boolean AppendTableauToOther(Tableau tableau, Card initialCard, int sourceStackPosition, int destinationPosition, CardsReceptor receptor) {
        if (receptor.addCard(initialCard, destinationPosition)) {
            int posUltimaCarta = tableau.cardsInStack(sourceStackPosition) - 1;
            tableau.drawCardsFromPosition(sourceStackPosition, posUltimaCarta);
            return true;
        }
        return false;
    }

    public static boolean AppendStockToOther(StockKlondike stock, Card card, int destinationPosition, CardsReceptor receptor) {
        if (receptor.addCard(card, destinationPosition)) {
            stock.drawACard();
            return true;
        }
        return false;
    }

    public static boolean AppendFoundationToOther(Foundation fund, Card initialCard, int columnPosition, int destinationPosition, CardsReceptor receptor) {
        if (receptor.addCard(initialCard, destinationPosition)) {
            fund.drawCard(columnPosition);
            return true;
        }
        return false;
    }

    public static boolean AppendCellToOther(Cell cell, Card sourceCard, int sourceColumnPosition, int destinationPosition, CardsReceptor receptor) {
        if (receptor.addCard(sourceCard, destinationPosition)) {
            cell.drawCard(sourceColumnPosition);
            return true;
        }
        return false;
    }


}
