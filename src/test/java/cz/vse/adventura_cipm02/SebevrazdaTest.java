package cz.vse.adventura_cipm02;


import cz.vse.adventura_cipm02.logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SebevrazdaTest {


        private Hra hra;

        @BeforeEach
        public void setUp() {
            hra = new Hra();
        }

        @Test
        public void testSebevrazdaBezeZbrani() {

            hra.zpracujPrikaz("sebevrazda");
            assertTrue(hra.konecHry());
        }

    @Test
    public void testSebevrazdaKudlou() {

        hra.zpracujPrikaz("jdi Hala");
        hra.zpracujPrikaz("jdi Zachody");
        hra.zpracujPrikaz("prohledat zachod");
        hra.zpracujPrikaz("sebevrazda");
        assertTrue(hra.konecHry());
    }



}