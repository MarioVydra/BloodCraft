package sk.uniza.fri.bloodcraft.mapa;

/**
 * Trieda Rohan implementovaná interfacom IRegion reprezentuje región Rohan.
 *
 * @author Mário Vydra
 * @version 4.5.2022
 */
public class Rohan implements IRegion {

    /**
     * Implementovaná metóda getPopisRegionu vypíše informácie o regióne Rohan.
     */
    @Override
    public void getPopisRegionu() {
        System.out.print("""
                Rohan je domovom pánov koní. Nachádza sa medzi Gondorom, Rhovanionom a Eriadorom. Skladá sa zo 4 území: Helm's Deep, Edoras, Isengard a Fangorn.
                Helm's Deep je opevnená roklina v Bielych Horách. Pevnosť je preslávená bitkou o Hornburg.
                Edoras je hlavné mesto Rohanu. Je to sídlo kráľa Theodena. Pred bránami mesta sa nachádzajú hroby rohanských kráľov.
                Isengard je veľká pevnosť v Nan Curunír, Čarodejníkovom údolí, v západnej časti Rohanu. Je to sídlo spojenca Saurona, čarodejníka Sarumana.
                Fangorn je les pod juhovýchodnými Misty Mountains. Je známy tým, že je posledným sídlom Entov (stromy, ktoré vedeli rozprávať, rozmýšľať a chodiť).
                                
                """);
    }

    /**
     * Implementovaný getter názvu regiónu.
     *
     * @return Rohan.
     */
    @Override
    public String getNazov() {
        return "Rohan";
    }
}
