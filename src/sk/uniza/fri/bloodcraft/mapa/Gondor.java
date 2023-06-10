package sk.uniza.fri.bloodcraft.mapa;

/**
 * Trieda Gondor implementovaná interfacom IRegion reprezentuje región Gondor.
 *
 * @author Mário Vydra
 * @version 4.5.2022
 */
public class Gondor implements IRegion {

    /**
     * Implementovaná metóda getPopisRegionu vypíše informácie o regióne Gondor.
     */
    @Override
    public void getPopisRegionu() {
        System.out.print("""
                Gondor bol najvýznamnejším kráľovstvom ľudí v Stredozemi, ohraničený Rohanom na severe a Mordorom na východe.
                Skladá sa zo 4 území: Minas Tirith, Ithilien, Osgiliath a Cair Andros.
                Minas Tirith je hlavné mesto Gondoru. Je sídlom kráľa Aragorna.
                Osgiliath bolo kedysi hlavné mesto Gondoru. Počas Vojny o Prsteň nadobudlo opustené mesto strategický význam ako prechod cez rieku Anduin, a to pre mužov z Gondoru aj pre orkov z Mordoru.
                Ithilien je územie a léno Gondoru hraničiace s Mordorom vo východnej Stredozemi.
                Bola to najvýchodnejšia provincia Gondoru, jediná oblasť kráľovstva, ktorá ležala medzi riekou Anduin a Ephel Dúath. Je rozdelený prúdom Morgulduin na severný a južný Ithilien.
                Cair Andros je ostrov v rieke Anduin, ležiaci takmer štyridsať míľ severne od Osgiliath. Pre Gondor mal prvoradý význam počas dlhej vojny s Mordorom.
                
                """);
    }

    /**
     * Implementovaný getter názvu regiónu.
     *
     * @return Gondor.
     */
    @Override
    public String getNazov() {
        return "Gondor";
    }
}
