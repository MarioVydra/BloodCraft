package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Trieda Lektvar implementovaná interfacom IZbroj, reprezentuje všetky lektvary, ktoré sa v hre môžu vyskytnúť.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class Lektvar implements IZbroj {

    private final String nazov;
    private int dlzkaTrvania;
    private double hodnotaZmeny;
    private boolean liecivy;

    /**
     * Konštruktor inicializuje názov daného lektvaru a dĺžku jeho trvania (pôsobenia).
     *
     * @param nazov String.
     * @param dlzkaTrvania int.
     */
    public Lektvar(String nazov, int dlzkaTrvania) {
        this.nazov = nazov;
        this.dlzkaTrvania = dlzkaTrvania;
        this.hodnotaZmeny = 0;
        this.liecivy = false;
    }

    /**
     * Implementovaný getter atribútu nazov.
     *
     * @return názov daného lektvaru.
     */
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Implementovaná metóda pouziSa na základe názvu lektvaru zvýši staty hráča.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        switch (this.nazov) {
            case "MalyPosilnovacDamagu" -> {
                System.out.println("Vypil si malý posilňovač damagu.");
                this.hodnotaZmeny = hrac.zmenDamage((Math.round(1.1 * hrac.getDamage()) - hrac.getDamage()));
                hrac.pridajLektvar(this);
            }
            case "StrednyPosilnovacDamagu" -> {
                System.out.println("Vypil si stredný posilňovač damagu.");
                this.hodnotaZmeny = hrac.zmenDamage((Math.round(1.2 * hrac.getDamage()) - hrac.getDamage()));
                hrac.pridajLektvar(this);
            }
            case "VelkyPosilnovacDamagu" -> {
                System.out.println("Vypil si veľký posilňovač damagu.");
                this.hodnotaZmeny = hrac.zmenDamage((Math.round(1.3 * hrac.getDamage()) - hrac.getDamage()));
                hrac.pridajLektvar(this);
            }
            case "MalyLektvarObratnosti" -> {
                System.out.println("Vypil si malý lektvar obratnosti.");
                this.hodnotaZmeny = hrac.zmenObratnost((Math.round(1.1 * hrac.getObratnost()) - hrac.getObratnost()));
                hrac.pridajLektvar(this);
            }
            case "StrednyLektvarObratnosti" -> {
                System.out.println("Vypil si stredný lektvar obratnosti.");
                this.hodnotaZmeny = hrac.zmenObratnost((Math.round(1.2 * hrac.getObratnost()) - hrac.getObratnost()));
                hrac.pridajLektvar(this);
            }
            case "VelkyLektvarObratnosti" -> {
                System.out.println("Vypil si veľký lektvar obratnosti.");
                this.hodnotaZmeny = hrac.zmenObratnost((Math.round(1.3 * hrac.getObratnost()) - hrac.getObratnost()));
                hrac.pridajLektvar(this);
            }
            case "MaleTekuteStastie" -> {
                System.out.println("Vypil si malé tekuté šťastie.");
                this.hodnotaZmeny = hrac.zmenStastie((Math.round(1.1 * hrac.getStastie()) - hrac.getStastie()));
                hrac.pridajLektvar(this);
            }
            case "StredneTekuteStastie" -> {
                System.out.println("Vypil si stredné tekuté šťastie.");
                this.hodnotaZmeny = hrac.zmenStastie((Math.round(1.2 * hrac.getStastie()) - hrac.getStastie()));
                hrac.pridajLektvar(this);
            }
            case "VelkeTekuteStastie" -> {
                System.out.println("Vypil si veľké tekuté šťastie.");
                this.hodnotaZmeny = hrac.zmenStastie((Math.round(1.3 * hrac.getStastie()) - hrac.getStastie()));
                hrac.pridajLektvar(this);
            }
            case "MalyLiecivyLektvar" -> {
                System.out.println("Vypil si malý liečivý lektvar");
                this.liecivy = true;
                hrac.zvysHP(25);
            }
            case "StrednyLiecivyLektvar" -> {
                System.out.println("Vypil si stredný liečivý lektvar.");
                this.liecivy = true;
                hrac.zvysHP(50);
            }
            case "VelkyLiecivyLektvar" -> {
                System.out.println("Vypil si veľký liečivý lektvar.");
                this.liecivy = true;
                hrac.zvysHP(75);
            }
            default -> System.out.println("Daný lektvar neviem vypiť.");
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

    /**
     * Getter atribútu dlzkaTrvania.
     *
     * @return dĺžka trvania lektvaru.
     */
    public int getDlzkaTrvania() {
        return this.dlzkaTrvania;
    }

    /**
     * Metóda znizDlzkuTrvania zníži dĺžku trvania lektvaru o 1.
     */
    public void znizDlzkuTrvania() {
        this.dlzkaTrvania--;
    }

    /**
     * Metóda jeLiecivy vracia boolean hodnotu atribútu liecivy.
     *
     * @return true - ak lektvar je liečivý, false - ak lektvar nie je liečivý.
     */
    public boolean jeLiecivy() {
        return this.liecivy;
    }

    /**
     * Implementovaná metóda odstranSa na základe názvu lektvaru zníži staty hráča o hodnotu, ktorú ju pôvodne zvýšil.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void odstranSa(Hrac hrac) {
        switch (this.nazov) {
            case "MalyPosilnovacDamagu", "VelkyPosilnovacDamagu", "StrednyPosilnovacDamagu" -> {
                hrac.zmenDamage(-this.hodnotaZmeny);
                hrac.odstranLektvar(this);
                System.out.println("Účinok lektvaru " + this.nazov + " vypršal.");
            }
            case "MalyLektvarObratnosti", "StrednyLektvarObratnosti", "VelkyLektvarObratnosti" -> {
                hrac.zmenObratnost(-this.hodnotaZmeny);
                hrac.odstranLektvar(this);
                System.out.println("Účinok lektvaru " + this.nazov + " vypršal.");
            }
            case "MaleTekuteStastie", "StredneTekuteStastie", "VelkeTekuteStastie" -> {
                hrac.zmenStastie(-this.hodnotaZmeny);
                hrac.odstranLektvar(this);
                System.out.println("Účinok lektvaru " + this.nazov + " vypršal.");
            }
            default -> System.out.println("Daný lektvar som ani nevypil.");
        }
    }

    /**
     * Implementovaná metóda typPostavy vracia, pre ktorý typ postavy sú lektvary určené.
     *
     * @return null - keďže lektvary sú univerzálne.
     */
    @Override
    public Postava typPostavy() {
        return null;
    }
}
