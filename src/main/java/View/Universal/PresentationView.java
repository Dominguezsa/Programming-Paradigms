package View.Universal;

import Controller.PresentationController;
import View.Constants.General;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PresentationView {
    private final Stage primaryStage;
    private final PresentationController controller;

    private final ArrayList<Button> buttons;

    public PresentationView(Stage primaryStage, PresentationController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.buttons = new ArrayList<Button>();
        menuPrincipal();
    }

    private void menuPrincipal() {
        VBox vbox = new VBox(20); // Space between elements
        vbox.setAlignment(Pos.CENTER);

        Text title = new Text("Choose a Solitaire:");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox buttonContainer = createButtonContainer();

        vbox.getChildren().addAll(title, buttonContainer);

        // Green background
        vbox.setStyle("-fx-background-color: #0096ff;");

        Scene scene = new Scene(vbox, General.PRESENTATIONWIDTH, General.PRESENTATIONHEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Solitaire Game");

        // Window size
        primaryStage.setWidth(General.PRESENTATIONWIDTH);
        primaryStage.setHeight(General.PRESENTATIONHEIGHT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private HBox createButtonContainer() {
        Button buttonKlondike = new Button("Klondike");
        Button buttonFreeCell = new Button("FreeCell");

        // Button style
        buttonKlondike.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");
        buttonFreeCell.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        buttonKlondike.setOnMousePressed(event -> buttonKlondike.setStyle("-fx-background-color: #6d6d6d; -fx-text-fill: white; -fx-font-size: 16px;"));
        buttonFreeCell.setOnMousePressed(event -> buttonFreeCell.setStyle("-fx-background-color: #6d6d6d; -fx-text-fill: white; -fx-font-size: 16px;"));

        buttons.add(buttonKlondike);
        buttons.add(buttonFreeCell);
        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(buttonKlondike, buttonFreeCell);
        buttonContainer.setAlignment(Pos.CENTER);
        return buttonContainer;
    }

    public void display() {
        primaryStage.show();
    }

    public void setButtonKlondikeClick(EventHandler eventHandler) {
        buttons.get(0).setOnAction(eventHandler);
    }

    public void setButtonFreeCellClick(EventHandler eventHandler) {
        buttons.get(1).setOnAction(eventHandler);
    }

    public void setButton(Button btn) {
        btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px;");
    }

    public Button getButton(int n) {
        return this.buttons.get(n);
    }
}
