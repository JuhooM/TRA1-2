import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * X8. Toteutetaan abstrakti tietotyyppi kurkistuspino. Kurkistuspinolla on konstruktorin ja nor-
 * maalien pino-operaatioiden (onkoTyhjÃ¤, koko, lisaa, ylin ja poistaYlin lisÃ¤ksi operaatiot
 * pinon toiseksi pÃ¤Ã¤llimmÃ¤isen alkion katsomiseen (toiseksiYlin) ja poistamiseen (poistaToi-
 * seksiYlin). Toiseksi ylimmÃ¤n poistaminen ei poista pÃ¤Ã¤llimmÃ¤istÃ¤ alkiota. Kukin operaatio
 * palauttaa pyydetyn alkion tai heittÃ¤Ã¤ poikkeuksen NoSuchElementException jos pinossa
 * ei ole riittÃ¤vÃ¤sti alkioita. Toteutettavat operaatiot, niiden parametrit ja palautusarvot on
 * lueteltu toteutettavassa rajapinnassa TRAI_22_X7. Voi toteuttaa kurkistuspinon valintasi mu-
 * kaan joko dynaamisella linkitetyllÃ¤ (yksisuuntainen riittÃ¤Ã¤) rakenteella tai taulukolla (muista
 * kasvattaa tilaa tarvittaessa). NÃ¤issÃ¤ ratkaisuissa ei siis kÃ¤ytetÃ¤ mitÃ¤Ã¤n valmista kokoelmaa
 * apuna. Vaihtoehtoisesti voit toteuttaa saman toiminnallisuuden kÃ¤yttÃ¤en talletusrakenteena
 * valmista ArrayList-kokoelmaa, mutta tÃ¤llÃ¶in maksimipistemÃ¤Ã¤rÃ¤ on 3. Moodlessa on pohjat
 * nÃ¤ihin kaikkiin kolmeen ratkaisutapaan.
 */

/**
 * Kurkistuspino: pino lisÃ¤ttynÃ¤ toiseksi pÃ¤Ã¤llimmÃ¤isen alkion kurkkimisella ja poistamisella.
 * @param <E> alkiotyyppi
 */
public class TRAI_22_t31_32_pohja<E> implements TRAI_22_X7<E>, Iterable<E> {

    // valitse seuraavista riveistÃ¤ YKSI toteutustavan mukaan:
    // E[] talletusTaulukko;            // taulukkototeutus
    // pinoSolmu<E> paallimmainen;      // dynaaminen linkitetty toteutus
    // ArrayList<E> talletusTaulukko;   // ArrayList:n hÃ¶ydyntÃ¤minen (max 3p)

    // jos kÃ¤ytÃ¤t dynaamista linkitettyÃ¤ rakennetta, niin mÃ¤Ã¤rittele solmun luokka
    // tÃ¤mÃ¤n luokan sisÃ¤luokaksi:
    /*
    private class pinoSolmu<E> {
        // TODO
    }
    */


    // TODO: tÃ¤ydennÃ¤ allaolevat kontruktori ja metodit
    private E pino[];
    private int index = 0;
    private int size;


    /**
     * Luo uuden tyhjÃ¤n kurkistuspinon.
     *
     * Jos kÃ¤ytÃ¤t taulukkoa, Ã¤lÃ¤ varaa tarpeettoman isoa tilaa.
     * Tilaa kuuluu kasvattaa vasta tilan tÃ¤ytyttyÃ¤.
     */
    public TRAI_22_t31_32_pohja() {
        size = 10;
        pino = (E[])new Object[size];
    }


    /**
     * Palauttaa pinossa olevien alkioiden lukumÃ¤Ã¤rÃ¤n.
     *
     * @return alkioiden mÃ¤Ã¤rÃ¤.
     */
    @Override
    public int size() {
        return index;
    }

    /**
     * LisÃ¤Ã¤ alkion x pinoon pÃ¤Ã¤llimmÃ¤iseksi.
     *
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio.
     */
    @Override
    public void push(E x) {
        if(index < size) {
            pino[index++] = x;
        } 
        else {
            size = index*2;
            pino = Arrays.copyOf(pino, size);
            pino[index++] = x;
        }
    }

    /**
     * Pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     *
     * @return pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pino on tyhjÃ¤.
     */
    @Override
    public E top() {
        if(index < 1)
            throw new NoSuchElementException("Pino tyhjä");
        else
            return pino[index-1];
    }

    /**
     * Pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     *
     * @return pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pinossa on vÃ¤hemmÃ¤n kuin kaksi alkiota.
     */
    @Override
    public E top2() {
        if(index < 2)
            throw new NoSuchElementException("Vähemmän kuin 2 alkiota");
        else
            return pino[index-2];
    }

    /**
     * Poistaa pinon pÃ¤Ã¤llimmÃ¤isen alkion.
     *
     * @return poistettu pinon pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pino on tyhjÃ¤.
     */
    @Override
    public E pop() {
        if(index < 1)
            throw new NoSuchElementException("Pino tyhjä");
        else
            return pino[--index];
    }

    /**
     * Poistaa pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     *
     * @return poistettu pinon toiseksi pÃ¤Ã¤llimmÃ¤inen alkio.
     * @throws NoSuchElementException jos pinossa on vÃ¤hemmÃ¤n kuin kaksi alkiota.
     */
    @Override
    public E pop2() {
        if(index < 2)
            throw new NoSuchElementException("Vähemmän kuin 2 alkiota");
        else {
            E first = pop();
            E second = pop();
            push(first);
            return second;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        // TODO tehtÃ¤vÃ¤ 31

        Iterator<E> iterator = new Iterator<E>() {
            private int currentIndex = size()-1;
            @Override
            public boolean hasNext() {
                return currentIndex >= 0 && pino[currentIndex] != null;
            }
            @Override
            public E next() {
                return pino[currentIndex--];
            }
        };
        return iterator;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        // TODO tehtÃ¤vÃ¤ 32

        Iterator<E> it = iterator();
        while(it.hasNext()) {
            sb.append(it.next()+ " ");
        }
        return sb.toString().trim();
    }
}
