package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Trieda Brnenie reprezentuje všetky brnenia, ktoré sa v hre môžu vyskytovať.
 * Trieda je implementovaná o daný interface IZbroj.
 *
 * @author Mário Vydra
 * @version 2.5.2022
 */
public class Brnenie implements IZbroj {
    private final String nazov;

    /**
     * Konštruktor inicializuje názov brnenia.
     *
     * @param nazov String.
     */
    public Brnenie(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov daného brnenia.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe názvu brnenia zvýši staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "dotrhanaKosela" -> {
                System.out.println("Obliekol si si dotrhanú košeľu.");
                hrac.zmenBrnenie(10);
            }
            case "kozenaVesta" -> {
                System.out.println("Obliekol si si koženú vestu.");
                hrac.zmenBrnenie(15);
            }
            case "oceloveBrnenie" -> {
                System.out.println("Obliekol si si oceľové brnenie.");
                hrac.zmenBrnenie(20);
            }
            case "mithrilBrnenie" -> {
                System.out.println("Obliekol si si Mithril.");
                hrac.zmenBrnenie(40);
                hrac.zmenObratnost(5);
                hrac.zmenStastie(5);
                hrac.zmenDamage(5);
            }
            default -> System.out.println("Dané brnenie si neviem obliecť.");
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ danej zbroje.
     *
     * @return TypPredmetu.BRNENIE.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.BRNENIE;
    }

    /**
     * Implementovaná metóda odstranSa na základe názvu brnenia zníži staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void odstranSa(Hrac hrac) {
        switch (this.nazov) {
            case "dotrhanaKosela" -> {
                System.out.println("Vyzliekol si si dotrhanú košeľu.");
                hrac.zmenBrnenie(-20);
            }
            case "kozenaVesta" -> {
                System.out.println("Vyzliekol si si koženú vestu.");
                hrac.zmenBrnenie(-30);
            }
            case "oceloveBrnenie" -> {
                System.out.println("Vyzliekol si si oceľové brnenie.");
                hrac.zmenBrnenie(-40);
            }
            case "mithrilBrnenie" -> {
                System.out.println("Vyzliekol si si Mithril.");
                hrac.zmenBrnenie(-40);
                hrac.zmenObratnost(-5);
                hrac.zmenStastie(-5);
                hrac.zmenDamage(-5);
            }
            default -> System.out.println("Dané brnenie si neviem vyzliecť.");
        }
    }

    /**
     * Implementovaná metóda typPostavy vracia, pre ktorý typ postavy je brnenie určené.
     *
     * @return null - keďže brnenia sú univerzálne.
     */
    @Override
    public Postava typPostavy() {
        return null;
    }
}
