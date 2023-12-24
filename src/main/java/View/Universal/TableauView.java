package View.Universal;


import Controller.Handler;
import Model.Global.MainObjects.Abstract.Tableau;
import Model.Global.MainObjects.Universal.Card;
import View.Constants.General;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class TableauView {
    private final Tableau tableau;
    private final ArrayList<ImageView> imagenes;

    public TableauView(Tableau tableau) {

        imagenes = new ArrayList<>();
        this.tableau = tableau;
    }

    public void establishTableauBox(GridPane root, Handler handler) {

        double offsetPixels = General.OFFSETTABLEAU;

        for (int posPila = 0; posPila < tableau.getStacksQuantity(); posPila++) {
            ArrayList<Card> pila = tableau.getStack(posPila);
            if (pila.isEmpty()) {
                var image = new Image("/Images/NoCard.png");
                var imageView = new ImageView(image);
                imageView.setFitHeight(General.CARDHEIGHT);
                imageView.setFitWidth(General.CARDWIDTH);

                HBox cardBox = new HBox(imageView);
                cardBox.setSpacing(5);
                final int finalI = posPila;
                imageView.setOnMouseClicked(event -> {
                    handler.moveToTableau(finalI, null, null);
                });
                root.add(cardBox, posPila, 1);
                cardBox.setPadding(new Insets(20, 10, 0, 0)); // top, right, bottom, and left
            }
            for (int posCarta = 0; posCarta < pila.size(); posCarta++) {
                HBox cardBox = gethBox(pila, posCarta, posPila, handler);
                if (posCarta > 0) {
                    root.add(cardBox, posPila, posCarta + 1);
                    cardBox.setPadding(new Insets(offsetPixels, 10, 0, 0));
                } else {
                    root.add(cardBox, posPila, posCarta + 1);
                    cardBox.setPadding(new Insets(20, 10, 0, 0));
                }
            }
        }

    }

    private HBox gethBox(ArrayList<Card> pila, int posCarta, int StackPosition, Handler handler) {
        Card card = pila.get(posCarta);
        ImageView imageView = new ImageView(CardView.getCardImage(card));
        imageView.setFitHeight(General.CARDHEIGHT);
        imageView.setFitWidth(General.CARDWIDTH);
        this.imagenes.add(imageView);
        HBox cardBox = new HBox(imageView);
        cardBox.setSpacing(5);
        imageView.setOnMouseClicked(event -> {
            handler.moveToTableau(StackPosition, card, imageView);
        });
        return cardBox;
    }

    public void clearTableau() {
        for (ImageView c : this.imagenes) {
            c.setImage(null);
        }
        this.imagenes.clear();
    }
}