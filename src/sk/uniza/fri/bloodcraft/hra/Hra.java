package sk.uniza.fri.bloodcraft.hra;

import sk.uniza.fri.bloodcraft.mapa.BojovaUloha;
import sk.uniza.fri.bloodcraft.mapa.Boss;
import sk.uniza.fri.bloodcraft.mapa.Mapa;
import sk.uniza.fri.bloodcraft.predmety.Lektvar;
import sk.uniza.fri.bloodcraft.prikazy.CitacPrikazu;
import sk.uniza.fri.bloodcraft.prikazy.Prikaz;

import java.util.*;

/**
 * Trieda Hra reprezentuje hru BloodCraft.
 * Vytvorí si jednotlivé komponenty.
 *
 * @author Mário Vydra
 * @version 27.4.2022
 */
public class Hra {
    private Hrac hrac;
    private final Mapa mapa;
    private final CitacPrikazu citac;
    private boolean jeKoniec;
    private int pocitadloNeviditelnosti;
    private boolean vyhranaHra;

    /**
     * Konštruktor si inicializuje jednotlivé atribúty a vytvorí inštancie komponentov.
     * Hráčovi sa vypíše privítanie a v úvode hry si vyberie typ postavy, za ktorú chce hrať.
     */
    public Hra() {
        this.vyhranaHra = false;
        this.privitanie();
        this.mapa = new Mapa();
        this.vypisPostav();
        this.vyberPostavy();
        System.out.print("Aktuálne sa nachádzaš v regióne Eriador. ");
        this.hrac.getKdeSom().getRegion().getPopisRegionu();
        System.out.println("""
                Ak si nebudeš vedieť dať rady. Tak napíš 'pomoc'.
                Na výber máš tieto príkazy: ukonci, chod, pomoc, inventar, pouzi, hladaj, zahod, zober, staty, zbroj, zautoc, otvor, uloha
                """);
        this.hrac.getKdeSom().getInfo();
        this.citac = new CitacPrikazu();
        this.jeKoniec = false;
    }

    /**
     * Metóda hraj je tvorená cyklusom, ktorý sa skončí až keď sa skončí hra.
     * V cykle sa načítavajú príkazy zadané hráčom, ktoré píše do terminálu.
     * Kontroluje sa v ňom napríklad, či hráč vyhral alebo prehral.
     * Taktiež sa v metóde každým prejdením cyklu zníži trvanie neviditeľnosti hráča a aktivovaných lektvarov (ak je niečo z toho aktivované).
     */
    public void hraj() {
        while (!this.jeKoniec) {
            this.trvanieAktivovanychLektvarov();
            this.trvanieNeviditelnosti();
            Prikaz prikaz = this.citac.nacitajPrikaz();
            this.vykonajPrikaz(prikaz);
            this.prehralSi();
            this.vyhralSi();
        }
    }

    /**
     * Privátna metóda vypisPostav vypíše informácie o typoch postáv, ktoré si hráč môže vybrať.
     */
    private void vypisPostav() {
        System.out.print("""
                Zvoľ si prosím postavu, za ktorú chceš hrať. Pevne však porozmýšľaj! Svoj výber si nebudeš môcť zmeniť.
                Na výber máš 3 typy postáv:
                Bojovník má zvýšený damage - pre výber bojovníka napíš 'BOJOVNIK'
                Lovec má väčšiu šancu vyhnutiu sa útoku - pre výber lovca napíš 'LOVEC'
                Kúzelník má väčšie šťastie na drop itemu z chestky - pre výber kúzelníka napíš 'KUZELNIK'
                """);
    }

    /**
     * Privátna metóda vyberPostavy cez skener kontroluje výber hráča. Ak hráč zadal neplatnú postavu, metóda sa zopakuje.
     */
    private void vyberPostavy() {
        Scanner skener = new Scanner(System.in);
        String vyber = skener.nextLine();
        switch (vyber) {
            case "BOJOVNIK" -> this.hrac = new Hrac(this.mapa.getStart(), Postava.BOJOVNIK);
            case "LOVEC" -> this.hrac = new Hrac(this.mapa.getStart(), Postava.LOVEC);
            case "KUZELNIK" -> this.hrac = new Hrac(this.mapa.getStart(), Postava.KUZELNIK);
            default -> {
                System.out.println("Zadal si nesprávnu postavu, prosím oprav sa.");
                this.vyberPostavy();
            }
        }
    }

    /**
     * Privátna metóda privitanie vypíše privítanie pre hráča.
     */
    private void privitanie() {
        System.out.print("""
                Vitaj v hre BloodCraft od študenta Mária Vydru.
                Názvy regiónov a území sú prebraté z mytológie J.R.R.Tolkiena.
                Tvojou úlohou je pozabíjať všetkých nepriateľov. Ak sa ti to podarí, hru si vyhral.
                Inventár si môžeš doplniť z vecí, ktoré nájdeš na danom území alebo z chestky, ktorá sa nachádza každá v jednom území.
                Keď chceš otvoriť nejakú chestku, musíš najskôr splniť jej úlohu. ... Prajem ti veľa šťastia. :-)
                
                """);
    }

