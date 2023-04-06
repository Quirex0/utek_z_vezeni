package cz.vse.adventura_cipm02.main;

import cz.vse.adventura_cipm02.logika.Prostor;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 *  Třída, která se stará o zobrazení místností
 *
 */
public class ListCellProstor extends ListCell<Prostor> {

    /**
     *  Metoda zobrazuje aktuální název a obrázek k dané místnosti
     *
     */
    @Override
    protected void updateItem(Prostor prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }else {
            setText(prostor.getNazev());
            String cesta = getClass().getResource("prostory/"+prostor.getNazev()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            iw.setFitWidth(150);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
