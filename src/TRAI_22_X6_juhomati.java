import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TRAI_22_X6_juhomati implements TRAI_22_X6 {
    //                  ^^^^^ oma tunnus tÃ¤hÃ¤n

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * Aikavaativuus: Jokaisen setin kohdalla käydään jokainen seti läpi ja tarkastetaan onko sama,
     * jos ei, niin sisältääkö samaa alkiota, jos siältää otetaan seuraava. Täten pahimmassa tapauksessa
     * kaikki setit eri, jolloin jokaisen setin kohdalla on käytävä toisten settien jokainen alkio läpi, 
     * paitsi samojen settien => O(n*n*m).
     */

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
    @Override
    public <E> Map<Set<E>, Set<Set<E>>> erillisetJoukot(Set<Set<E>> SS) {
        Map<Set<E>, Set<Set<E>>> tulos = new HashMap<>();

        // TODO
        SS.forEach((s) -> {
            HashSet<Set<E>> ok = new HashSet<>();
            SS.forEach((k) -> {
                if(!s.equals(k)) {
                    boolean eri = true;
                    for(E x : k) {
                        if(s.contains(x)) {
                            eri = false;
                            break;
                        }
                    }
                    if(eri) {
                        ok.add(k);
                    }
                }
            });
            tulos.put(s, ok);
        });
        return tulos;
    }
}
