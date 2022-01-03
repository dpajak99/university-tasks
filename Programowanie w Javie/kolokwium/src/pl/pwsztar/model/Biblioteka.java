package pl.pwsztar.model;

import pl.pwsztar.utils.CzytelnikLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteka {
    List<Czytelnik> listaCzytelnikow = new ArrayList<>();

    public void dodajCzytelnika(Czytelnik czytelnik) {
        listaCzytelnikow.add(czytelnik);
    }

    public List<Czytelnik> getListaCzytelnikow() {
        return listaCzytelnikow;
    }

    public List<Czytelnik> wyszukajCzytelnika(String pattern ) {
        List<Czytelnik> czytelnicyZgodniZeWzorcem = new ArrayList<>();
        for( Czytelnik czytelnik : listaCzytelnikow ) {
            if( czytelnik.getSearchString().contains(pattern)) {
                czytelnicyZgodniZeWzorcem.add(czytelnik);
            }
        }
        return czytelnicyZgodniZeWzorcem;
    }

    public void skasujCzytelnikowGdzieImieZawieraPodwojnaLitere( char pattern ) {
        List<Czytelnik> nowaListaCzytelnikow = new ArrayList<>();
        for( Czytelnik czytelnik : listaCzytelnikow ) {
            long wystapieniaZnaku = czytelnik.getImie().chars().filter(ch -> ch == pattern).count();
            if( wystapieniaZnaku < 2 ) {
                nowaListaCzytelnikow.add(czytelnik);
            }
        }
        listaCzytelnikow = nowaListaCzytelnikow;
    }

    public void zapiszCzytelnikowPoPrzedziale( int odNumer, int doNumer ) throws IOException {
        CzytelnikLogger logger = new CzytelnikLogger();
        logger.otworzPlik();
        for( Czytelnik czytelnik : listaCzytelnikow ) {
            int id = czytelnik.getNumerCzytelnika();
            if( id  >= odNumer && id <= doNumer ) {
                logger.wpiszDoPliku(czytelnik.getNumerCzytelnika() + "    " + czytelnik.getImie() + "   " + czytelnik.getNazwisko());
            }
        }
        logger.zamknijPlik();
    }
}
