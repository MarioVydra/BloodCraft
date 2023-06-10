package sk.uniza.fri.bloodcraft.mapa;

import sk.uniza.fri.bloodcraft.hra.Postava;
import sk.uniza.fri.bloodcraft.predmety.Brnenie;
import sk.uniza.fri.bloodcraft.predmety.Helma;
import sk.uniza.fri.bloodcraft.predmety.Jedlo;
import sk.uniza.fri.bloodcraft.predmety.Kamen;
import sk.uniza.fri.bloodcraft.predmety.Lektvar;
import sk.uniza.fri.bloodcraft.predmety.Nohavice;
import sk.uniza.fri.bloodcraft.predmety.PrstenMoci;
import sk.uniza.fri.bloodcraft.predmety.QuestPredmet;
import sk.uniza.fri.bloodcraft.predmety.Topanky;
import sk.uniza.fri.bloodcraft.predmety.VysavacDusi;
import sk.uniza.fri.bloodcraft.predmety.Zbran;

import java.util.ArrayList;


/**
 *  Trieda Mapa reprezentuje mapu danej hry.
 *  Mapa sa skladá z regiónov, území, bossov, chestiek, úloh a predmetov.
 *
 * @author Mário Vydra
 * @version 27.4.2022
 */
public class Mapa {
    private final Uzemie start;
    private final ArrayList<Boss> zoznamBossov;

