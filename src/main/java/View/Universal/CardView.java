package View.Universal;

import Model.Global.MainObjects.Universal.Card;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CardView {

    public static String getCardImage(Card card) {
        if (!card.isVisible()) {
            return "Images/Back.png";
        }
        return "/Images/" + card.getValue().toString() + card.getSuit().toString() + ".png";
    }

    public static void ApplyFilter(ImageView imageView) {
        ColorInput colorInput = new ColorInput(
                0, 0, imageView.getFitWidth(), imageView.getFitHeight(),
                Color.SLATEBLUE
        );
        Blend blend = new Blend(BlendMode.MULTIPLY);
        blend.setTopInput(colorInput);
        imageView.setEffect(blend);
    }

    public static void DeleteCardFilter(ImageView carta1Vista) {
        carta1Vista.setEffect(null);
    }
}

