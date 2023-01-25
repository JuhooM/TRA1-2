import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X1.
 */
public class TRAI_22_X1_testi {

    static Random rnd = new Random();

    static TRAI_22_X1 testattava = new TRAI_22_X1_juhomati();

    public static void main(String[] args) {

        // taulukoiden koko
        int N = 11;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);
        rnd.setSeed(siemen);

        // tulostuksen mÃ¤Ã¤rÃ¤
        int print = 2;
        if (args.length > 2)
            print = Integer.parseInt(args[1]);

        boolean ok = true;

        System.out.println("\nTestataan muutama selkeÃ¤ syÃ¶te:");
        ok &= testaaX1(2, 3, 1, 2, 1, rnd, print);
        ok &= testaaX1(3, 3, 1, 2, 1, rnd, print);
        ok &= testaaX1(4, 3, 1, 2, 1, rnd, print);

        System.out.println("\nTestataan muutama syÃ¶te jossa suurin ja/tai toiseksi suurin on tuplana:");
        ok &= testaaX1(6, 3, 2, 2, 1, rnd, print);
        ok &= testaaX1(6, 3, 1, 2, 2, rnd, print);
        ok &= testaaX1(6, 3, 2, 2, 2, rnd, print);

        System.out.println("\nTestataan muutama syÃ¶te jossa isoja lukuja:");
        ok &= testaaX1(10, 1000, 2, 500, 1, rnd, print);
        ok &= testaaX1(10, 1001, 1, 501, 2, rnd, print);
        ok &= testaaX1(10, 1002, 2, 502, 2, rnd, print);

        System.out.println("\nTestataan muutama syÃ¶te joissa ei ole toiseksi suurinta, pitÃ¤isi tulla null");
        ok &= testaaX1(new Integer[]{1}, null, print);
        ok &= testaaX1(new Integer[]{1, 1}, null, print);
        ok &= testaaX1(new Integer[]{}, null, print);

        System.out.println("\nTestataan monta satunnaista syÃ¶tettÃ¤");
        ok &= testaaX1(1000, rnd, 0);

