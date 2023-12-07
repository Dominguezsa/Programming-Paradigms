package Modelo.Global.Constantes;

public enum Palos {
    PICA(Colores.NEGRO),
    DIAMANTE(Colores.ROJO),
    TREBOL(Colores.NEGRO),
    CORAZON(Colores.ROJO);

    final Colores color;

    Palos(Colores color){
        this.color = color;
    }

    public Colores getColor() {
        return color;
    }


}