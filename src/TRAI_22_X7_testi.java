
import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X7.
 */
public class TRAI_22_X7_testi {

    static Random rnd = new Random();

    static TRAI_22_X7<Integer> testattava = new TRAI_22_X7_juhomati<>(); /* <-- Oma tunnus tÃ¤hÃ¤n */
    static TRAI_22_X7<Double> testattava2 = new TRAI_22_X7_juhomati<>(); /* <-- Oma tunnus tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // taulukoiden koko
        int N = 8;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = (int)(System.currentTimeMillis() & 0xfffffffl);
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        rnd.setSeed(siemen);

        boolean ok = true;

        ok &= testaaX7_1(testattava);
        ok &= testaaX7_2(testattava);
        ok &= testaaX7_N(testattava, N);
        ok &= testaaX7_N(testattava, N*2);
        ok &= testaaX7_N(testattava, rnd.nextInt(N*4));
        for (int i = 4; i <= 24; i+=2)
            ok &= testaaX7_N(testattava, 1<<i);

        ok &= testaaDoublella(testattava2, rnd);

        System.out.println("\nKaikki ok: " + ok);

    }


    /**
     * TyhjentÃ¤Ã¤ pinon.
     * @param pino
     */
    public static void tyhjenna(TRAI_22_X7 pino) {
        System.out.println("\nTyhjennetÃ¤Ã¤n.");
        while (!pino.isEmpty())
            pino.pop();
    }



    /**
     * Testaa kerrallaan pinossa yhden alkion toimintaa.
     * Olettaa pinon olevan tyhjÃ¤.
     * @param pino testattava kurkistuspino
     * @return true jos kaikki ok, muuten false
     */
    public static boolean testaaX7_1(TRAI_22_X7<Integer> pino) {

        boolean ok = true;

        // tyhjenna(pino);

        ok &= testaaTyhja(pino, "Alku_1", true);
        ok &= testaaKoko(pino, "Alku_1", 0);

        ok &= testaaLisays(pino, "LisÃ¤tÃ¤Ã¤n 0 (alkio nolla)", 0);
        ok &= testaaTyhja(pino, "Yksi alkio lisÃ¤tty", false);
        ok &= testaaKoko(pino, "Yksi alkio lisÃ¤tty", 1);
        ok &= testaa(pino, 0, "PÃ¤Ã¤llÃ¤ 0", false, true, false);

        ok &= testaa(pino, 0, "PÃ¤Ã¤ltÃ¤ pois 0", true, true, false);
        ok &= testaaTyhja(pino, "Ainoa poistettu", true);

        ok &= testaa(pino, null, "TyhjÃ¤stÃ¤ pÃ¤Ã¤llimmÃ¤inen", false, true, true);
        ok &= testaa(pino, null, "TyhjÃ¤stÃ¤ poisto", true, true, true);
        
        System.out.println("Testi_1: " + ok + "\n");
        
        return ok;

    }

    /**
     * Testaa muutaman alkion toimintaa.
     * Olettaa pinon olevan tyhjÃ¤.
     * @param pino testattava kurkistuspino
     * @return true jos kaikki ok, muuten false
     */
    public static boolean testaaX7_2(TRAI_22_X7<Integer> pino) {

        boolean ok = true;

        // tyhjenna(pino);

        ok &= testaaTyhja(pino, "testi_2 alku ", true);

        ok &= testaaLisays(pino, "LisÃ¤tÃ¤Ã¤n 0", 0);
        ok &= testaaLisays(pino, "LisÃ¤tÃ¤Ã¤n 1", 1);

        ok &= testaaKoko(pino, "Kaksi", 2);

        ok &= testaa(pino, 1, "PÃ¤Ã¤llÃ¤ 1", false, true, false);
        ok &= testaa(pino, 1, "PÃ¤Ã¤ltÃ¤ pois 1", true, true, false);
        ok &= testaaTyhja(pino, "Yksi jÃ¤ljellÃ¤ ", false);
        ok &= testaaKoko(pino, "Yksi jÃ¤ljellÃ¤", 1);
        ok &= testaa(pino, 0, "PÃ¤Ã¤llÃ¤ 0", false, true, false);

        ok &= testaa(pino, 0, "YhdestÃ¤ toiseksiYlin", false, false, true);
        ok &= testaa(pino, 0, "YhdestÃ¤ pois toiseksiYlin", true, false, true);

        ok &= testaaLisays(pino, "LisÃ¤tÃ¤Ã¤n 2", 2);
        ok &= testaaLisays(pino, "LisÃ¤tÃ¤Ã¤n 3", 3);

        ok &= testaa(pino, 2, "Toisena 2 ", false, false, false);
        ok &= testaa(pino, 2, "Toisena 2 pois", true, false, false);
        ok &= testaa(pino, 3, "PÃ¤Ã¤llÃ¤ 3", false, true, false);

        ok &= testaa(pino, 0, "Toinen 0 pois", true, false, false);
        ok &= testaa(pino, 3, "PÃ¤Ã¤ltÃ¤ 3 pois", true, true, false);

        ok &= testaaTyhja(pino, "Lopussa tyhjÃ¤", true);
        
        System.out.println("Testi_2: " + ok + "\n");

        return ok;


    }

