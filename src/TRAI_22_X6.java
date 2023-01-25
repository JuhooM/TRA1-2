import java.util.Map;
import java.util.Set;

public interface TRAI_22_X6 {

    /**
     * Palauttaa tiedon siitÃ¤ mitkÃ¤ joukot ovat keskenÃ¤Ã¤n erillisiÃ¤ (siis millÃ¤ ei ole yhteisiÃ¤ alkioita).
     * Tulos palautetaan kuvauksena siten, ettÃ¤ jos syÃ¶tteen joukolla Si ei ole yhtÃ¤Ã¤n yhteistÃ¤ alkioita
     * syÃ¶tteen joukon Sj kanssa, niin kuvauksen avaimeen Si liittyvÃ¤ssÃ¤ arvossa (joukossa) on
     * viittaus joukkoon Sj (ja pÃ¤invastoin). Kuvaus sisÃ¤ltÃ¤Ã¤ avaiminaan kunkin joukon SS sisÃ¤ltÃ¤mÃ¤n
     * joukon Si ja kunkin avaimen arvona on joukko niistÃ¤ joukoista Sj joilla ei ole lainkaan yhteisiÃ¤ alkita
     * joukon Si kanssa.
     * @param SS syÃ¶te (joukkojen joukko)
     * @param <E> joukkojen alkiotyyppi
     * @return kuvaus joukkojen erillisyyksistÃ¤
     */
    public <E> Map<Set<E>, Set<Set<E>>> erillisetJoukot(Set<Set<E>> SS);
}
