package cz.vse.adventura_cipm02.logika;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Třída PrikazRozbij implementuje pro hru příkaz 'rozbij'.
 * Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazRozbij implements IPrikaz {

    public static final String NAZEV = "rozbij";
    private HerniPlan herniplan;

    /**
     * Konstruktor třídy
     *
     * @param herniplan plán, ve kterém se hraje hra
     */
    public PrikazRozbij(HerniPlan herniplan) {
        this.herniplan = herniplan;

    }

    /**
     * Rozbije cestu do další nepřístupné (zamknuté) místnosti. Lze úspěšně použít pouze v prostoru 'Zachody' a 'Cela'.
     * Při špatném zadání je vrácena chybná hláška.
     *
     * @return zpráva, kterou hra vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {

            Prostor aktualni = herniplan.getAktualniProstor();
            Inventar inventar = herniplan.getInventar();

            if (aktualni.getNazev().equals("Zachody")  || aktualni.getNazev().equals("Cela")) {


                if (!inventar.getMapaSVecmi().containsKey("palice")) {
                    return "No s rukama tu těžko něco zničíš.";
                }
                Set<Prostor> vychod = aktualni.getVychody().stream().filter(Prostor::jeZamcena)
                        .collect(Collectors.toSet());
                Prostor prostor = vychod.iterator().next();
                prostor.odemknout();
                vychod.clear();
                return "Rozbil jsi to tu jak hulk a objevil nový způsob útěku.";

            }

        }
        return "Rozbijet můžeš jen v určitých místnostec.";

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev () {
        return NAZEV;
    }


}
