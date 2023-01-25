/**
 * Testiluokka TRAI 2022 tehtÃ¤vÃ¤Ã¤n X4
 */

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.*;

public class TRAI_22_X4_testi {

    static TRAI_22_X4 testattava = new TRAI_22_X4_juhomati();
    //                                              ^^^^ oma tunnus tÃ¤hÃ¤n

    public static void main(String[] args) {

        // komentoriviparametrina saatu puun koko
        int N = 8;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);
        Random rnd = new Random(siemen);

        // tulostusten mÃ¤Ã¤rÃ¤
        int print = 4;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);


        boolean ok = true;

        // testataan muutama triviaali tapaus

        // 1-solmuinen puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList(1)), true, print);
        // 2-solmuinen jÃ¤rjestetty puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList(1, 2)), true, print);
        // 2-solmuinen epÃ¤jÃ¤rjestetty puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList(2, 1)), false, print);

        // 3-solmuinen jÃ¤rjestetty puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList(1, 2, 3)), true, print);
        // 3-solmuinen epÃ¤jÃ¤rjestetty puu (juuren lapset vÃ¤Ã¤rÃ¤ssÃ¤ jÃ¤rjestyksessÃ¤)
        ok &= testaaX4(new ArrayList<>(Arrays.asList(1, 3, 2)), false, print);

        // 5-solmuinen jÃ¤rjestetty puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)), true, print);
        // 5-solmuinen eijÃ¤rjestetty puu, virhe oikealla
        ok &= testaaX4(new ArrayList<>(Arrays.asList(1, 2, 4, 3, 5)), false, print);

        // 7-solmuinen jÃ¤rjestetty puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10, 11)), true, print);
        // 7-solmuinen eijÃ¤rjestetty puu, virhe vasemmalla
        ok &= testaaX4(new ArrayList<>(Arrays.asList(5, 6, 8, 7, 9, 10, 11)), false, print);


        // 0-solmuinen puu
        ok &= testaaX4(new ArrayList<Integer>(), true, print);

        // testataan myÃ¶s merkkijonoilla

        // 3-solmuinen jÃ¤rjestetty puu
        ok &= testaaX4(new ArrayList<>(Arrays.asList("A", "B", "C")), true, print);
        // 3-solmuinen epÃ¤jÃ¤rjestetty puu (juuren lapset vÃ¤Ã¤rÃ¤ssÃ¤ jÃ¤rjestyksessÃ¤)
        ok &= testaaX4(new ArrayList<>(Arrays.asList("A", "D", "C")), false, print);

        System.out.println();

        if (ok)
            System.out.println("Alun testit ok");
        else
            System.out.println("Alun testeissÃ¤ ongelmia");

        // N testiÃ¤ eri kokoisilla syÃ¶tteillÃ¤
        for (int i = 0; i < N; i++)
            ok &= testaaX4(i, rnd.nextBoolean(), rnd, print);

        if (ok)
            System.out.println("Vakiotestit ok");
        else
            System.out.println("VakiotesteissÃ¤ ongelmia");


        // max 1000 satunnaista testiÃ¤
        rnd.setSeed(System.currentTimeMillis());
        int nTest = 1000;
        int k;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (!testaaX4(rnd.nextInt(k+1), rnd.nextBoolean(), rnd, 0))
                virheet++;
            if (virheet > 30)
                break;
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " satunnaisesta testistÃ¤ " + (k - virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    } // main()

    /**
     * Testaa X4:n n kokoisella puulla.
     * @param n puusolmujen mÃ¤Ã¤rÃ¤
     * @param jarjestys tehdÃ¤Ã¤nkÃ¶ jÃ¤rjestys oikein vai vÃ¤Ã¤rin
     * @param rnd satunnaislukugeneraattori
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return true jos testi onnistui, muuten false
     */
    static boolean testaaX4(int n, boolean jarjestys, Random rnd, int print) {
        ArrayList<Integer> AL = satunnainenKasvavaLista(n, rnd);
        if (! jarjestys)
            jarjestys = sotkeLista(AL, rnd); // mahdollisesti sotketaan lista
        return testaaX4(AL, jarjestys, print);
    }

    /**
     * Testaa X4:n puulla joka kasataan listata
     * @param L puuhun laitettavat alkiot
     * @param jarjestys onko lista jÃ¤rjestyksessÃ¤ vai ei?
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return true jos testi onnistui, muuten false
     */
    static <E extends Comparable<? super E>> boolean testaaX4(ArrayList<E> L, boolean jarjestys, int print) {

        if (print > 0)
            System.out.println("\nTesti, n = " + L.size() + " odotusTulos=" + jarjestys);

        if (print > 3 && L.size() <= 20)
            System.out.println("L=" + L);

        BTree<E> puu = teePuuTaulukostaSisaj(L);

        boolean tulos;
        try {
            tulos = testattava.onkoSisaJarjestyksessa(puu);
        } catch (Exception e) {
            if (print > 0) System.out.println("TestistÃ¤ tuli poikkeus: " + e);
            return false;
        }

        if (print > 1) {
            System.out.println("Onko sisÃ¤jÃ¤rjestyksessÃ¤ (tulos):    " + tulos);
        }

        if (tulos == jarjestys) {
            if (print > 0) System.out.println("Tulos oikein");
            return true;
        } else {
            if (print > 0) System.out.println("Tulos ei ole oikein");
            return false;
        }

    }


    /**
     * Luo satunnaisen uniikkien alkioiden listan
     * @param n alkiomÃ¤Ã¤rÃ¤
     * @param rnd satunnaislukugeneraattori
     * @return satunnainen lista
     */
    public static ArrayList<Integer> satunnainenLista(int n, Random rnd) {
        HashSet<Integer> HS = new HashSet<>(n*2);
        ArrayList<Integer> AL = new ArrayList<>(n);
        int i = 0;
        while (i < n) {
            int x = rnd.nextInt(n*2+1);
            if (HS.add(x)) {
                AL.add(x);
                i++;
            }
        }
        return AL;
    }

    /**
     * Luo satunnaisen uniikkien alkioiden kasvavan listan
     * @param n alkiomÃ¤Ã¤rÃ¤
     * @param rnd satunnaislukugeneraattori
     * @return satunnainen lista jossa alkiot ovat kasvavassa jÃ¤rjestyksessÃ¤
     */
    public static ArrayList<Integer> satunnainenKasvavaLista(int n, Random rnd) {
        ArrayList<Integer> AL = satunnainenLista(n, rnd);
        Collections.sort(AL);
        return AL;
    }

    /**
     * Vaihtaa listan kahden alkion paikkaa keskenÃ¤Ã¤n.
     * @param AL muokattava lista
     * @param rnd satunnaislukugeneraattori
     * @param <E> alkiotyyppi
     * @return false jos jÃ¤rjestys sotkettiin, muuten true
     */
    public static <E> boolean sotkeLista(ArrayList<E> AL, Random rnd) {

        // 0-1 alkioista listaa ei voi sotkea, jÃ¤rjestys sÃ¤ilyy
        if (AL.size() < 2)
            return true;

        // kaksi eri satunnaista indeksiÃ¤
        int i1 = rnd.nextInt(AL.size());
        int i2;
        do {
            i2 = rnd.nextInt(AL.size());
        } while (i2 == i1);

        // vaihdetaan alkiot keskenÃ¤Ã¤n
        E tmp = AL.get(i1);
        AL.set(i1, AL.get(i2));
        AL.set(i2, tmp);

        return false; // jÃ¤rjestys sotkettiin
    }

    /**
     * Muodostaa taulukon alkioista puun.
     * Taulukon keskimmÃ¤inen alkio tulee puun juureksi, lopuista alkioista puolet
     * juuren vasemmaksi lapseksi, loput oikeaksi lapseksi.
     * Jos taulukko oli jÃ¤rjestyksessÃ¤, puusta tulee sisÃ¤jÃ¤rjestetty.
     * @param alkiot alkiot jotka laitetaan puuhun
     * @param <E> alkiotyyppi
     * @return uusi puu
     */
    public static <E extends Comparable<? super E>> BTree<E> teePuuTaulukostaSisaj(ArrayList<E> alkiot) {
        BTree<E> puu = new BTree<>();
        if (! alkiot.isEmpty())
            puu.setRoot(teeHaaraTaulukosta(alkiot, 0, alkiot.size()-1));
        return puu;
    }

    /**
     * Muodostaa osataulukon alkioista osapuun sisÃ¤jÃ¤rjestyksestÃ¤.
     * Taulukon alkio keskeltÃ¤ osapuun juureksi, alkiosta indekseissÃ¤
     * alku..keski-1 muodostetaan vasen alipuu ja keski+1..loppu oikeaksi lapseksi.
     * @param alkiot alkiot jotka laitetaan puuhun
     * @param <E> alkiotyyppi
     * @return uusi solmu jÃ¤lkelÃ¤isineen
     */
    public static <E extends Comparable<? super E>> BTreeNode<E> teeHaaraTaulukosta(ArrayList<E> alkiot, int alku, int loppu) {
        if (loppu < alku)
            return null;

        int keski = (alku+loppu)/2;
        // eka alkio solmuksi
        BTreeNode<E> n = new BTreeNode<>(alkiot.get(keski));
        n.setLeftChild(teeHaaraTaulukosta(alkiot, alku, keski-1));
        n.setRightChild(teeHaaraTaulukosta(alkiot, keski + 1, loppu));
        return n;
    }

}
