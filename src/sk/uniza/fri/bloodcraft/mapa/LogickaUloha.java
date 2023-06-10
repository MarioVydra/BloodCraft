package sk.uniza.fri.bloodcraft.mapa;

import java.util.Scanner;

/**
 * Trieda LogickaUloha implementovaná interfacom IUloha reprezentuje logické úlohy.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public class LogickaUloha implements IUloha {
    private final int uloha;
    private int pocetPokusov;
    private final String odpoved;
    private boolean splnena;

    /**
     * Konštruktor inicializuje číslo danej úlohy, priradí jej odpoveď a určí počet pokusov.
     *
     * @param uloha int.
     * @param odpoved String.
     * @param pocetPokusov int.
     */
    public LogickaUloha(int uloha, String odpoved, int pocetPokusov) {
        this.uloha = uloha;
        this.odpoved = odpoved;
        this.pocetPokusov = pocetPokusov;
        this.splnena = false;
    }

    /**
     * Implementovaná metóda vypisSa na základe čisla úlohy vypíše hádanku, na ktorú hráč musí odpovedať.
     * Ak hráč minie všetky svoje pokusy, úlohu už nebude môcť nikdy splniť.
     */
    @Override
    public void vypisSa() {
        if (!this.splnena) {
            if (this.pocetPokusov != 0) {
                Scanner skener = new Scanner(System.in);
                System.out.println("Na otvorenie chestky, musíš uhádnuť danú hádanku. Dávaj však pozor máš len nasledovný počet pokusov: " + this.pocetPokusov);
                System.out.println("Odpovede píš malými písmenami bez medzier a diakritiky.");
                System.out.println("Ak nechceš teraz plniť úlohu, napíš 'ujdi'.");
                switch (this.uloha) {
                    case 1 -> System.out.println("Zo začiatku to chodí po 4, na obed to chodí po 2 a večer to chodí po 3. ...Čo je to?");
                    case 2 -> System.out.println("Bez kľúča a veka schránky oválnej, ale predsa je zlatý poklad v nej. ...Čo je to?");
                    case 3 -> System.out.println("""
                            Tridsať bielych koní
                            na červenej stráni
                            zubiska vyceria, zdupocú kopytami
                            a ticho postoja tak ako vlani. ...Čo je to?
                            """);
                    case 4 -> System.out.println("""
                            Korene v zemi hlboko skrýva,
                            zdá sa, že rastie, no nie je živá,
                            z vysoka pozerá na vrchovce jedlí,
                            ako pyšný obor nad krajinou bedlí. ...Čo je to?
                            """);
                    case 5 -> System.out.println("""
                            Všetko žerie, všetko sa v ňom stráca, stromy, kvety, zvieratá aj vtáky;
                            hryzie kov aj pláty z ocele, tvrdý kameň na prach zomelie;
                            mestá rozvalí a kráľa skolí, vysokánske hory zvrhne do údolia. ...Čo je to?
                            """);
                }
                this.vypisNapovedu();
                String odpoved = skener.nextLine();
                if (odpoved.equals(this.odpoved)) {
                    System.out.println("Výborne, uhádol si. Splnil si úlohu. Môžeš otvoriť chestku.");
                    this.splnena = true;
                } else if (odpoved.equals("ujdi")){
                    System.out.println("Opustil si úlohu. Môžeš sa k nej vrátit neskôr.");
                } else {
                    if (this.pocetPokusov == 0) {
                        System.out.println("Vyčerpal si všetky pokusy.");
                    } else {
                        this.pocetPokusov--;
                        this.vypisSa();
                    }
                }
            } else {
                System.out.println("Danú úlohu už nemôžeš splniť. Vyčerpal si všetky pokusy.");
            }
        } else {
            System.out.println("Úlohu si už splnil.");
        }
    }

    /**
     * Privátna metóda vypisNapovedu vypíše podľa počtu zostávajúcich pokusov nápovedu.
     */
    private void vypisNapovedu() {
        if (this.pocetPokusov == 2) {
            System.out.println("Začiatočné písmeno odpovedi je: " + this.odpoved.charAt(0));
        } else if(this.pocetPokusov == 1) {
            System.out.println("Prvé dve písmená odpovede sú: " + this.odpoved.charAt(0) + this.odpoved.charAt(1));
        }
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
}
