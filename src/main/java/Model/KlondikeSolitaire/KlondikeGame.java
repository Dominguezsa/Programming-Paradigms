package Model.KlondikeSolitaire;


import Model.Global.Constants.General;
import Model.Global.Constants.ObjectType;
import Model.Global.Interfaces.Solitaire;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Deck;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import Model.KlondikeSolitaire.ConcreteObjects.KlondikeTableau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class KlondikeGame implements Solitaire, Serializable {
    private final Foundation foundations;
    private final StockKlondike stock;
    private final KlondikeTableau tableau;
    private boolean gameFinished;

    public KlondikeGame() {
        KlondikeValidations validations = new KlondikeValidations();
        this.foundations = new Foundation(validations);
        this.stock = new StockKlondike();
        this.tableau = new KlondikeTableau(validations);
        this.gameFinished = true;
    }

    public void prepareRandomGame() {
        Deck deck = new Deck();
        deck.desorder();
        this.gameFinished = false;
        this.tableau.prepareStacks(deck);
        this.stock.prepareStock(deck);
    }

    public void prepareGameWithSeed(Random rnd) {
        Deck deck = new Deck();
        deck.desorder(rnd);
        this.gameFinished = false;
        this.tableau.prepareStacks(deck);
        this.stock.prepareStock(deck);
    }

    public void prepareSpecificGame(Stack<Card> nonVisibleStock,
                                    ArrayList<Card> visibleStock, ArrayList<ArrayList<Card>> foundations,
                                    ArrayList<ArrayList<Card>> tableauStacks) {
        this.gameFinished = false;
        this.tableau.prepareSpecificStacks(tableauStacks);
        this.stock.prepareSpecificStock(nonVisibleStock, visibleStock);
        this.foundations.prepareSpecificFoundations(foundations);
    }

    public boolean moveCardStock() {
        return this.stock.moveCard();
    }


    //All movements return boolean whether they are done or not
    public boolean moveStockToFoundation(int destinationColumn) {
        return Movements.moveStockToFoundation(this.stock, this.foundations, destinationColumn);
    }

    public boolean moveStockToTableau(int destinationColumn) {
        return Movements.moveStockToTableau(this.tableau, this.stock, destinationColumn);
    }

    public boolean moveFoundationToTableau(int sourceStackColumn, int destinationColumn) {
        return Movements.moveFoundationToTableau(this.foundations, this.tableau, sourceStackColumn, destinationColumn);
    }

    public boolean moveBetweenFoundations(int sourceColumn, int destinationColumn) {
        return Movements.moveBetweenFoundations(this.foundations, sourceColumn, destinationColumn);
    }

    public boolean moveBetweenTableau(int sourceColumn, int cardPosition, int destinationColumn) {
        return Movements.moveBetweenTableau(this.tableau, sourceColumn, cardPosition, destinationColumn);
    }

    public boolean moveTableauToFoundation(int sourceColumn, int cardPosition, int destinationPosition) {
        return Movements.moveTableauToFoundation(this.tableau, this.foundations, cardPosition, sourceColumn, destinationPosition);
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public Foundation getFoundations() {
        return this.foundations;
    }

    public StockKlondike getStock() {
        return this.stock;
    }

    public KlondikeTableau getTableau() {
        return this.tableau;
    }

    public int countAllCards() {
        return this.foundations.totalCards() + this.tableau.TotalCards() + this.stock.totalCards();
    }

    public boolean gameFinished() {
        return this.foundations.totalCards() == 52;
    }

    public void universalMove(Card sourceCard, Card destinationCard, int objectTypeDestination, int column) {
        int source = sourceCard.getObjectType();
        if (destinationCard == null) {
            if (objectTypeDestination == ObjectType.FOUNDATION) {
                if (source == ObjectType.STOCK) {
                    moveStockToFoundation(column);
                }
                if (source == ObjectType.TABLEAU) {
                    moveTableauToFoundation(sourceCard.getColumn(), sourceCard.getPosition(), column);
                }
                if (source == ObjectType.FOUNDATION) {
                    moveBetweenFoundations(sourceCard.getColumn(), column);
                }
            } else {
                if (source == ObjectType.FOUNDATION) {
                    moveFoundationToTableau(sourceCard.getColumn(), column);
                }
                if (source == ObjectType.STOCK) {
                    moveStockToTableau(column);
                }
                if (source == ObjectType.TABLEAU) {
                    moveBetweenTableau(sourceCard.getColumn(), sourceCard.getPosition(), column);
                }
            }
        } else {
            if (source == ObjectType.TABLEAU && objectTypeDestination == ObjectType.TABLEAU) {
                moveBetweenTableau(sourceCard.getColumn(), sourceCard.getPosition(), column);
            }
            if (source == ObjectType.STOCK && objectTypeDestination == ObjectType.TABLEAU) {
                moveStockToTableau(column);
            }
            if (source == ObjectType.FOUNDATION && objectTypeDestination == ObjectType.TABLEAU) {
                moveFoundationToTableau(sourceCard.getColumn(), column);
            }
        }
    }

    public String getPath() {
        return General.KLONDIKEPATH;
    }
}