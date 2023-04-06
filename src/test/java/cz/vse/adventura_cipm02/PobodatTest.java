package cz.vse.adventura_cipm02;


import cz.vse.adventura_cipm02.logika.HerniPlan;
import cz.vse.adventura_cipm02.logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PobodatTest {

    private Hra hra;
    private HerniPlan plan;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    @Test
    public void testPobodat() {

        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("jdi Zachody");
        hra.zpracujPrikaz("prohledat zachod");
        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("pobodat Straznik_Mendez");
        assertTrue(hra.konecHry());
    }


}
