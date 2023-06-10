package sk.uniza.fri.bloodcraft.mapa;

/**
 * Trieda Rhovanion implementovaná interfacom IRegion reprezentuje región Rhovanion.
 *
 * @author Mário Vydra
 * @version 4.5.2022
 */
public class Rhovanion implements IRegion {

    /**
     * Implementovaná metóda getPopisRegionu vypíše informácie o regióne Rhovanion.
     */
    @Override
    public void getPopisRegionu() {
        System.out.print("""
                Rhovanion je región nachádzajúci sa na západ od Misty Mountains. Žijú tu trpaslíci, elfovia, ľudia, jeden čarodej a kopa nepriateľov.
                Skladá sa zo 4 území: Lothlórien, Mirkwood, Dol Guldur a Erebor.
                Lothlórien je najkrajšou ríšou elfov, ktorí zostali v Stredozemi.  Vládnu jej Galadriel a Celeborn z ich mesta v domoch na stromoch v Caras Galadhone.
                Mirkwood je hustý a ťažký les, ktorý tvoril veľkú časť východnej časti Rhovanion. Je domovom obrovských pavúkov a kráľovstva kráľa Thranduila a jeho elfov.
                Dol Guldur bol Sauronovou pevnosťou v Mirkwoode, predtým ako sa presťahoval do Barad-dûr v Mordore.
                Erebor je hora severovýchodne od Mirkwoodu. Je to miesto trpasličieho kráľovstva pod horou. Mesto Dale leží v údolí na jeho južných svahoch.
                
                """);
    }

    /**
     * Implementovaný getter názvu regiónu.
     *
     * @return Rhovanion.
     */
    @Override
    public String getNazov() {
        return "Rhovanion";
    }


}
