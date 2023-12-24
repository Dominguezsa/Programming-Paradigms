package View.Juegos;

import Model.FreeCellSolitaire.FreeCellGame;
import View.Constants.General;
import View.InterfacesAndAbstractClasses.GameView;
import View.Universal.CellView;
import View.Universal.FoundationView;
import View.Universal.TableauView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FreeCellView extends GameView {
    private CellView vistaCelda;
    private FoundationView vistaFundaciones;
    private TableauView vistaTablero;

    private final FreeCellGame juegoModelo;

    public FreeCellView(FreeCellGame j, Stage st) {
        super(st);
        this.juegoModelo = j;
    }

    public void displayGame(Stage primaryStage) {
        Scene scene = new Scene(root, General.FREECELLWIDTH, General.FREECELLHEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FreeCell");
        root.setStyle("-fx-background-color: green;");

        // CELDAS
        vistaCelda = new CellView(juegoModelo.getCells());
        vistaCelda.establishCellBox(root, this.acciones);

        // FUNDACIONES
        vistaFundaciones = new FoundationView(juegoModelo.getFoundations());
        vistaFundaciones.establishFoundationBox(root, 4, false, this.acciones);

        // TABLERO
        vistaTablero = new TableauView(juegoModelo.getTableau());
        vistaTablero.establishTableauBox(root, this.acciones);

        primaryStage.setWidth(General.FREECELLWIDTH);
        primaryStage.setHeight(General.FREECELLHEIGHT);
        primaryStage.show();

        root.setPadding(new Insets(20, 0, 0, 30)); // top, right, bottom, and left
    }

    public void refreshView() {
        this.vistaCelda.establishCellBox(root, this.acciones);
        this.vistaFundaciones.establishFoundationBox(root, 4, false, this.acciones);
        this.vistaTablero.clearTableau();
        this.vistaTablero.establishTableauBox(root, this.acciones);
    }

}
