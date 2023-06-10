package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Trieda Helma reprezentuje všetky helmy, ktoré sa v hre môžu vyskytovať.
 * Trieda je implementovaná o daný interface IZbroj.
 *
 * @author Mário Vydra
 * @version 2.5.2022
 */
public class Helma implements IZbroj {
    private final String nazov;

    /**
     * Konštruktor inicializuje názov danej helmy.
     *
     * @param nazov String.
     */
    public Helma(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov danej helmy.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe názvu helmy zvýši staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "kozenaPrilba" -> {
                System.out.println("Nasadil si si koženú prilbu.");
                hrac.zmenBrnenie(20);
                hrac.zmenDamage(5);
            }
            case "zhrdzavenaHelma" -> {
                System.out.println("Nasadil si si zhrdzavenú helmu.");
                hrac.zmenBrnenie(30);
                hrac.zmenDamage(10);
            }
            case "ocelovaHelma" -> {
                System.out.println("Nasadil si si oceľovú helmu.");
                hrac.zmenBrnenie(40);
                hrac.zmenDamage(15);
            }
            default -> System.out.println("Danú helmu si neviem nasadiť.");
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ danej zbroje.
     *
     * @return TypPredmetu.HELMA.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.HELMA;
    }

    /**
     * Implementovaná metóda odstranSa na základe názvu helmy zníži staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void odstranSa(Hrac hrac) {
        switch (this.nazov) {
            case "kozenaPrilba" -> {
                System.out.println("Zložil si si koženú prilbu.");
                hrac.zmenBrnenie(-20);
                hrac.zmenDamage(-5);
            }
            case "zhrdzavenaHelma" -> {
                System.out.println("Zložil si si zhrdzavenú helmu.");
                hrac.zmenBrnenie(-30);
                hrac.zmenDamage(-10);
            }
            case "ocelovaHelma" -> {
                System.out.println("Zložil si si oceľovú helmu.");
                hrac.zmenBrnenie(-40);
                hrac.zmenDamage(-15);
            }
            default -> System.out.println("Danú helmu si neviem zložiť.");
        }
    }

    /**
     * Implementovaná metóda typPostavy vracia, pre ktorý typ postavy je helma určená.
     *
     * @return null - keďže helmy sú univerzálne.
     */
    @Override
    public Postava typPostavy() {
        return null;
    }
}
