package cz.vse.adventura_cipm02.logika;

/**
 * Třída PrikazOdemknout implementuje pro hru příkaz 'odemknout'.
 * Tato třída je součástí primitivní textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazOdemknout implements IPrikaz {

    public static final String NAZEV = "odemknout";
    private HerniPlan herniplan;

    /**
     * Konstruktor třídy
     *
     * @param herniplan plán, ve kterém se hraje hra
     */
    public PrikazOdemknout(HerniPlan herniplan) {
        this.herniplan = herniplan;
    }

    /**
     * Odemyká prostor, který byl zamčený. K odemknutí je potřeba klíč.
     * Pokud je místnost odemčená nebo je špatně zadán příkaz, vrátí chybové hlášení.
     *
     * @return zpráva, kterou hra vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš si vybrat, kterou místnost chceš odemknout.";
        }

        String nazevProstoru = parametry[0];


        Prostor aktualni = herniplan.getAktualniProstor();
        Prostor vybranyProstor = aktualni.vratSousedniProstor(nazevProstoru);
        Inventar inventar = herniplan.getInventar();



        if(!aktualni.getVychody().contains(vybranyProstor)){
            return "Musíš si vybrat mistnost, co chceš odemknout.";
        }

        if (!vybranyProstor.jeZamcena()){
            return "Místnost musí být zamčená, aby šel odemknout. To ti nikdo neřekl?";
        }

        if(vybranyProstor.jeZamcena() && inventar.getMapaSVecmi().containsKey("klic")){

            if (aktualni.getNazev().equals("Zachody")){
                return "Na zachodech nelze nic odemknout. Můžeš tu jen něco rozbít.";
            }
            vybranyProstor.odemknout();
            return "Odemknul jsi " + vybranyProstor.getNazev();
        }
        else {
            return "Takhle to neodemkneš.";
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
