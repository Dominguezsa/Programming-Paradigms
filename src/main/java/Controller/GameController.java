package Controller;

import Model.Global.Interfaces.Solitaire;
import Model.Global.MainObjects.Universal.Card;
import Model.Global.MainObjects.Universal.Persistence;
import View.InterfacesAndAbstractClasses.GameView;
import javafx.stage.Stage;

public class GameController {

    protected final Stage primarystage;
    protected final Solitaire gameModel;
    protected final GameView gameView;
    protected Card SelectedCard;

    public GameController(Stage stage, Solitaire game, GameView view) {
        this.primarystage = stage;
        this.gameModel = game;
        this.gameView = view;
    }

    public void initiate() {
        gameView.displayGame(primarystage);
        gameView.setOnClose(Event -> {
            if (!gameModel.gameFinished()) {
                Persistence.saveGame(Persistence.getOutputStream(gameModel.getPath()), gameModel);
            } else {
                Persistence.deleteGame(gameModel.getPath());
            }
        }, this.primarystage);
    }

    public void deselectCard() {
        SelectedCard = null;
    }

    public Card getSelectedCard() {
        return this.SelectedCard;
    }

    public void establishSelectedCard(Card card) {
        if (this.SelectedCard == null) {
            this.SelectedCard = card;
        }
    }

    public void changeCards(Card card2, int CardContainer, int ColumnPosition) {
        gameModel.universalMove(SelectedCard, card2, CardContainer, ColumnPosition);
        deselectCard();
        updateView();
        if (gameModel.gameFinished()) {
            gameView.showWinMessage();
        }
    }

    public void updateView() {
        gameView.refreshView();
    }
}
