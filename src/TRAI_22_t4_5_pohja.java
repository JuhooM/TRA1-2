// TraI_21_t4_5.java SJ
// Esimerkkiratkaisu viikon 1 tehtÃ¤viin 4-5

/**
 * 4. Kirjoita algoritmi (Java-metodi) joka saa parametrinaan kaksi kokonaislukutaulukkoa (In-
 * teger[] A, Integer[] B) ja joka luo ja palauttaa uuden kokonaislukutaulukon jossa on kaikki
 * ne alkiot jotka lÃ¶ytyvÃ¤t molemmista taulukoista (siis niiden leikkauksen). Kukin alkio
 * (.equals() palauttaa toden) tulee kuitenkin tulostaulukkoon vain yhden kerran vaikka se
 * esiintyisi molemmissa taulukoissa useamman kerran. MikÃ¤ on algoritmisi aikavaativuus?
 * Voisiko sitÃ¤ parantaa? Ota tehtÃ¤vÃ¤Ã¤n runko Moodlesta.
 *
 * 5. Kertaa Ohjelmointi II -kurssilla kÃ¤sitelty ArrayList -luokka. LisÃ¤tietoa: https://docs.
 * oracle.com/javase/8/docs/api/java/util/ArrayList.html. Muuta edellisen tehtÃ¤-
 * vÃ¤n algoritmi toimimaan taulukoiden sijaan ArrayList -kokoelmilla. MikÃ¤ on algoritmisi
 * aikavaativuus? Voisiko sitÃ¤ parantaa? Ota tehtÃ¤vÃ¤Ã¤n runko kurssin www-sivulta.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class TRAI_22_t4_5_pohja {

    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_22_t4_5 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int n1 = 10;
        if (args.length > 0) n1 = Integer.parseInt(args[0]);

        int n2 = n1 + 2;
        if (args.length > 1) n2 = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2) siemen = Integer.parseInt(args[2]);

        // luodaan esimerkkitaulukot
        Integer[] T1 = new Integer[n1];
        Integer[] T2 = new Integer[n2];

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        java.util.Random r = new java.util.Random(siemen);
        for (int i = 0; i < n1; i++) {
            T1[i] = r.nextInt(n1);
        }

        for (int i = 0; i < n2; i++) {
            T2[i] = r.nextInt(n2 * 2);
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("T1: " + Arrays.toString(T1));
            System.out.println("T2: " + Arrays.toString(T2));
        }

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 4
        Integer[] E4 = leikkaus4(T1, T2);

        System.out.print("TehtÃ¤vÃ¤ 4, leikkaus = ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(Arrays.toString(E4));
        } else {
            System.out.println(E4.length + " alkioinen taulukko");
            // huom: tÃ¤mÃ¤ tulostaa taulukon koon, ei todellisten alkioiden mÃ¤Ã¤rÃ¤Ã¤!
        }

        // Muodostetaan taulukoista ArrayList:t

        ArrayList<Integer> L1 = new ArrayList<>(T1.length);
        ArrayList<Integer> L2 = new ArrayList<>(T2.length);
        for (Integer x : T1) L1.add(x);

        for (Integer x : T2) L2.add(x);

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 5
        ArrayList<Integer> E5 = leikkaus5(L1, L2);

        System.out.print("TehtÃ¤vÃ¤ 5, leikkaus = ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(E5);
        } else {
            System.out.println(E5.size() + " alkiota");
        }
    } // main()


    /**
     * 4. Palauttaa taulukoiden leikkauksen, siis ne alkiot jotka ovat taulukossa T1 ja
     * T2. Kukin alkio tulee tulostaulukkoon vain kertaalleen.
     * Taulukot kÃ¤sitellÃ¤Ã¤n kokonaan, null alkiot ohitetaan.
     *
     * @param T1 ensimmÃ¤inen taulukko
     * @param T2 toinen taulukko
     * @return leikkaus taulukkona
     */
    public static Integer[] leikkaus4(Integer[] T1, Integer[] T2) {

        Integer[] tulos = null;
        
        Set<Integer> yks = new HashSet<Integer>(Arrays.asList(T1));
        Set<Integer> kaks = new HashSet<Integer>(Arrays.asList(T2));
        yks.retainAll(kaks);
        
        tulos = yks.toArray(new Integer[yks.size()]);
 
        return tulos;
    } // leikkaus4



    /**
     * 5. Palauttaa taulukkopohjaisten listojen leikkauksen uutena listana.
     *
     * @param L1 ensimmÃ¤inen lista
     * @param L2 toinen lista
     * @return leikkaus listana
     */
    // neliÃ¶llinen ratkaisu, tehdÃ¤Ã¤n myÃ¶hemmin tehokkaampi
    public static ArrayList<Integer> leikkaus5(ArrayList<Integer> L1, ArrayList<Integer> L2) {

        ArrayList<Integer> tulos = new ArrayList<>();

        // TODO
        Set<Integer> yks = new HashSet<Integer>(L1);
        Set<Integer> kaks = new HashSet<Integer>(L2);
        yks.retainAll(kaks);
        
        tulos = new ArrayList<Integer>(yks);
 
        return tulos;
    } // leikkaus5()


} // class
