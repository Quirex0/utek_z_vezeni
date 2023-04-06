package cz.vse.adventura_cipm02.logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída Inventář vytváří inventář, do kterého se pak vkládají věci, které hráč získá.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class Inventar {


    private Map <String, Vec> mapaSVecmi;

    /**
     * Metoda vrací věci v inventáři.
     *
     * @return věci v inventáři
     */
    public Map<String, Vec> getMapaSVecmi() {
        return mapaSVecmi;
    }

    /**
     * Inventář, ve kterém má hráč uložené své věci.
     */
    public Inventar() {
        mapaSVecmi = new HashMap();
    }



}