    /**
     * Testaa useaa alkion lisÃ¤yksen toimintaa.
     * @param pino testattava kurkistuspino
     * @param n lisÃ¤ttÃ¤vien alkioiden mÃ¤Ã¤rÃ¤
     * @return true jos kaikki ok, muuten false
     */
    public static boolean testaaX7_N(TRAI_22_X7<Integer> pino, int n) {

        System.out.println("Testaa_N n=" + n + " ");
        boolean ok = true;
        int virhe = 0;
        try {
            for (int i = 0; i < n; i++) {
                pino.push(i);
            }
        } catch (Exception e) {
            System.out.println("Tuli poikkeus " + e + " vaikka piti lisÃ¤tÃ¤ alkio i");
            return false;
        }

        testaa(pino, n-1, "testaa_" + n + "_lisatty, ylin ", false, true, n==0);

        try {
            for (int i = n-1; i >= 0; i--) {
                Integer x =  pino.pop();
                if (! x.equals(i)) {
                    virhe++;
                    ok = false;
                    System.out.println("Tuli vÃ¤Ã¤rÃ¤ alkio " + x + " vaikka piti tulla " + i);
                }
                if (virhe > 20) {
                    System.out.println("Liikaa virheitÃ¤, luovutetaan");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Tuli poikkeus " + e + " vaikka piti lisÃ¤tÃ¤ alkio i");
            return false;
        }

        ok &= testaaTyhja(pino, "Lopussa tyhjÃ¤", true);

        System.out.println(" ok " + ok + "\n");
        return ok;

    }

        /**
         * Testaa onkoTyhja -operaatiota.
         * @param pino testattava kurkistuspino
         * @param teksti tulostettava teksti
         * @param odotus tyhjyyden odotusarvo
         * @param <E> parametrityyppi
         * @return true jos tyhjyys oikein, muuten false
         */
    static <E> boolean testaaTyhja(TRAI_22_X7<E> pino, String teksti, boolean odotus) {
        if (pino.isEmpty() == odotus) {
            System.out.println(teksti + ": isEmpty oikein: " + odotus);
            return true;
        } else {
            if (odotus)
                System.out.println(teksti + " Pino ei ole tyhjÃ¤ vaikka pitÃ¤isi olla");
            else
                System.out.println(teksti + " Pino on tyhjÃ¤ vaikka ei pitÃ¤isi olla");
            }
        return false;
    }


    /**
     * Testaa koko -operaatiota.
     * @param pino testattava kurkistuspino
     * @param teksti tulostettava teksti
     * @param odotus tyhjyyden odotusarvo
     * @param <E> alkiotyyppi
     * @return true jos koko oikein, muuten false
     */

    static <E> boolean testaaKoko(TRAI_22_X7<E> pino, String teksti, int odotus) {
        if (pino.size() == odotus) {
            System.out.println(teksti + ": size ok: " + odotus);
            return true;
        } else {
                System.out.println(teksti + " Pinossa " +  pino.size() + " alkiota vaikka pitÃ¤isi olla " + odotus);
                return false;
        }
    }

    /**
     * LisÃ¤Ã¤ alkion. Ei varsinaisesti voi testata onnistuiko lisÃ¤ys, sillÃ¤
     * lisÃ¤ysoperaatio ei palauta mitÃ¤Ã¤n
     * @param pino testattava kurkistuspino
     * @param teksti tulostettava teksti
     * @param lisattava lisÃ¤ttÃ¤vÃ¤ alkio
     * @param <E> alkiotyyppi
     * @return true jollei tullut poikkeusta, false jos tuli poikkeus
     */
    static <E> boolean testaaLisays(TRAI_22_X7<E> pino, String teksti, E lisattava) {
        System.out.print(teksti + " lisÃ¤tÃ¤Ã¤n " + lisattava + ":");
        try {
            pino.push(lisattava);
        } catch (Exception e) {
            System.out.println("Tuli poikkeus " + e + " vaikka piti lisÃ¤tÃ¤ alkio " + lisattava);
            return false;
        }
        System.out.println(" lisÃ¤ystÃ¤ kutsuttu");
        return true;
    }


    /**
     * Testaa yhden kurkistus/poisto-operaation.
     * @param pino      testattava pino
     * @param odotus         odotettu alkio
     * @param teksti    viesti testin alkuun
     * @param poista    poistetaanko vai kurkataanko
     * @param ylin      ylin vai toiseksi ylin
     * @param poikkeus  odotetaanko poikkeusta
     * @param <E>       alkiotyyppi
     * @return true jos kaikki ok, muuten false
     */
    static <E> boolean testaa(TRAI_22_X7<E> pino, E odotus, String teksti, boolean poista, boolean ylin, boolean poikkeus) {
        try {

            System.out.print("Testi " + teksti + " " + (poista ? "pop" : "top")
                    + (ylin ? "" : "2") + " : ");

            E x = null;

            if (ylin) { // ylin alkio
                if (poista)
                    x = pino.pop();
                else
                    x = pino.top();
            } else {    // toiseksi ylin
                if (poista)
                    x = pino.pop2();
                else
                    x = pino.top2();
            }

            if (odotus != null && odotus.equals(x))
                System.out.println("tulos ok : " + odotus);
            else {
                if (!poikkeus)
                    System.out.println("Tuli " + x + " vaikka piti tulla " + odotus);
                else
                    System.out.println("Tuli " + x + " vaikka piti tulla poikkeus NoSuchElementException");

                return false;
            }

        } catch (NoSuchElementException e) {
            if (poikkeus)   // odotettiin poikkeusta
                System.out.println("poikkeus ok");
            else {
                System.out.println("Tuli poikkeus NoSuchElementException vaikka piti tulla alkio " + odotus);
                return false;
            }

        } catch (Exception e) {
            if (!poikkeus)
                System.out.println("Tuli poikkeus " + e + " vaikka piti tulla alkio " + odotus);
            else
                System.out.println("Tuli poikkeus " + e + " vaikka piti tulla poikkeus NoSuchElementException");
            return false;
        }
        return true;
    }


    /**
     * Testaa pinon toimintaa liukuluvuilla
     * @param testattava2 liukulukupino
     * @param rnd satunnaislukugeneraattori
     * @return true jos testit menivÃ¤t oikein, muuten false
     */
    public static boolean testaaDoublella(TRAI_22_X7<Double> testattava2, Random rnd) {
        boolean ok = true;
        Stack<Double> vrt = new Stack<>();

        System.out.println("Testataan liukuluvuilla");

        for (int i = 0; i < 20; i++) {
            Double d = rnd.nextDouble() * 200 - 100;
            vrt.push(d);
            testattava2.push(d);
        }
        while (!vrt.isEmpty() && !testattava2.isEmpty()) {
            if (Double.compare(vrt.pop(), testattava2.pop()) != 0) {
                System.out.println("VÃ¤Ã¤rÃ¤ liukuluku pinosta");
                tyhjenna(testattava2);
                return false;
            }
        }

        if (!vrt.isEmpty() && !testattava2.isEmpty()) {
            System.out.println("VÃ¤Ã¤rÃ¤ mÃ¤Ã¤rÃ¤ alkoita liukulukupinossa");
            tyhjenna(testattava2);
            return false;
        }
        return true;
    }


}
