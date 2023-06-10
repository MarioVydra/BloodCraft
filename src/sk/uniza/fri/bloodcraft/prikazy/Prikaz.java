package sk.uniza.fri.bloodcraft.prikazy;


/**
 * Trieda Prikaz reprezentuje časti príkazu, ktoré môže hráč zadať.
 * Príkaz tvoria dve slová: názov príkazu a parameter.
 *
 * @author Cvičenia
 * @version 27.4.2022
 */
public class Prikaz {
    private final String nazovPrikazu;
    private final String parameter;

    /**
     * Konštruktor inicializuje dve slová príkazu, zadanými parametrami.
     *
     * @param nazovPrikazu prvé slovo - názov príkazu.
     * @param parameter druhé slovo - parameter.
     */
    public Prikaz(String nazovPrikazu, String parameter) {
        this.nazovPrikazu = nazovPrikazu;
        this.parameter = parameter;
    }

    /**
     * Getter atribútu nazovPrikazu.
     *
     * @return názov príkazu.
     */
    public String getNazovPrikazu() {
        return this.nazovPrikazu;
    }

    /**
     * Getter atribútu parameter.
     *
     * @return parameter príkazu.
     */
    public String getParameter() {
        return this.parameter;
    }

    /**
     * Metóda neexistujePrikaz vracia boolean hodnotu.
     *
     * @return true - ak neexistuje príkaz, false - ak existuje príkaz.
     */
    public boolean neexistujePrikaz() {
        return this.nazovPrikazu == null;
    }

    /**
     * Metóda obsahujeParameter vracia boolean hodnotu.
     *
     * @return true - ak príkaz obsahuje parameter, false - ak príkaz neobsahuje parameter.
     */
    public boolean obsahujeParameter() {
        return this.parameter != null;
    }
}
