package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

/**
 * Trieda Jedlo implementovaná interfacom IZbroj, reprezentuje všetky jedlá, ktoré sa v hre môžu vyskytnúť.
 *
 * @author Mário Vydra
 * @version 28.4.2022
 */
public class Jedlo implements IPredmet {
    private final String nazov;
    private final double energia;
    private final double hp;

    /**
     * Konštruktor inicializuje názov jedla, jeho energiu a hp.
     *
     * @param nazov String.
     * @param energia double.
     * @param hp double.
     */
    public Jedlo(String nazov, double energia, double hp) {
        this.nazov = nazov;
        this.energia = energia;
        this.hp = hp;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov daného jedla.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe atribútov zvýši hráčovi energiu a hp.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "flasa" -> {
                System.out.println("Vypil si vodu.");
                hrac.zvysEnergiu(this.energia);
            }
            case "chlieb" -> {
                System.out.println("Zjedol si chlieb.");
                hrac.zvysEnergiu(this.energia);
            }
            case "maso" -> {
                System.out.println("Zjedol si mäso.");
                hrac.zvysEnergiu(this.energia);
            }
            case "jablko" -> {
                System.out.println("Zjedol si jablko.");
                hrac.zvysEnergiu(this.energia);
                hrac.zvysHP(this.hp);
            }
            case "mrkva" -> {
                System.out.println("Zjedol si mrkvu.");
                hrac.zvysEnergiu(this.energia);
                hrac.zvysHP(this.hp);
            }
            case "pomaranc" -> {
                System.out.println("Zjedol si pomaranč.");
                hrac.zvysEnergiu(this.energia);
                hrac.zvysHP(this.hp);
            }
            case "kolac" -> {
                System.out.println("Zjedol si koláč.");
                hrac.zvysEnergiu(this.energia);
                hrac.zvysHP(this.hp);
            }
            case "elfskyChlieb" -> {
                System.out.println("Zjedol si elfský chlieb.");
                hrac.zvysEnergiu(this.energia);
                hrac.zvysHP(this.hp);
            }
            case "cervavyChlieb" -> {
                System.out.println("Zjedol si červavý chlieb.");
                hrac.znizHP(this.hp);
                hrac.znizEnergiu(this.energia);
            }
            case "zhniteMaso" -> {
                System.out.println("Zjedol si zhnité mäso.");
                hrac.znizHP(this.hp);
                hrac.znizEnergiu(this.energia);
            }
            default -> System.out.println("Daný predmet neviem zjesť.");
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
