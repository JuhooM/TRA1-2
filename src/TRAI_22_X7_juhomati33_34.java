import java.util.NoSuchElementException;

/**
 * ITSEARVIOINTI TÃ„HÃ„N:
 * Linkitetysolmupino:
 * Kaikki metodit vakioaikaisia.
 */

 
/**
 * Kurkistuspino: pino lisÃ¤ttynÃ¤ toiseksi pÃ¤Ã¤llimmÃ¤isen alkion kurkkimisella ja poistamisella.
 * @param <E> alkiotyyppi
 */
public class TRAI_22_X7_juhomati33_34<E> implements TRAI_22_X7<E> {

    // valitse seuraavista riveistÃ¤ YKSI toteutustavan mukaan:
    // E[] talletusTaulukko;            // taulukkototeutus
    // pinoSolmu<E> paallimmainen;      // dynaaminen linkitetty toteutus
    // ArrayList<E> talletusTaulukko;   // ArrayList:n hÃ¶ydyntÃ¤minen (max 3p)

    // jos kÃ¤ytÃ¤t dynaamista linkitettyÃ¤ rakennetta, niin mÃ¤Ã¤rittele solmun luokka
    // tÃ¤mÃ¤n luokan sisÃ¤luokaksi:
    
    private class pinoSolmu<E> {
        E data;
        pinoSolmu<E> link;
    }
    
    // TODO: tÃ¤ydennÃ¤ allaolevat kontruktori ja metodit
    private int nodecount = 0;
    private pinoSolmu<E> top;

    /**
     * Luo uuden tyhjÃ¤n kurkistuspinon.
     *
     * Jos kÃ¤ytÃ¤t taulukkoa, Ã¤lÃ¤ varaa tarpeettoman isoa tilaa.
     * Tilaa kuuluu kasvattaa vasta tilan tÃ¤ytyttyÃ¤.
     */
    public TRAI_22_X7_juhomati33_34() {
        this.top = null;
    }


    /**
     * Palauttaa pinossa olevien alkioiden lukumÃ¤Ã¤rÃ¤n.
     *
     * @return alkioiden mÃ¤Ã¤rÃ¤.
     */
    @Override
    public int size() {
        return nodecount;
    }

    /**
     * LisÃ¤Ã¤ alkion x pinoon pÃ¤Ã¤llimmÃ¤iseksi.
     *
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio.
     */
    @Override
    public void push(E x) {
        pinoSolmu<E> a = new pinoSolmu<>();
        a.data = x;
        a.link = top;
        top = a;
        nodecount++;
    }

    /**
     * Pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     *
     * @return pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pino on tyhjÃ¤.
     */
    @Override
    public E top() {
        if(nodecount < 1)
            throw new NoSuchElementException("Pino tyhjä");
        else
            return top.data;
    }

    /**
     * Pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     *
     * @return pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pinossa on vÃ¤hemmÃ¤n kuin kaksi alkiota.
     */
    @Override
    public E top2() {
        if(nodecount < 2)
            throw new NoSuchElementException("Vähemmän kuin 2 alkiota");
        else {
            return top.link.data;
        }
    }

    /**
     * Poistaa pinon pÃ¤Ã¤llimmÃ¤isen alkion.
     *
     * @return poistettu pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pino on tyhjÃ¤.
     */
    @Override
    public E pop() {
        if(nodecount < 1)
            throw new NoSuchElementException("Pino tyhjä");
        else {
            E first = top.data;
            top = top.link;
            nodecount--;
            return first;
        }
    }

    /**
     * Poistaa pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     *
     * @return poistettu pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pinossa on vÃ¤hemmÃ¤n kuin kaksi alkiota.
     */
    @Override
    public E pop2() {
        if(nodecount < 2)
            throw new NoSuchElementException("Vähemmän kuin 2 alkiota");
        else {
            E first = pop();
            E second = pop();
            push(first);
            return second;
        }
    }
}
