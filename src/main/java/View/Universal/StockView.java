package View.Universal;


import Controller.Handler;
import Model.Global.MainObjects.Universal.Card;
import Model.KlondikeSolitaire.ConcreteObjects.StockKlondike;
import View.Constants.General;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class StockView {
    private final StockKlondike stock;
    private ImageView imgViewCartasVisibles;
    private ImageView imgViewCartasNoVisibles;

    public StockView(StockKlondike s) {
        this.stock = s;
    }

    public void establishStockBox(GridPane root, Handler handler) {

        this.imgViewCartasNoVisibles = new ImageView();
        this.imgViewCartasVisibles = new ImageView();
        HBox stockNoVisibleBox = new HBox();
        HBox stockVisibleBox = new HBox();
        stockNoVisibleBox.setSpacing(10);
        stockVisibleBox.setSpacing(10);


        if (!stock.getNonVisibleStock().isEmpty()) {
            imgViewCartasNoVisibles.setImage(new Image("/Images/Back.png"));
        } else {
            imgViewCartasNoVisibles.setImage(new Image("/Images/EmptyStock.png"));
        }
        stockNoVisibleBox.getChildren().add(imgViewCartasNoVisibles);
        imgViewCartasNoVisibles.setFitHeight(General.CARDHEIGHT);
        imgViewCartasNoVisibles.setFitWidth(General.CARDWIDTH);

        imgViewCartasNoVisibles.setOnMouseClicked(event -> {
            handler.updateStock(true, stock, null, null);
        });

        if (stock.getVisibleStock().isEmpty()) {
            imgViewCartasVisibles.setImage(new Image("/Images/NoCard.png"));
        } else {
            Card card = stock.seeFirstCard();
            imgViewCartasVisibles.setImage(new Image((CardView.getCardImage(card))));
            imgViewCartasVisibles.setOnMouseClicked(event -> {
                handler.updateStock(false, stock, card, imgViewCartasVisibles);
            });
        }

        stockVisibleBox.getChildren().add(imgViewCartasVisibles);
        imgViewCartasVisibles.setFitHeight(General.CARDHEIGHT);
        imgViewCartasVisibles.setFitWidth(General.CARDWIDTH);

        ArrayList<HBox> stocks = new ArrayList<>();
        stocks.add(stockNoVisibleBox);
        stocks.add(stockVisibleBox);
        stockNoVisibleBox.setSpacing(30);
        stockVisibleBox.setSpacing(30);
        root.add(stockNoVisibleBox, 0, 0);
        root.add(stockVisibleBox, 1, 0);
    }
}
