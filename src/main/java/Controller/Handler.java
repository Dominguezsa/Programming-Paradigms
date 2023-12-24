package Controller;

import Model.Global.Constants.ObjectType;
import Model.Global.MainObjects.Abstract.Stock;
import Model.Global.MainObjects.Concrete.Foundation;
import Model.Global.MainObjects.Universal.Card;
import View.Universal.CardView;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Handler implements Serializable {
    private final GameController controller;

    public Handler(GameController c) {
        this.controller = c;
    }

    public void moveToFreeCells(int columnPosition, Card card, ImageView img) {
        if (controller.getSelectedCard() != null) {
            controller.changeCards(card, ObjectType.CELL, columnPosition);
        } else if (card != null) {
            CardView.ApplyFilter(img);
            controller.establishSelectedCard(card);
        }
    }

    public void moveToFoundation(int ColumnPosition, Foundation foundation, ImageView img, boolean canBeSelected) {
        if (controller.getSelectedCard() != null) {
            controller.changeCards(null, ObjectType.FOUNDATION, ColumnPosition);
        } else if (canBeSelected && foundation.seeCard(ColumnPosition) != null) {
            CardView.ApplyFilter(img);
            controller.establishSelectedCard(foundation.seeCard(ColumnPosition));
        }
    }

    public void moveToTableau(int StackPosition, Card card, ImageView img) {
        if (card == null && controller.getSelectedCard() != null) {
            controller.changeCards(null, ObjectType.TABLEAU, StackPosition);
        } else if (card != null) {
            if (card.isVisible() && controller.getSelectedCard() == null) {
                CardView.ApplyFilter(img);
                controller.establishSelectedCard(card);
            } else if (card.isVisible()) {
                controller.changeCards(card, ObjectType.TABLEAU, StackPosition);
            } else {
                controller.deselectCard();
                controller.updateView();
            }
        }
    }

    public void updateStock(boolean isStockUpsideDown, Stock stock, Card card, ImageView img) {
        if (isStockUpsideDown) {
            stock.moveCard();
            controller.deselectCard();
            controller.updateView();
        } else {
            if (controller.getSelectedCard() == null) {
                CardView.ApplyFilter(img);
                controller.establishSelectedCard(card);
            } else {
                CardView.DeleteCardFilter(img);
                controller.deselectCard();
                controller.updateView();
            }
        }
    }
}
