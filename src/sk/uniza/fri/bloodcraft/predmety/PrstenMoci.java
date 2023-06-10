package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

/**
 * Trieda PrstenMoci implementovaná interfacom IPredmet reprezentuje Sauronov prsteň.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class PrstenMoci implements IPredmet {

    /**
     * Implementovaný getter názvu predmetu.
     *
     * @return PrstenMoci.
     */
    @Override
    public String getNazov() {
        return "PrstenMoci";
    }

    /**
     * Implementovaná metóda pouziSa nastaví hráčovi neviditeľnosť na nasledujúcich 10 krokov.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        hrac.setJeViditelny();
        System.out.println("Použil si Prsteň moci, si neviditeľný na ďalších 10 krokov, ktoré vykonáš.");
        System.out.println("Môžeš prehľadávať územia a zbierať predmety bez toho, aby si ťa Bossovia všimli.");
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ daného predmetu.
     *
     * @return typ daného predmetu.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.TRVALY;
    }
}
