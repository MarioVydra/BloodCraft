package sk.uniza.fri.bloodcraft.hra;

import sk.uniza.fri.bloodcraft.mapa.Boss;
import sk.uniza.fri.bloodcraft.mapa.Uzemie;
import sk.uniza.fri.bloodcraft.predmety.IPredmet;
import sk.uniza.fri.bloodcraft.predmety.IZbroj;
import sk.uniza.fri.bloodcraft.predmety.Lektvar;
import sk.uniza.fri.bloodcraft.predmety.SlnecneHodiny;
import sk.uniza.fri.bloodcraft.predmety.TypPredmetu;
import sk.uniza.fri.bloodcraft.predmety.Zbran;
import sk.uniza.fri.bloodcraft.prikazy.Prikaz;

import java.util.HashMap;
import java.util.Random;

/**
 * Trieda Hrac reprezentuje daného hráča.
 * Hráč má viacero druhov statov, má inventár, dokáze si nasadiť zbroj, vie zahodiť/použiť/zobrať predmet.
 *
 * @author Mário Vydra
 * @version 27.4.2022
 */
public class Hrac {
    private double hp;
    private double energia;
    private final HashMap<String, IPredmet> inventar;
    private final HashMap<String, IZbroj> zbroj;
    private final HashMap<String, Lektvar> aktivovaneLektvary;
    private Uzemie kdeSom;
    private double damage;
    private double stastie;
    private double obratnost;
    private double brnenie;
    private final Random random;
    private Postava postava;
    private boolean jeViditelny;

    /**
     * Konštruktor inicializuje jednotlivé atribúty, vytvorí priestor pre zbroj, inventár, aktivované lektvary.
     *
     * @param kdeSom Uzemie.
     * @param postava Postava.
     */
    public Hrac(Uzemie kdeSom, Postava postava) {
        this.hp = 100;
        this.energia = 100;
        this.stastie = 15;
        this.damage = 15;
        this.obratnost = 15;
        this.brnenie = 10;
        this.jeViditelny = true;
        this.inventar = new HashMap<>();
        this.zbroj = new HashMap<>();
        this.aktivovaneLektvary = new HashMap<>();
        this.random = new Random();
        this.kdeSom = kdeSom;
        this.inventar.put("SlnecneHodiny", new SlnecneHodiny());
        this.vyber(postava);
    }

    /**
     * Privátna metóda vyber na základe výberu hráča nastaví atribúty (staty) podľa zvolenej hodnoty a priradí mu základnú zbraň.
     *
     * @param postava Postava.
     */
    private void vyber(Postava postava) {
        if (postava.equals(Postava.BOJOVNIK)) {
            this.damage = 50;
            this.inventar.put("tupyMec", new Zbran("tupyMec", Postava.BOJOVNIK));
            this.postava = postava;
        } else if (postava.equals(Postava.LOVEC)) {
            this.obratnost = 50;
            this.inventar.put("staryLuk", new Zbran("staryLuk", Postava.LOVEC));
            this.postava = postava;
        } else if (postava.equals(Postava.KUZELNIK)) {
            this.stastie = 50;
            this.inventar.put("sprachnivenaPalica", new Zbran("sprachnivenaPalica", Postava.KUZELNIK));
            this.postava = postava;
        }
    }

    /**
     * Metóda hladaj vyvolá metódu vypisPredmety (metóda triedy Uzemie), ktorá vypíše chestku a predmety, ktoré sa nachádzajú na území, kde je aktuálne hráč.
     * Ak sa na území nachádza boss, ktorý je živý, predmety nevypíše.
     * Ak je hráč neviditeľný, predmety a chestku vypíše.
     */
    public void hladaj() {
        if (this.kdeSom.getBoss() != null) {
            if (this.jeViditelny) {
                System.out.println("Najskôr musíš zabiť bossa. Až potom nájdeš predmety.");
                return;
            }
        }
        this.kdeSom.vypisPredmetyAChestku();
    }

