package cz.vse.adventura_cipm02.logika;

/**
 * Třída PrikazPobodat implementuje pro hru příkaz 'pobodat'.
 * Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazPobodat implements IPrikaz{

    public static final String NAZEV = "pobodat";
    private HerniPlan herniplan;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazPobodat(HerniPlan herniplan, Hra hra) {
        this.herniplan = herniplan;
        this.hra = hra;
    }

    /**
     * Hra je ukončena, pokud má hráč u sebe předmět 'kudla' a zadal příkaz 'pobodat' při přítomnosti existující postavy.
     * Při špatném zadání příkazu, hra pokračuje a vrátí chybové hlášení.
     *
     * @return zpráva, kterou hra vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Pobodat koho? ";
        }

        String nazevPostavy = parametry[0];

        Inventar inventar = herniplan.getInventar();
        Prostor aktualni = herniplan.getAktualniProstor();
        Postava pobodany = aktualni.getPostava(nazevPostavy);

        if (!inventar.getMapaSVecmi().containsKey("kudla")) {


            return "Nemáš ho čím pobodat.";
        }
        if (pobodany == null){
            return "Nikdo takový tu není";
        }

        hra.setKonecHry(true);
        return "Pobodal jsi člověka ve vězení. Přišli na to a zastřelili tě.";
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
