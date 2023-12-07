package Modelo.Global.Constantes;

public enum Valores {
    AS(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),

    DIEZ(10),
    JOTA(11),

    REINA(12),
    REY(13);
    final int valor;

    Valores(int valorCarta){
        this.valor = valorCarta;
    }

    public int getValue(){
        return valor;
    }
}
