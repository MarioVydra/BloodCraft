package sk.uniza.fri.bloodcraft.mapa;

/**
 * Trieda Mordor implementovaná interfacom IRegion reprezentuje región Mordor.
 *
 * @author Mário Vydra
 * @version 4.5.2022
 */
public class Mordor implements IRegion {

    /**
     * Implementovaná metóda getPopisRegionu vypíše informácie o regióne Mordor.
     */
    @Override
    public void getPopisRegionu() {
        System.out.print("""
                Mordor ríšou a základňou zlého Saurona. Leží na východ od Gondoru a veľkej rieky Anduin a na juh od Mirkwoodu. Je to región pradávneho nepriateľa Saurona.
                Skladá sa zo 4 území: Black Gate, Barad-dur, Mount Doom a Minas Morgul.
                Black Gate je veľká čierna brána, ktorá chráni región Mordor, bola vybudovaná v Druhom veku.
                Barad-dur je najväčšou pevnosťou v Stredozemi. Pôvodne bol postavený v Druhom veku a zrovnaný so zemou po Sauronovej porážke vo Vojne poslednej aliancie. Sauron nechal Barad-dûr prestavať neskoro v Treťom veku.
                Mount Doom je sopka v Mordore, kde bol vytvorený Jeden prsteň, a je to jediné miesto, kde môže byť zničený.
                Minas Morgul bola kedysi pevnosťou Gondoru, nazývaná Minas Ithil, Mesačná veža. Minas Ithil ako najvýchodnejšia pevnosť v kráľovstve Gondor chránila východné hranice ríše a chránila hlavné mesto Osgiliath pred silami Mordoru počas ranej fázy Tretieho veku.
                Ako Gondor postupne zoslaboval, pevnosť bola prebratá Mordorom.
                
                """);
    }

    /**
     * Implementovaný getter názvu regiónu.
     *
     * @return Mordor.
     */
    @Override
    public String getNazov() {
        return "Mordor";
    }
}