    /**
     * Metóda chodDoUzemia zmení aktuálne územie hráča na iné podľa parametra príkazu.
     * Uzemie, kam hráč chce ísť musí byť susedné s územím, kde sa aktuálne nachádza.
     * Pri každej zmene územia sa vypíše informácia o danom území a hráčovi sa zníži energia o hodnotu 10.
     *
     * @param prikaz Prikaz.
     */
    public void chodDoUzemia(Prikaz prikaz) {
        if (this.energia != 0) {
            if (!prikaz.obsahujeParameter()) {
                System.out.println("Nezadal si kam chceš ísť.");
                return;
            }
            String smer = prikaz.getParameter();
            if (smer.equals(this.kdeSom.getNazov())) {
                System.out.println("Nemôžeš ísť tam, kde sa už nachádzaš.");
                return;
            }
            Uzemie noveUzemie = this.kdeSom.najdiUzemieVSmere(smer);
            if (noveUzemie == null) {
                System.out.println("Na dané územie sa odtiaľto nedostaneš.");
            } else {
                if (this.kdeSom.getRegion() != noveUzemie.getRegion()) {
                    System.out.println("Vstúpil si do regiónu " + noveUzemie.getRegion().getNazov() + ".");
                    noveUzemie.getRegion().getPopisRegionu();
                }
                this.kdeSom = noveUzemie;
                this.kdeSom.getInfo();
                this.energia -= 10;
            }
        } else {
            System.out.println("Nemáš žiadnu energiu. Najedz sa alebo napi, inak tu budeš uväznený na vždy.");
        }
    }

    /**
     * Metóda pouzi na základe prikazu v parametri a parametra daného príkazu použije predmet.
     * Ak hráč chce napríklad použiť zbraň, no jednu už vo zbroji má, tak automaticky sa mu používaná zbraň vráti do inventára a nová sa mu vráti do zbroje.
     * To isté platí pre všetky typy zbroji.
     *
     * @param prikaz Prikaz.
     */
    public void pouzi(Prikaz prikaz) {
        if (!prikaz.obsahujeParameter()) {
            System.out.println("Čo mám použiť?");
            return;
        }
        IPredmet predmet = this.inventar.get(prikaz.getParameter());
        if (predmet == null) {
            predmet = this.kdeSom.najdiPredmet(prikaz.getParameter());
            if (predmet != null) {
                System.out.println("Najskôr musíš daný predmet zobrať a potom ho použiť.");
                return;
            }
        }
        if (predmet == null) {
            System.out.println("Daný predmet pri sebe nemáš a nie je ani v okolí.");
            return;
        }
        if (predmet instanceof IZbroj novyPredmet) {
            IZbroj staryPredmet = this.najdiZbroj(predmet.getTypPredmetu());
            if (staryPredmet != null) {
                if (novyPredmet.typPostavy() == null || novyPredmet.typPostavy().equals(this.postava)) {
                    this.zbroj.remove(staryPredmet.getNazov());
                    this.inventar.put(staryPredmet.getNazov(), staryPredmet);
                    staryPredmet.odstranSa(this);
                    this.zbroj.put(novyPredmet.getNazov(), novyPredmet);
                    this.inventar.remove(novyPredmet.getNazov());
                } else if (!novyPredmet.typPostavy().equals(this.postava)) {
                    System.out.println("Nemôžeš sa vyzbrojiť zbraňou, ktorá je určená pre inú postavu.");
                    return;
                }
            } else {
                this.inventar.remove(novyPredmet.getNazov());
                this.zbroj.put(novyPredmet.getNazov(), novyPredmet);
            }
        }
        if (predmet.getTypPredmetu().equals(TypPredmetu.SPOTREBOVATELNY)) {
            predmet.pouziSa(this);
            this.inventar.remove(predmet.getNazov());
            return;
        } else if (predmet.getTypPredmetu().equals(TypPredmetu.ZNOVA_POUZITELNY)) {
            this.inventar.remove(predmet.getNazov());
            this.kdeSom.pridajPredmet(predmet);
        }
        predmet.pouziSa(this);
    }

