// TRAI_22_29.java SJ

import java.util.*;

public class TRAI_22_t29_pohja {

    public static void main(String[] args) {

        int N = 6;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        Set<Set<Integer>> SS = new HashSet<>();

        Random r = new Random(N + 1);
        System.out.println("SyÃ¶tejoukot:");
        for (int i = 0; i < N; i++) {
            Set<Integer> S = new HashSet<>();
            for (int j = 0; j < N; j++) {
                S.add(r.nextInt(N * 2));
            }
            SS.add(S);
            System.out.println("S" + i + ": " + S);
        }

        LinkedList<Set<Integer>> LS = new LinkedList<>(SS);
        System.out.println("Joukkojen lista:");
        tulostaRiveittain(LS);

        lajitteleAlkiomaaranMukaan(LS);

        System.out.println("Lista lajiteltuna joukkojen alkiomÃ¤Ã¤rÃ¤n mukaan:");
        tulostaRiveittain(LS);

    } // main()

    /**
     * Tulosta kokoelma riveittÃ¤in.
     *
     * @param CC  syÃ¶tekokoelma
     * @param <E> alkiotyyppi
     */
    static <E> void tulostaRiveittain(Collection<E> CC) {
        System.out.println("(");
        for (E x : CC) {
            System.out.println(x.toString());
        }
        System.out.println(")");
    }



    /**
     * 29. Kirjoita algoritmi joka saa parametrinaan joukkojen listan (List<Set<E>>) ja lajittelee listan joukkojen
     * alkiomÃ¤Ã¤rÃ¤n mukaiseen jÃ¤rjestykseen. Vihje: Collections.sort() ja Comparator<Set>. MikÃ¤ on algoritmisi
     * aikavaativuus?
     *
     * @param LS  lista
     * @param <E> joukkojen alkiotyyppi (ei kÃ¤ytetÃ¤)
     */
    public static <E> void lajitteleAlkiomaaranMukaan(List<Set<E>> LS) {
        // TODO
        Collections.sort(LS, new Comparator<Set<E>>() {
            @Override
            public int compare(Set<E> o1, Set<E> o2) {
                return Integer.valueOf(o1.size()).compareTo(o2.size());
            }
        });
    }


} // class
