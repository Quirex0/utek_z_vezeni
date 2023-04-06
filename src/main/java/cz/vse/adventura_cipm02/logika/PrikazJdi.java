package cz.vse.adventura_cipm02.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz 'jdi'.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */

 public class PrikazJdi implements IPrikaz {
    public static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "A kam jako? Musíš si vybrat destinaci ty hlavo.";
        }

        //ošetření rozdílu malého a velkého písmena na začátku
        String smer = parametry[0];
        String smer1 = smer.substring(1);
        String smer2 = smer.substring(0,1).toUpperCase();
        String smer3 = smer2+smer1;

        System.out.println(smer2+smer1);

        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer3);

        if (sousedniProstor == null) {
            return "Co to meleš. Však odtud se tam jít nedá.";
        }

            if (sousedniProstor.jeZamcena()) {
                return "dveře do místnosti "+sousedniProstor.getNazev()
                        +" jsou zamčené";
            }
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();

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
