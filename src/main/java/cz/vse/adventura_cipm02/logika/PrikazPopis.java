package cz.vse.adventura_cipm02.logika;

public class PrikazPopis implements IPrikaz {

    private static final String NAZEV = "popis";
    private HerniPlan plan;

    public PrikazPopis(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {

            return plan.getAktualniProstor().dlouhyPopis();

        }

        return  "chyba";
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }


}
