package cz.vse.adventura_cipm02.logika;

import java.util.Map;

/**
 * Třída PrikazInventar implementuje pro hru příkaz 'inventar'.
 * Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazInventar implements IPrikaz {

    private static final String NAZEV = "inventar";
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan plán, ve kterém se hraje hra
     */
    public PrikazInventar(HerniPlan herniPlan){
        this.herniPlan = herniPlan;
    }


    /**
     * Provádí příkaz inventar - vypíše seznam věcí v inventáři.
     * Pokud je inventář prázdný vypíše se varovné oznámení.
     *
     * @return zpráva, která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {


        if ( herniPlan.getInventar().getMapaSVecmi().isEmpty()) {
            return "Nic nemáš bráško.";
        }
        else{
            return "V inventáři máš:" + herniPlan.getInventar().getMapaSVecmi().keySet();
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
