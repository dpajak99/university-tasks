package pl.pwsztar;

import pl.pwsztar.exceptions.PustyString;
import pl.pwsztar.model.Biblioteka;
import pl.pwsztar.model.Czytelnik;

import java.io.IOException;

public class Main {
    static Biblioteka biblioteka;

    public static void main(String[] args) throws IOException, PustyString {
        biblioteka = new Biblioteka();

        Czytelnik czytelnik1 = new Czytelnik("Dominik", "Pajak", 1);
        Czytelnik czytelnik2 = new Czytelnik("Jakub", "Zbylut", 2);
        Czytelnik czytelnik3 = new Czytelnik("Filip", "Kowalski", 3);
        Czytelnik czytelnik4 = new Czytelnik("Lech", "Kaczyński", 3);
        Czytelnik czytelnik5 = new Czytelnik("Andrzej", "Duda", 3);
        Czytelnik czytelnik6 = new Czytelnik("Filip", "Kowalski", 3);
        // Testuje poprawność wyjątku (odkomentować)
        // Czytelnik czytelnik4 = new Czytelnik("", "Kowalski", 3);

        // Testuje poprawność dodawania użytkowników
        biblioteka.dodajCzytelnika(czytelnik1);
        biblioteka.dodajCzytelnika(czytelnik2);
        biblioteka.dodajCzytelnika(czytelnik3);
        System.out.println(biblioteka.getListaCzytelnikow().toString());

        // Testuje poprawność kasowania użytkowników
        biblioteka.skasujCzytelnikowGdzieImieZawieraPodwojnaLitere('i');
        System.out.println(biblioteka.getListaCzytelnikow().get(0).getImie());

        // Testuje poprawność szukania użytkowników
        System.out.println("Wyszukani: " + biblioteka.wyszukajCzytelnika("Jakub").toString());

        biblioteka.zapiszCzytelnikowPoPrzedziale(1,2);
    }
}
