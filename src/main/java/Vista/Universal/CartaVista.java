package Vista.Universal;

import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CartaVista {

    public static String ObtenerUbicacionCarta(Carta carta) {
        if (!carta.esVisible()){
            return "Imagenes/Espalda.png";
        }
        return "/Imagenes/" + carta.obtenerValor().toString() + carta.obtenerPalo().toString() + ".png";}

    public static void AplicarFiltro(ImageView imageView) {
        ColorInput colorInput = new ColorInput(
                0, 0, imageView.getFitWidth(), imageView.getFitHeight(),
                Color.SLATEBLUE
        );
        Blend blend = new Blend(BlendMode.MULTIPLY);
        blend.setTopInput(colorInput);
        imageView.setEffect(blend);
    }

    public static void DesaplicarFiltro(ImageView carta1Vista) {
        carta1Vista.setEffect(null);
    }
}

