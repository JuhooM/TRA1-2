// TRAI_22_t15.java.java SJ

/**
 * 13. Kirjoita algoritmi, joka lisÃ¤Ã¤ sisÃ¤jÃ¤rjestyksessÃ¤ olevaan binÃ¤Ã¤ripuuhun uuden solmun siten, ettÃ¤ puu sÃ¤ilyy
 * sisÃ¤jÃ¤rjestyksessÃ¤. Jos sama alkio (.equals()) oli jo puussa, niin alkiota ei lisÃ¤tÃ¤ puuhun. Parametreina puu ja
 * alkio. Algoritmi luo uuden solmun jos lisÃ¤ys tehdÃ¤Ã¤n.  Algoritmi palauttaa totuusarvon lisÃ¤ttiinkÃ¶ alkio vai ei.
 * Algoritmin toiminta kÃ¤ydÃ¤Ã¤n lÃ¤pi luennolla. Aikavaativuus?
 **/

// Tarvitset projektiin (tai komentoriville) TRA-kirjaston Moodlesta.

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.Random;

public class TRAI_22_t15_pohja {

    public static void main(String[] args) {

        BTree<Integer> puu = new BTree<Integer>();

        int N = 12;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        System.out.println("Puuhun:");
        Random r = new Random(42);
        Integer x = 0;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N * 2);
            System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        for (int i = 0; i < N * 2; i++) {
            System.out.println("Onko " + i + " : " +
                    inorderMember(puu, i));
        }

    } // main()


    /**
     * 15. LisÃ¤ys sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun.
     * @param T binÃ¤Ã¤ripuu
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio
     * @return tehtiinkÃ¶ lisÃ¤ys vai ei
     */
    public static <E extends Comparable<? super E>>
            boolean inorderInsert(BTree<E> T, E x) {

        // TODO
        boolean onko = true;
        // jos puu on tyhjÃ¤, aseta uusi solmu juureksi
        if(T.getRoot() == null) {
            T.setRoot(new BTreeNode<E>(x));
        }
        // muuten, etsi paikka jossa alkiota yllÃ¤pitÃ¤vÃ¤
        // solmu olisi ja tee lisÃ¤ys siihen (paitsi jos
        // alkio lÃ¶ytyy)
        else {
            BTreeNode<E> n = T.getRoot();
            while (n != null) {
                if (x.equals(n.getElement())) {
                    onko = true;
                    break;
                }
                else if (x.compareTo(n.getElement()) < 0) {
                    if(n.getLeftChild() == null) {
                        n.setLeftChild(new BTreeNode<E>(x));
                        onko = false;
                        break;
                    }
                    n = n.getLeftChild();   
                }
                else {
                    if(n.getRightChild() == null) {
                        n.setRightChild(new BTreeNode<E>(x));
                        onko = false;
                        break;
                    }
                    n = n.getRightChild();
                }
            } // while
        }

        return onko;

    } // inorderInsert()


    // -------------------------------
    // esimerkkejÃ¤

    /**
     * Onko alkio sisÃ¤jÃ¤rjestetyssÃ¤ binÃ¤Ã¤ripuussa vai ei
     * @param T sisÃ¤jÃ¤rjestetty binÃ¤Ã¤ripuu
     * @param x etsittÃ¤vÃ¤ alkio
     * @param <E> alkiotyyppi
     * @return true jos alkio x on puussa, muuten false
     */
    public static <E extends Comparable<? super E>>
    boolean inorderMember(BTree<E> T, E x) {
        BTreeNode<E> n = T.getRoot();

        while (n != null) {
            if (x.compareTo(n.getElement()) == 0)
                return true;
            else if (x.compareTo(n.getElement()) < 0)
                n = n.getLeftChild();
            else
                n = n.getRightChild();
        } // while
        return false;

    } // inorderMember()


    /**
     * Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti.
     * @param T tulostettava puu
     */
    public static void inorderPrint(BTree T) {
        inorderPrintBranch(T.getRoot());
        System.out.println();
    } // inorderPrint()


    /**
     * Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti.
     * @param n tulostettavan alipuun juuri
     */
    public static void inorderPrintBranch(BTreeNode n) {
        if (n == null)
            return;

        inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        inorderPrintBranch(n.getRightChild());

    } // inorderPrintBranch()


} // class TRAI_22_t15.java
