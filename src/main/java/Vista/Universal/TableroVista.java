package Vista.Universal;


import Controlador.Universal.Handler;
import Modelo.Global.ObjetosPrincipales.Abstactos.Tablero;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Vista.Constantes.Generales;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class TableroVista {
    private final Tablero tablero;
    private final ArrayList<ImageView> imagenes;
    public TableroVista(Tablero tablero) {

        imagenes = new ArrayList<>();
        this.tablero = tablero;
    }

    public void establecerTableroBox(GridPane root, Handler handler) {

        double offsetPixels = Generales.OFFSETTABLERO; // Ajusta según sea necesario

        for (int posPila = 0; posPila < tablero.obtenerCantPilas(); posPila++) {
            ArrayList<Carta> pila = tablero.obtenerPila(posPila);
            if (pila.isEmpty()){
                var image = new Image("/Imagenes/espacioSinCarta.png") ;
                var imageView = new ImageView(image);
                imageView.setFitHeight(Generales.ALTOCARTA);
                imageView.setFitWidth(Generales.ANCHOCARTA);

                HBox cardBox = new HBox(imageView);
                cardBox.setSpacing(5);
                final int finalI = posPila;
                imageView.setOnMouseClicked(event -> {
                    handler.moverATablero(-1,finalI,null,null);
                });
                root.add(cardBox, posPila, 1);  // columna=i fila=j+1
                cardBox.setPadding(new Insets(20, 10, 0, 0)); // top, right, bottom, and left
            }
            for (int posCarta = 0; posCarta < pila.size(); posCarta++) {
                HBox cardBox = gethBox(pila, posCarta, handler);

                // Ajuste para posicionar todas las cartas (excepto la primera) 20 pixeles más arriba
                if (posCarta > 0) {
                    root.add(cardBox, posPila, posCarta+1); // columna=i fila=j+1
                    cardBox.setPadding(new Insets(offsetPixels, 10, 0, 0)); // top, right, bottom, and left
                } else {
                    root.add(cardBox, posPila, posCarta + 1);  // columna=i fila=j+1
                    cardBox.setPadding(new Insets(20, 10, 0, 0)); // top, right, bottom, and left
                }
            }
        }

    }

    private HBox gethBox(ArrayList<Carta> pila, int posCarta, Handler handler) {
        Carta carta = pila.get(posCarta);
        ImageView imageView = new ImageView(Vista.Universal.CartaVista.ObtenerUbicacionCarta(carta));
        imageView.setFitHeight(Generales.ALTOCARTA);
        imageView.setFitWidth(Generales.ANCHOCARTA);
        this.imagenes.add(imageView);
        HBox cardBox = new HBox(imageView);
        cardBox.setSpacing(5); // Ajusta según sea necesario
        imageView.setOnMouseClicked(event -> {
            handler.moverATablero(posCarta,-1,carta,imageView);
        });
        return cardBox;
    }

    public void clearTablero() {
        for (ImageView c : this.imagenes) {
            c.setImage(null);
        }
        this.imagenes.clear();
    }
}