package Vista.Universal;


import Controlador.Universal.Handler;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;
import Modelo.SolitarioKlondlike.ObjetosConcretos.StockKlondike;
import Vista.Constantes.Generales;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class StockVista {
    private final StockKlondike stock;
    private ImageView imgViewCartasVisibles;
    private ImageView imgViewCartasNoVisibles;

    public StockVista(StockKlondike s){
        this.stock = s;
    }
    public void establecerStockBox(GridPane root, Handler handler) {

        this.imgViewCartasNoVisibles = new ImageView();
        this.imgViewCartasVisibles = new ImageView();
        HBox stockNoVisibleBox = new HBox();
        HBox stockVisibleBox = new HBox();
        stockNoVisibleBox.setSpacing(10);
        stockVisibleBox.setSpacing(10);


        if (!stock.obtenerStockNoVisible().isEmpty()){
            imgViewCartasNoVisibles.setImage(new Image("/Imagenes/espalda.png"));
        } else {
            imgViewCartasNoVisibles.setImage(new Image("/Imagenes/StockVacio.png"));
        }
        stockNoVisibleBox.getChildren().add(imgViewCartasNoVisibles);
        imgViewCartasNoVisibles.setFitHeight(Generales.ALTOCARTA); // Establece el tamaño deseado para la imagen
        imgViewCartasNoVisibles.setFitWidth(Generales.ANCHOCARTA);

        imgViewCartasNoVisibles.setOnMouseClicked(event -> {
            handler.actualizarStock(true,stock,this,root,null,null);
        });

        if (stock.obtenerCartasVisibles().isEmpty()){
            imgViewCartasVisibles.setImage(new Image("/Imagenes/espacioSinCarta.png"));
        } else {
            Carta carta = stock.verPrimerCarta();
            imgViewCartasVisibles.setImage(new Image((Vista.Universal.CartaVista.ObtenerUbicacionCarta(carta))));
            imgViewCartasVisibles.setOnMouseClicked(event -> {
                handler.actualizarStock(false,null,this,root,carta,imgViewCartasVisibles);
            });
        }

        stockVisibleBox.getChildren().add(imgViewCartasVisibles);
        imgViewCartasVisibles.setFitHeight(Generales.ALTOCARTA); // Establece el tamaño deseado para la imagen
        imgViewCartasVisibles.setFitWidth(Generales.ANCHOCARTA);

        ArrayList<HBox> stocks = new ArrayList<>();
        stocks.add(stockNoVisibleBox);
        stocks.add(stockVisibleBox);
        stockNoVisibleBox.setSpacing(30);
        stockVisibleBox.setSpacing(30);
        root.add(stockNoVisibleBox, 0, 0); // columna=0 fila=0
        root.add(stockVisibleBox, 1, 0); // columna=1 fila=0
    }
}
