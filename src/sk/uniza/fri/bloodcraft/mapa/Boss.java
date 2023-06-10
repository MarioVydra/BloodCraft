package sk.uniza.fri.bloodcraft.mapa;

import sk.uniza.fri.bloodcraft.hra.Hrac;

import java.util.Random;

/**
 * Trieda Boss reprezentuje daného bossa, ktorý má rôzne atribúty
 * Boss vie zaútočiť na hráča.
 *
 * @author Mário Vydra
 * @version 29.4.2022
 */
public class Boss {
    private double hp;
    private final double damage;
    private final double obratnost;
    private final String meno;
    private final Random random;

    /**
     * Konšturktor inicializuje meno bossa a jednotlivé atribúty bossa (hp, damage, obratnost).
     *
     * @param meno String.
     * @param hp double.
     * @param dmg double.
     * @param obratnost double.
     */
    public Boss(String meno, double hp, double dmg, double obratnost) {
        this.meno = meno;
        this.hp = hp;
        this.damage = dmg;
        this.obratnost = obratnost;
        this.random = new Random();
    }

    /**
     * Metóda utok spôsobí hráčovi damage, čím sa mu znížia hp.
     * Hráč sa môže na základe jeho obratnosti aj vyhnúť útoku, prípadne dostať menšie poškodenie vďaka veľkosti jeho brnenia.
     *
     * @param hrac referencia na hráča.
     */
    public void utok(Hrac hrac) {
        int pomer = (int)(hrac.getObratnost() / 10);
        if (this.random.nextInt(pomer) == 0) {
            hrac.znizHP(this.damage - (0.3 * (hrac.getBrnenie())));
        } else {
            System.out.println("Vyhol si sa útoku.");
        }
    }

    /**
     * Getter atribútu obratnost.
     *
     * @return obratnosť bossa.
     */
    public double getObratnost() {
        return this.obratnost;
    }

    /**
     * Metóda zomrel vracia boolean hodnotu na záklde hp bossa.
     *
     * @return true - ak boss má menej nanajvýš 0 hp, false - ak boss má viac ako 0 hp.
     */
    public boolean zomrel() {
        return this.hp <= 0;
    }

    /**
     * Metóda znizHp zníži bossovi počet životov o hodnotu danú v parametri.
     * Metóda sa využíva napríklad, keď hráč na daného bossa zaútočí.
     *
     * @param damage double.
     */
    public void znizHp(double damage) {
        if (this.hp - damage < 0) {
            this.hp = 0;
            System.out.println(this.meno + "-ovi sa znížili životy o " + (damage - this.hp) + ". Aktuálne má " + this.hp + " HP.");
        } else {
            this.hp -= damage;
            System.out.println(this.meno + "-ovi sa znížili životy o " + damage + ". Aktuálne má " + this.hp + " HP.");
        }
    }

    /**
     * Getter atribútu meno.
     *
     * @return meno daného bossa.
     */
    public String getMeno() {
        return this.meno;
    }

    /**
     * Getter atribútu hp.
     *
     * @return hp (životy) bossa.
     */
    public double getHp() {
        return this.hp;
    }
}
