import java.util.HashSet;
import java.util.Set;

public class TRAI_22_X5_juhomati implements TRAI_22_X5 {
    //                  ^^^^^ oma tunnus tÃ¤hÃ¤n

    /**
     * ITSEARVIOINTI JA AIKAVAATIVUUS TÃ„HÃ„N:
     *  Aikavaativuus lineaaarinen
     *  O(8n) = O(n)
     *
     */

    /**
     * YhdessÃ¤-kolmesta -yhdiste.
     * Luo ja palauttaa uuden joukon jossa on ne alkiot jotka ovat
     * tasan yhdessÃ¤ kolmesta syÃ¶tejoukosta.
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * Ei muuta syÃ¶tejoukkoja.
     * @param s1    syÃ¶tejoukko
     * @param s2    syÃ¶tejoukko
     * @param s3    syÃ¶tejoukko
     * @param <E>   alkioiden tyyppi
     * @return  tulosjoukko
     */
    @Override
    public <E> Set<E> yhdessaKolmesta(Set<E> s1, Set<E> s2, Set<E> s3) {

        Set<E> tulos = new HashSet<>();

        // TODO
        
        HashSet<E> hs1 = new HashSet<>(s1); // O(1)
        HashSet<E> hs11 = new HashSet<>(s1); // O(1)
        HashSet<E> hs2 = new HashSet<>(s2); // O(1)
        HashSet<E> hs22 = new HashSet<>(s2); // O(1)
        HashSet<E> hs3 = new HashSet<>(s3); // O(1)
        hs1.removeAll(hs2); // O(n)
        hs1.removeAll(hs3); // O(n)
        hs2.removeAll(hs11); // O(n)
        hs2.removeAll(hs3); // O(n)
        hs3.removeAll(hs11); // O(n)
        hs3.removeAll(hs22); // O(n)
        tulos = hs1; // O(1)
        tulos.addAll(hs2); // O(n)
        tulos.addAll(hs3); // O(n)

        return tulos; // O(1)
    }
}