    /**
     * Privátna metóda vyhralSi kontroluje, či hráč vyhral.
     * Ak hráč vyhral (zabil všetkých bossov), vypíše sa mu gratulácia a môže sa rozhodnúť, či chce vypnúť hru alebo pokračovať v hre.
     */
    private void vyhralSi() {
        if (this.vyhranaHra) {
            return;
        }
        if (this.mapa.prehladajBossov()) {
            System.out.println("""
                    Gratulujem zabil si všetkých Bossov a vyhral si hru. Chceš pokračovať v preskúmavaní hry alebo chceš ukončiť aplikáciu?
                    Napíš: 'ukonci' - ak chceš ukončiť hru.
                           'pokracuj' - ak chceš pokračovať v hraní.
                    """);
            this.vyhranaHra = true;
            this.pokracovatAleboSkoncit();
        }
    }

    /**
     * Privátna metóda pokracovatAleboSkoncit načíta cez citac výber hráča, či chce skončiť alebo pokračovať.
     * Ak hráč napíše niečo iné z daných možností, metóda sa zopakuje.
     */
    private void pokracovatAleboSkoncit() {
        Prikaz prikaz = this.citac.nacitajPrikaz();
        if (prikaz.getNazovPrikazu().equals("pokracuj")) {
            System.out.println("Ďakujem za prejdenie hry. <3 Neboj sa, máš toho ešte veľa na preskumávanie.");
        } else if (prikaz.getNazovPrikazu().equals("ukonci")) {
            System.out.println("Ďakujem za prejdenie hry. <3");
            this.vykonajPrikaz(prikaz);
        } else {
            System.out.println("Prosím oprav sa.");
            this.pokracovatAleboSkoncit();
        }
    }

    /**
     * Metóda zacniBojSBossom vytvorí cyklus, ktorý sa skončí, až kým boss nezomrie/kým hráč nezomrie alebo keď hráč ujde.
     * Keď boss zomrie nastaví sa mu hodnota null.
     *
     * @param boss referencia na bossa.
     */
    public void zacniBojSBossom(Boss boss) {
        if (boss == null) {
            return;
        }
        if (!this.hrac.getJeViditelny()) {
            System.out.println(boss.getMeno() + " si ťa nevšimol, môžeš zbierať predmety.");
            return;
        }
        System.out.println(boss.getMeno() + " prichádza! Priprav sa na súboj.");
        System.out.println("""
                Môžeš použiť špeciálny príkaz 'ujdi' ak nechceš bojovať, no nebudeš môcť hľadať predmety v okolí.
                Až keď bossa zabiješ. Môžeš hľadať predmety, prípadne ich zobrať.
                Ak budeš chcieť znova bojovať proti bossovi, zadaj príkaz 'hladaj'.
                """);
        while (!boss.zomrel()) {
            if (this.jeKoniec) {
                break;
            }
            System.out.println("Sprav svoj krok. Dávaj pozor na výber máš len jeden! Potom na teba " + boss.getMeno() + " zaútočí.");
            Prikaz prikaz = this.citac.nacitajPrikaz();
            if (prikaz.getNazovPrikazu() != null && prikaz.getNazovPrikazu().equals("ujdi")) {
                System.out.println("Ušiel si ako zbabelec. " + boss.getMeno() + "-ove HP ostanú v takom štádiu, ako keď si ušiel.");
                break;
            }
            this.vykonajPrikaz(prikaz);
            if (boss.zomrel()) {
                break;
            }
            if (!this.hrac.getJeViditelny()) {
                System.out.println("Stratil si sa bossovi z dohľadu, môžeš zbierať predmety.");
                break;
            }
            boss.utok(this.hrac);
            if (this.hrac.getHp() == 0) {
                break;
            }
        }
        if (boss.zomrel()) {
            System.out.println(boss.getMeno() + " zomrel.");
            this.hrac.getKdeSom().setBossToNull();
        }
    }

    /**
     * Metóda napoveda vypíše do terminálu zoznam možných príkazov a informácie o území, v ktorom sa aktuálne hráč nachádza.
     */
    public void napoveda() {
        System.out.println("Na výber máš tieto príkazy: ukonci, chod, pomoc, inventar, pouzi, hladaj, zahod, zober, staty, zbroj, zautoc, otvor, uloha");
        this.hrac.getKdeSom().getInfo();
    }

