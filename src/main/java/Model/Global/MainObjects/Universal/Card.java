package Model.Global.MainObjects.Universal;

import Model.Global.Constants.Colours;
import Model.Global.Constants.Suits;
import Model.Global.Constants.Values;

import java.io.Serializable;

public class Card implements Serializable {
    private final Values value;
    private final Suits suit;
    private boolean visible;
    private int objectType;
    private int column;
    private int position;


    public Card(Values number, Suits suit) {
        this.suit = suit;
        this.value = number;
        this.visible = false;
        this.objectType = -1;
    }

    public Values getValue() {
        return value;
    }

    public Suits getSuit() {
        return suit;
    }

    public Colours getColour() {
        return suit.getColour();
    }

    public int getColumn() {
        return this.column;
    }

    public int getObjectType() {
        return this.objectType;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void establishColumn(int n) {
        this.column = n;
    }

    public void establishPosition(int n) {
        this.position = n;
    }

    public void changeVisibility(boolean visible) {
        this.visible = visible;
    }

    public void changeObjectType(int n) {
        this.objectType = n;
    }

    public String toString() {
        return this.visible ? "V-" + value + "-" + suit.toString() : "***";
    }
}
