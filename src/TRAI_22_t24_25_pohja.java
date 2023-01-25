// TRAI_22_t24_25.java SJ

import java.util.*;

public class TRAI_22_t24_25_pohja {

    // kannattaa testata monipuolisesti erilaisilla syÃ¶tteillÃ¤ ja vaikka
    // tehdÃ¤ eri versioita syÃ¶tteen generoinnista

    public static void main(String[] args) {

        // taulukoiden koko
        int N1 = 15;
        if (args.length > 0)
            N1 = Integer.parseInt(args[0]);

        int N2 = N1 + 2;
        if (args.length > 0)
            N2 = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = N1 + N2;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);

        // saako olla samoja alkioita
        int eri = 0;
        if (args.length > 3)
            eri = 1;

        Random r = new Random(siemen);

        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();

        for (int i = 0; i < N1; i++) {
            L1.add(r.nextInt(N1 / 2));
        }
        for (int i = 0; i < N1; i++) {
            L2.add(r.nextInt((N2 / 2) + eri * N1));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (N1 <= 20 && N2 <= 20) {
            System.out.println("L1: " + L1);
            System.out.println("L2: " + L2);
        }
        // kutsutaan tehtÃ¤vÃ¤Ã¤ 24
        System.out.println();
        HashMap<Integer, Integer> esiintymat1 = esiintymat(L1);
        for (Map.Entry<Integer, Integer> e : esiintymat1.entrySet()) {
            System.out.println("Alkio " + e.getKey() + " esiintyi listassa L1 " + e.getValue() + " kertaa.");
        }
        System.out.println();

        // tehtÃ¤vÃ¤n 25 testaus
        System.out.println();
        HashMap<Integer, Integer> esiintymat2 = esiintymat(L2);
        HashMap<Integer, Integer> yhdiste = laukkuYhdiste(esiintymat1, esiintymat2);
        for (Map.Entry<Integer, Integer> e : yhdiste.entrySet()) {
            System.out.println("Alkio " + e.getKey() + " esiintyi yhdisteessÃ¤ " + e.getValue() + " kertaa.");
        }
        System.out.println();

    } // main()


    /**
     * 24. Kirjoita algoritmi joka saa parametrinaan kokoelman (Collection<E>) ja joka muodostaa ja palauttaa kuvauksen
     * (HashMap<E, Integer>) jossa on avaimina kaikki eri alkiot jotka kokoelmasta lÃ¶ytyvÃ¤t. Kunkin avaimen kuvana on
     * ko. esiintymien lukumÃ¤Ã¤rÃ¤. Vihje: kaikki kokoelmat tukevat foreach-lÃ¤pikÃ¤yntiÃ¤. Aikavaativuus?
     *
     * @param C   syÃ¶tekokoelma
     * @param <E> alkiotyyppi
     * @return kuvaus jossa avaimina ovat C:n alkiot ja arvona ko. alkion esiintymismÃ¤Ã¤rÃ¤t
     */
    public static <E> HashMap<E, Integer> esiintymat(Collection<E> C) {
        
        HashMap<E, Integer> es = new HashMap<>();
        // TODO
        for(E e : C){
            es.merge(e, 1, Integer::sum);      
        }
        return es;
    }


    /**
     * 25. Ajatellaan kuvauksen Map<E, Integer> olevan laukun toteutus. TehtÃ¤vÃ¤n 24 metodilla saadaan kokoelmasta
     * tÃ¤llainen kuvaus jossa on kunkin alkion esiintymismÃ¤Ã¤rÃ¤t. Tee metodi laukkuYhdiste(Map<E, Integer> A, Map<E,
     * Integer> B) joka luo ja palauttaa uuden kuvauksen siten, ettÃ¤ tuloksessa on avaimena kukin sellainen avain joka
     * esiintyy jommassa kummassa tai molemmissa syÃ¶tekuvauksista ja kunkin avaimen x arvona on summa niistÃ¤ arvoista
     * jotka avaimella x on syÃ¶tekuvauksissa A ja B. Esimerkiksi kuvauk- sista A = { (a:2), (b:1) } ja B = { (b:1),
     * (c:3) } tulee tuloskuvaus { (a:2), (b:3), (c:3) }. Aikavaativuus?
     *
     * @param A   syÃ¶tekuvaus
     * @param B   syÃ¶tekuvaus
     * @param <E> alkiotyyppi
     * @return tuloskuvaus yhdisteenÃ¤ syÃ¶tekuvauksista
     */
    public static <E> HashMap<E, Integer> laukkuYhdiste(HashMap<E, Integer> A, HashMap<E, Integer> B) {
        HashMap<E, Integer> tulos = new HashMap<>(A);

        // TODO
        B.forEach((k, v) -> tulos.merge(k, v, Integer::sum));
        return tulos;
    }


} // class

