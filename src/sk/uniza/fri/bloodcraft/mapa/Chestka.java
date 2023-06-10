package sk.uniza.fri.bloodcraft.mapa;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.predmety.IPredmet;

import java.util.HashMap;
import java.util.Random;

/**
 * Trieda Chestka reprezentuje chestku, ktorej je priradená úloha a vzácny predmet.
 * Do chestky vieme pridať bežné predmety a vieme ju otvoriť po splnení úlohy.
 *
 * @author Mário Vydra
 * @version 1.5.2022
 */
public class Chestka {
    private final HashMap<String, IPredmet> beznePredmety;
    private final IPredmet vzacnyPredmet;
    private boolean jeOtvorena;
    private final IUloha uloha;
    private final String nazov;

    /**
     * Konštruktor inicializuje bežné predmety, názov chestky, priradí jej vzácny predmet a úlohu.
     * Taktiež nastaví atribútu jeOtvorena false hodnotu.
     *
     * @param nazov String.
     * @param vzacnyPredmet IPredmet.
     * @param uloha IUloha.
     */
    public Chestka(String nazov, IPredmet vzacnyPredmet, IUloha uloha) {
        this.beznePredmety = new HashMap<>();
        this.nazov = nazov;
        this.vzacnyPredmet = vzacnyPredmet;
        this.uloha = uloha;
        this.jeOtvorena = false;
    }

    /**
     * Metóda vypisUlohu vyvolá metódu vypisSa, čím sa vypíšu informácie o danej úlohe a prípadne danú úlohu aj spustí.
     */
    public void vypisULohu() {
        this.uloha.vypisSa();
    }

    /**
     * Metóda otvorSa otvorí danú chestku, ak jej úloha je splnená.
     * Po otvorení vypíše dané predmety, ktoré z nej padli a vloží ich na územie, v ktorom sa daná chestka/hráč nachádza.
     * Na základe šťastia hráča môže padnúť z chestky aj nejaký vzácny predmet.
     *
     * @param hrac referencia na hráča.
     */
    public void otvorSa(Hrac hrac) {
        if (this.jeOtvorena) {
            System.out.println("Danú chestku si už otvoril.");
            return;
        }
        if (this.uloha.jeSplnena()) {
            System.out.println(this.nazov + " sa otvorila, môžeš si z územia vybrať veci.");
            System.out.print("Padli z nej nasledovné veci: ");
            StringBuilder veci = new StringBuilder();
            this.jeOtvorena = true;
            Random random = new Random();
            int sanca = (int)Math.round(hrac.getStastie() / 10);
            if (random.nextInt(sanca) != 0) {
                hrac.getKdeSom().pridajPredmet(this.vzacnyPredmet);
                veci.append(this.vzacnyPredmet.getNazov()).append(" ");
            }
            for (IPredmet predmet : this.beznePredmety.values()) {
                hrac.getKdeSom().pridajPredmet(predmet);
                veci.append(predmet.getNazov()).append(" ");
            }
            System.out.println(veci);
        } else {
            System.out.println("Nesplnil si úlohu. " + this.nazov + " je zatvorená.");
        }
    }

    /**
     * Metóda pridajBeznePredmety pomocou parametrov pridá do chestky 2 bežné predmety.
     *
     * @param predmet1 IPredmet.
     * @param predmet2 IPredmet.
     */
    public void pridajBeznePredmety(IPredmet predmet1, IPredmet predmet2) {
        this.beznePredmety.put(predmet1.getNazov(), predmet1);
        this.beznePredmety.put(predmet2.getNazov(), predmet2);
    }

    /**
     * Getter atribútu nazov.
     *
     * @return názov danej chestky.
     */
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Getter BojovejUlohy.
     *
     * @return BojovaUloha - ak daná chestka takú úlohu má pridelenú, null - ak chestka nemá bojovú úlohu.
     */
    public BojovaUloha getBojovaUloha() {
        if (this.uloha instanceof BojovaUloha) {
            return (BojovaUloha)this.uloha;
        }
        return null;
    }
}
