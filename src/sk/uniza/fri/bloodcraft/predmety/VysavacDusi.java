package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

/**
 * Trieda VysavacDusi implementovaná interfacom IPredmet reprezentuje mocnú zbraň, ktorá dokáže zabiť bossa na ranu.
 *
 * @author Mário Vydra
 * @version 15.5.2022
 */
public class VysavacDusi implements IPredmet {

    /**
     * Implementovaný getter názvu predmetu.
     *
     * @return VysavacDusi.
     */
    @Override
    public String getNazov() {
        return "VysavacDusi";
    }

    /**
     * Implementovaná metóda pouziSa zabije bossa, na území, v ktorom sa aktuálne nachádza hráč.
     * Ak na danom území nie je žiadny boss, predmet zabije daného hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        if (hrac.getKdeSom().getBoss() == null) {
            System.out.println("Vysávač duší, nenašiel žiadneho bossa, ktorému by vysal dušu a tak sa obrátil proti tebe.");
            hrac.znizHP(100);
        } else {
            System.out.println("Vysal si dušu " + hrac.getKdeSom().getBoss().getMeno() + "-ovi.");
            hrac.getKdeSom().getBoss().znizHp(hrac.getKdeSom().getBoss().getHp());
            System.out.println(hrac.getKdeSom().getBoss().getMeno() + " zomrel. Môžeš zbierať predmety, splniť úlohu a otvoriť chestku.");
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ daného predmetu.
     *
     * @return typ daného predmetu.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.SPOTREBOVATELNY;
    }
}
