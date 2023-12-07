package Vista.Universal;


import Controlador.Universal.Handler;
import Modelo.Global.Constantes.Klondlike;
import Modelo.Global.ObjetosPrincipales.Concretos.Fundacion;
import Vista.Constantes.Generales;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FundacionVista {
    private final Fundacion fundacion;
    public FundacionVista(Fundacion f) {
        this.fundacion = f;
    }

    public void establecerFundacionesBox(GridPane root, int offsetFund, boolean sePuedenSeleccionar
    , Handler handler) {
        ArrayList<HBox> fundacionesBoxes = new ArrayList<HBox>();
        // Agregar visualización de cada fundación
        ImageView imageView;// Establece el tamaño deseado para la imagen
        Image image;
        for (int posPilaFund = 0; posPilaFund < Klondlike.FUNDACIONES; posPilaFund++) {
            var hboxFund = new HBox();
            int finalPosFund = posPilaFund;
            if (fundacion.verPrimerCartaFundacion(posPilaFund) == null) {
                image = new Image("/Imagenes/espacioParaCarta.png") ;
                imageView = new ImageView();
                imageView.setOnMouseClicked(event -> {
                    handler.moverAFundacion(finalPosFund,fundacion,null,sePuedenSeleccionar);
                });

            } else {
                image = new Image(CartaVista.ObtenerUbicacionCarta(fundacion.verPrimerCartaFundacion(posPilaFund)));
                imageView = new ImageView(image);
                ImageView finalImageView1 = imageView;
                imageView.setOnMouseClicked(event -> {
                    handler.moverAFundacion(finalPosFund,fundacion,finalImageView1,sePuedenSeleccionar);
                });
            }
            imageView.setImage(image);
            imageView.setFitHeight(Generales.ALTOCARTA);
            imageView.setFitWidth(Generales.ANCHOCARTA);
            hboxFund.getChildren().add(imageView);
            fundacionesBoxes.add(hboxFund);
        }


        HBox fundBox;
        for (int i=0;i<fundacionesBoxes.size();i++){
            fundBox = fundacionesBoxes.get(i);
            root.add(fundBox,i+offsetFund,0); // columna = i+3 fila = 0
        }
    }
}