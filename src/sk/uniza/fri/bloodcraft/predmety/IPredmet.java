package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

/**
 * Interface IPredmet pre predmety.
 *
 * @author Mário Vydra
 * @version 27.4.2022
 */
public interface IPredmet {
    /**
     * Getter názvu.
     *
     * @return názov daného predmetu.
     */
    String getNazov();

    /**
     * Metóda pouziSa použije daný predmet.
     *
     * @param hrac referencia na hraca.
     */
    void pouziSa(Hrac hrac);

    /**
     * Metóda getTypPredmetu vráti typ daného predmetu.
     *
     * @return typ daného predmetu.
     */
    TypPredmetu getTypPredmetu();
}
