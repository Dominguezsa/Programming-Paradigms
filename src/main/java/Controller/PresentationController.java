package Controller;

import Model.Global.Constants.General;
import Model.Global.MainObjects.Universal.Persistence;
import Model.FreeCellSolitaire.FreeCellGame;
import Model.KlondikeSolitaire.KlondikeGame;
import View.Juegos.FreeCellView;
import View.Juegos.KlondikeView;
import View.Universal.PresentationView;
import javafx.stage.Stage;

public class PresentationController {

    private final Stage st;

    public PresentationController(Stage primaryStage) {
        PresentationView view = new PresentationView(primaryStage, this);

        view.setButtonKlondikeClick(event -> {
            view.setButton(view.getButton(0));
            loadKlondikeGame();
        });

        view.setButtonFreeCellClick(event -> {
            view.setButton(view.getButton(1));
            loadFreeCellGame();
        });

        this.st = primaryStage;
        view.display();
    }

    public void loadKlondikeGame() {
        var is = Persistence.getInputStream(General.KLONDIKEPATH);
        KlondikeGame game;
        KlondikeView view;
        GameController controller;
        if (is != null) {
            game = (KlondikeGame) Persistence.loadGame(is);
        } else {
            game = new KlondikeGame();
            game.prepareRandomGame();
        }
        view = new KlondikeView(game, st);
        controller = new GameController(st, game, view);
        view.setViewActions(new Handler(controller));
        controller.initiate();
    }

    public void loadFreeCellGame() {
        var is = Persistence.getInputStream(General.FREECELLPATH);
        FreeCellGame game;
        FreeCellView view;
        GameController controller;
        if (is != null) {
            game = (FreeCellGame) Persistence.loadGame(is);
        } else {
            game = new FreeCellGame();
            game.prepareRandomGame();
        }
        view = new FreeCellView(game, st);
        controller = new GameController(st, game, view);
        view.setViewActions(new Handler(controller));
        controller.initiate();
    }
}