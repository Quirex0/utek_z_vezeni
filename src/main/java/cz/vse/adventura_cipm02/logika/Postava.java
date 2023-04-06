package cz.vse.adventura_cipm02.logika;

import java.util.Objects;

/**
 * Třída Postava slouží k vytvoření postavy a jejího proslovu.
 * Dále obsahuje parametry nutné pro výměnu předmětů mezi hráčem a postavou.
 *
 *@author     Miroslav Cipra
 *@version    1.0
 *@created    červen 2020
 */

public class Postava {

    private String jmeno;
    private String proslov;
    private Vec coChce;
    private Vec coMa;

    /**
     * Konstruktor třídy
     *
     * @param jmeno - název postavy
     * @param proslov - proslov, který pronese postava, když na ní hráč promluví
     */
    public Postava(String jmeno, String proslov) {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }

    /**
     * Vrací název postavy (byl zadán při vytváření postavy jako parametr
     * konstruktoru)
     *
     * @return jméno postavy
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrací proslov postavy (byl zadán při vytváření postavy jako parametr
     * konstruktoru)
     *
     * @return proslov postavy
     */
    public String getMluv() {
        return proslov;
    }

    /**
     * Vrací předmět, který je potřeba pro úspěšnou výměnu.
     *
     * @return předmět, který postava chce dostat
     */
    public Vec getCoChce() {
        return coChce;
    }

    /**
     * Nastavuje předmět, který je potřeba pro úspěšnou výměnu.
     *
     * @param coChce - předmět, který postava chce dostat
     */
    public void setCoChce(Vec coChce) {
        this.coChce = coChce;
    }

    /**
     * Vrací předmět, který hráč dostane od postavy po úspěšné výměně.
     *
     * @return předmět, který postava chce odevzdat
     */
    public Vec getCoMa() {
        return coMa;
    }

    /**
     * Nasstavuje předmět, který hráč dostane od postavy po úspěšné výměně.
     *
     * @param coMa - předmět, který postava chce odevzdat
     */
    public void setCoMa(Vec coMa) {
        this.coMa = coMa;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Postava postava = (Postava) o;
        return Objects.equals(jmeno, postava.jmeno);
    }

}
