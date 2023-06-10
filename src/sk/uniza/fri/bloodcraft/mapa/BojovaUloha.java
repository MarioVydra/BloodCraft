package sk.uniza.fri.bloodcraft.mapa;


/**
 * Trieda BojovaUloha implementovaná interfacom IUloha reprezentuje bojové úlohy.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class BojovaUloha implements IUloha {

    private final String nazovUlohy;
    private final String nazovNepriatela;
    private int pocetNepriatelov;
    private final int povodnyPocetNepriatelov;
    private final double damageNepriatela;
    private boolean splnena;

    /**
     * Konštruktor inicializuje názov úlohy, názov nepriateľa, počet nepriateľov, damage nepriateľa a nastaví atribút splnena na false.
     *
     * @param nazovUlohy String.
     * @param nazovNepriatela String.
     * @param pocetNepriatelov int.
     * @param damageNepriatela double.
     */
    public BojovaUloha(String nazovUlohy, String nazovNepriatela, int pocetNepriatelov, double damageNepriatela) {
        this.nazovUlohy = nazovUlohy;
        this.nazovNepriatela = nazovNepriatela;
        this.pocetNepriatelov = pocetNepriatelov;
        this.povodnyPocetNepriatelov = pocetNepriatelov;
        this.damageNepriatela = damageNepriatela;
        this.splnena = false;
    }

    /**
     * Implementovaná metóda vypisSa vypíše informácie o danej úlohe (ktorí nepriatelia na hráča útočia a koľko ich je).
     */
    @Override
    public void vypisSa() {
        System.out.println("Dávaj bacha v okoli sa spawnujú " + this.nazovNepriatela + ", je ich približne " + this.pocetNepriatelov + ".");
        System.out.println("""
                Poraz všetkých nepriateľov a odmena z chestky je tvoja.
                Ak nechceš teraz zabíjať nepriateľov, tak napíš 'ujdi'.
                """);
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

    /**
     * Getter atribútu pocetNepriatelov.
     *
     * @return aktuálny počet nepriateľov.
     */
    public int getPocetNepriatelov() {
        return this.pocetNepriatelov;
    }

    /**
     * Metóda znizPocetNepriatelov zníži počet nepriateľov o 1.
     * Taktiež kontroluje, že či počet nepriateľov je rovný 0 (ak áno tak úloha je splnená).
     */
    public void znizPocetNepriatelov() {
        if (this.getPocetNepriatelov() == 0) {
            System.out.println("Splnil si úlohu " + this.nazovUlohy + " a zabil si všetkých nepriateľov. Môžeš otvoriť chestku.");
            this.splnena = true;
        } else {
            this.pocetNepriatelov--;
            System.out.println("Zabil si jedného nepriateľa, ostáva ich už len: " + this.pocetNepriatelov);
        }
    }

    /**
     * Metóda setPovodnyPocetNepriatelov zmení aktuálny počet nepriateľov na pôvodný počet nepriateľov.
     */
    public void setPovodnyPocetNepriatelov() {
        this.pocetNepriatelov = this.povodnyPocetNepriatelov;
    }

    /**
     * Getter atribútu nazovNepriatela.
     *
     * @return názov nepriateľa.
     */
    public String getNazovNepriatela() {
        return this.nazovNepriatela;
    }

    /**
     * Getter atríbútu damageNepriatela.
     *
     * @return damage nepriateľa.
     */
    public double getDamageNepriatela() {
        return this.damageNepriatela;
    }

    /**
     * Getter atribútu nazovUlohy.
     *
     * @return názov danej úlohy.
     */
    public String getNazovUlohy() {
        return this.nazovUlohy;
    }
}
