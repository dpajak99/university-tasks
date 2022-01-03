package pl.pwsztar.model;

import pl.pwsztar.exceptions.PustyString;

public class Czytelnik {
    String imie;
    String nazwisko;
    int numerCzytelnika;

    public Czytelnik(String imie, String nazwisko, int numerCzytelnika) throws PustyString {
        if( imie.isEmpty() || nazwisko.isEmpty() ) {
            throw new PustyString();
        }
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerCzytelnika = numerCzytelnika;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getNumerCzytelnika() {
        return numerCzytelnika;
    }

    public String getSearchString() {
        return "imie:"+imie+",nazwisko:"+nazwisko+",numer:"+numerCzytelnika;
    }

    @Override
    public String toString() {
        return "Czytelnik{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", numerCzytelnika=" + numerCzytelnika +
                '}';
    }
}
