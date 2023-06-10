package sk.uniza.fri.bloodcraft.mapa;

/**
 * Interface IUloha pre úlohy.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public interface IUloha {
    /**
     * Metóda vypisSa vypíše informácie o danej úlohe, prípadne danú úlohu aj spustí.
     */
    void vypisSa();

    /**
     * Metóda jeSplnena vracia boolean hodnotu.
     *
     * @return true - ak úloha je splnená, false - ak úloha nie je splnená.
     */
    boolean jeSplnena();
}
