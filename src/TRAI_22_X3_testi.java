import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X3.
 */
public class TRAI_22_X3_testi {

    static Random rnd = new Random();

    static TRAI_22_X3 testattava = new TRAI_22_X3_juhomati(); /* <-- Oma tunnus tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // taulukoiden koko
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        // tulostusten mÃ¤Ã¤rÃ¤
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(siemen);

        boolean ok = true;

        // "satunnaisia"
        ok &= testaaTulosX3(N, N, 0, 0, N, N, false,  print);
        ok &= testaaTulosX3(N, N, 0, 0, N * 2, N * 5, false, print);

        // toisessa pienempi alkio, mutta silti limittÃ¤in
        ok &= testaaTulosX3(N, N, 0, 2, N , N+2, true, print);
        ok &= testaaTulosX3(N, N, 2, 0, N+2 , N, true, print);

        // toisen suurin on toisen pienin
        ok &= testaaTulosX3(N, N, 0, N, N , N*2, true, print);
        ok &= testaaTulosX3(N, N, N, 0, N*2 , N, true, print);

        // toisen suurin on pienempi kuin toisen pienin
        ok &= testaaTulosX3(N, N, 0, N+1, N , N*2, true, print);
        ok &= testaaTulosX3(N, N, N+1, 0, N*2 , N, true, print);

        // lyhyitÃ¤ listoja
        ok &= testaaTulosX3(1, 1, 0, 0, N, N, false, print);
        ok &= testaaTulosX3(1, 2, 0, 0, 1, 1, false, print);
        ok &= testaaTulosX3(2, 1, 0, 0, 3, 2, false, print);
        ok &= testaaTulosX3(2, 0, 0, 0, N, N, false, print);
        ok &= testaaTulosX3(0, 1, 0, 0, N, N, false, print);
        ok &= testaaTulosX3(0, 2, 0, 1, 0, 1, false, print);
        ok &= testaaTulosX3(2, 2, 0, 1, 0, 1, false, print);
        ok &= testaaTulosX3(1, 1, 0, 0, N, N, false, print);
        ok &= testaaTulosX3(0, 0, 0, 0, N, N, false, print);

        // suurempia lukuja
        ok &= testaaTulosX3(N, N, 1000, 1000, 1000+N, 1000+N, false,  print);
        ok &= testaaTulosX3(N, N, 1000, 1000, 1000 + N * 2, 1000 + N * 3, false, print);


        // testataan vielÃ¤ merkkijonoilla

        ok &= testaaTulosX3mjono(N, N, 1, print);
        ok &= testaaTulosX3mjono(N, N, 2, print);

        if (ok)
            System.out.println("Alun testit antoivat kaikki oikean tuloksen.");

        // asetetaan "satunnainen" satunnaislukusiemen
        rnd.setSeed(System.currentTimeMillis());

        // suoritetaan testiÃ¤ korkeintaan 10 sekuntia
        long loppu = System.currentTimeMillis() + 10*1000;

        // testataan monta satunnaista syÃ¶tettÃ¤
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            int N1 = rnd.nextInt(k+2)*10 + 1;
            int N2 = rnd.nextInt(k+2)*10 + 1;
            if (! testaaTulosX3(N1, N2, 0, 0, N1, N2, rnd.nextBoolean(),  0))
                virheet++;
            if (virheet > 30)
                break;
            if (System.currentTimeMillis() > loppu)
                break;
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " satunnaisesta lisÃ¤testistÃ¤ " + (k-virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.\nMuista myÃ¶s itsearviointi ja aikavaativuus.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");

    }

    /**
     * Testaa yhdistettÃ¤ eri kokoisille taulukoille.
     *
     * @param n1            1. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param n2            2. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param min1          pienin luku taulukossa 1
     * @param min2          pienin luku taulukossa 2
     * @param max1          suurin luku taulukossa 1
     * @param max2          suurin luku taulukossa 2
     * @param varmistaRajat varmistetaanko min ja max kuhunkin taulukkoon
     * @param print         tulostusten mÃ¤Ã¤rÃ¤
     * @return              true jos testi meni oikein, muuten false
     */
    static boolean testaaTulosX3(int n1, int n2, int min1, int min2,
                                 int max1, int max2, boolean varmistaRajat, int print) {

        // generoidaan syÃ¶tteet
        LinkedList<Integer> A = satunnainenKasvavaLista(n1, min1, max1, varmistaRajat);
        LinkedList<Integer> B = satunnainenKasvavaLista(n2, min2, max2, varmistaRajat);

        // kopiot syÃ¶tteistÃ¤ talteen
        LinkedList<Integer> cA = new LinkedList<>(A);
        LinkedList<Integer> cB = new LinkedList<>(B);

        // tulostetaan syÃ¶tteet
        if (print > 0) System.out.println("\nTESTI n1="+n1 + " n2="+n2 + " min1="+min1 +
                " min2="+min2 + " max1="+max1 + " max2="+max2);
        if ((n1 < 20 && n2 < 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }

        
        // kutsutaan testattavaa metodia
        testattava.lisaaUudet(A, B);

        // tehdÃ¤Ã¤n verrokko

        lisaaUudetJoukoilla(cA, cB);

        // tulostetaan tulos
        if ((A.size() < 20 && print > 1) || print > 5)
            System.out.println("Tulos: " + A);

        boolean ok = true;

        ok &= onkoJarjestyksessa(A, print);

        boolean sisaltoSailynyt = B.equals(cB);
        if (! sisaltoSailynyt && print > 0)
            System.out.println("Virhe: metodi muuttaa syÃ¶telistan B  sisÃ¤ltÃ¶Ã¤!");

        // verrataan tulosta verrokkileikkaukseen
        ok &= vertaa(A, cA, print);

        return ok && sisaltoSailynyt;

    }


    static boolean testaaTulosX3mjono(int n1, int n2, int len,int print) {

        // generoidaan syÃ¶tteet
        LinkedList<String> A = satunnainenKasvavaMjonolista(rnd, n1, len);
        LinkedList<String> B = satunnainenKasvavaMjonolista(rnd, n2, len);

        LinkedList<String>  cA = new LinkedList<>(A);
        LinkedList<String> cB = new LinkedList<>(B);

        // tulostetaan syÃ¶tteet
        if (print > 0) System.out.println("\nTESTI MJONO n1="+n1 + " n2="+n2 + " len="+len);
        if ((n1 <= 20 && n2 <= 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }


        // kutsutaan testattavaa metodia
        testattava.lisaaUudet(A, B);

        // tehdÃ¤Ã¤n verrokko

        lisaaUudetJoukoilla(cA, cB);

        // tulostetaan tulos
        if ((A.size() <= 20 && print > 1) || print > 5)
            System.out.println("Tulos: " + A);

        boolean ok = true;

        ok &= onkoJarjestyksessa(A, print);

        boolean sisaltoSailynyt = B.equals(cB);
        if (! sisaltoSailynyt && print > 0)
            System.out.println("Virhe: metodi muuttaa syÃ¶telistan B  sisÃ¤ltÃ¶Ã¤!");

        // verrataan tulosta verrokkileikkaukseen
        ok &= vertaa(A, cA, print);

        return ok && sisaltoSailynyt;

    }




    public static LinkedList<String> satunnainenKasvavaMjonolista(Random r, int n, int len) {
        LinkedList<String> L = new LinkedList<>();

        for (int i = 0; i < n; i++)
            L.add(randomString(r, len));

        Collections.sort(L);
        return L;
    }

    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }


    /**
     * Generoi satunnaisen n kokoisen kasvavassa jÃ¤rjestyksessÃ¤ olevan listan
     *
     * @param n             alkioiden mÃ¤Ã¤rÃ¤
     * @param min           pienin mahdollinen alkio
     * @param max           suurin mahdollinen alkio
     * @param varmistaRajat jos tosi, niin min ja max ovat aina mukana (paitsi jos n<2)
     * @return uusi taulukko.
     */
    static LinkedList<Integer> satunnainenKasvavaLista(int n, int min, int max, boolean varmistaRajat) {
        LinkedList<Integer> tulos = new LinkedList<Integer>();
        int k = 0;
        if (max < 1)
            max = 1;
        if (n < 2)
            max = min;
        if (varmistaRajat) {
            if (n >= 1) {
                tulos.add(min);
                k++;
            }
            if (n >= 2) {
                tulos.add(max);
                k++;
            }
        }
        for (int i = k; i < n; i++) {
            tulos.add(rnd.nextInt(max - min + 1) + min);
        }
        Collections.sort(tulos);
        return tulos;
    }


    /**
     * Tarkastaa onko syÃ¶tekokoelmassa jokin alkio useamman kerran.
     *
     * @param C tarkasteltava kokoelma
     * @return true jos jokin alkio on syÃ¶tekokoelmassa useasti, muuten false
     * @param <E> parametrikokoelman alkioiden tyyppi
     */
    static <E> boolean onkoDuplikaatteja(Collection<E> C, int print) {
        boolean dupl = C.size() != (new HashSet<>(C)).size();
        if (print > 1 && dupl)
            System.out.println("Tuloksessa on jokin alkio useasti!");
        return dupl;
}

    /**
     * Tarkastaa onko annettu kokonaislukulista kasvavassa jÃ¤rjestyksessÃ¤.
     *
     * @param A     tarkastettava lista
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos on kasvava jÃ¤rjestys, muuten epÃ¤tosi
     */
    static public <E extends Comparable<? super E>> boolean onkoJarjestyksessa(LinkedList<E> A, int print) {
        if (A.size() < 2)
            return true;
        ListIterator<E> li = A.listIterator();
        E prev = li.next();
        while (li.hasNext()) {
            E x = li.next();
            if (prev.compareTo(x) > 0) {
                if (print > 1)
                    System.out.println("VÃ¤Ã¤rÃ¤ jÃ¤rjestys: A[" + (li.previousIndex()-1) + "]=" + prev +
                            " A[" + li.previousIndex()+ "]=" + x);
                return false;
            }
            prev = x;
        }
        if (print > 2)
            System.out.println("Kasvavassa jÃ¤rjestyksessÃ¤ ok");
        return true;
    }

    /**
     * Tehokkaampi vertailu jÃ¤rjestetylle listoille
     * @param A
     * @param V
     * @param print
     * @return
     * @param <E>
     */
    static public <E extends Comparable<? super E>> boolean vertaaJarjestettyja(LinkedList<E> A, LinkedList<E> V, int print) {
        ListIterator<E> ai = A.listIterator();
        ListIterator<E> vi = V.listIterator();

        while (ai.hasNext() && vi.hasNext())
            if (! ai.next().equals(vi.next()))
                return false;
        if (ai.hasNext() || vi.hasNext())
            return false;

        return true;
    }


        /**
         * Vertaa kahden listan sisÃ¤ltÃ¶Ã¤.
         *
         * @param A     verrattava
         * @param V     verrokki
         * @param print
         * @return tosi jos samat alkiot samassa jÃ¤rjestyksessÃ¤, muuten epÃ¤tosi
         */
    static public <E extends Comparable<? super E>> boolean vertaa(LinkedList<E> A, LinkedList<E> V, int print) {

        if (print > 1 && A.size() <= 20 && V.size() <= 20) {
            System.out.println("Vertailu odotettuun tulokseen:");
            System.out.println("A=" + A);
            System.out.println("V=" + V);
        }

        if (A.size() != V.size()) {
            System.out.println("Eri kokoiset: T:" + A.size() + "  V:" + V.size());
            if (A.size() > V.size())
                System.out.println("Tuloslistassa enemmÃ¤n alkioita, onko vÃ¤Ã¤riÃ¤ alkioita?");
            else
                System.out.println("Tuloslistassa vÃ¤hemmÃ¤n alkioita, joku puuttuu?");
            return false;
        }

        if (A.size() > 100 || V.size() > 0)
            return vertaaJarjestettyja(A, V, print);

        if (! A.containsAll(V) || !V.containsAll(A)) {
            System.out.println("SisÃ¤llÃ¶t eivÃ¤t tÃ¤smÃ¤Ã¤");
            return false;
        }
/*
        // tÃ¤stÃ¤ voi laittaa lisÃ¤Ã¤ tulostusta jos haluaa

        for (int i = 0; i < A.size(); i++) {
            if (!A.get(i).equals(V.get(i))) {
                System.out.println("VÃ¤Ã¤rÃ¤ alkio: " + "A[" + i + "]=" + A.get(i) +
                            "V[" + i + "]=" + V.get(i));
                return false;
            }
        }

 */

        if (print > 1)
            System.out.println("Vertailu ok");

        return true;
    }




    static public <E extends Comparable<? super E>> void lisaaUudetJoukoilla(LinkedList<E> A, LinkedList<E> B) {
        TreeMap<E, Integer> AM = new TreeMap<>();
        HashSet<E> AS = new HashSet<>(A);

        for (E x : A)
            AM.compute(x, (k, v) -> v == null ? 1 : v+1);

        for (E x : B)
            if (! AS.contains(x))
                AM.compute(x, (k, v) -> v == null ? 1 : v+1);

        A.clear();

        for (Map.Entry<E, Integer> e : AM.entrySet())
            for (int i = 0; i < e.getValue(); i++)
                A.add(e.getKey());
    }

}
