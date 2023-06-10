package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Trieda Nohavice reprezentuje všetky nohavice, ktoré sa v hre môžu vyskytovať.
 * Trieda je implementovaná o daný interface IZbroj.
 *
 * @author Mário Vydra
 * @version 2.5.2022
 */
public class Nohavice implements IZbroj {
    private final String nazov;

    /**
     * Konštruktor inicializuje názov daných nohavíc.
     *
     * @param nazov String.
     */
    public Nohavice(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov daných nohavíc.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe názvu nohavíc zvýši staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "dotrhaneNohavice" -> {
                System.out.println("Obliekol si si dotrhané nohavice.");
                hrac.zmenBrnenie(10);
                hrac.zmenObratnost(5);
            }
            case "kozeneNohavice" -> {
                System.out.println("Obliekol si si kožené nohavice.");
                hrac.zmenBrnenie(15);
                hrac.zmenObratnost(10);
            }
            case "oceloveNohavice" -> {
                System.out.println("Obliekol si si oceľové nohavice.");
                hrac.zmenBrnenie(30);
                hrac.zmenObratnost(15);
            }
            default -> System.out.println("Dané nohavice si neviem obliecť.");
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ danej zbroje.
     *
     * @return TypPredmetu.NOHAVICE.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.NOHAVICE;
    }

    /**
     * Implementovaná metóda odstranSa na základe názvu nohavíc zníži staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void odstranSa(Hrac hrac) {
        switch (this.nazov) {
            case "dotrhaneNohavice" -> {
                System.out.println("Vyzliekol si si dotrhané nohavice.");
                hrac.zmenBrnenie(-10);
                hrac.zmenObratnost(-5);
            }
            case "kozeneNohavice" -> {
                System.out.println("Vyzliekol si si kožené nohavice.");
                hrac.zmenBrnenie(-15);
                hrac.zmenObratnost(-10);
            }
            case "oceloveNohavice" -> {
                System.out.println("Vyzliekol si si oceľové nohavice.");
                hrac.zmenBrnenie(-30);
                hrac.zmenObratnost(-15);
            }
            default -> System.out.println("Dané brnenie si neviem vyzliecť.");
        }
    }

    /**
     * Implementovaná metóda typPostavy vracia, pre ktorý typ postavy sú nohavice určené.
     *
     * @return null - keďže nohavice sú univerzálne.
     */
    @Override
    public Postava typPostavy() {
        return null;
    }
}
