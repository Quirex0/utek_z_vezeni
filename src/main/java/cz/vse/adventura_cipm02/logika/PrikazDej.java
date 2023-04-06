package cz.vse.adventura_cipm02.logika;

/**
 * Třída PrikazDej implementuje příkaz 'dej'.
 * Tato třída je součástí jednoduché textové hry.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class PrikazDej implements IPrikaz {

    public static final String NAZEV = "dej";
    private HerniPlan herniplan;

    /**
     * Konstruktor třídy
     *
     * @param herniplan - plán, ve kterém se hraje hra
     */
    public PrikazDej(HerniPlan herniplan) {
        this.herniplan = herniplan;
    }


    /**
     * Provádí příkaz dej - umožní vyměnit hráči předmět s postavou.
     * Je potřeba mít předmět, který postava vyžaduje.
     * Při neúspěšné výměně se vypíše varovné upozornění.
     *
     * @return zpráva, která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Dát co jako?";
        }

        if (parametry.length == 1) {
            return "Komu že to mám dát?";
        }

        Prostor aktualniProstor = herniplan.getAktualniProstor();
        Inventar inventar = herniplan.getInventar();

        if (parametry.length == 2 && inventar.getMapaSVecmi().containsKey(parametry[0])) {
            Vec vecOdevzdana = inventar.getMapaSVecmi().get(parametry[0]);
                Postava postava = aktualniProstor.getPostava(parametry[1]);
                if (postava == null){
                    return "Nikdo takovej tady neni.";
                }
                Vec vecCoChce = postava.getCoChce();
                Vec vecCoMa = postava.getCoMa();
                if (vecCoChce != null){
                    if (vecCoChce.equals(vecOdevzdana)){
                        inventar.getMapaSVecmi().remove(vecOdevzdana.getNazev());
                        inventar.getMapaSVecmi().put(vecCoMa.getNazev(),vecCoMa);
                        postava.setCoChce(null);
                        return "Vymena probehla. Dostal jsi: " + vecCoMa.getNazev();
                    }
                    return "Tuhle vec nechce.";
                }
                return "Tomuhle podivínovi nejde nic dát.";

        }
        else {
            return "Tohle u sebe nemám. Sakra!";
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
