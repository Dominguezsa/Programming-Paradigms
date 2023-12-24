package Model.Global.Constants;

public enum Suits {
    SPADE(Colours.BLACK),
    DIAMOND(Colours.RED),
    CLUB(Colours.BLACK),
    HEART(Colours.RED);
    final Colours colour;

    Suits(Colours colour) {
        this.colour = colour;
    }

    public Colours getColour() {
        return colour;
    }


}