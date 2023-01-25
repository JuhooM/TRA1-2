/**
 * Kurkistuspino: pino lisÃ¤ttynÃ¤ toiseksi pÃ¤Ã¤llimmÃ¤isen alkion kurkkimisella ja poistamisella.
 * @param <E> alkiotyyppi
 */
public interface TRAI_22_X7<E> {

    /**
     * Onko pino tyhjÃ¤?
     * @return true jos pinossa ei ole alkioita, false jos pinossa on alkioita.
     */
    public default boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Palauttaa pinon alkioiden lukumÃ¤Ã¤rÃ¤n.
     * @return alkioiden mÃ¤Ã¤rÃ¤.
     */
    public int size();

    /**
     * LisÃ¤Ã¤ alkion x pinoon.
     *
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio.
     */
    public void push(E x);

    /**
     * Pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @return pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws java.util.NoSuchElementException jos pino on tyhjÃ¤.
     */
    public E top();

    /**
     * Pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @return pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws java.util.NoSuchElementException jos pinossa on vÃ¤hemmÃ¤n kuin kaksi alkiota.
     */
    public E top2();

    /**
     * Poistaa pinon pÃ¤Ã¤llimmÃ¤isen alkion.
     * @return poistettu pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws java.util.NoSuchElementException jos pino on tyhjÃ¤.
     */
    public E pop();

    /**
     * Poista pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @return poistettu pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws java.util.NoSuchElementException jos pinossa on vÃ¤hemmÃ¤n kuin kaksi alkiota.
     */
    public E pop2();

}
