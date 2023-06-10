package sk.uniza.fri.bloodcraft.predmety;

import sk.uniza.fri.bloodcraft.hra.Hrac;
import sk.uniza.fri.bloodcraft.hra.Postava;

/**
 * Interface IZbroj pre zbroj (zbraň, helma, nohavice, topánky, brnenie).
 * Interface IZbroj je rozšírený o interface IPredmet.
 *
 * @author Mário Vydra
 * @version 17.5.2022
 */
public interface IZbroj extends IPredmet {
    /**
     * Metóda odstranSa odstráni účinky danej zbroje na hráčove atribúty.
     *
     * @param hrac referencia na hráča.
     */
    void odstranSa(Hrac hrac);

    /**
     * Metóda typPostavy vracia typ postavy, pre ktorú je daný predmet určený.
     *
     * @return
     */
    Postava typPostavy();
}
