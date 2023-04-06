package cz.vse.adventura_cipm02.main;

import cz.vse.adventura_cipm02.logika.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *  Třída, která zajištujě vizuální stránku hry.
 *
 *
 */
public class HomeController {



    @FXML
    private ImageView hrac;
    @FXML
    private ListView<Prostor> panelVychodu;
    @FXML
    private ListView<Vec> panelPredmetu;
    @FXML
    private Button tlacitkoOdesli;
    @FXML
    private Button tlacitkoPobodat;
    @FXML
    private Button tlacitkoSebevrazda;
    @FXML
    private Button tlacitkoMluv;
    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;
    @FXML
    private ListView<Vec> panelDej;
    @FXML
    private ListView<Vec> panelBatoh;

    private IHra hra = new Hra();

    private ObservableList<Prostor> seznamVychodu = FXCollections.observableArrayList();

    private ObservableList<Vec> seznamPredmetu = FXCollections.observableArrayList();

    private ObservableList<Vec> seznamBatoh = FXCollections.observableArrayList();

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    /**
     *   Metoda zajišťuje spuštění hry a nastavení startovací pozice.
     */
    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);
        panelPredmetu.setItems(seznamPredmetu);
        panelBatoh.setItems(seznamBatoh);
        panelDej.setItems(seznamBatoh);
        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
        });
        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_INVENTARE, () -> {
            aktualizujSeznamBatoh();
        });
        hra.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        aktualizujSeznamPredmetu();
        aktualizujSeznamBatoh();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellProstor());
        panelPredmetu.setCellFactory(param -> new ListCellVec());
        panelBatoh.setCellFactory(param -> new ListCellVec());
        panelDej.setCellFactory(param -> new ListCellVec());
    }

    /**
     *  Metoda, která určuje souřadnice místností pro vizuální pohyb po mapě.
     *
     */
    private void vlozSouradnice() {
        souradniceProstoru.put("Cela", new Point2D(260, 129));
        souradniceProstoru.put("Hala", new Point2D(374, 130));
        souradniceProstoru.put("Jidelna", new Point2D(489, 131));
        souradniceProstoru.put("Kuchyn", new Point2D(592, 130));
        souradniceProstoru.put("Dilna", new Point2D(374, 14));
        souradniceProstoru.put("Dira", new Point2D(157, 130));
        souradniceProstoru.put("Zachody", new Point2D(375, 245));
        souradniceProstoru.put("Kanalizace", new Point2D(375, 345));
    }

    /**
     *   Metoda aktualizující seznam východů.
     */
    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    /**
     *   Metoda aktualizující seznam předmětů.
     */
    private void aktualizujSeznamPredmetu() {
        seznamPredmetu.clear();
        seznamPredmetu.addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
    }

    /**
     *   Metoda aktualizující seznam předmětů v inventáři(batohu).
     */
    private void aktualizujSeznamBatoh() {
        seznamBatoh.clear();
        seznamBatoh.addAll(hra.getHerniPlan().getInventar().getMapaSVecmi().values());
        System.out.println("asd");
    }

    /**
     *   Metoda aktualizující polohu hráče na mapě.
     */
    private void aktualizujPolohuHrace() {
        String prostor = hra.getHerniPlan().getAktualniProstor().getNazev();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());
    }

    /**
     *   Metoda aktualizující konec hry a vypnutí možnosti hrát.
     */
    private void aktualizujKonecHry() {
        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
        }
        vstup.setDisable(hra.konecHry());
        tlacitkoOdesli.setDisable(hra.konecHry());
        panelVychodu.setDisable(hra.konecHry());
        panelPredmetu.setDisable(hra.konecHry());
        panelBatoh.setDisable(hra.konecHry());
        panelDej.setDisable(hra.konecHry());
        tlacitkoMluv.setDisable(hra.konecHry());
        tlacitkoSebevrazda.setDisable(hra.konecHry());
        tlacitkoPobodat.setDisable(hra.konecHry());
    }

    /**
     *   Metoda odešle vstup uživatele.
     */
    @FXML
    private void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    /**
     *   Metoda zpracovává vstupy uživatele jako příkazy.
     */
    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz + "\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");

    }

    /**
     *   Metoda ukončí hru.
     */
    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Skutečně si přejete ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }

    }

    /**
     *   Metoda spustí novou hru.
     */
    @FXML
    private void novaHraKlik(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Skutečně si přejete spustit novou hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            hra = new Hra();
            initialize();
            aktualizujPolohuHrace();
            aktualizujKonecHry();
        }
    }

    /**
     *   Metoda zajišťuje funkci po kliknutí na vybranou položku v panelu východů.
     */
    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        if (hra.getHerniPlan().getInventar().getMapaSVecmi().containsKey("klic") && cil.jeZamcena()){
            String prikaz = PrikazOdemknout.NAZEV+" "+cil.getNazev();
            zpracujPrikaz(prikaz);
        }else {
            String prikaz = PrikazJdi.NAZEV + " " + cil.getNazev();
            zpracujPrikaz(prikaz);
            aktualizujSeznamPredmetu();
        }
    }

    /**
     *   Metoda zobrazí nápovědu ve formě html.
     */
    @FXML
    private void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView wv = new WebView();
        Scene napovedaScena = new Scene(wv);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        wv.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());
    }

    /**
     *   Metoda zajišťuje funkci po kliknutí na vybranou položku v panelu předmětů v místnosti.
     */
    @FXML
    private void klikPanelPredmetu(MouseEvent mouseEvent) {
        Vec cil = panelPredmetu.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        if (cil.isPrenositelne()) {
            String prikaz = PrikazSeber.NAZEV + " " + cil.getNazev();
            zpracujPrikaz(prikaz);
        }else {
            String prikaz = PrikazProhledat.NAZEV + " " + cil.getNazev();
            zpracujPrikaz(prikaz);
        }
        /*
        dodělat pomocí observeru
         */
        aktualizujSeznamPredmetu();
//        aktualizujSeznamBatoh();

    }

    /**
     *   Metoda zajišťuje funkci po kliknutí na vybranou položku v panelu Batoh(inventář).
     */
    @FXML
    private void klikPanelBatoh(MouseEvent mouseEvent) {
        Vec cil = panelBatoh.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        if (cil.getNazev().equals("sroubovak")){
            String prikaz = PrikazPobodat.NAZEV+" "+cil.getNazev();
            zpracujPrikaz(prikaz);
        }
        else if (cil.getNazev().equals("palice")){
            String prikaz = PrikazRozbij.NAZEV+" "+cil.getNazev();
            zpracujPrikaz(prikaz);
        }
        else return;
        aktualizujSeznamPredmetu();
    }

    /**
     *   Metoda zajišťuje funkci po kliknutí na vybranou položku v panelu Dej.
     */
    @FXML
    private void klikPanelDej(MouseEvent mouseEvent) {
        String jmenoPostavy = "Kulik";
        Vec cil = panelDej.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        for (Postava nekdo : hra.getHerniPlan().getAktualniProstor().getPostava().values()){
            jmenoPostavy = nekdo.getJmeno();
        }
        String prikaz = PrikazDej.NAZEV + " " + cil.getNazev() + " " + jmenoPostavy;
        zpracujPrikaz(prikaz);
        aktualizujSeznamPredmetu();
//        aktualizujSeznamBatoh();
    }

    /**
     *   Metoda provede prikaz sebevrazda.
     */
    @FXML
    private void provedPrikazSebevrazda(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Fakticky se chceš zabít?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            String prikaz = PrikazSebevrazda.NAZEV;
            zpracujPrikaz(prikaz);
        }

    }

    /**
     *   Metoda provede prikaz mluv.
     */
    @FXML
    private void provedPrikazMluv(ActionEvent actionEvent) {
        String jmenoPostavy = "Dulik";
        for (Postava nekdo : hra.getHerniPlan().getAktualniProstor().getPostava().values()){
            jmenoPostavy = nekdo.getJmeno();
        }
        String prikaz = PrikazMluv.NAZEV + " " + jmenoPostavy;
        zpracujPrikaz(prikaz);
    }

    /**
     *   Metoda provede prikaz pobodat.
     */
    @FXML
    private void provedPrikazPobodat(ActionEvent actionEvent) {
        String jmenoPostavy = "Bubik";
        for (Postava nekdo : hra.getHerniPlan().getAktualniProstor().getPostava().values()){
            jmenoPostavy = nekdo.getJmeno();
        }
        String prikaz = PrikazPobodat.NAZEV + " " + jmenoPostavy;
        zpracujPrikaz(prikaz);

    }


}