        if (ok)
            System.out.println("\nKaikki testit ok");
        else
            System.out.println("\nJoku virhe tuli");

    }


    /**
     * Testaa annetulla taulukolla
     *
     * @param A       taulukko
     * @param tSuurin odotustulos
     * @param print   tulostuksen mÃ¤Ã¤rÃ¤
     * @return true jos testi meni oikein, muuten false
     */
    static boolean testaaX1(Integer A[], Integer tSuurin, int print) {
        if (print > 1)
            System.out.println("\nTesti: A[" + A.length + "]: " + Arrays.toString(A) +
                    ", tSuurin= " + tSuurin);
        Integer tulos = null;

        try {
            tulos = testattava.toiseksiSuurin(A);
        } catch (Exception e) {
            System.out.println("TestistÃ¤ tuli poikkeus: " + e);
        }

        if (print > 1)
            System.out.println("Toiseksi suurimmaksi tulokseksi saatiin: " + tulos + ", odotettu tulos oli: " + tSuurin);

        if (print > 0)
            System.out.println("Testin tulos: " + Objects.equals(tulos, tSuurin));

        return Objects.equals(tulos, tSuurin);

    }


    /**
     * Testaa monta satunnaista testiÃ¤
     * @param nTest testien mÃ¤Ã¤rÃ¤
     * @param rnd satunnaislukugeneraattori
     * @param print tulostuksen mÃ¤Ã¤rÃ¤
     * @return true jos kaikki testit meni oikein, muuten false
     */
    static boolean testaaX1(int nTest, Random rnd, int print) {

        boolean ok = true;
        int virheet = 0;
        int oikein = 0;
        for (int k = 0; k < nTest; k++) {
            int Nm = rnd.nextInt(k + 1) * 10; // pienempien mÃ¤Ã¤Ã¤rÃ¤
            int Ns = rnd.nextInt(3) + 1;      // suurimpien mÃ¤Ã¤rÃ¤
            int Nts = rnd.nextInt(3) + 1;  // toiseksi suurimpien mÃ¤Ã¤rÃ¤

            int N = Nm + Ns + Nts; // yhteismÃ¤Ã¤rÃ¤

            int ts = rnd.nextInt(1000) + 100;   // toiseksi suurin
            int s = ts + rnd.nextInt(10) + 1;   // suurin

            if (!testaaX1(N, s, Ns, ts, Nts, rnd, print))
                virheet++;
            else
                oikein++;
            if (virheet > 30) {
                break;
            }
        }
        if (virheet > 0) ok = false;
        System.out.println((virheet + oikein) + " satunnaisesta lisÃ¤testistÃ¤ " + oikein + " oikein.");

        return ok;
    }

    /**
     * Testaa toiseksi pienintÃ¤ n kokoisella taulukolla
     *
     * @param n        Alkioiden yhteismÃ¤Ã¤rÃ¤
     * @param suurin   suurin alkio
     * @param suurinN  montako kopiota suurimmasta alkiosta
     * @param tSuurin  toiseksi suurin alkio
     * @param tSuurinN montako kopiota toiseksi suurimmasta alkiosta
     * @param rnd      satunnaislukugeneraattori
     * @param print
     * @return true jos testi meni oikein, muuten false
     */
    static boolean testaaX1(int n, int suurin, int suurinN, int tSuurin, int tSuurinN, Random rnd, int print) {

        if (print > 1)
            System.out.println("\nTesti: n=  " + n + ", odotus= " + tSuurin);

        Integer[] A = satunnainenTaulukko(n, suurin, suurinN, tSuurin, tSuurinN, rnd);

        Integer tulos = null;

        if (print > 1 && n < 20)
            System.out.println("A[" + n + "]: " + Arrays.toString(A));

        try {
            tulos = testattava.toiseksiSuurin(A);
        } catch (Exception e) {
            System.out.println("TestistÃ¤ tuli poikkeus: " + e);
        }

        if (print > 1)
            System.out.println("Toiseksi suurimmaksi tulokseksi saatiin: " + tulos + ", odotettu tulos oli: " + tSuurin);

        if (print > 0)
            System.out.println("Testin tulos: " + Objects.equals(tulos, tSuurin));

        return Objects.equals(tulos, tSuurin);
    }



    /**
     * Generoi satunnaisen syÃ¶tetaulukon tehtÃ¤vÃ¤lle X1.
     *
     * @param n        taulukon koko
     * @param suurin   suurin alkio
     * @param suurinN  suurimpien alkioiden mÃ¤Ã¤rÃ¤
     * @param tSuurin  toiseksi suurin alkio
     * @param tSuurinN toiseksi suurimpien alkioiden mÃ¤Ã¤rÃ¤
     * @param rnd      satunnaislukugeneraattori
     * @return pyydetty taulukko, tai tyhjÃ¤ taulukko jollei pyydettyÃ¤ ole mahdollista muodostaa.
     */
    static Integer[] satunnainenTaulukko(int n, int suurin, int suurinN, int tSuurin, int tSuurinN, Random rnd) {
        ArrayList<Integer> AL = new ArrayList<>();
        if (n < suurinN + tSuurinN) {
            System.out.println("Liian pieni taulukko, ei voi generoida pyydettyÃ¤ syÃ¶tettÃ¤");
            return new Integer[0];
        }
        for (int i = 0; i < suurinN; i++)
            AL.add(suurin);
        for (int i = 0; i < tSuurinN; i++)
            AL.add(tSuurin);
        while (AL.size() < n)
            AL.add(rnd.nextInt(tSuurin));

        Collections.shuffle(AL, rnd);

        Integer[] A = new Integer[n];
        return AL.toArray(A);
    }

}
