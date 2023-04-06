package cz.vse.adventura_cipm02.logika;

import java.util.stream.Collectors;

/**
 * Třída PrikazMluv implementuje pro hru příkaz 'mluv'.
 * Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazMluv implements IPrikaz{

    public static final String NAZEV = "mluv";
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan - plán, ve kterém se hraje hra
     */
    public PrikazMluv(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Vrací proslov postavy, se kterou hráč mluví.
     * Pokud postava neexistuje nebo není zadán název postavy, vrátí chybovou hlášku.
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Mluvit? A s kým?";
        }

        String nazevPostavy = parametry[0];

        Prostor aktualni = herniPlan.getAktualniProstor();
        Postava mluvici = aktualni.getPostava(nazevPostavy);

        if (mluvici == null){
            return "Nikdo takovy tu neni.";
        }

        if (mluvici.getJmeno().equals(nazevPostavy)){
            return mluvici.getMluv();
        }
        else {
            return "Nikdo takový tu přece není.";
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

}
