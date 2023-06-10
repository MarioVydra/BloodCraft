package sk.uniza.fri.bloodcraft.prikazy;


/**
 * Trieda NazvyPrikazov reprezentuje zoznam všetkých platných príkazov v hre.
 * Jej úlohou je rozpoznávať platné príkazy.
 *
 * @author Cvičenia, Mário Vydra
 * @version 27.4.2022
 */
public class NazvyPrikazov {
    private static final String[] PLATNE_PRIKAZY = {"ukonci", "chod", "pomoc", "inventar", "pouzi", "hladaj", "zahod", "zober", "staty", "zbroj", "zautoc", "ujdi", "otvor", "uloha", "pokracuj", "uloz"};

    /**
     * Metóda jePrikaz kontroluje, či prikaz v parametri je platný príkaz.
     *
     * @param prikaz String.
     * @return true - ak je parameter platný príkaz, false - ak nie je.
     */
    public boolean jePrikaz(String prikaz) {
        for (String s : PLATNE_PRIKAZY) {
            if (s.equals(prikaz)) {
                return true;
            }
        }
        return false;
    }
}
