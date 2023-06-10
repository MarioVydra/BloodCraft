package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Trieda ZakladneZbrane reprezentuje základné zbrane, ktoré sa v hre môžu vyskytovať.
 * Trieda je implementovaná o daný interface IZbroj.
 *
 * @author Mário Vydra
 * @version 28.4.2022
 */
public class Zbran implements IZbroj {
    private final String nazov;
    private final Postava postava;

    /**
     * Konštruktor inicializuje názov danej zbrane a postavu, pre ktorú je zbraň určená.
     *
     * @param nazov String.
     * @param postava Postava.
     */
    public Zbran(String nazov, Postava postava) {
        this.nazov = nazov;
        this.postava = postava;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov danej zbrane.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe názvu zbrane zvýši staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "sprachnivenaPalica" -> {
                System.out.println("Vystrojil si sa spráchnivenou palicou.");
                hrac.zmenStastie(10);
            }
            case "palicaModrehoCarodeja" -> {
                System.out.println("Vystrojil si sa palicou modrého čarodeja.");
                hrac.zmenStastie(20);
                hrac.zmenDamage(20);
            }
            case "radagastovaPalica" -> {
                System.out.println("Vystrojil si sa Radagastovou palicou.");
                hrac.zmenStastie(50);
                hrac.zmenDamage(50);
                hrac.zmenObratnost(10);
            }
            case "sarumanovaPalica" -> {
                System.out.println("Vystrojil si sa Sarumanovou palicou.");
                hrac.zmenStastie(70);
                hrac.zmenDamage(70);
                hrac.zmenObratnost(20);
            }
            case "gandalfovaPalica" -> {
                System.out.println("Vystrojil si sa Gandalfovou palicou.");
                hrac.zmenStastie(100);
                hrac.zmenDamage(100);
                hrac.zmenObratnost(30);
            }
            case "tupyMec" -> {
                System.out.println("Vystrojil si sa tupým mečom.");
                hrac.zmenDamage(10);
            }
            case "rohanskyMec" -> {
                System.out.println("Vystrojil si sa rohanským mečom.");
                hrac.zmenDamage(30);
                hrac.zmenObratnost(10);
            }
            case "gondorskyBojovyMec" -> {
                System.out.println("Vystrojil si sa bojovým gondorským mečom.");
                hrac.zmenDamage(50);
                hrac.zmenObratnost(15);
                hrac.zmenStastie(15);
            }
            case "mecZihadlo" -> {
                System.out.println("Vystrojil si sa mečom Žihadlom.");
                hrac.zmenDamage(60);
                hrac.zmenStastie(15);
                hrac.zmenObratnost(15);
            }
            case "aragornovMec" -> {
                System.out.println("Vystrojil si sa Aragornovým mečom.");
                hrac.zmenDamage(120);
                hrac.zmenStastie(40);
                hrac.zmenObratnost(30);
            }
            case "staryLuk" -> {
                System.out.println("Vystrojil si sa starým lukom.");
                hrac.zmenObratnost(10);
            }
            case "loveckyLuk" -> {
                System.out.println("Vystrojil si sa loveckým lukom.");
                hrac.zmenObratnost(20);
                hrac.zmenDamage(10);
            }
            case "bojovyLuk" -> {
                System.out.println("Vystrojil si sa bojovým lukom.");
                hrac.zmenDamage(20);
                hrac.zmenStastie(10);
                hrac.zmenObratnost(30);
            }
            case "preciznyLuk" -> {
                System.out.println("Vystrojil si sa bojovým lukom.");
                hrac.zmenObratnost(50);
                hrac.zmenDamage(30);
                hrac.zmenStastie(20);
            }
            case "legolasovLuk" -> {
                System.out.println("Vystrojil si sa Legolasovým lukom.");
                hrac.zmenDamage(60);
                hrac.zmenObratnost(100);
                hrac.zmenStastie(40);
            }
            default -> System.out.println("Danú zbraň neviem použiť.");
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ danej zbroje.
     *
     * @return TypPredmetu.ZBRAN.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.ZBRAN;
    }

