package cz.vse.adventura_cipm02.logika;

import java.util.Objects;

/**
 *  Třída Věc vytváří věci, jejich vlastnosti,
 *  odebírá je z prostorů při sebrání a přidává je do inventáře
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */
public class Vec {

    private String nazev;
    private boolean prenositelne;
    private Vec vec;


    /**
     * Konstruktor, který vytváří věci a určuje viditelnost.
     *
     */
    public Vec(String nazev, boolean prenositelne) {
        this.nazev = nazev;
        this.prenositelne = prenositelne;
        vec = null;
    }

    /**
     * Vrací název předmětu
     *
     * @return název předmětu
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Rozhoduje o tom, zda je předmět přenositelný a lze sebrat či nikoliv
     *
     * @return přenositelnost předmětu
     */
    public boolean isPrenositelne() {
        return prenositelne;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec vec = (Vec) o;
        return Objects.equals(nazev, vec.nazev);
    }

    /**
     * Vrací předmět
     *
     * @return věc
     */
    public Vec getVec() {
        return vec;
    }

    /**
     * Vytváří věc
     *
     * @return
     */
    public String setVec(Vec vec) {
        if(!this.prenositelne){
            this.vec = vec;
            return "Vec added";
        }
        return "Vec not added";
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazev);
    }

}
