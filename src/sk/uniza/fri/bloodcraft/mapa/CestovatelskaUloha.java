package sk.uniza.fri.bloodcraft.mapa;

import sk.uniza.fri.bloodcraft.predmety.IPredmet;

/**
 * Trieda CestovatelskaUloha implementovaná interfacom IUloha reprezentuje cestovateľské úlohy.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class CestovatelskaUloha implements IUloha {
    private final String nazov;
    private final IPredmet questovyPredmet;
    private final Uzemie cieloveUzemie;
    private boolean splnena;


    /**
     * Konštruktor inicializuje daný názov úlohy, priradí mu questový predmet a cieľové územie, na ktoré musí hráč questový predmet doniesť a následne zahodiť.
     *
     * @param nazov String.
     * @param questovyPredmet IPredmet.
     * @param cieloveUzemie Uzemie.
     */
    public CestovatelskaUloha(String nazov, IPredmet questovyPredmet, Uzemie cieloveUzemie) {
        this.nazov = nazov;
        this.questovyPredmet = questovyPredmet;
        this.cieloveUzemie = cieloveUzemie;
        this.splnena = false;
    }

    /**
     * Implementovaná metóda vypisSa vypíše čo je hráčovou úlohou (doniesť questový predmet do cieľového územia).
     * Taktiež metóda kontroluje, či danú úlohu splnil.
     */
    @Override
    public void vypisSa() {
        if (!this.splnena) {
            System.out.println("Tvojou úlohou je zobrať " + this.questovyPredmet.getNazov() + " a doniesť ho (vyhodiť) na územie " + this.cieloveUzemie.getNazov() + ".");
            if (this.cieloveUzemie.najdiPredmet(this.questovyPredmet.getNazov()) != null) {
                System.out.println("Splnil si úlohu " + this.nazov + ". Predmet " + this.questovyPredmet.getNazov() + " si doniesol do správneho územia. Môžeš otvoriť chestku.");
                this.splnena = true;
            } else {
                System.out.println("Úlohu " + this.nazov + " si zatiaľ nesplnil.");
            }
        } else {
            System.out.println("Úlohu si už splnil.");
        }
    }

    /**
     * Implementovaná metóda jeSplnena vracia boolean hodnotu atribútu jeSplnena.
     *
     * @return true - ak úloha je splnená, false - ak úloha nie je splnená.
     */
    @Override
    public boolean jeSplnena() {
        return this.splnena;
    }
}
