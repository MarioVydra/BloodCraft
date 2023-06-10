package sk.uniza.fri.bloodcraft.mapa;

import sk.uniza.fri.bloodcraft.predmety.IPredmet;

import java.util.HashMap;

/**
 * Trieda Uzemie reprezentuje územie, teda časť regiónu.
 * Do územia vieme priradiť región, bossa, chestku a pridať mu predmety.
 *
 * @author Mário Vydra
 * @version 27.4.2022
 */
public class Uzemie {
    private final String nazov;
    private final IRegion region;
    private Boss boss;
    private final HashMap<String, IPredmet> predmety;
    private final HashMap<String, Uzemie> susedneUzemia;
    private Chestka chestka;

    /**
     * Konštruktor inicializuje názov, predmety, susedné územia. A priradí danému územiu bossa, región.
     *
     * @param nazov String.
     * @param region Region.
     * @param boss Boss.
     */
    public Uzemie(String nazov, IRegion region, Boss boss) {
        this.nazov = nazov;
        this.region = region;
        this.boss = boss;
        this.predmety = new HashMap<>();
        this.susedneUzemia = new HashMap<>();
        this.chestka = null;
    }

    /**
     * Metóda pridajSusedneUzemia na základe parametrov priradí danému územiu susedné územie.
     *
     * @param nazovUzemia String.
     * @param uzemie Uzemie.
     */
    public void pridajSusedneUzemia(String nazovUzemia, Uzemie uzemie) {
        this.susedneUzemia.put(nazovUzemia, uzemie);
    }

    /**
     * Metóda pridajPredmety na základe hodnoty parametra pridá daný predmet do územia.
     *
     * @param predmet IPredmet.
     */
    public void pridajPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    /**
     * Metóda vypisPredmety vypíše chestku a všetky predmety, ktoré sa na danom územi nachádzajú.
     */
    public void vypisPredmetyAChestku() {
        if (this.predmety.isEmpty()) {
            System.out.println("Na danom území sa nenachádzajú žiadne predmety.");
            return;
        }
        System.out.println("Na územi nájdeš tieto predmety:");
        for (String s : this.predmety.keySet()) {
            System.out.print(s + " ");
        }
        System.out.println();
        if (this.chestka != null) {
            System.out.println("Chestka: " + this.chestka.getNazov());
        } else {
            System.out.println("Na území nie je žiadna chestka.");
        }
    }

    /**
     * Metóda najdiUzemieVSmere na základe smeru v parametri nájde dané susedné územie.
     *
     * @param smer String.
     * @return susedné územie.
     */
    public Uzemie najdiUzemieVSmere(String smer) {
        return this.susedneUzemia.get(smer);
    }

    /**
     * Getter atribútu nazov.
     *
     * @return názov daného územia.
     */
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Metóda najdiPremdet na základne názvu v parametri nájde daný predmet.
     *
     * @param nazov String.
     * @return IPredmet, ktorý sme na danom území našli.
     */
    public IPredmet najdiPredmet(String nazov) {
        return this.predmety.get(nazov);
    }

    /**
     * Metóda vyberPredmet odstráni na základe názvu v parametri z množiny predmetov daný predmet.
     *
     * @param nazov String.
     */
    public void vyberPredmet(String nazov) {
        this.predmety.remove(nazov);
    }

    /**
     * Metóda getInfo vypíše do terminálu názov územia a susedné územia, na ktoré sa môžeš dostať.
     */
    public void getInfo() {
        System.out.println("Aktuálne sa nachádzaš na území " + this.nazov);
        System.out.println("Z tohto územia sa môžeš presunúť na nasledovné územia:");
        for (String s : this.susedneUzemia.keySet()) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /**
     * Getter atribútu region.
     *
     * @return región danáho územia.
     */
    public IRegion getRegion() {
        return this.region;
    }

    /**
     * Getter atribútu boss.
     *
     * @return bossa daného územia.
     */
    public Boss getBoss() {
        return this.boss;
    }

    public Chestka getChestka() {
        return this.chestka;
    }

    /**
     * Metóda setBossToNull nastaví bossovi hodnotu null.
     */
    public void setBossToNull() {
        this.boss = null;
    }

    /**
     * Metóda pridajChestku pridá do daného územia chestku.
     *
     * @param chestka Chestka.
     */
    public void pridajChestku(Chestka chestka) {
        this.chestka = chestka;
    }
}