    /**
     * Metóda vykonajPrikaz na základe prikazu v parametri vykoná daný príkaz.
     * Ak daný príkaz nepozná, vypíše do terminálu, že sa má hráč opraviť.
     *
     * @param prikaz Prikaz.
     */
    public void vykonajPrikaz(Prikaz prikaz) {
        if (prikaz.neexistujePrikaz()) {
            System.out.println("Zadal si nesprávny príkaz, skús sa opraviť.");
            return;
        }
        switch (prikaz.getNazovPrikazu()) {
            case "ukonci" -> {
                this.jeKoniec = true;
                System.out.println("Vráť sa čo najskôr! <3");
            }
            case "chod" -> {
                this.hrac.chodDoUzemia(prikaz);
                this.zacniBojSBossom(this.hrac.getKdeSom().getBoss());
            }
            case "pomoc" -> this.napoveda();
            case "inventar" -> this.hrac.zobrazInventar();
            case "pouzi" -> this.hrac.pouzi(prikaz);
            case "hladaj" -> this.hrac.hladaj();
            case "zahod" -> this.hrac.zahodPredmet(prikaz);
            case "zober" -> this.hrac.zoberPredmet(prikaz);
            case "staty" -> this.hrac.vypisStaty();
            case "zbroj" -> this.hrac.zobrazZbroj();
            case "zautoc" -> this.hrac.zautoc(this.hrac.getKdeSom().getBoss());
            case "otvor" -> this.hrac.otvorChestku();
            case "uloha" -> {
                this.hrac.vypisUlohu();
                if (this.hrac.getKdeSom().getChestka() != null) {
                    this.zacniBojSNepriatelom(this.hrac.getKdeSom().getChestka().getBojovaUloha());
                }
            }
        }
    }

    /**
     * Privátna metóda prehralSi na základe životov hráča, kontroluje, či má skončiť hru alebo nie.
     */
    private void prehralSi() {
        if (this.hrac.getHp() <= 0) {
            System.out.println("Prehral si. :( Skús to znova opätovným spustením programu.");
            this.jeKoniec = true;
        }
    }

    /**
     * Metóda trvanieNeviditelnosti kontroluje, či je hráč viditeľný alebo nie je.
     * Ak nie je viditeľný tak sa počítadlo zvýši o 1.
     * Ak počítadlo dosiahnie hodnotu 10, neviditeľnosť sa skončí.
     */
    public void trvanieNeviditelnosti() {
        if (!this.hrac.getJeViditelny()) {
            this.pocitadloNeviditelnosti++;
            if (this.pocitadloNeviditelnosti == 10) {
                System.out.println("Skončila ti neviditeľnosť.");
                this.hrac.setJeViditelny();
                this.pocitadloNeviditelnosti = 0;
                this.zacniBojSBossom(this.hrac.getKdeSom().getBoss());
            }
        }
    }

    /**
     * Metóda trvanie aktivovaných lektvarov kontroluje, či hráč aktivoval nejaký lektvar.
     * Ak je nejaký lektvar aktivovaný, zníži sa jeho dĺžka trvania o 1.
     * Ak dĺžka trvania lektvaru je rovná nule, tak sa odstráni.
     */
    public void trvanieAktivovanychLektvarov() {
        HashMap<String, Lektvar> aktivovaneLektvary = this.hrac.getAktivovaneLektvary();
        if (aktivovaneLektvary.isEmpty()) {
            return;
        }
        for (Lektvar lektvar : aktivovaneLektvary.values()) {
            lektvar.znizDlzkuTrvania();
            if (lektvar.getDlzkaTrvania() == 0) {

                lektvar.odstranSa(this.hrac);
            }
        }
    }

    /**
     * Metóda zacniBojSNepriatelom vytvorí cyklus, ktorý sa skončí, až kým nepriatelia nezomrú/kým hráč nezomrie alebo keď hráč ujde.
     * Ak hráč ujde, počet nepriateľov sa vráti do pôvodného stavu.
     *
     * @param uloha referencia na bojovu ulohu.
     */
    public void zacniBojSNepriatelom(BojovaUloha uloha) {
        if (uloha == null) {
            return;
        }
        if (uloha.jeSplnena()) {
            System.out.println("Úlohu si už splnil.");
            return;
        }
        if (!this.hrac.getJeViditelny()) {
            System.out.println("Nemôžeš splniť bojovú úlohu, pokiaľ si neviditeľný.");
            return;
        }
        Random random = new Random();
        int pomer;
        while (uloha.getPocetNepriatelov() != 0) {
            if (this.jeKoniec) {
                    break;
            }
            pomer = (int)this.hrac.getObratnost() / 10;
            System.out.println(uloha.getNazovNepriatela() + " útočia.");
            if (random.nextInt(pomer) == 0) {
                this.hrac.znizHP(uloha.getDamageNepriatela());
            } else {
                System.out.println("Vyhol si sa útoku.");
            }
            if (this.hrac.getHp() == 0) {
                break;
            }
            Prikaz prikaz = this.citac.nacitajPrikaz();
            if (prikaz.getNazovPrikazu() != null && prikaz.getNazovPrikazu().equals("zautoc")) {
                uloha.znizPocetNepriatelov();
            } else if (prikaz.getNazovPrikazu() != null && prikaz.getNazovPrikazu().equals("ujdi")){
                System.out.println("Ušiel si z boja, počet nepriateľov sa vráti do pôvodného stavu.");
                uloha.setPovodnyPocetNepriatelov();
                break;
            } else {
                this.vykonajPrikaz(prikaz);
            }
            if (uloha.getPocetNepriatelov() == 0) {
                System.out.println("Splnil si úlohu " + uloha.getNazovUlohy() + ". Zabil si všetkých nepriateľov, môžeš otvoriť chestku.");
            }
        }
    }

    /*public void uloz() {
        try {
            FileOutputStream fos = new FileOutputStream("ulozenaHra.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
