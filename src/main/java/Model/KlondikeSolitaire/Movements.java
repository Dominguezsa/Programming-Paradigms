package Model.KlondikeSolitaire;

import Model.Global.MainObjects.Concrete.AppendClass;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import Model.KlondikeSolitaire.ConcreteObjects.KlondikeTableau;

import java.io.Serializable;
import java.util.ArrayList;


public class Movements implements Serializable {

    public static boolean moveStockToFoundation(StockKlondike stock, Foundation foundations, int destinationColumn) {
        Card card = stock.seeFirstCard();
        return AppendClass.AppendStockToOther(stock, card, destinationColumn, foundations);
    }

    public static boolean moveStockToTableau(KlondikeTableau tableau, StockKlondike stock, int destinationColumn) {
        Card card = stock.seeFirstCard();
        return AppendClass.AppendStockToOther(stock, card, destinationColumn, tableau);
    }

    public static boolean moveFoundationToTableau(Foundation foundations, KlondikeTableau tableau, int sourceColumn, int destinationColumn) {
        Card card = foundations.seeCard(sourceColumn);
        return AppendClass.AppendFoundationToOther(foundations, card, sourceColumn, destinationColumn, tableau);
    }


    public static boolean moveBetweenFoundations(Foundation foundations, int sourceColumn, int destinationColumn) {
        return foundations.moveBetweenFoundations(sourceColumn, destinationColumn);
    }

    public static boolean moveBetweenTableau(KlondikeTableau tableau, int sourceColumn, int sourceCardPosition, int destinationColumn) {
        return tableau.moveBetweenStacks(sourceColumn, sourceCardPosition, destinationColumn);
    }

    public static boolean moveTableauToFoundation(KlondikeTableau tableau, Foundation foundations,
                                                  int sourceCardPosition, int sourceColumn, int destinationColumn) {
        ArrayList<Card> stack = tableau.getStack(sourceColumn);
        if (KlondikeValidations.isLastCard(stack, sourceCardPosition)) {
            Card initialCard = tableau.seeCard(sourceColumn, sourceCardPosition);
            return AppendClass.AppendTableauToOther(tableau, initialCard, sourceColumn, destinationColumn, foundations);
        }
        return false;
    }
}
