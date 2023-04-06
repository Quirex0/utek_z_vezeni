package cz.vse.adventura_cipm02.logika;

import cz.vse.adventura_cipm02.main.Pozorovatel;
import cz.vse.adventura_cipm02.main.PredmetPozorovani;
import cz.vse.adventura_cipm02.main.ZmenaHry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
        *  Class HerniPlan - třída představující mapu a stav adventury.
        *
        *  Tato třída inicializuje prvky ze kterých se hra skládá:
        *  vytváří všechny prostory,
        *  propojuje je vzájemně pomocí východů,
        *  pamatuje si aktuální prostor, ve kterém se hráč právě nachází,
 * vytváří postavy a vkládá je do prostoru,
 * vytváří předměty a vkládá je do prostoru.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
*/

public class HerniPlan implements PredmetPozorovani {
    
    private Prostor aktualniProstor;
    private Prostor vitezny;
    private Inventar inventar;
    private Map<ZmenaHry, Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();


    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        inventar = new Inventar();
        for (ZmenaHry zmenaHry : ZmenaHry.values()) {
            seznamPozorovatelu.put(zmenaHry, new HashSet<>());
        }
    }

    public Inventar getInventar() {
        return inventar;
    }


    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Dále vytváří a vkládá předměty do prostorů.
     *  Nakonec vytváří postavy a jejich dialogy.
     */
    private void zalozProstoryHry() {

        // vytvářejí se jednotlivé prostory
        Prostor cela = new Prostor("Cela","Cela, to je ten prostor, kde si vězni střádají svoje harampádí");
        Prostor zachody = new Prostor("Zachody", "Záchody jsou místo, kde by měl mít každý svůj klid. \n" +
                                                            "Tady tomu bohužel tak není");
        Prostor hala = new Prostor("Hala", "Hala to je ta dlouhá chodba, kterou se dostaneš skoro kamkoliv");
        Prostor dilna = new Prostor("Dilna", "Dílna je pracovna s omezeným přístupem, kde najdeš spoustu užitečných věcí");
        Prostor kuchyn = new Prostor("Kuchyn", "Kuchyň - zde se kuchtí všechny ty dobroty");
        Prostor jidelna = new Prostor("Jidelna", "V jídelně si můžeš sníst všechny ty bašty co ti kydnou na tác");
        Prostor kanalizace = new Prostor("Kanalizace", "Kanalizace, kterou tečou všeožný hnusy. " +
                "Přesto jsi sem vlezl a odplazil se na svobodu. \n Hru ukonči příkazem 'Konec'.");
        Prostor dira = new Prostor("Dira", "Spolkla tě časoprostorová díra. Nicméně jsi se dostal z vězení. " +
                "\n Hru ukonči příkazem 'Konec'.");

        //propojování místností
        cela.setVychod(hala);
        hala.setVychod(cela);
        hala.setVychod(jidelna);
        jidelna.setVychod(hala);
        jidelna.setVychod(kuchyn);
        kuchyn.setVychod(jidelna);
        hala.setVychod(zachody);
        zachody.setVychod(hala);
        hala.setVychod(dilna);
        dilna.setVychod(hala);
        zachody.setVychod(kanalizace);
        cela.setVychod(dira);


        //zamcene prostory
        dilna.zamknout();
        kanalizace.zamknout();
        dira.zamknout();

        //vítězný prostor(alternativa, kterou jsem nezvolil)
        Prostor svoboda = new Prostor("Svoboda","Tam se chceš dostat");
        vitezny = svoboda;
                
        aktualniProstor = cela;  // hra začíná v cele


        //vytváření + vkládání věcí

        Vec postel = new Vec("postel", false);
        cela.vlozVec(postel);
        Vec skrinka = new Vec("skrinka", false);
        cela.vlozVec(skrinka);
        Vec kartacek = new Vec("kartacek", true);
        skrinka.setVec(kartacek);

        Vec mydlo = new Vec("mydlo", true);
        zachody.vlozVec(mydlo);             // udělat hlášku pro sebrání mýdla
        Vec zachod = new Vec("zachod", false);
        zachody.vlozVec(zachod);
        Vec kudla = new Vec("kudla", true);
        zachod.setVec(kudla);

        Vec sroubovak = new Vec("sroubovak", true);
        dilna.vlozVec(sroubovak);
        Vec palice = new Vec("palice", true);
        dilna.vlozVec(palice);

        Vec tac = new Vec("tac", true);
        jidelna.vlozVec(tac);               // zařídit, aby tác nešel odnést z jídelny (případně vyměnit za oběd?)
        Vec kos = new Vec("kos", false);
        jidelna.vlozVec(kos);

        Vec lednice = new Vec("lednice", false);
        kuchyn.vlozVec(lednice);
        Vec gothaj = new Vec("gothaj", true);
        lednice.setVec(gothaj);
        Vec banan = new Vec("banan", true);
        kuchyn.vlozVec(banan);

        Vec klic = new Vec("klic", true);


        //postavy
        Postava spoluvezen = new Postava("Bob", "Uggrhhh... \n(Z něj asi nic nedostanu. Je úplně mimo.)");
        cela.vlozPostava(spoluvezen);
        Postava hlidac1 = new Postava("Straznik_Fiala", "... \n(Jen blbě čumí)");
        jidelna.vlozPostava(hlidac1);
        Postava hlidac2 = new Postava ("Straznik_Mendez", "Jestli nemas gothaj, tak tahni dohajzlu!");
        hala.vlozPostava(hlidac2);
        hlidac2.setCoChce(gothaj);
        hlidac2.setCoMa(klic);
        Postava smazka = new Postava("Vlado", "Zzzz... \n (Ten je úplně vyplej.)");
        zachody.vlozPostava(smazka);

    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
        upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
    }

    //tento styl jsem nezvolil
    public boolean jeVyhra(){
        return aktualniProstor == vitezny;

    }

    /**
     *  Metoda registruje případné změny ve hře.
     *
     */
    @Override
    public void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmenaHry).add(pozorovatel);
    }

    /**
     *  Metoda upozorní pozorovatele, pokud se ve hře udějou nějaké změny.
     *
     */
    private void upozorniPozorovatele(ZmenaHry zmenaHry) {
        for (Pozorovatel pozorovatel : seznamPozorovatelu.get(zmenaHry)) {
            pozorovatel.aktualizuj();
        }
    }
}
