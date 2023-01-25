// TRAI_22_X5_testi.java SJ

import java.util.*;

public class TRAI_22_X5_testi {


    static TRAI_22_X5 testattava = new TRAI_22_X5_juhomati(); /* <-- Oma tunnus tÃ¤hÃ¤n */
    static int print = 4;

    public static void main(String[] args) {

        int N = 5;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        int seed = N;
        if (args.length > 0)
            seed = Integer.parseInt(args[1]);

        if (args.length > 0)
            print = Integer.parseInt(args[2]);

        Random r = new Random(seed);

        boolean ok = true;
        
        ok &= testaa(r, 1, 1, 1, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, 2, 2, 2, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, 3, 3, 3, false, 2);    // satunnainen syÃ¶te

        ok &= testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, false, 1);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N + 1, N + 2, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N + 2, N + 1, N, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N * 2, N * 3, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N * 3, N * 2, N, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, false, 4);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, false, 10);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, false, 100);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, true, 2);        // kaikkiin samat arvot
        ok &= testaa(r, N, N, 0, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        ok &= testaa(r, N, 0, N, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        ok &= testaa(r, 0, N, N, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        ok &= testaa(r, N, N, 0, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        ok &= testaa(r, N, 0, N, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        ok &= testaa(r, 0, N, N, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        ok &= testaa(r, N, 0, 0, true, 2);    // kaksi tyhjÃ¤Ã¤, kolmanteen jotain
        ok &= testaa(r, 0, N, 0, true, 2);    // kaksi tyhjÃ¤Ã¤, kolmanteen jotain
        ok &= testaa(r, 0, 0, N, true, 2);    // kaksi tyhjÃ¤Ã¤, kolmanteen jotain
        ok &= testaa(r, 0, 0, 0, true, 2);    // kolme tyhjÃ¤Ã¤

        r.setSeed(System.currentTimeMillis());
        ok &= testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        ok &= testaa(r, N, N, N, false, 4);    // satunnainen syÃ¶te


        ok &= testaaMjono(r, N, N, N, 1);    // satunnainen merkkijonosyÃ¶te
        ok &= testaaMjono(r, N*5, N*5, N*5, 2);    // satunnainen merkkijonosyÃ¶te

        // testataan vÃ¤hÃ¤n isompia syÃ¶tteitÃ¤, kunhan ei mene liian pitkÃ¤Ã¤n aikaa
        long start = System.currentTimeMillis();
        ok &= testaa(r, 100, 100, 100, false, 2);
        ok &= testaa(r, 1000, 1000, 1000, false, 2);
        ok &= testaa(r, 10000, 10000, 10000, false, 2);
        if (System.currentTimeMillis() < start+10*1000)
            ok &= testaa(r, 100000, 100000, 100000, false, 2);
        if (System.currentTimeMillis() < start+10*1000)
            ok &= testaa(r, 1000000, 1000000, 1000000, false, 2);


        if (ok)
            System.out.println("\n Kaikki testit ok");
        else
            System.out.println("\n Jossain testeissÃ¤ virheitÃ¤");


    } // main()

    /**
     * Testaa metodia halutun kokoisilla syÃ¶tteillÃ¤.
     * Joukkojen koot voivat olla pienempiÃ¤ jos/kun satunnaislukugeneraattori
     * antaa samoja lukuja uudestaan.
     *
     * @param r     satunnaislukugeneraattori
     * @param n1    S1 koko
     * @param n2    S2 koko
     * @param n3    S3 koko
     * @param samat laitetaanko joukkoisin samoja lukuja vai eri lukuja
     * @param k     kerroin N:lle satunnaisluvun maksimia varten
     * @return true jos testi meni oikein, muuten false
     */
    static boolean testaa(Random r, int n1, int n2, int n3, boolean samat, int k) {
        Set<Integer> S1 = new HashSet<>(n1*2);
        Set<Integer> S2 = new HashSet<>(n2*2);
        Set<Integer> S3 = new HashSet<>(n3*3);


        if (samat) {    // samat arvot kaikkiin
            int N = Math.max(Math.max(n1, n2), n3);
            for (int i = 0; i < N; i++) {
                int x = r.nextInt(N * k);
                if (i < n1) S1.add(x);
                if (i < n2) S2.add(x);
                if (i < n3) S3.add(x);
            }

        } else {    // kullekin eri arvoja
            for (int i = 0; i < n1; i++)
                S1.add(r.nextInt(n1 * k));

            for (int i = 0; i < n2; i++)
                S2.add(r.nextInt(n2 * k));

            for (int i = 0; i < n3; i++)
                S3.add(r.nextInt(n3 * k));
        }

        // kopiot talteen tulostamista ja vertailua varten
        TreeSet<Integer> TS1 = new TreeSet<>(S1);
        TreeSet<Integer> TS2 = new TreeSet<>(S2);
        TreeSet<Integer> TS3 = new TreeSet<>(S3);

        System.out.println("---------------------------------------------\nSyÃ¶tejoukot:");

        if (n1+n2+n3 < 30) {

            System.out.println("S1:              " + TS1);  // tulostusta varten jÃ¤rjestyksessÃ¤ jotta
            System.out.println("S2:              " + TS2);  // on helpompi nÃ¤hdÃ¤ alkiot
            System.out.println("S3:              " + TS3);
        } else {
            System.out.println("Joukkojen alkiomÃ¤Ã¤rÃ¤t: " + n1 + " " + n2 + " " + n3);
        }

        Set<Integer> vrt = yhdessaKolmestaKuvauksella(S1, S2, S3);

        Set<Integer> tulos = testattava.yhdessaKolmesta(S1, S2, S3);

        // System.out.println("yhdessaKolmesta: " + tulos);

        if (vrt.size() + tulos.size() < 30) {
            System.out.println("yhdessaKolmesta: " + (new TreeSet<>(tulos)));    // jos haluat lajiteltuna tuloksen

            System.out.println("verrokki       : " + (new TreeSet<>(vrt)));        // jos haluat lajiteltuna tuloksen
        } else
            System.out.println("Tuloksen alkiomÃ¤Ã¤rÃ¤t tulos: " + tulos.size() + ", vrt: " + vrt.size());

        boolean ok = vrt.equals(tulos);
        if (ok)
            System.out.println("SisÃ¤ltÃ¶ tÃ¤smÃ¤Ã¤, hienoa");
        else
            System.out.println("SisÃ¤llÃ¶issÃ¤ eroa");


        if (!S1.equals(TS1) || !S2.equals(TS2) || !S3.equals(TS3)) {
            System.out.println("Muuttaa syÃ¶tejoukkoa vaikka ei saisi!");
            ok = false;
        }
        
        return ok;
	
    }


    static boolean testaaMjono(Random r, int n1, int n2, int n3, int len) {
        Set<String> S1 = new HashSet<>(n1*2);
        Set<String> S2 = new HashSet<>(n2*2);
        Set<String> S3 = new HashSet<>(n3*3);

            for (int i = 0; i < n1; i++)
                S1.add(randomString(r,len));

            for (int i = 0; i < n2; i++)
                S2.add(randomString(r,len));

            for (int i = 0; i < n3; i++)
                S3.add(randomString(r,len));

        // kopiot talteen tulostamista ja vertailua varten
        TreeSet<String> TS1 = new TreeSet<>(S1);
        TreeSet<String> TS2 = new TreeSet<>(S2);
        TreeSet<String> TS3 = new TreeSet<>(S3);

        System.out.println("---------------------------------------------\nSyÃ¶tejoukot:");

        if (n1+n2+n3 < 30) {

            System.out.println("S1:              " + TS1);  // tulostusta varten jÃ¤rjestyksessÃ¤ jotta
            System.out.println("S2:              " + TS2);  // on helpompi nÃ¤hdÃ¤ alkiot
            System.out.println("S3:              " + TS3);
        } else {
            System.out.println("Joukkojen alkiomÃ¤Ã¤rÃ¤t: " + n1 + " " + n2 + " " + n3);
        }

        Set<String> vrt = yhdessaKolmestaKuvauksella(S1, S2, S3);

        Set<String> tulos = testattava.yhdessaKolmesta(S1, S2, S3);

        // System.out.println("yhdessaKolmesta: " + tulos);

        if (vrt.size() + tulos.size() < 30) {
            System.out.println("yhdessaKolmesta: " + (new TreeSet<>(tulos)));    // jos haluat lajiteltuna tuloksen

            System.out.println("verrokki       : " + (new TreeSet<>(vrt)));        // jos haluat lajiteltuna tuloksen
        } else
            System.out.println("Tuloksen alkiomÃ¤Ã¤rÃ¤t tulos: " + tulos.size() + ", vrt: " + vrt.size());

        boolean ok = vrt.equals(tulos);
        if (ok)
            System.out.println("SisÃ¤ltÃ¶ tÃ¤smÃ¤Ã¤, hienoa");
        else
            System.out.println("SisÃ¤llÃ¶issÃ¤ eroa");


        if (!S1.equals(TS1) || !S2.equals(TS2) || !S3.equals(TS3)) {
            System.out.println("Muuttaa syÃ¶tejoukkoa vaikka ei saisi!");
            ok = false;
        }

        return ok;

    }


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
     * Vain yhdessÃ¤ -yhdiste kÃ¤yttÃ¤en kuvausta.
     * TÃ„MÃ„ SIIS TEKEE SAMAN KUIN X5 VAADITAAN, MUTTA X5:SSA ON NYT KIELLETTY KUVAUKSEN KÃ„YTTÃ–
     * JOTEN TÃ„STÃ„ EI KANNATA OTTAA MALLIA
     * Luo ja palauttaa uuden joukon jossa on ne alkiot jotka ovat
     * tasan yhdessÃ¤ kolmesta syÃ¶tejoukosta.
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * Ei muuta syÃ¶tejoukkoja.
     *
     * @param s1 syÃ¶tejoukko
     * @param s2 syÃ¶tejoukko
     * @param s3 syÃ¶tejoukko
     * @param <E> alkiotyyppi
     * @return tulosjoukko
     */
    public static <E> Set<E> yhdessaKolmestaKuvauksella(Set<E> s1, Set<E> s2, Set<E> s3) {
        HashMap<E, Integer> hm = new HashMap<>(((s1.size()+s2.size()+s3.size())*2));
        for (E x : s1) hm.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : s2) hm.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : s3) hm.compute(x, (k, v) -> v == null ? 1 : v+1);
        hm.entrySet().removeIf(v -> v.getValue() > 1);
        return hm.keySet();
    }

} // class TRAI_22_X5_testi
