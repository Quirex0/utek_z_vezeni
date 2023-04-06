package cz.vse.adventura_cipm02.main;

import cz.vse.adventura_cipm02.logika.Vec;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 *  Třída, která se stará o zobrazení věcí v prostoru
 *
 */
public class ListCellVec extends ListCell<Vec> {

    /**
     *  Metoda zobrazuje aktuální název a obrázek k danému předmětu v místnosti
     *
     */
        @Override
        protected void updateItem(Vec vec, boolean empty) {
            super.updateItem(vec, empty);
            if (empty){
                setText(null);
                setGraphic(null);
            }else {
                setText(vec.getNazev());
                String cesta = getClass().getResource("veci/"+vec.getNazev()+".jpg").toExternalForm();
                ImageView iw = new ImageView(cesta);
                iw.setFitHeight(50);
                iw.setPreserveRatio(true);
                setGraphic(iw);
            }
        }

}
