// TRAI_22_t22_23.java SJ

import java.util.*;

public class TRAI_22_t22_23_pohja {

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

        Set<Integer> tulos = yhdiste(SS);
        System.out.println("\nKaikkien yhdiste: " + tulos);
        System.out.println();

        tulos = vainYhdessa(SS);
        System.out.println("Vain yhdessÃ¤: " + tulos);
        System.out.println();


        LinkedList<Set<Integer>> LS = new LinkedList<>(SS);
        System.out.println("Joukkojen lista:");
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
     * 22. Kirjoita algoritmi joka saa parametrinaan joukkojen joukon (Set<Set<E>>) ja joka pa- lauttaa joukkona
     * (Set<E>) kaikki ne alkiot jossakin (tai joissakin) syÃ¶tejoukoissa. Siis yhdisteen. Vihje: foreach ja
     * joukko-operaatiot. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param SS  joukkojen joukko
     * @param <E> joukkojen alkioiden tyyppi
     * @return kaikki jossain joukossa esiintyneet alkiot
     */
    public static <E> Set<E> yhdiste(Set<Set<E>> SS) {
        Set<E> tulos = new HashSet<>();

        // TODO
        for (Set<E> s : SS) {
            tulos.addAll(s);
        }
        return tulos;
    }

    /**
     * 23. Kirjoita algoritmi joka saa parametrinaan joukkojen joukon (Set<Set<E>>) ja joka pa- lauttaa joukkona
     * (Set<E>) kaikki ne alkiot jotka ovat tasan yhdessÃ¤ syÃ¶tejoukoista. Ne alkiot jotka ovat useammassa kuin yhdessÃ¤
     * syÃ¶tejoukoista eivÃ¤t tule tulokseen. Ã„lÃ¤ muuta syÃ¶tejoukkoja. Vihje: foreach ja kuvaus. MikÃ¤ on
     * algoritmisi aikavaativuus?
     *
     * @param SS  joukkojen joukko
     * @param <E> joukkojen alkioiden tyyppi
     * @return kaikki ne alkiot jotka esiintyvÃ¤t vain yhdessÃ¤ joukossa
     */
    public static <E> Set<E> vainYhdessa(Set<Set<E>> SS) {
        Set<E> tulos = new HashSet<>();

        // TODO
        HashMap<E, Integer> relaatio = new HashMap<>();
        for (Set<E> s : SS) {
            for(E e : s){
                relaatio.merge(e, 1, Integer::sum);
            }
        }
        relaatio.forEach((k, v) -> {if(v == 1)tulos.add(k);});
        return tulos;
    } // vainYhdessa()


} // class
