import java.util.Set;

public interface TRAI_22_X5 {

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
    public <E> Set<E> yhdessaKolmesta(Set<E> s1, Set<E> s2, Set<E> s3);

}
