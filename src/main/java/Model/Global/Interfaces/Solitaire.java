package Model.Global.Interfaces;

import Model.Global.MainObjects.Universal.Card;

import java.util.Random;

public interface Solitaire {
    void prepareRandomGame();

    void prepareGameWithSeed(Random rnd);

    boolean gameFinished();

    int countAllCards();

    void universalMove(Card sourceCard, Card destinationCard, int objectTypeDestination, int column);

    String getPath();
}
