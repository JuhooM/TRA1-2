// TRAI_22_t21.java SJ

import fi.uef.cs.tra.TraSet;

import java.util.Random;

public class TRAI_22_t21_pohja {

    public static void main(String[] args) {

        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);


        TraSet<Integer> S1 = new TraSet<>();
        TraSet<Integer> S2 = new TraSet<>();
        TraSet<Integer> S3 = new TraSet<>();

        Random r = new Random(42);
        Integer x, y;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N + 2);
            y = r.nextInt(N + 2);
            S1.add(x);
            S2.add(x - y);
            S3.add(x + y);
        }


        System.out.println("S1:      " + S1);
        System.out.println("S2:      " + S2);
        System.out.println("S3:      " + S3);


        System.out.println("YhdessaKolmestaTRA: " + yhdessaKolmesta(S1, S2, S3));


    } // main()


    /**
     * 21. Kirjoita algoritmi joka hakee kolmesta joukosta ne alkiot jotka esiintyvÃ¤t vain yhdes- sÃ¤ joukossa. Algoritmi
     * saa siis parametrinaan kolme tietorakennekirjastomme joukkoa (TraSet) ja muodostaa uuden joukon niistÃ¤ alkioista
     * jotka kuuluvat tÃ¤smÃ¤lleen yhteen syÃ¶tejoukoista. Mukana ei siis ole niitÃ¤ alkioita jotka kuuluvat kahteen
     * syÃ¶tejoukoista, eikÃ¤ niitÃ¤ alkioita jotka kuuluvat kaikkiin syÃ¶tejoukkoihin. Ã„lÃ¤ muuta syÃ¶tejoukkoja Ã¤lÃ¤kÃ¤ kÃ¤ytÃ¤
     * apuna kuvausta (Map) tai Javan vakiokirjaston joukkoa (Set). VihjeitÃ¤: voit ottaa joukoista kopioita, kÃ¤ytÃ¤
     * joukko-operaatioita, ei kannata lÃ¤hteÃ¤ iteroimaan joukkoja alkioittain. MikÃ¤ on algoritmisi aikavaativuus kun
     * TraSet:n operaatioiden aikavaativuus on kuten vastaavilla TreeSet -operaatioilla?
     *
     * @param A   syÃ¶tejoukko
     * @param B   syÃ¶tejoukko
     * @param C   syÃ¶tejoukko
     * @param <E> alkiotyyppi (ei kÃ¤ytetÃ¤)
     * @return uusi joukko jossa on ne alkiot jotka lÃ¶ytyvÃ¤t tasan yhdestÃ¤ syÃ¶tejoukosta
     */
    public static <E> TraSet<E> yhdessaKolmesta(TraSet<E> A, TraSet<E> B, TraSet<E> C) {

        // TODO
        TraSet<E> tulos = new TraSet<>(A);
        TraSet<E> hs1 = new TraSet<>(A);
        TraSet<E> hs11 = new TraSet<>(A);
        TraSet<E> hs2 = new TraSet<>(B);
        TraSet<E> hs22 = new TraSet<>(B);
        TraSet<E> hs3 = new TraSet<>(C);
        hs1.removeAll(hs2); // O(n)
        hs1.removeAll(hs3); // O(n)
        hs2.removeAll(hs11); // O(n)
        hs2.removeAll(hs3); // O(n)
        hs3.removeAll(hs11); // O(n)
        hs3.removeAll(hs22); // O(n)
        tulos = hs1; // O(1)
        tulos.addAll(hs2); // O(n)
        tulos.addAll(hs3); // O(n)

        return tulos;

    } // yhdessaKolmesta()


} // class TRAI_22_t21
