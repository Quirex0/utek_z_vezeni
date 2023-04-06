package cz.vse.adventura_cipm02.logika;

/**
 * Třída PrikazSebevrazda implementuje pro hru příkaz 'sebevrazda'.
 * Tato třída je součástí primitivní textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazSebevrazda implements IPrikaz{

    public static final String NAZEV = "sebevrazda";
    private HerniPlan herniPlan;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan plán, ve kterém se hraje hra
     * @param hra odkaz na hru, která má být příkazem ukončena
     */
    public PrikazSebevrazda(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Hra je ukončena sebevraždou.
     *
     * @return zpráva, kterou hra vypíše hráči (liší se, pokud hráč má nebo nemá předmět 'kudla')
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {

            Inventar inventar = herniPlan.getInventar();

            if (!inventar.getMapaSVecmi().containsKey("kudla") ){

                hra.setKonecHry(true);
                return "Mlátil jsi hlavou o zeď tak dlouho, doku jsi se nezabil.";
            }

        }

        hra.setKonecHry(true);
        return "Úspěšně jsi se podřezal.";

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
