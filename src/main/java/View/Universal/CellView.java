package View.Universal;

import Controller.Handler;
import Model.Global.MainObjects.Concrete.Cell;
import View.Constants.General;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CellView {

    private final Cell cell;

    public CellView(Cell c) {
        this.cell = c;
    }

    public void establishCellBox(GridPane root, Handler handler) {
        ArrayList<HBox> cellBoxes = new ArrayList<HBox>();
        ImageView imageView;
        Image image;
        for (int i = 0; i < 4; i++) {
            var hboxFund = new HBox();
            int finalPosCelda = i;
            if (cell.seeCard(i) == null) {
                image = new Image("/Images/EmptyCard.png");
                imageView = new ImageView();
                imageView.setOnMouseClicked(event -> {
                    handler.moveToFreeCells(finalPosCelda, null, null);
                });
            } else {
                image = new Image(CardView.getCardImage(cell.seeCard(i)));
                imageView = new ImageView(image);
                ImageView finalImageView1 = imageView;
                imageView.setOnMouseClicked(event -> {
                    handler.moveToFreeCells(finalPosCelda, cell.seeCard(finalPosCelda), finalImageView1);
                });
            }
            imageView.setImage(image);
            imageView.setFitHeight(General.CARDHEIGHT);
            imageView.setFitWidth(General.CARDWIDTH);
            hboxFund.getChildren().add(imageView);
            cellBoxes.add(hboxFund);
        }
        HBox fundBox;
        for (int i = 0; i < cellBoxes.size(); i++) {
            fundBox = cellBoxes.get(i);
            root.add(fundBox, i, 0);
        }
    }
}
