package sk.uniza.fri.bloodcraft.mapa;

/**
 * Trieda Eriador implementovaná interfacom IRegion reprezentuje región Eriador.
 *
 * @author Mário Vydra
 * @version 4.5.2022
 */
public class Eriador implements IRegion {

    /**
     * Implementovaná metóda getPopisRegionu vypíše informácie o regióne Eriador.
     */
    @Override
    public void getPopisRegionu() {
        System.out.print("""
                Eriador je jedným z najzápadnejších regiónov Stredozeme. Skladá sa zo 4 hlavných území: Kraj, Roklinka, Moria a Misty Mountains.
                V Kraji žijú mieromiluvné stvorenia Hobbiti. Bývajú v domčekoch, postavených v zemi. Architektúra domčekov je taktiež výstižná v tom, že hlavný vchod má okrúhle dvere a všetky okenice majú tiež tvar kruhu.
                Ich pohostinstvo a dobré spôsoby vás vždy milo prekvapia.
                Roklinka je jedným z posledných domácich domovov Elfov. Vládne tam Pán Elrond s dcérou Arwen. Roklinka sa nachádza v údolí pod Misty Mountains.
                Jej prostredie tvoria vodopády tečúce z vrcholkov hôr.
                Moria bola kedysi dávno staré kráľovstvo trpaslíkov. Je postavené pod pohorím Misty Mountains. Teraz však tomuto miestu vládne tieň a plameň, staroveká bytosť Balrog spolu s národom orkov.
                Toto miesto sa stalo hrobkou pre trpaslíkov.
                Misty Mountains je súbor pohorí, ktoré oddeľujú regióny Eriador a Rhovanion. V pohoriach sídlia inteligentné orli. Na tomto území nájdeš však aj národy a kráľovstvá orkov a trollov.

                """);
    }

    /**
     * Implementovaný getter názvu regiónu.
     *
     * @return Eriador.
     */
    @Override
    public String getNazov() {
        return "Eriador";
    }
}
