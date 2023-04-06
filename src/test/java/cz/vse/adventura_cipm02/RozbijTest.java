package cz.vse.adventura_cipm02;


import cz.vse.adventura_cipm02.logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RozbijTest {

    private Hra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    @Test
    public void testHryKanalizace() {

        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("jdi Jidelna");
        hra.zpracujPrikaz("jdi Kuchyn");
        hra.zpracujPrikaz("prohledat lednice");
        hra.zpracujPrikaz("jdi Jidelna");
        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("dej gothaj Straznik_Mendez");
        hra.zpracujPrikaz("odemknout Dilna");
        hra.zpracujPrikaz("jdi Dilna");
        hra.zpracujPrikaz("seber palice");
        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("jdi Zachody");
        hra.zpracujPrikaz("rozbij");
        hra.zpracujPrikaz("jdi Kanalizace");
        assertEquals("Kanalizace", hra.getHerniPlan().getAktualniProstor().getNazev());
    }

    @Test
    public void testHryDira() {
        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("jdi Jidelna");
        hra.zpracujPrikaz("jdi Kuchyn");
        hra.zpracujPrikaz("prohledat lednice");
        hra.zpracujPrikaz("jdi Jidelna");
        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("dej gothaj Straznik_Mendez");
        hra.zpracujPrikaz("odemknout Dilna");
        hra.zpracujPrikaz("jdi Dilna");
        hra.zpracujPrikaz("seber palice");
        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("jdi Cela");
        hra.zpracujPrikaz("rozbij");
        hra.zpracujPrikaz("jdi Dira");
        assertEquals("Dira", hra.getHerniPlan().getAktualniProstor().getNazev());
    }
}
