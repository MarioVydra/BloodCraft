package sk.uniza.fri.bloodcraft.hra;


/**
 * Trieda Main reprezentuje main triedu textovej hry BloodCraft.
 * Predstavuje vstupný bod celého programu.
 *
 * @author Mário Vydra
 * @version 27.4.2022
 */
public class Main {

    /**
     * Verejná statická metóda main vytvori novú inštanciu triedy Hra a vyvolá jej metódu hraj, čím sa spustí program.
     *
     * @param args String[].
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.hraj();
    }

        /*System.out.println("""
                Chceš začať novú hru alebo načítať posledný uložený postup?
                'novaHra' - vytvoríš novú hru
                'nacitaj' - načítaš posledný uložený postup
                """);
        Scanner skener = new Scanner(System.in);
        String vyber = skener.nextLine();
        if (vyber.equals("novaHra")) {
            hra = new Hra();
            hra.hraj();
        } else if (vyber.equals("nacitaj")) {
            nacitajHru();
        } else {
            System.out.println("Nezadal si správny krok, prosím oprav sa!");
        }

    }

    public static void nacitajHru() {
        try {
            FileInputStream fis = new FileInputStream("src/ulozenaHra.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            hra = (Hra) ois.readObject();
            hra.hraj();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
}
