// TRAII_22s_t3_4.java SJ

/**
 * 3. Kirjoita algoritmi, joka saa syÃ¶tteenÃ¤Ã¤n kokoelman (Collection<E> C ) ja kokonaisluvun k
(k >0), ja joka palauttaa tuloksenaan uuden listan niistÃ¤ alkioista jotka esiintyivÃ¤t kokoelmassa
C tasan k kertaa. Kukin k kertaa esiintynyt alkio tulee tuloslistaan yhden kerran. Ã„lÃ¤ muuta
syÃ¶tettÃ¤. Alkiot ovat samat jos niiden .equals() -metodi palauttaa true. KÃ¤ytÃ¤ apuna kuvausta
(Map<E, Integer>). MikÃ¤ on algoritmisi aikavaativuus? Ota pohjaa ja esimerkkiÃ¤ Moodlesta.

4. Vertaa tehtÃ¤vÃ¤n 3 toimintaa kun apuvÃ¤linekuvauksena on (a) HashMap tai (b) TreeMap.
Kirjoita ohjelma joka mittaa nÃ¤iden nopeutta kun syÃ¶te kasvaa. Miten selitÃ¤t tulokset? Ota
pohjaa ja esimerkkiÃ¤ Moodlesta.

 */

import java.util.*;

public class TRAII_22s_t3_4_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAII_22s_t3_4 [N] [siemen]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤
    // ja siemen on satunnaislukusiemen

    public static void main(String[] args) {

        // á¸±okoelman koko
        int N = 1200000;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);


        // satunnaislukusiemen
        int siemen = N;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);


        // ensin pieni lista
        Random r = new Random(siemen);
        LinkedList<Integer> L = randomLinkedList(20, r);

        // tulostetaan lista jos alkioita ei ole paljoa
        System.out.println(L.size() < 30 ? L : (L.size() + " alkioinen syÃ¶telista"));
        Ajastin2 at = null;
        List<Integer> kTulos = null;


        at = new Ajastin2("" + L.size());
        kTulos = kEsiintyvat(L, 2);
        at.stop();
        System.out.println("aika: " + at + ", " +
                (at.time() * 1.0 / L.size()) + " ns/elem");
        System.out.println("2 kertaa esiintyneet " + kTulos);

        // sitten vÃ¤hÃ¤n isompi
        L = randomLinkedList(N, r);

        // tulostetaan lista jos alkioita ei ole paljoa
        System.out.println(L.size() < 30 ? L : ("\n" + L.size() + " alkioinen syÃ¶telista"));

        System.out.println("HashMap:");
        at = new Ajastin2("" + L.size());
        kTulos = kEsiintyvat(L, 3);
        at.stop();
        System.out.println("aika: " + at + ", " +
                (at.time() * 1.0 / L.size()) + " ns/elem");
        if (kTulos.size() < 20)
            System.out.println("3 kertaa esiintyneet " + kTulos);
        else
            System.out.println("3 kertaa esiintyneitÃ¤ oli " + kTulos.size() + " kappaletta");

        // TODO T4
        System.out.println("TreeMap:");
        at = new Ajastin2("" + L.size());
        kTulos = kEsiintyvatTree(L, 3);
        at.stop();
        System.out.println("aika: " + at + ", " +
                (at.time() * 1.0 / L.size()) + " ns/elem");
        if (kTulos.size() < 20)
            System.out.println("3 kertaa esiintyneet " + kTulos);
        else
            System.out.println("3 kertaa esiintyneitÃ¤ oli " + kTulos.size() + " kappaletta");

    } // main()


    /**
     * MitkÃ¤ alkio esiintyvÃ¤t tasan k kertaa C?
     * Palautetaan ko. alkiot listana siten, ettÃ¤ kukin alkio on tuloslistassa vain kerran.
     *
     * @param <E> alkiotyyppi
     * @param C   SyÃ¶tekokoelma
     * @param k   Haettavien esiintymien mÃ¤Ã¤rÃ¤
     * @return lista jossa on kaikki ne alkiot jotka esiintyvÃ¤t syÃ¶tteessÃ¤ k kertaa
     */
    public static <E> List<E> kEsiintyvat(Collection<E> C, int k) {
        List<E> tulos = new ArrayList<>();

        // TODO
        HashMap<E,Integer> ok = new HashMap<E,Integer>();
        C.forEach(x -> ok.merge(x, 1, Integer::sum));
        ok.forEach((mk, mv) -> {
            if(mv.equals(k))
                tulos.add(mk);
        });
        return tulos;
    }

    public static <E> List<E> kEsiintyvatTree(Collection<E> C, int k) {
        List<E> tulos = new ArrayList<>();

        // TODO
        TreeMap<E, Integer> ok = new TreeMap<E,Integer>();
        C.forEach(x -> ok.merge(x, 1, Integer::sum));
        ok.forEach((mk, mv) -> {
            if(mv.equals(k))
                tulos.add(mk);
        });
        return tulos;
    }



    public static LinkedList<Integer> randomLinkedList(int n, int seed) {
        Random r = new Random(seed);
        LinkedList<Integer> V = new LinkedList<>();
        for (int i = 0; i < n; i++)
            V.add(r.nextInt(n));
        return V;
    }

    public static LinkedList<Integer> randomLinkedList(int n, Random r) {
        LinkedList<Integer> V = new LinkedList<>();
        for (int i = 0; i < n; i++)
            V.add(r.nextInt(n));
        return V;
    }


} // class

