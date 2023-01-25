import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;
import fi.uef.cs.tra.LinkedQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class TRAI_22_t16_17_pohja {

    public static void main(String[] args) {

        BTree<Integer> puu = null;

        int N = 15;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // testataan ensin vakiopuulla

        puu = exampleBTree();
        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti:");
        inorderPrint(puu);

        // tulostus tehtÃ¤vÃ¤n 16 avulla
        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤ t16:");
        BTreeNode<Integer> n = inorderFirst(puu);
        while (n != null) {
            System.out.print(n.getElement() + " ");
            n = inorderNext(n);
        }
        System.out.println();

        n = matalinYksiLapsinen(puu);
        if (n != null)
            System.out.println("Matalin yksilapsinen= " + n.getElement());
        else
            System.out.println("Ei yksilapsista");


        // tehdÃ¤Ã¤n uusi puu

        puu = new BTree<Integer>();


        System.out.println("\nPuuhun:");
        Random r = new Random(42);
        Integer x = null;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N * 2);
            System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti:");
        inorderPrint(puu);

        // tulostus tehtÃ¤vÃ¤n 16 avulla
        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤ t16:");
        n = inorderFirst(puu);
        while (n != null) {
            System.out.print(n.getElement() + " ");
            n = inorderNext(n);
        }
        System.out.println();


        n = matalinYksiLapsinen(puu);
        if (n != null)
            System.out.println("Matalin yksilapsinen= " + n.getElement());
        else
            System.out.println("Ei yksilapsista");

    } // main()


    /**
     * Palauttaa binÃ¤Ã¤ripuun sisÃ¤jÃ¤rjestyksessÃ¤ ensimmÃ¤isen solmun.
     *
     * @param T Tarkasteltava puu.
     * @return SisÃ¤jÃ¤rjestyksessÃ¤ ensimmÃ¤inen solmu tai null jos puu on tyhjÃ¤.
     */
    public static <E> BTreeNode<E> inorderFirst(BTree<E> T) {

        BTreeNode<E> n = T.getRoot();
        if (n == null)
            return null;
        // Ã¤Ã¤rimmÃ¤isenÃ¤ vasemmalla oleva solmu
        while (n.getLeftChild() != null)
            n = n.getLeftChild();
        return n;
    }

    /**
     * Palauttaa binÃ¤Ã¤ripuun solmun sisÃ¤jÃ¤rjestyksessÃ¤ seuraajasolmun.
     *
     * @param n   BinÃ¤Ã¤ripuun solmu.
     * @param <E> solmun alkioiden tyyppi
     * @return seuraajasolmu sisÃ¤jÃ¤rjestyksessÃ¤ tai null jollei seuraajaa ole.
     */
    public static <E> BTreeNode<E> inorderNext(BTreeNode<E> n) {

        //TODO

        // jos solmulla on oikea lapsi, haetaan sen
        // vasemmanpuoleinen jalkelainen
        if(n.getRightChild() != null) {
            n = n.getRightChild();
            while(n.getLeftChild() != null) {
                n = n.getLeftChild();
            }
            return n;
        }

        // muutoin haetaan ensimmainen esivanhempi jonka
        // vasemmassa alipuussa solmu n on
        else if(n.getRightChild() == null) {
            while(n.getParent() != null && n.getParent().getRightChild() != null && n.getParent().getRightChild().equals(n)) {
                n = n.getParent();
            }
            if(n.getParent() != null) {
                n = n.getParent();
                return n;
            }
        }

        // jollei loytynyt
        return null;

    }


    /**
     * 17. Kirjoita algoritmi joka etsii binÃ¤Ã¤ripuun vÃ¤hiten syvÃ¤n (lÃ¤himpÃ¤nÃ¤ juurta olevan) sellaisen
     * solmun jolla on tasan yksi lapsi. Vihje: tasoittainen lÃ¤pikÃ¤ynti. Aikavaativuus?
     *
     * @param T syÃ¶tepuu
     * @return lÃ¤hinnÃ¤ juurta oleva solmu jolla on tasan yksi lapsi
     */
    public static <E> BTreeNode<E> matalinYksiLapsinen(BTree<E> T) {

        // TODO
        LinkedQueue<BTreeNode<E>> Q = new LinkedQueue<BTreeNode<E>>();
        if (T.getRoot() != null)
            Q.offer(T.getRoot());
        while (! Q.isEmpty()) {
            BTreeNode<E> n = Q.poll();
            if(n.getLeftChild() != null && n.getRightChild() != null) {
                n = n.getLeftChild();
            }
            if(n.getLeftChild() == null || n.getRightChild() == null) {
                return n;
            }
            while (n != null) {
                Q.offer(n);
                n = n.getRightChild();
            }
        }
        return null;
        
    } // matalinYksiLapsinen()


    // esimerkkejÃ¤ ja pohjia


    public static BTree<Integer> exampleBTree() {

        BTree<Integer> T = new BTree<Integer>();

        // juuri
        T.setRoot(new BTreeNode<Integer>(9));

        BTreeNode<Integer> n = T.getRoot();

        // juuren lapset
        n.setLeftChild(new BTreeNode<Integer>(5));
        n.setRightChild(new BTreeNode<Integer>(15));

        // vasen haara
        BTreeNode<Integer> l = n.getLeftChild();

        l.setLeftChild(new BTreeNode<Integer>(3));
        l.setRightChild(new BTreeNode<Integer>(8));

        l.getLeftChild().setRightChild(new BTreeNode<Integer>(4));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));


        System.out.println("                 ");
        System.out.println("       9        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5        15   ");
        System.out.println("  / \\      /  \\  ");
        System.out.println(" 3   8   12    18");
        System.out.println("  \\      /\\      ");
        System.out.println("   4    11 13    ");
        System.out.println("                 ");

        return T;

    } // exampleBTree()


    /**
     * 22. LisÃ¤ys sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun.
     *
     * @param T binÃ¤Ã¤ripuu
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio
     * @return tehtiinkÃ¶ lisÃ¤ys vai ei
     */
    public static <E extends Comparable<? super E>>
    boolean inorderInsert(BTree<E> T, E x) {

        // System.out.println("TÃ¤mÃ¤ lisÃ¤tÃ¤Ã¤n vasta perjantain 23.9. harjoitusten jÃ¤lkeen.");
        // return false;

        BTreeNode<E> n = T.getRoot();
        if (n == null) {
            T.setRoot(new BTreeNode<E>(x));
            return true;
        }

        while (true) {

            if (x.compareTo(n.getElement()) == 0)
                // prev jo puussa, eli lisÃ¤tÃ¤
                return false;

            else if (x.compareTo(n.getElement()) < 0) {
                // prev edeltÃ¤Ã¤ n:n alkiota
                if (n.getLeftChild() == null) {
                    // lisÃ¤yskohta lÃ¶ydetty
                    n.setLeftChild(new BTreeNode<E>(x));
                    return true;
                } else
                    n = n.getLeftChild();
            } else {
                // prev suurempi kuin n
                if (n.getRightChild() == null) {
                    // lisÃ¤yskohta lÃ¶ydetty
                    n.setRightChild(new BTreeNode<E>(x));
                    return true;
                } else
                    n = n.getRightChild();
            }
        } // while

    } // inorderInsert()


    // Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti
    public static void inorderPrint(BTree T) {
        inorderPrintBranch(T.getRoot());
        System.out.println();
    } // inorderPrint()


    public static void inorderPrintBranch(BTreeNode n) {
        if (n == null)
            return;

        inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        inorderPrintBranch(n.getRightChild());

    } // inorderPrintBranch()


} // class TRAI_22_t17.java
