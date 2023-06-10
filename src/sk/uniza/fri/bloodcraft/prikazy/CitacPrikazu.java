package sk.uniza.fri.bloodcraft.prikazy;

import java.util.Scanner;

/**
 * Trieda CitacPrikazu reprezentuje čítanie vstupu zadaného hráčom do terminálu.
 *
 * @author Cvičenia
 * @version 27.4.2022
 */
public class CitacPrikazu {
    private final NazvyPrikazov prikazy;
    private final Scanner scanner;

    /**
     * Konštruktor inicializuje prikazy a vytvorí scanner na čítanie vstupov z terminálu.
     */
    public CitacPrikazu() {
        this.prikazy = new NazvyPrikazov();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Metóda nacitajPrikaz načíta zadané dve slová do temrinálu a vytvorí daný príkaz.
     *
     * @return príkaz zadaný hráčom.
     */
    public Prikaz nacitajPrikaz() {
        System.out.print("-> ");
        String nazov = null;
        String parameter = null;

        // najde len prve dve slova v danom riadku
        Scanner tokenizer = new Scanner(this.scanner.nextLine());
        if (tokenizer.hasNext()) {
            nazov = tokenizer.next();
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();
            }
        }
        if (this.prikazy.jePrikaz(nazov)) {
            return new Prikaz(nazov, parameter);
        }
        return new Prikaz(null, parameter);
    }
}
