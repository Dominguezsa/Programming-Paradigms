package Vista.Universal;

import Controlador.Universal.Handler;
import Modelo.Global.ObjetosPrincipales.Concretos.Celda;
import Vista.Constantes.Generales;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CeldaVista {

    private final Celda celda;

    public CeldaVista(Celda c) {
        this.celda = c;
    }

    public void obtenerCeldaBox(GridPane root, Handler handler) {
        ArrayList<HBox> celdasBoxes = new ArrayList<HBox>();
        // Agregar visualización de cada fundación
        ImageView imageView;// Establece el tamaño deseado para la imagen
        Image image;
        for (int i = 0; i< 4; i++) {
            var hboxFund = new HBox();
            int finalPosCelda = i;
            if (celda.verCarta(i) == null) {
                image = new Image("/Imagenes/espacioParaCarta.png");
                imageView = new ImageView();
                imageView.setOnMouseClicked(event -> {
                    handler.moverACelda(finalPosCelda,null,null);
                });
            } else {
                image = new Image(CartaVista.ObtenerUbicacionCarta(celda.verCarta(i)));
                imageView = new ImageView(image);
                ImageView finalImageView1 = imageView;
                imageView.setOnMouseClicked(event -> {
                    handler.moverACelda(finalPosCelda,celda,finalImageView1);
                });
            }
            imageView.setImage(image);
            imageView.setFitHeight(Generales.ALTOCARTA);
            imageView.setFitWidth(Generales.ANCHOCARTA);
            hboxFund.getChildren().add(imageView);
            celdasBoxes.add(hboxFund);
        }
        HBox fundBox;
        for (int i = 0; i < celdasBoxes.size(); i++) {
            fundBox = celdasBoxes.get(i);
            root.add(fundBox, i, 0); // columna = i+3 fila = 0
        }
    }
}
