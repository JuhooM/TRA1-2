// TRAI_22_t09_10_pohja SJ

import java.util.*;

/**
 * 9. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa A ja B (ArrayList)
 * ja joka poistaa listasta A kaikki ne alkiot jotka eivÃ¤t esiinny listassa B. Ã„lÃ¤ kÃ¤ytÃ¤ valmista
 * retainAll() -operaatioita. Aikavaativuus? Miten aikavaativuutta voisi parantaa?
 * <p>
 * 10. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa A ja B (Linked-
 * List) ja joka poistaa listasta A kaikki ne alkiot jotka eivÃ¤t esiinny listassa B. Ã„lÃ¤ kÃ¤ytÃ¤
 * valmista retainAll() -operaatioita. Aikavaativuus? Miten aikavaativuutta voisi parantaa?
 */

public class TRAI_22_t09_10_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_22_t09_10 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int n1 = 10;
        if (args.length > 0)
            n1 = Integer.parseInt(args[0]);

        int n2 = n1 + 2;
        if (args.length > 1)
            n2 = Integer.parseInt(args[1]);

        int pituus = 1; // merkkijonojen pituus
        if (n1 > 30)
            pituus = 2;

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        // testataan vaihteeksi merkkijonoilla

        ArrayList<String> AL1 = new ArrayList<>(n1);
        ArrayList<String> AL2 = new ArrayList<>(n2);

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n1; i++) {
            AL1.add(randomString(r, pituus));
        }

        for (int i = 0; i < n2; i++) {
            AL2.add(randomString(r, pituus));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("AL1: " + AL1);
            System.out.println("AL2: " + AL2);
        }

        // kopiot tehtÃ¤vÃ¤Ã¤ 10 varten
        LinkedList<String> LL1 = new LinkedList<>(AL1);
        LinkedList<String> LL2 = new LinkedList<>(AL2);

        // testataan tehtÃ¤vÃ¤Ã¤ 9

        sailytaKaikki9(AL1, AL2);

        System.out.print("TehtÃ¤vÃ¤  9: ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(AL1);
        } else {
            System.out.println(AL1.size() + " alkiota");
        }

        // testataan tehtÃ¤vÃ¤Ã¤ 10

        sailytaKaikki10(LL1, LL2);

        System.out.print("TehtÃ¤vÃ¤ 10: ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(LL1);
        } else {
            System.out.println(LL1.size() + " alkiota");
        }


    } // main()


    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }


    /**
     * Poistaa listasta L1 sellaiset alkiot jotka eivÃ¤t esiinny listassa L2.
     *
     * @param L1 lista josta poistetaan
     * @param L2 alkiot jotka poistetaan
     */
    public static <E> void sailytaKaikki9(ArrayList<E> L1, ArrayList<E> L2) {

        // TODO
        HashSet<E> hs = new HashSet<>(L2);
        for(int i = 0; i < L1.size(); i++) {
            if(hs.contains(L1.get(i)) == false) {
                L1.remove(i);
                i--;
            }
        }
    }

    /**
     * Poistaa listasta L1 sellaiset alkiot jotka eivÃ¤t esiinny listassa L2.
     *
     * @param L1 lista josta poistetaan
     * @param L2 alkiot jotka poistetaan
     */
    public static <E> void sailytaKaikki10(LinkedList<E> L1, LinkedList<E> L2) {

        // TODO
        HashSet<E> hs = new HashSet<>(L2);
        ListIterator<E> li = L1.listIterator();
        while(li.hasNext()) {
            if(!hs.contains(li.next())) {
                li.remove();
            }
        }
    }

} // class
