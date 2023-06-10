package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

import java.util.Random;

/**
 * Trieda Kamen implementovaná interfacom IPredmet reprezentuje kameň, ktorý môžeš hodiť.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class Kamen implements IPredmet {

    /**
     * Implementovaný getter názvu predmetu.
     *
     * @return Kamen.
     */
    @Override
    public String getNazov() {
        return "Kamen";
    }

    /**
     * Implementovaná metóda pouziSa vypíše do terminálu, že si hodil kameň.
     * Je šanca 1 ku 3, že kameň hodíš nešťastne, odrazí sa a udrie ťa.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Hodil si kameň.");
        Random random =  new Random();
        if (random.nextInt(3) == 0) {
            System.out.println("Kameň sa nešťastne odrazil a trafil ťa.");
            hrac.znizHP(5);
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ daného predmetu.
     *
     * @return typ daného predmetu.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.ZNOVA_POUZITELNY;
    }
}