    /**
     * Konštruktor si vytvorí inštancie území, bossov, predmetov, regiónov, úloh a chestiek.
     * Do území priradí bossov, regióny a vloží do nich vytvorené predmety a chestky s priradenými úlohami.
     * Taktiež si inicializuje zoznam bossov a štartovacie územie.
     */
    public Mapa() {
        Eriador eriador = new Eriador();
        Gondor gondor = new Gondor();
        Mordor mordor = new Mordor();
        Rhovanion rhovanion = new Rhovanion();
        Rohan rohan = new Rohan();

        Boss nazgul = new Boss("Nazgul", 100, 20, 30);
        Boss witchKing = new Boss("Witch-king of Angmar", 150, 30, 35);
        Boss sauron = new Boss("Sauron", 200, 50, 30);
        Boss troll = new Boss("Olog-hai", 300, 50, 10);
        Boss saruman = new Boss("Saruman", 100, 50, 20);
        Boss balrog = new Boss("Balrog", 500, 30, 10);
        Boss smaug = new Boss("Smaug", 400, 30, 30);

        this.zoznamBossov = new ArrayList<>();
        this.zoznamBossov.add(nazgul);
        this.zoznamBossov.add(witchKing);
        this.zoznamBossov.add(sauron);
        this.zoznamBossov.add(troll);
        this.zoznamBossov.add(saruman);
        this.zoznamBossov.add(balrog);
        this.zoznamBossov.add(smaug);

        Uzemie minasMorgul = new Uzemie("Minas_Morgul", mordor, witchKing);
        Uzemie blackGate = new Uzemie("Black_Gate", mordor, troll);
        Uzemie baradDur = new Uzemie("Barad-dur", mordor, sauron);
        Uzemie mountDoom = new Uzemie("Mount-Doom", mordor, nazgul);

        Uzemie osgiliath = new Uzemie("Osgiliath", gondor, null);
        Uzemie minasTirith = new Uzemie("Minas_Tirith", gondor, null);
        Uzemie ithilien = new Uzemie("Ithilien", gondor, null);
        Uzemie cairAndros = new Uzemie("Cair_Andros", gondor, null);

        Uzemie helmsDeep = new Uzemie("Helm's Deep", rohan, null);
        Uzemie edoras = new Uzemie("Edoras", rohan, null);
        Uzemie isengard = new Uzemie("Isengard", rohan, saruman);
        Uzemie fangorn = new Uzemie("Fangorn", rohan, null);

        Uzemie kraj = new Uzemie("Kraj", eriador, null);
        Uzemie roklinka = new Uzemie("Roklinka", eriador, null);
        Uzemie moria = new Uzemie("Moria", eriador, balrog);
        Uzemie mistyMountains = new Uzemie("Misty_Mountains", eriador, null);

        Uzemie mirkwood = new Uzemie("Mirkwood", rhovanion, null);
        Uzemie dolGuldur = new Uzemie("Dol_Guldur", rhovanion, null);
        Uzemie erebor = new Uzemie("Erebor", rhovanion, smaug);
        Uzemie lothlorien = new Uzemie("Lothlórien", rhovanion, null);

        minasMorgul.pridajSusedneUzemia("Black_Gate", blackGate);
        minasMorgul.pridajSusedneUzemia("Barad-dur", baradDur);
        minasMorgul.pridajSusedneUzemia("Mount-Doom", mountDoom);
        minasMorgul.pridajSusedneUzemia("Osgiliath", osgiliath);
        blackGate.pridajSusedneUzemia("Minas_Morgul", minasMorgul);
        blackGate.pridajSusedneUzemia("Barad-dur", baradDur);
        blackGate.pridajSusedneUzemia("Mount-Doom", mountDoom);
        blackGate.pridajSusedneUzemia("Mirkwood", mirkwood);
        blackGate.pridajSusedneUzemia("Cair_Andros", cairAndros);
        baradDur.pridajSusedneUzemia("Black_Gate", blackGate);
        baradDur.pridajSusedneUzemia("Mount-Doom", mountDoom);
        baradDur.pridajSusedneUzemia("Minas_Morgul", minasMorgul);
        mountDoom.pridajSusedneUzemia("Minas_Morgul", minasMorgul);
        mountDoom.pridajSusedneUzemia("Black_Gate", blackGate);
        mountDoom.pridajSusedneUzemia("Barad-dur", baradDur);

        ithilien.pridajSusedneUzemia("Osgiliath", osgiliath);
        ithilien.pridajSusedneUzemia("Cair_Andros", cairAndros);
        osgiliath.pridajSusedneUzemia("Minas_Morgul", minasMorgul);
        osgiliath.pridajSusedneUzemia("Minas_Tirith", minasTirith);
        osgiliath.pridajSusedneUzemia("Ithilien", ithilien);
        minasTirith.pridajSusedneUzemia("Osgiliath", osgiliath);
        cairAndros.pridajSusedneUzemia("Ithilien", ithilien);
        cairAndros.pridajSusedneUzemia("Black_Gate", blackGate);
        cairAndros.pridajSusedneUzemia("Edoras", edoras);

        edoras.pridajSusedneUzemia("Cair_Andros", cairAndros);
        edoras.pridajSusedneUzemia("Helm's_Deep", helmsDeep);
        helmsDeep.pridajSusedneUzemia("Edoras", edoras);
        helmsDeep.pridajSusedneUzemia("Isengard", isengard);
        isengard.pridajSusedneUzemia("Helm's_Deep", helmsDeep);
        isengard.pridajSusedneUzemia("Fangorn", fangorn);
        isengard.pridajSusedneUzemia("Roklinka", roklinka);
        fangorn.pridajSusedneUzemia("Lothlórien", lothlorien);
        fangorn.pridajSusedneUzemia("Isengard", isengard);

        kraj.pridajSusedneUzemia("Roklinka", roklinka);
        roklinka.pridajSusedneUzemia("Moria", moria);
        roklinka.pridajSusedneUzemia("Kraj", kraj);
        roklinka.pridajSusedneUzemia("Misty_Mountains", mistyMountains);
        roklinka.pridajSusedneUzemia("Isengard", isengard);
        moria.pridajSusedneUzemia("Roklinka", roklinka);
        moria.pridajSusedneUzemia("Misty_Mountains", mistyMountains);
        moria.pridajSusedneUzemia("Lothlórien", lothlorien);
        mistyMountains.pridajSusedneUzemia("Roklinka", roklinka);
        mistyMountains.pridajSusedneUzemia("Mirkwood", mirkwood);
        mistyMountains.pridajSusedneUzemia("Lothlórien", lothlorien);
        mistyMountains.pridajSusedneUzemia("Moria", moria);

        mirkwood.pridajSusedneUzemia("Black_Gate", blackGate);
        mirkwood.pridajSusedneUzemia("Misty_Mountains", mistyMountains);
        mirkwood.pridajSusedneUzemia("Lothlórien", lothlorien);
        mirkwood.pridajSusedneUzemia("Erebor", erebor);
        mirkwood.pridajSusedneUzemia("Dol_Guldur", dolGuldur);
        lothlorien.pridajSusedneUzemia("Fangorn", fangorn);
        lothlorien.pridajSusedneUzemia("Mirkwood", mirkwood);
        lothlorien.pridajSusedneUzemia("Moria", moria);
        lothlorien.pridajSusedneUzemia("Misty_Mountains", mistyMountains);
        dolGuldur.pridajSusedneUzemia("Mirkwood", mirkwood);
        erebor.pridajSusedneUzemia("Mirkwood", mirkwood);

        QuestPredmet questPredmet1 = new QuestPredmet("Gaštan");
        lothlorien.pridajPredmet(questPredmet1);
        QuestPredmet questPredmet2 = new QuestPredmet("Arcikam");
        cairAndros.pridajPredmet(questPredmet2);
        QuestPredmet questPredmet3 = new QuestPredmet("Galadrielino_zrkadlo");
        roklinka.pridajPredmet(questPredmet3);
        QuestPredmet questPredmet4 = new QuestPredmet("Palantír");
        isengard.pridajPredmet(questPredmet4);
        QuestPredmet questPredmet5 = new QuestPredmet("Záznamy_prastarej_bitky");
        dolGuldur.pridajPredmet(questPredmet5);

        LogickaUloha uloha1 = new LogickaUloha(1, "clovek", 3);
        LogickaUloha uloha2 = new LogickaUloha(2, "vajco", 4);
        LogickaUloha uloha3 = new LogickaUloha(3, "zuby", 5);
        LogickaUloha uloha4 = new LogickaUloha(4, "hora", 5);
        LogickaUloha uloha5 = new LogickaUloha(5, "cas", 6);

        BojovaUloha uloha6 = new BojovaUloha("Príchod_goblinov_zo_severu", "Goblini", 10, 5);
        BojovaUloha uloha7 = new BojovaUloha("Isengardský_Uruk-Hai", "Uruk-Hai", 5, 10);
        BojovaUloha uloha8 = new BojovaUloha("Návrat_orkov_do_Mordoru", "Orkovia", 10, 7);
        BojovaUloha uloha9 = new BojovaUloha("Vrci_z_Gundabadu", "Vrci", 3, 13);
        BojovaUloha uloha10 = new BojovaUloha("Viacnohé_zlo", "Pavúci", 7, 6);

        CestovatelskaUloha uloha11 = new CestovatelskaUloha("Sadenie_v_Bilbovej_záhrade", questPredmet1, kraj);
        CestovatelskaUloha uloha12 = new CestovatelskaUloha("Vrátenie_dedičstva_trpaslíkov", questPredmet2, erebor);
        CestovatelskaUloha uloha13 = new CestovatelskaUloha("Stratené_zrkadlo", questPredmet3, lothlorien);
        CestovatelskaUloha uloha14 = new CestovatelskaUloha("Krištáľová_guľa", questPredmet4, minasTirith);
        CestovatelskaUloha uloha15 = new CestovatelskaUloha("Nezabúdaj_na_históriu", questPredmet5, helmsDeep);

        Chestka chestka1 = new Chestka("Chestka_Strieborných_Lyžíc", new Zbran("mecZihadlo", Postava.BOJOVNIK), uloha11);
        chestka1.pridajBeznePredmety(new Jedlo("pomaranc", 10, 5), new Brnenie("kozenaVesta"));
        Chestka chestka2 = new Chestka("Elrondova_Chestka", new Zbran("radagastovaPalica", Postava.KUZELNIK), uloha1);
        chestka2.pridajBeznePredmety(new Jedlo("maso", 20, 10), new Lektvar("StrednyLiecivyLektvar", 0));
        Chestka chestka3 = new Chestka("Stará_Goblinská_chestka", new Zbran("preciznyLuk", Postava.LOVEC), uloha6);
        chestka3.pridajBeznePredmety(new Jedlo("flasa", 5, 5), new Brnenie("kozenaVesta"));
        Chestka chestka4 = new Chestka("Theódenova_Chestka", new VysavacDusi(), uloha7);
        chestka4.pridajBeznePredmety(new Jedlo("chlieb", 15, 5), new Lektvar("VelkyLiecivyLektvar", 0));
        Chestka chestka5 = new Chestka("Skrytá_Chesta", new Helma("ocelovaHelma"), uloha15);
        chestka5.pridajBeznePredmety(new Jedlo("mrkva", 10, 10), new Lektvar("MalyLiecivyLektvar", 0));
        Chestka chestka6 = new Chestka("Entská_Zbierka", new Brnenie("oceloveBrnenie"), uloha4);
        chestka6.pridajBeznePredmety(new Lektvar("VelkyLektvarObratnosti", 10), new Lektvar("StrednyPosilnovacDamagu", 7));
        Chestka chestka7 = new Chestka("Chestka_Temného_Lesa", new Zbran("legolasovLuk", Postava.LOVEC), uloha9);
        chestka7.pridajBeznePredmety(new Jedlo("chlieb", 10, 5), new Lektvar("StrednyPosilnovacDamagu", 8));
        Chestka chestka8 = new Chestka("Chestka_Pani_Galadriel", new VysavacDusi(), uloha13);
        chestka8.pridajBeznePredmety(new Topanky("pevneKozeneBoty"), new Lektvar("VelkeTekuteStastie", 10));
        Chestka chestka9 = new Chestka("Zaprášená_Chestka", new Zbran("radagastovaPalica", Postava.KUZELNIK), uloha10);
        chestka9.pridajBeznePredmety(new Helma("zhrdzavenaHelma"), new Zbran("preciznyLuk", Postava.LOVEC));
        Chestka chestka10 = new Chestka("Durinov_Poklad", new VysavacDusi(), uloha12);
        chestka10.pridajBeznePredmety(new Lektvar("MalyLektvarObratnosti", 4), new Lektvar("StrednyLiecivyLektvar", 0));
        Chestka chestka11 = new Chestka("Faramirova_Chestka", new Topanky("oceloveBoty"), uloha2);
        chestka11.pridajBeznePredmety(new Lektvar("VelkyLiecivyLektvar", 0), new Helma("kozenaPrilba"));
        Chestka chestka12 = new Chestka("Mestská_Chestka", new Nohavice("oceloveNohavice"), uloha5);
        chestka12.pridajBeznePredmety(new Jedlo("maso", 15, 10), new Zbran("palicaModrehoCarodeja", Postava.KUZELNIK));
        Chestka chestka13 = new Chestka("Chestka_Bieleho_Mesta", new Zbran("aragornovMec", Postava.BOJOVNIK), uloha14);
        chestka13.pridajBeznePredmety(new Zbran("loveckyLuk", Postava.LOVEC), new Zbran("rohanskyMec", Postava.BOJOVNIK));
        Chestka chestka14 = new Chestka("Mýtická_Chestka", new VysavacDusi(), uloha3);
        chestka14.pridajBeznePredmety(new Lektvar("MaleTekuteStastie", 4), new Jedlo("mrkva", 5, 10));
        Chestka chestka15 = new Chestka("Chestka_Čiernokňažníka", new Zbran("gandalfovaPalica", Postava.KUZELNIK), uloha8);
        chestka15.pridajBeznePredmety(new Nohavice("kozeneNohavice"), new Brnenie("dotrhanaKosela"));

        kraj.pridajChestku(chestka1);
        roklinka.pridajChestku(chestka2);
        mistyMountains.pridajChestku(chestka3);
        edoras.pridajChestku(chestka4);
        helmsDeep.pridajChestku(chestka5);
        fangorn.pridajChestku(chestka6);
        mirkwood.pridajChestku(chestka7);
        lothlorien.pridajChestku(chestka8);
        dolGuldur.pridajChestku(chestka9);
        erebor.pridajChestku(chestka10);
        ithilien.pridajChestku(chestka11);
        osgiliath.pridajChestku(chestka12);
        minasTirith.pridajChestku(chestka13);
        cairAndros.pridajChestku(chestka14);
        minasMorgul.pridajChestku(chestka15);

        kraj.pridajPredmet(new PrstenMoci());
        kraj.pridajPredmet(new Jedlo("chlieb", 10, 10));
        kraj.pridajPredmet(new Brnenie("dotrhanaKosela"));
        kraj.pridajPredmet(new Kamen());
        roklinka.pridajPredmet(new Zbran("bojovyLuk", Postava.LOVEC));
        roklinka.pridajPredmet(new Lektvar("StrednyLiecivyLektvar", 7));
        roklinka.pridajPredmet(new Topanky("rozpadnuteBoty"));
        roklinka.pridajPredmet(new Jedlo("elfskyChlieb", 20, 10));
        moria.pridajPredmet(new Helma("kozenaPrilba"));
        moria.pridajPredmet(new Lektvar("MalyPosilnovacDamagu", 4));
        moria.pridajPredmet(new Zbran("palicaModrehoCarodeja", Postava.KUZELNIK));
        mistyMountains.pridajPredmet(new Nohavice("dotrhaneNohavice"));
        mistyMountains.pridajPredmet(new Jedlo("jablko", 10, 10));
        mistyMountains.pridajPredmet(new Zbran("rohanskyMec", Postava.BOJOVNIK));
        mistyMountains.pridajPredmet(new Kamen());

        lothlorien.pridajPredmet(new Jedlo("mrkva", 10, 10));
        lothlorien.pridajPredmet(new Zbran("loveckyLuk", Postava.LOVEC));
        lothlorien.pridajPredmet(new Lektvar("StredneTekuteStastie", 8));
        lothlorien.pridajPredmet(new Jedlo("elfskyChlieb", 20, 10));
        mirkwood.pridajPredmet(new Lektvar("StrednyPosilnovacDamagu", 7));
        mirkwood.pridajPredmet(new Brnenie("kozenaVesta"));
        mirkwood.pridajPredmet(new Helma("zhrdzavenaHelma"));
        dolGuldur.pridajPredmet(new Lektvar("MalyLektvarObratnosti", 3));
        dolGuldur.pridajPredmet(new Lektvar("StrednyLiecivyLektvar", 0));
        dolGuldur.pridajPredmet(new Topanky("pevneKozeneBoty"));
        erebor.pridajPredmet(new Brnenie("mithrilBrnenie"));
        erebor.pridajPredmet(new Zbran("palicaModrehoCarodeja", Postava.KUZELNIK));
        erebor.pridajPredmet(new Jedlo("flasa", 10, 5));
        erebor.pridajPredmet(new Kamen());

        isengard.pridajPredmet(new Zbran("sarumanovaPalica", Postava.KUZELNIK));
        isengard.pridajPredmet(new Lektvar("StrednyLiecivyLektvar", 0));
        isengard.pridajPredmet(new Jedlo("maso", 20, 10));
        helmsDeep.pridajPredmet(new Jedlo("pomaranc", 10, 15));
        helmsDeep.pridajPredmet(new Jedlo("chlieb", 15, 5));
        helmsDeep.pridajPredmet(new Zbran("bojovyLuk", Postava.LOVEC));
        edoras.pridajPredmet(new Zbran("rohanskyMec", Postava.BOJOVNIK));
        edoras.pridajPredmet(new Helma("zhrdzavenaHelma"));
        edoras.pridajPredmet(new Jedlo("jablko", 10, 12));
        fangorn.pridajPredmet(new Kamen());
        fangorn.pridajPredmet(new Lektvar("MalyLiecivyLektvar", 0));
        fangorn.pridajPredmet(new Zbran("loveckyLuk", Postava.LOVEC));

        cairAndros.pridajPredmet(new Lektvar("MalyLektvarObratnosti", 3));
        cairAndros.pridajPredmet(new Jedlo("kolac", 15, 10));
        cairAndros.pridajPredmet(new Zbran("preciznyLuk", Postava.LOVEC));
        minasTirith.pridajPredmet(new Zbran("gondorskyBojovyMec", Postava.BOJOVNIK));
        minasTirith.pridajPredmet(new Jedlo("kolac", 12, 8));
        minasTirith.pridajPredmet(new Topanky("pevneKozeneBoty"));
        osgiliath.pridajPredmet(new Brnenie("kozenaVesta"));
        osgiliath.pridajPredmet(new Lektvar("MalyLiecivyLektvar", 0));
        osgiliath.pridajPredmet(new Jedlo("flasa", 10, 5));
        ithilien.pridajPredmet(new Jedlo("jablko", 10, 12));
        ithilien.pridajPredmet(new Jedlo("elfskyChlieb", 15, 15));
        ithilien.pridajPredmet(new Lektvar("StrednyLektvarObratnosti", 7));

        blackGate.pridajPredmet(new Jedlo("cervavyChlieb", 10, 10));
        blackGate.pridajPredmet(new Jedlo("zhniteMaso", 20, 15));
        blackGate.pridajPredmet(new Zbran("tupyMec", Postava.BOJOVNIK));
        mountDoom.pridajPredmet(new Helma("zhrdzavenaHelma"));
        mountDoom.pridajPredmet(new Lektvar("MaleTekuteStastie", 4));
        mountDoom.pridajPredmet(new Kamen());
        minasMorgul.pridajPredmet(new Jedlo("cervavyChlieb", 15, 12));
        minasMorgul.pridajPredmet(new Jedlo("zhniteMaso", 16, 14));
        minasMorgul.pridajPredmet(new Lektvar("VelkyLiecivyLektvar", 0));
        baradDur.pridajPredmet(new Jedlo("cervavyChlieb", 18, 15));
        baradDur.pridajPredmet(new Jedlo("zhniteMaso", 25, 20));
        baradDur.pridajPredmet(new Zbran("palicaModrehoCarodeja", Postava.KUZELNIK));

        this.start = kraj;
    }

    /**
     * Getter atribútu start.
     *
     * @return štartovacie územie.
     */
    public Uzemie getStart() {
        return this.start;
    }

    /**
     * Metóda prehladajBossov prehľadáva arraylist bossov.
     *
     * @return true - ak každý boss má hodnotu null, false - ak aspoň jeden boss nemá priradenú hodnotu null.
     */
    public boolean prehladajBossov() {
        for (Boss boss : this.zoznamBossov) {
            if (boss != null) {
                return false;
            }
        }
        return true;
    }
}
