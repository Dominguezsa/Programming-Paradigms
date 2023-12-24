package View.Universal;


import Controller.Handler;
import Model.Global.Constants.Klondlike;
import Model.Global.MainObjects.Concrete.Foundation;
import View.Constants.General;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FoundationView {
    private final Foundation foundation;

    public FoundationView(Foundation f) {
        this.foundation = f;
    }

    public void establishFoundationBox(GridPane root, int offsetFund, boolean canBeSelected
            , Handler handler) {
        ArrayList<HBox> foundationBox = new ArrayList<HBox>();
        ImageView imageView;
        Image image;
        for (int posPilaFund = 0; posPilaFund < Klondlike.FOUNDATIONS; posPilaFund++) {
            var hboxFund = new HBox();
            int finalPosFund = posPilaFund;
            if (foundation.seeCard(posPilaFund) == null) {
                image = new Image("/Images/EmptyCard.png");
                imageView = new ImageView();
                imageView.setOnMouseClicked(event -> {
                    handler.moveToFoundation(finalPosFund, foundation, null, canBeSelected);
                });

            } else {
                image = new Image(CardView.getCardImage(foundation.seeCard(posPilaFund)));
                imageView = new ImageView(image);
                ImageView finalImageView1 = imageView;
                imageView.setOnMouseClicked(event -> {
                    handler.moveToFoundation(finalPosFund, foundation, finalImageView1, canBeSelected);
                });
            }
            imageView.setImage(image);
            imageView.setFitHeight(General.CARDHEIGHT);
            imageView.setFitWidth(General.CARDWIDTH);
            hboxFund.getChildren().add(imageView);
            foundationBox.add(hboxFund);
        }


        HBox fundBox;
        for (int i = 0; i < foundationBox.size(); i++) {
            fundBox = foundationBox.get(i);
            root.add(fundBox, i + offsetFund, 0);
        }
    }
}