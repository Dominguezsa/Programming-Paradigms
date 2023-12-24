package View.Juegos;

import Model.KlondikeSolitaire.KlondikeGame;
import View.Constants.General;
import View.InterfacesAndAbstractClasses.GameView;
import View.Universal.FoundationView;
import View.Universal.StockView;
import View.Universal.TableauView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KlondikeView extends GameView {
    StockView vistaStock;
    FoundationView vistaFundaciones;
    TableauView vistaTablero;


    private final KlondikeGame juegoModelo;

    public KlondikeView(KlondikeGame j, Stage st) {
        super(st);
        this.juegoModelo = j;
    }

    public void displayGame(Stage primaryStage) {
        Scene sc = new Scene(root, General.KLONDIKEWIDTH, General.KLONDIKEHEIGHT);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Klondike");
        root.setStyle("-fx-background-color: green;");

        // STOCK
        vistaStock = new StockView(juegoModelo.getStock());
        vistaStock.establishStockBox(root, this.acciones);

        // FUNDACIONES
        vistaFundaciones = new FoundationView(juegoModelo.getFoundations());
        vistaFundaciones.establishFoundationBox(root, 3, true, this.acciones);

        // TABLERO
        vistaTablero = new TableauView(juegoModelo.getTableau());
        vistaTablero.establishTableauBox(root, this.acciones);

        primaryStage.setWidth(General.KLONDIKEWIDTH);
        primaryStage.setHeight(General.KLONDIKEHEIGHT);
        primaryStage.show();
        //Espaciado del contenido del gridpane con bordes de este.
        // Recordar que el gridpane tiene el mismo tamaño que la ventana.
        root.setPadding(new Insets(20, 0, 0, 30)); // top, right, bottom, and left
    }

    public void refreshView() {
        this.vistaStock.establishStockBox(root, this.acciones);
        this.vistaFundaciones.establishFoundationBox(root, 3, true, this.acciones);
        this.vistaTablero.clearTableau();
        this.vistaTablero.establishTableauBox(root, this.acciones);
    }
}
