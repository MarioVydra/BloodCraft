package sk.uniza.fri.bloodcraft.mapa;

/**
 * Interface IRegion pre regióny.
 *
 * @author Mário Vydra
 * @version 4.5.2022
 */
public interface IRegion {
    /**
     * Metóda getPopisRegionu vypíše informácie o dnaom regióne.
     */
    void getPopisRegionu();

    /**
     * Getter názvu regiónu.
     *
     * @return názov daného regiónu.
     */
    String getNazov();
}
