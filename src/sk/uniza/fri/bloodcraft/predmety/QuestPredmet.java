package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

/**
 * Trieda QuestPredmet implementovaná interfacom IPredmet reprezentuje úlohové predmety.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class QuestPredmet implements IPredmet {
    private final String nazov;

    /**
     * Konštruktor inicializuje názov daného quest predmetu.
     *
     * @param nazov String.
     */
    public QuestPredmet(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Implementovaný getter názvu predmetu.
     *
     * @return názov quest predmetu.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Metóda pouziSa vypíše do terminálu, že questový predmet sa nedá použiť.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Nemôžeš použiť questový predmet.");
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ daného predmetu.
     *
     * @return typ daného predmetu.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.QUEST_PREDMET;
    }
}
