package cz.vse.adventura_cipm02.logika;

import java.util.Map;

/**
 * Třída PrikazSeber implementuje do hry příkaz 'seber'.
 * Tato třída je součástí primitivní textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazSeber implements IPrikaz {


        public static final String NAZEV = "seber";
        private HerniPlan plan;
    public int mistoInvt;

        /**
         *  Konstruktor třídy
         *
         *  @param plan herní plán, ve kterém se bude ve hře "chodit"
         */
        public PrikazSeber(HerniPlan plan) {
            this.plan = plan;
            mistoInvt = 4;
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

                return "Co chceš jako sebrat?";
            }

            String nazevSbiraneho = parametry[0];


            Prostor aktualni = plan.getAktualniProstor();
            Vec sbirana = aktualni.odeberVec(nazevSbiraneho);

            if (sbirana == null) {
                return "To tu není jestli jsi nevšiml.";
            }
            else {
                if(sbirana.isPrenositelne() && mistoInvt > 0){

                        Map<String, Vec> inv = plan.getInventar().getMapaSVecmi();
                        inv.put(sbirana.getNazev(), sbirana);
                        mistoInvt --;

                        return "Nacpal jsi si to do kapes jako správná čorka.";

            }
                else{
                    aktualni.vlozVec(sbirana);
                    return "Tak to se ti do kapes nevejde kamaráde.";
                }
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
