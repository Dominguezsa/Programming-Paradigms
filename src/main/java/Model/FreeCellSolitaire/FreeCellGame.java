package Model.FreeCellSolitaire;

import Model.Global.Constants.General;
import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.Solitaire;
import Model.Global.MainObjects.Concrete.Cell;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.FreeCellSolitaire.ConcreteObjects.FreeCellTableau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class FreeCellGame implements Solitaire, Serializable {
    private Cell cells;
    private Foundation foundations;
    private FreeCellTableau tableau;
    private boolean juegoTerminado;

    public FreeCellGame() {
        firstInstance();
        this.juegoTerminado = true;
    }

    public void prepareRandomGame() {
        Deck deck = new Deck();
        deck.desorder();
        this.juegoTerminado = false;
        this.tableau.prepareStacks(deck);
    }

    public void prepareGameWithSeed(Random rnd) {
        Deck deck = new Deck();
        deck.desorder(rnd);
        this.juegoTerminado = false;
        this.tableau.prepareStacks(deck);
    }

    public void prepareSpecificGame(Card[] cells, ArrayList<ArrayList<Card>> foundations,
                                    ArrayList<ArrayList<Card>> tableauStacks) {
        this.juegoTerminado = false;
        this.tableau.prepareSpecificStacks(tableauStacks);
        this.cells.prepareSpecificCells(cells);
        this.foundations.prepareSpecificFoundations(foundations);
    }


    public boolean moveTableauToTableau(int sourceColumn, int sourceCardPosition, int destinationColumn) {
        return Movements.moveTableauToTableau(tableau, cells, sourceColumn, sourceCardPosition, destinationColumn);
    }

    public boolean moveTableauToFoundation(int sourceColumn, int sourceCardPosition, int destinationColumn) {
        return Movements.moveTableauToFoundation(tableau, foundations, sourceCardPosition, sourceColumn, destinationColumn);
    }

    public boolean moveTableauToCell(int sourceColumn, int sourceCardPosition, int destinationColumn) {
        return Movements.moveTableauToCell(tableau, sourceColumn, sourceCardPosition, cells, destinationColumn);
    }

    public boolean moveBetweenCells(int sourceColumn, int destinationColumn) {
        return Movements.moveBetweenCells(cells, sourceColumn, destinationColumn);
    }

    public boolean moverCellToTableau(int sourceColumn, int destinationColumn) {
        return Movements.moveCellToTableau(cells, sourceColumn, tableau, destinationColumn);
    }

    public boolean moveCellToFoundation(int sourceColumn, int destinationColumn) {
        return Movements.moverCellToFoundation(cells, sourceColumn, foundations, destinationColumn);
    }

    public boolean isGameOver() {
        return juegoTerminado;
    }

    public Cell getCells() {
        return this.cells;
    }

    public FreeCellTableau getTableau() {
        return this.tableau;
    }

    public Foundation getFoundations() {
        return this.foundations;
    }

    public boolean gameFinished() {
        return this.tableau.TotalCards() == 0 && this.cells.totalCard() == 0;
    }

    public int countAllCards() {
        return this.foundations.totalCards() + this.tableau.TotalCards() + this.cells.totalCard();
    }

    public void firstInstance() {
        FreeCellValidations validaciones = new FreeCellValidations();
        this.cells = new Cell(validaciones);
        this.foundations = new Foundation(validaciones);
        this.tableau = new FreeCellTableau(validaciones);
    }

    public void universalMove(Card sourceCard, Card destinationCard, int objectTypeDestination, int column) {
        int source = sourceCard.getObjectType();
        if (destinationCard == null) {
            switch (objectTypeDestination) {
                case ObjectType.FOUNDATION:
                    if (source == ObjectType.TABLEAU) {
                        moveTableauToFoundation(sourceCard.getColumn(), sourceCard.getPosition(), column);
                    }
                    if (source == ObjectType.CELL) {
                        moveCellToFoundation(sourceCard.getColumn(), column);
                    }
                    break;
                case ObjectType.CELL:
                    if (source == ObjectType.TABLEAU) {
                        moveTableauToCell(sourceCard.getColumn(), sourceCard.getPosition(), column);
                    } else if (source == ObjectType.CELL) {
                        moveBetweenCells(sourceCard.getColumn(), column);
                    }
                    break;
                case ObjectType.TABLEAU:
                    if (source == ObjectType.TABLEAU) {
                        moveTableauToTableau(sourceCard.getColumn(), sourceCard.getPosition(), column);
                    } else if (source == ObjectType.CELL) {
                        moverCellToTableau(sourceCard.getColumn(), column);
                    }
                    break;
            }
        } else {
            if (source == ObjectType.TABLEAU) {
                if (objectTypeDestination == ObjectType.TABLEAU) {
                    moveTableauToTableau(sourceCard.getColumn(), sourceCard.getPosition(), destinationCard.getColumn());
                } else if (objectTypeDestination == ObjectType.FOUNDATION) {
                    moveTableauToFoundation(sourceCard.getColumn(), sourceCard.getPosition(), destinationCard.getColumn());
                } else if (objectTypeDestination == ObjectType.CELL) {
                    moveTableauToCell(sourceCard.getColumn(), sourceCard.getPosition(), destinationCard.getColumn());
                }
            } else if (source == ObjectType.CELL) {
                if (objectTypeDestination == ObjectType.FOUNDATION) {
                    moveCellToFoundation(sourceCard.getColumn(), destinationCard.getColumn());
                } else if (objectTypeDestination == ObjectType.TABLEAU) {
                    moverCellToTableau(sourceCard.getColumn(), destinationCard.getColumn());
                } else if (objectTypeDestination == ObjectType.CELL) {
                    moveBetweenCells(sourceCard.getColumn(), destinationCard.getColumn());
                }
            }
        }
    }

    public String getPath() {
        return General.FREECELLPATH;
    }

}
