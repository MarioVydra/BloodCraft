package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Trieda Topanky reprezentuje všetky topánky, ktoré sa v hre môžu vyskytovať.
 * Trieda je implementovaná o daný interface IZbroj.
 *
 * @author Mário Vydra
 * @version 2.5.2022
 */
public class Topanky implements IZbroj {
    private final String nazov;

    /**
     * Konštruktor inicializuje názov daných topánok.
     *
     * @param nazov String.
     */
    public Topanky(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov daných topánok.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe názvu topánok zvýši staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "rozpadnuteBoty" -> {
                System.out.println("Obul si si rozpadnuté boty.");
                hrac.zmenBrnenie(5);
                hrac.zmenStastie(3);
            }
            case "pevneKozeneBoty" -> {
                System.out.println("Obul si si pevné kožené boty.");
                hrac.zmenBrnenie(10);
                hrac.zmenStastie(5);
            }
            case "oceloveBoty" -> {
                System.out.println("Obul si si oceľové boty.");
                hrac.zmenBrnenie(15);
                hrac.zmenStastie(10);
            }
            default -> System.out.println("Dané boty si neviem obuť.");
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ danej zbroje.
     *
     * @return TypPredmetu.TOPANKY.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.TOPANKY;
    }

    /**
     * Implementovaná metóda odstranSa na základe názvu topánok zníži staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void odstranSa(Hrac hrac) {
        switch (this.nazov) {
            case "rozpadnuteBoty" -> {
                System.out.println("Vyzul si si rozpadnuté boty.");
                hrac.zmenBrnenie(-5);
                hrac.zmenStastie(-3);
            }
            case "pevneKozeneBoty" -> {
                System.out.println("Vyzul si si pevné kožené boty.");
                hrac.zmenBrnenie(-10);
                hrac.zmenStastie(-5);
            }
            case "oceloveBoty" -> {
                System.out.println("Vyzul si si oceľové boty.");
                hrac.zmenBrnenie(-15);
                hrac.zmenStastie(-10);
            }
            default -> System.out.println("Dané boty si neviem vyzuť.");
        }
    }

    /**
     * Implementovaná metóda typPostavy vracia, pre ktorý typ postavy sú topánky určené.
     *
     * @return null - keďže topánky sú univerzálne.
     */
    @Override
    public Postava typPostavy() {
        return null;
    }
}
