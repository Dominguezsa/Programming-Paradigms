package View.InterfacesAndAbstractClasses;

import javafx.event.EventHandler;
import javafx.stage.Stage;

public interface View {
    void displayGame(Stage st);

    void setOnClose(EventHandler eventHandler, Stage primaryStage);

    void showWinMessage();

    void refreshView();
}
