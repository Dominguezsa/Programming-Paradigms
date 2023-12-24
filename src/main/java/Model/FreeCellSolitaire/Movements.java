package Model.FreeCellSolitaire;

import Model.Global.MainObjects.Concrete.AppendClass;
import Model.Global.MainObjects.Concrete.Cell;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.FreeCellSolitaire.ConcreteObjects.FreeCellTableau;

public class Movements {

    public static boolean moveTableauToTableau(FreeCellTableau tableau, Cell cells, int sourceColumn, int sourceCardPosition, int destinationColumn) {
        if (tableau.reviewCardsQuantity(cells, sourceColumn, sourceCardPosition, destinationColumn)) {
            return tableau.moveBetweenStacks(sourceColumn, sourceCardPosition, destinationColumn);
        }
        return false;
    }

    public static boolean moveTableauToFoundation(FreeCellTableau tableau, Foundation foundations, int sourceCardPosition, int sourceColumn, int destinationColumn) {
        if (FreeCellValidations.isNotLastCard(sourceCardPosition, tableau, sourceColumn)) {
            return false;
        }
        Card initialCard = tableau.seeCard(sourceColumn, sourceCardPosition);
        return AppendClass.AppendTableauToOther(tableau, initialCard, sourceColumn, destinationColumn, foundations);
    }

    public static boolean moveTableauToCell(FreeCellTableau tableau, int sourceColumn, int sourceCardPosition, Cell cells, int destinationColumn) {
        if (FreeCellValidations.isNotLastCard(sourceCardPosition, tableau, sourceColumn)) {
            return false;
        }
        Card initialCard = tableau.seeCard(sourceColumn, sourceCardPosition);
        return AppendClass.AppendTableauToOther(tableau, initialCard, sourceColumn, destinationColumn, cells);
    }

    public static boolean moveBetweenCells(Cell cells, int sourceColumn, int destinationColumn) {
        return cells.moveBetweenCells(sourceColumn, destinationColumn);
    }

    public static boolean moveCellToTableau(Cell cells, int sourceColumn, FreeCellTableau tableau, int destinationColumn) {
        Card card = cells.seeCard(sourceColumn);
        return AppendClass.AppendCellToOther(cells, card, sourceColumn, destinationColumn, tableau);
    }

    public static boolean moverCellToFoundation(Cell cells, int sourceColumn, Foundation foundations, int destinationColumn) {
        Card card = cells.seeCard(sourceColumn);
        return AppendClass.AppendCellToOther(cells, card, sourceColumn, destinationColumn, foundations);
    }
}
