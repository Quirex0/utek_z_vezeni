package cz.vse.adventura_cipm02.logika;

import cz.vse.adventura_cipm02.main.Pozorovatel;
import cz.vse.adventura_cipm02.main.PredmetPozorovani;
import cz.vse.adventura_cipm02.main.ZmenaHry;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Třída PrikazProhledat implementuje do hry příkaz 'prohledat'.
 * Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazProhledat implements IPrikaz, PredmetPozorovani {

    public static final String NAZEV = "prohledat";
    private HerniPlan herniPlan;
    private Map<ZmenaHry, Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();

    /**
     * Konstruktor třídy
     *
     * @param herniplan plán, ve kterém se hraje hra
     */
    public PrikazProhledat(HerniPlan herniplan) {
        this.herniPlan = herniplan;
        for (ZmenaHry zmenaHry : ZmenaHry.values()) {
            seznamPozorovatelu.put(zmenaHry, new HashSet<>());
        }
    }

    /**
     * Prohledá se předmět, který se nedá sebrat. Předmět uložený uvnitř je rovnou uložen do inventáře.
     * Při špatném zadání příkazu je vrácena chybná hláška.
     *
     * @return zpráva, kterou hra vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {

            return "Prohledat co? Musíš si vybrat co chceš prohledat, víš?";
        }



        String prohledavana = parametry[0];
        Prostor aktualni = herniPlan.getAktualniProstor();
        Set<Vec> veci = aktualni.getVeci().stream().filter(vec -> vec.getNazev().equals(prohledavana)).collect(Collectors.toSet());


        if (veci.isEmpty()){
            return "Takova vec tu fakt neni.";
        }

        Vec prohledavanaVec = veci.iterator().next();

        if (!prohledavanaVec.isPrenositelne() && prohledavanaVec.getVec() != null) {
            Vec nalezenaVec = prohledavanaVec.getVec();
            herniPlan.getInventar().getMapaSVecmi().put(nalezenaVec.getNazev(),nalezenaVec);
            prohledavanaVec.setVec(null);
            veci.remove(nalezenaVec);

            upozorniPozorovatele(ZmenaHry.ZMENA_INVENTARE);


            return "Hele co jsi objevil: " + nalezenaVec.getNazev();
        }
        else{
            return "Čum si na to jak chceš, ale nic z toho stejně nedostaneš.";
        }

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     *  Metoda registruje případné změny ve hře.
     *
     */
    @Override
    public void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmenaHry).add(pozorovatel);
    }

    /**
     *  Metoda upozorní pozorovatele, pokud se ve hře udějou nějaké změny.
     *
     */
    private void upozorniPozorovatele(ZmenaHry zmenaHry) {
        for (Pozorovatel pozorovatel : seznamPozorovatelu.get(zmenaHry)) {
            pozorovatel.aktualizuj();
        }
    }

}