    /**
     * Privátna metóda najdiZbroj na základe paramatra prehľadáva zbroj.
     *
     * @param typPredmetu TypPredmetu.
     * @return IPredmet - ak sa v zbroji predmet s daným typom predmetu (zbroji) nachádza, false - ak sa taký predmet vo zbroji nenachádza.
     */
    private IZbroj najdiZbroj(TypPredmetu typPredmetu) {
        for (IZbroj p : this.zbroj.values()) {
            if (p.getTypPredmetu().equals(typPredmetu)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metóda zvysEnergiu zvýši energiu o veľkosť danú v parametri.
     *
     * @param mnozstvo double.
     */
    public void zvysEnergiu(double mnozstvo) {
        if (this.energia + mnozstvo > 100) {
            this.energia = 100;
            System.out.println("Energia sa ti zvýšila o " + (100 - this.energia) + ".");
        } else {
            this.energia += mnozstvo;
            System.out.println("Energia sa ti zvýšila o " + mnozstvo + ".");
        }
        System.out.println("Tvoja aktuálna energia je " + this.energia + ".");
    }

    /**
     * Metóda znizEnergiu zníži energiu o veľkosť danú v parametri.
     *
     * @param mnozstvo double.
     */
    public void znizEnergiu(double mnozstvo) {
        if (this.energia - mnozstvo < 0) {
            this.energia = 0;
            System.out.println("Energia sa ti znížila o " + (100 - this.energia) + ".");
        } else {
            this.energia -= mnozstvo;
            System.out.println("Energia sa ti znížila o " + mnozstvo + ".");
        }
        System.out.println("Tvoja aktuálna energia je " + this.energia + ".");
    }

    /**
     * Metóda zobrazInventar prehľadá inventár a vypíše všetky predmety, ktoré sa v ňom nachádzajú.
     */
    public void zobrazInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("V inventári nemáš žiadny item.");
            return;
        }
        System.out.println("Obsah inventára:");
        for (String s : this.inventar.keySet()) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /**
     * Metóda zobrazZbroj prejde všetky predmety, ktoré sa nachádzajú v zbroji a vypíše ich.
     */
    public void zobrazZbroj() {
        if (this.zbroj.isEmpty()) {
            System.out.println("Nie si vyzbrojený žiadnym vybavením.");
            return;
        }
        System.out.println("Tvoju zbroj tvorí:");
        for (String s : this.zbroj.keySet()) {
            System.out.println(s + " ");
        }
        System.out.println();
    }

    /**
     * Metóda zoberPredmet na základe parametra daného prikazu zoberie z územie, kde sa hráč aktuálne nachádza a vloži ho do inventára.
     * Ak sa na území nachádza živý boss, hráč nemôže zobrať predmet z daného územia.
     *
     * @param prikaz Prikaz.
     */
    public void zoberPredmet(Prikaz prikaz) {
        if (!prikaz.obsahujeParameter()) {
            System.out.println("Čo mám zobrať?");
            return;
        }
        IPredmet predmet = this.kdeSom.najdiPredmet(prikaz.getParameter());
        if (predmet == null) {
            System.out.println("Daný predmet v okolí nie je.");
            return;
        }
        Boss boss = this.getKdeSom().getBoss();
        if (boss != null) {
            if (this.jeViditelny) {
                System.out.println("Najskôr musíš zabiť Bossa, až potom môžeš zobrať predmet.");
                return;
            }
        }
        this.kdeSom.vyberPredmet(prikaz.getParameter());
        this.inventar.put(prikaz.getParameter(), predmet);
        System.out.println("Zobral si predmet " + predmet.getNazov() + " do svojho inventára.");
    }

    /**
     * Metóda zahodPredmet zahodí predmet na základe parametra príkazu do územia, kde sa aktuálne nachádza.
     * Ak sa na území nachádza živý boss, hráč nemôže zahodiť predmet do daného územia.
     *
     * @param prikaz Prikaz.
     */
    public void zahodPredmet(Prikaz prikaz) {
        if (!prikaz.obsahujeParameter()) {
            System.out.println("Čo mám zahodiť?");
            return;
        }
        IPredmet predmet = this.inventar.get(prikaz.getParameter());
        if (predmet == null) {
            System.out.println("Daný predmet v inventári nemáš.");
            return;
        }
        Boss boss = this.getKdeSom().getBoss();
        if (boss != null) {
            if (this.jeViditelny) {
                System.out.println("Najskôr musíš zabiť bossa, až potom môžeš zahodiť predmet.");
                return;
            }
        }
        this.inventar.remove(predmet.getNazov());
        this.kdeSom.pridajPredmet(predmet);
        System.out.println("Vyhodil si predmet " + predmet.getNazov() + " z inventára");
    }

    /**
     * Metóda zautoc zníži životy bossovi (veľkosť určuje atribút damage hráča), ktorý sa nachádza na danom území.
     * Boss sa môže vyhnúť útokom, na základe jeho obratnosti.
     *
     * @param boss referencia na bossa.
     */
    public void zautoc(Boss boss) {
        if (boss == null) {
            System.out.println("Na území, kde sa nachádzaš nie sú žiadny nepriatelia.");
            return;
        }
        int pomer = (int)(boss.getObratnost() / 10);
        if (this.random.nextInt(pomer) == 0) {
            boss.znizHp(this.damage);
        } else {
            System.out.println(boss.getMeno() + " sa vyhol útoku.");
        }
    }

    /**
     * Metóda vypisStaty vypíše do terminálu aktuálne staty hráča.
     */
    public void vypisStaty() {
        System.out.println("Životy: " + this.hp);
        System.out.println("Energia: " + this.energia);
        System.out.println("Damage: " + this.damage);
        System.out.println("Obratnosť: " + this.obratnost);
        System.out.println("Šťastie: " + this.stastie);
        System.out.println("Brnenie: " + this.brnenie);
        if (this.jeViditelny) {
            System.out.println("Viditeľný: áno");
        } else {
            System.out.println("Viditeľný: nie");
        }
    }

    /**
     * Getter atribútu kdeSom.
     *
     * @return územie, na ktorom sa hráč aktuálne nachádza.
     */
    public Uzemie getKdeSom() {
        return this.kdeSom;
    }

    /**
     * Getter atribútu obratnost.
     *
     * @return aktuálna veľkosť obratnosti hráča.
     */
    public double getObratnost() {
        return this.obratnost;
    }

    /**
     * Getter atribútu hp.
     *
     * @return aktuálnu veľkosť životov hráča.
     */
    public double getHp() {
        return this.hp;
    }

    /**
     * Getter atribútu brnenie.
     *
     * @return aktuálun hodnotu brnenia hráča.
     */
    public double getBrnenie() {
        return this.brnenie;
    }

    /**
     * Metóda zmenStastie zmení atribút stastie o hodnotu danú v parametri.
     *
     * @param hodnota double.
     * @return veľkosť zmeny hodnoty.
     */
    public double zmenStastie(double hodnota) {
        this.stastie += hodnota;
        if (hodnota > 0) {
            System.out.println("Zvýšilo sa ti šťastie o " + hodnota + ".");
        } else {
            System.out.println("Znížilo sa ti šťastie o " + Math.abs(hodnota) + ".");
        }
        return hodnota;
    }

    /**
     * Metóda zmenDamage zmení atribút damage o hodnotu danú v parametri.
     *
     * @param hodnota double.
     * @return veľkosť zmeny.
     */
    public double zmenDamage(double hodnota) {
        this.damage += hodnota;
        if (hodnota > 0) {
            System.out.println("Zvýšil sa ti damage o " + hodnota + ".");
        } else {
            System.out.println("Znížil sa ti damage o " + Math.abs(hodnota) + ".");
        }
        return hodnota;
    }

    /**
     * Metóda zmenObratnost zmení atribút obratnost o hodnotu danú v parametri.
     *
     * @param hodnota double.
     * @return veľkosť zmeny.
     */
    public double zmenObratnost(double hodnota) {
        this.obratnost += hodnota;
        if (hodnota > 0) {
            System.out.println("Zvýšila sa ti obratnosť o " + hodnota + ".");
        } else {
            System.out.println("Znížila sa ti obratnosť o " + Math.abs(hodnota) + ".");
        }
        return hodnota;
    }

    /**
     * Metóda zmenBrnenie zmení atribút brnenie o hodnotu danú v parametri.
     *
     * @param hodnota double.
     */
    public void zmenBrnenie(double hodnota) {
        this.brnenie += hodnota;
        if (hodnota > 0) {
            System.out.println("Zvýšilo sa ti brnenie o " + hodnota + ".");
        } else {
            System.out.println("Znížilo sa ti brnenie o " + Math.abs(hodnota) + ".");
        }
    }

    /**
     * Metóda znizHP zníži hp hráčovi o veľkosť danú v parametri.
     *
     * @param damage double.
     */
    public void znizHP(double damage) {
        if (this.hp - damage <= 0) {
            this.hp = 0;
            System.out.println("Znížili sa ti životy o " + (damage - this.hp) + ". Aktuálne máš " + this.hp + " HP.");
        } else {
            this.hp -= damage;
            System.out.println("Znížili sa ti životy o " + damage + ". Aktuálne máš " + this.hp + " HP.");
        }
    }

    /**
     * Metóda zvysHP zvýši hp hráčovi o veľkosť danú v parametri.
     *
     * @param hp double.
     */
    public void zvysHP(double hp) {
        if (this.hp + hp >= 100) {
            this.hp = 100;
            System.out.println("Zvýšili sa ti životy o " + (100 - this.hp) + ". Aktuálne máš " + this.hp + " HP.");
        } else {
            this.hp += hp;
            System.out.println("Zvýšili sa ti životy o " + hp + ". Aktuálne máš " + this.hp + " HP.");
        }
    }

    /**
     * Metóda pridajLektvar pridá do zoznamu aktivovaných lektvarov lektvar uvedený v parametri.
     * Hráč nemôže vypiť dva krát ten istý lektvar (keď skončí účinok prvého lektvaru, potom môže).
     *
     * @param lektvar Lektvar.
     */
    public void pridajLektvar(Lektvar lektvar) {
        if (this.aktivovaneLektvary.containsKey(lektvar.getNazov())) {
            System.out.println("Nemôžeš vypiť ďalší elixír.");
            return;
        }
        if (lektvar.jeLiecivy()) {
            return;
        }
        this.aktivovaneLektvary.put(lektvar.getNazov(), lektvar);
    }

    /**
     * Metóda odstranLektvar odstráni zo zonamu aktivovaných lektvarov lektvar uvedený v parametri.
     *
     * @param lektvar Lektvar.
     */
    public void odstranLektvar(Lektvar lektvar) {
        Lektvar vymaz = null;
        for (Lektvar l : this.aktivovaneLektvary.values()) {
            if (l.getNazov().equals(lektvar.getNazov())) {
                vymaz = l;
            }
        }
        if (vymaz != null) {
            this.aktivovaneLektvary.remove(vymaz.getNazov(), vymaz);
        }
    }

    /**
     * Metóda otvorChestku otvorí chestku v území, v ktorom sa hráč aktuálne nachádza.
     * Ak je boss an danom území živý, chestku nemôže hráč otvoriť.
     */
    public void otvorChestku() {
        if (this.kdeSom.getBoss() != null) {
            System.out.println("Najskôr musíš zabiť Bossa, až potom môžeš otvárať chestky.");
        } else {
            if (this.kdeSom.getChestka() == null) {
                System.out.println("Na dnaom území nie je chestka s úlohou.");
            } else {
                this.kdeSom.getChestka().otvorSa(this);
            }
        }
    }

    /**
     * Metóda vypisUlohu vypíše úlohu na území, kde sa hráč aktuálne nachádza.
     * Ak je boss an danom území živý, úloha sa nevypíše.
     */
    public void vypisUlohu() {
        if (this.kdeSom.getBoss() != null) {
            System.out.println("Najskôr musíš zabiť Bossa, až potom môžeš plniť úlohu.");
        } else {
            if (this.kdeSom.getChestka() == null) {
                System.out.println("Na danom území nie je chestka s úlohou.");
            } else {
                this.kdeSom.getChestka().vypisULohu();
            }
        }
    }

    /**
     * Setter atribútu jeViditelny.
     * Nastaví atribútu opačnú hodnotu.
     */
    public void setJeViditelny() {
        this.jeViditelny = !this.jeViditelny;
    }

    /**
     * Getter atribútu jeViditelny.
     *
     * @return true - ak je hráč viditeľný, false - ak hráč nie je viditeľný.
     */
    public boolean getJeViditelny() {
        return this.jeViditelny;
    }

    /**
     * Getter atribútu damage.
     *
     * @return aktuálny damage hráča.
     */
    public double getDamage() {
        return this.damage;
    }

    /**
     * Getter atribútu stastie.
     *
     * @return aktuálne šťastie hráča.
     */
    public double getStastie() {
        return this.stastie;
    }

    /**
     * Getter aktivovanych lektvarov.
     *
     * @return zoznam aktivovaných lektvarov.
     */
    public HashMap<String, Lektvar> getAktivovaneLektvary() {
        return this.aktivovaneLektvary;
    }

}