    /**
     * Implementovaná metóda odstranSa na základe názvu zbrane zníži staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void odstranSa(Hrac hrac) {
        switch (this.nazov) {
            case "sprachnivenaPalica" -> {
                System.out.println("Odstránil si si zo zbroje spráchnivenú palicu.");
                hrac.zmenStastie(-10);
            }
            case "palicaModrehoCarodeja" -> {
                System.out.println("Odstránil si si zo zbroje palicu modrého čarodeja.");
                hrac.zmenStastie(-20);
                hrac.zmenDamage(-20);
            }
            case "radagastovaPalica" -> {
                System.out.println("Odstránil si si zo zbroje Radagastovu palicu.");
                hrac.zmenStastie(-50);
                hrac.zmenDamage(-50);
                hrac.zmenObratnost(-10);
            }
            case "sarumanovaPalica" -> {
                System.out.println("Odstránil si si zo zbroje Sarumanovu palicu.");
                hrac.zmenStastie(-70);
                hrac.zmenDamage(-70);
                hrac.zmenObratnost(-20);
            }
            case "gandalfovaPalica" -> {
                System.out.println("Odstránil si si zo zbroje Gandalfovu palicu.");
                hrac.zmenStastie(-100);
                hrac.zmenDamage(-100);
                hrac.zmenObratnost(-30);
            }
            case "tupyMec" -> {
                System.out.println("Odstránil si si zo zbroje tupý meč.");
                hrac.zmenDamage(-10);
            }
            case "rohanskyMec" -> {
                System.out.println("Odstránil si si zo zbroje rohanský meč.");
                hrac.zmenDamage(-30);
                hrac.zmenObratnost(-10);
            }
            case "gondorskyBojovyMec" -> {
                System.out.println("Odstránil si si zo zbroje bojový gondorský meč.");
                hrac.zmenDamage(-50);
                hrac.zmenObratnost(-15);
                hrac.zmenStastie(-15);
            }
            case "mecZihadlo" -> {
                System.out.println("Odstránil si si zo zbroje meč Žihadlo.");
                hrac.zmenDamage(-60);
                hrac.zmenStastie(-15);
                hrac.zmenObratnost(-15);
            }
            case "aragornovMec" -> {
                System.out.println("Odstránil si si zo zbroje Aragornov meč.");
                hrac.zmenDamage(-120);
                hrac.zmenStastie(-40);
                hrac.zmenObratnost(-30);
            }
            case "staryLuk" -> {
                System.out.println("Odstránil si si zo zbroje starý luk.");
                hrac.zmenObratnost(-10);
            }
            case "loveckyLuk" -> {
                System.out.println("Odstránil si si zo zbroje lovecký luk.");
                hrac.zmenObratnost(-20);
                hrac.zmenDamage(-10);
            }
            case "bojovyLuk" -> {
                System.out.println("Odstránil si si zo zbroje bojový luk.");
                hrac.zmenDamage(-20);
                hrac.zmenStastie(-10);
                hrac.zmenObratnost(-30);
            }
            case "preciznyLuk" -> {
                System.out.println("Odstránil si si zo zbroje precízny luk.");
                hrac.zmenObratnost(-50);
                hrac.zmenDamage(-30);
                hrac.zmenStastie(-20);
            }
            case "legolasovLuk" -> {
                System.out.println("Odstránil si si zo zbroje Legolasov luk.");
                hrac.zmenDamage(-60);
                hrac.zmenObratnost(-100);
                hrac.zmenStastie(-40);
            }
            default -> System.out.println("Danú zbraň neviem odstrániť.");
        }
    }

    /**
     * Implementovaná metóda typPostavy vracia, pre ktorý typ postavy je zbraň určená.
     *
     * @return daný typ postavy.
     */
    @Override
    public Postava typPostavy() {
        return this.postava;
    }
}
