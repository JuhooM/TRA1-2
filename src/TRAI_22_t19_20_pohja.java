// TRAI_22_t09_10_pohja SJ

import java.util.*;


public class TRAI_22_t19_20_pohja {


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
        int siemen = n1+n2;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        // testataan vaihteeksi merkkijonoilla
        ArrayList<String> AL1 = new ArrayList<>(n1);
        ArrayList<String> AL2 = new ArrayList<>(n2);

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n1; i++) {
            AL1.add(randomString(r, pituus, 10));
        }

        for (int i = 0; i < n2; i++) {
            AL2.add(randomString(r, pituus, 10));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("AL1: " + AL1);
            System.out.println("AL2: " + AL2);
        }

        // kopiot tehtÃ¤vÃ¤Ã¤ 19 varten
        LinkedList<String> LL1 = new LinkedList<>(AL1);
        LinkedList<String> LL2 = new LinkedList<>(AL2);

        // testataan tehtÃ¤vÃ¤Ã¤ 19

        removeAll(LL1, LL2);

        System.out.print("TehtÃ¤vÃ¤ 19 (poistakaikki): ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(LL1);
        } else {
            System.out.println(LL1.size() + " alkiota");
        }

        // testataan tehtÃ¤vÃ¤Ã¤ 20

        List<String> xortulos = listaXor(AL1, AL2);

        System.out.print("TehtÃ¤vÃ¤ 20 (xor): ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(xortulos);
        } else {
            System.out.println(xortulos.size() + " alkiota");
        }


    } // main()


    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @param s   aakkoston koko
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len, int s) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(s) + 'a');
        return new String(C);
    }



    /**
     * 20. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa ja joka muodostaa
     * ja palauttaa uuden listan joka sisÃ¤ltÃ¤Ã¤ kaikki ne alkiot jotka esiintyvÃ¤t vain yhdessÃ¤
     * listassa (siis niiden joko-tai -yhdisteen (xor)). Jos jokin alkio esiintyy yhdessÃ¤ listassa
     * useasti, mutta ei toisessa listassa, niin se tulee tuloslistaan yhtÃ¤ monta kertaa kuin mitÃ¤
     * se esiintyi siinÃ¤ listassa jossa se esiintyi. KÃ¤ytÃ¤ joukko(j)a (Set) apuna ja pyri lineaariseen
     * aikavaativuuteen. Vihje: mieti ensin tarkasti miten kÃ¤ytÃ¤t joukko(j)a hyÃ¶dyksi ja ryhdy
     * tarkentamaan algoritmiasi vasta sitten.
     * @param A syÃ¶telista
     * @param B syÃ¶telista
     * @return uusi lista jossa on ne alkiot jotka esiintyvÃ¤t vain yhdessÃ¤ listassa
     * @param <E> alkioiden tyyppi
     */
    public static <E> List<E> listaXor(List<E> A, List<E> B) {
        List<E> tulos = new ArrayList<>();

        // TODO
        HashSet<E> sa = new HashSet<>(A);
        HashSet<E> sb = new HashSet<>(B);
        for(E x : A) {
            if(!sb.contains(x)) {
                tulos.add(x);
            }
        }
        for(E x : B) {
            if(!sa.contains(x)) {
                tulos.add(x);
            }
        }
        return tulos;
    }


    /**
     * T19:
     * Kirjoita lineaarisessa ajassa toimiva algoritmi removeAll(LinkedList A, Collection B)
     * joka poistaa listasta A kaikki ne alkiot jotka esiintyvät kokoelmassa B. Vihje: muodosta
     * joukko (HashSet) kokoelman B alkioista, jolloin läpikäydessäsi listaa A sinun on helppo
     * ja nopea päättää mitkä alkiot poistetaan ja mitkä ei.
     * @param A lista josta poistetaan
     * @param C alkiot jotka poistetaan
     */
    public static <E> void removeAll(LinkedList<E> A, Collection<E> C) {

        // TODO
        HashSet<E> s = new HashSet<>(C);
        ListIterator<E> li = A.listIterator();
        while(li.hasNext()) {
            if(s.contains(li.next())) {
                li.remove();
            }
        }
    }



} // class

