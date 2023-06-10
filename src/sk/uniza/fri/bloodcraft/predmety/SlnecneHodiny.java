package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Trieda SlnecneHodiny reprezentuje predmet slnečné hodiny.
 * Trieda je implementovaná interfacom IPredmet.
 * Na základe reálneho času a podľa slnečných hodín vráti informáciu, aká časť dňa je.
 *
 * @author Mário Vydra
 * @version 2.5.2022
 */
public class SlnecneHodiny implements IPredmet {
    private final Random random;

    /**
     * Konštruktor inicializuje random generátor.
     */
    public SlnecneHodiny() {
        this.random = new Random();
    }

    /**
     * Implementovaný getter názvu predmetu.
     *
     * @return SlnecneHodiny.
     */
    @Override
    public String getNazov() {
        return "SlnecneHodiny";
    }

    /**
     * Implementovaná metóda pouziSa na základe aktuálneho času vráti informácie o čase (vo forme slnečných hodín).
     * Na základe random generátora môže nastať škaredé počasie.
     *
     * @param hrac referencia na hráča.
     */
    @Override
    public void pouziSa(Hrac hrac) {
        int hodina = LocalDateTime.now().getHour();
        if (this.random.nextInt(11) != 0) {
            if (hodina < 6) {
                System.out.println("Je hlboká noc. Na oblohe žiaria hviezdy a svetlý plný mesiac vyniká nad úbočím hôr.");
            } else if (hodina < 9) {
                System.out.println("Prvé slnečné lúče zobúdzajú okolitú krajinu. Tieň na slnečných hodinách je najväčší. Je ráno.");
            } else if (hodina < 12) {
                System.out.println("Slnko sa pomaly presúva z východu na západ. Tieň na slnečných hodinách sa postupne skracuje. Je doobedie.");
            } else if (hodina == 12) {
                System.out.println("Je pravé poludnie. Slnko sa nachádza priamo nado mnou. Na slnečných hodinách nie je žiadny tieň.");
            } else if (hodina < 19) {
                System.out.println("Tieň sa postupne zväčšuje. Slnko sa pomaly posúva na západ. Je poobedie.");
            } else if (hodina < 21) {
                System.out.println("Na krajinu dopadajú posledné slnečné lúče dnešného dňa. Tieň na slnečných hodinách sa zväčšuje, no onedlho zmizne. Je večer.");
            } else {
                System.out.println("Na oblohe sa pomaly vynára žiarivý mesiac. Začína sa noc.");
            }
        } else {
            if (hodina < 6 || hodina >= 21) {
                System.out.println("Z oblohy padá hustý dážď. Nevidím si ani pred nos. Pravdepodobne už je noc.");
            } else {
                System.out.println("Prší ako z krhly. Určite noc ešte nie je. No netuším aká časť dňa je.");
            }
        }
    }

    /**
     * Implementovaná metóda getTypPredmetu vracia typ daného predmetu.
     *
     * @return typ daného predmetu.
     */
    @Override
    public TypPredmetu getTypPredmetu() {
        return TypPredmetu.TRVALY;
    }
}
