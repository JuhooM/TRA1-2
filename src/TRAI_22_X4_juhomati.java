import java.util.ArrayList;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public class TRAI_22_X4_juhomati implements TRAI_22_X4 {
    //                  ^^^^^ oma tunnus tÃ¤hÃ¤n

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * Järjestyksessä olevan arraylistin tarkistus on O(m): m=list.size()
     * Arraylistin täyttäminen iorderSearch metodilla puun alkioiden määrä O(n)
     * Täten aikavaativuus lineaarinen O(2n) = O(n)
     */

    /**
     * Palauttaa tiedon ovatko binÃ¤Ã¤ripuun alkiot puussa sisÃ¤jÃ¤rjestyksessÃ¤ vai ei.
     * Apumetodeja saa ja kannattaa kÃ¤yttÃ¤Ã¤, kunhan tÃ¤mÃ¤n metodin otsikko pysyy ennallaan.
     * @param T tarkasteltava puu
     * @return true jos alkiot ovat sisÃ¤jÃ¤rjestyksessÃ¤, muuten false
     */
    @Override
    public <E extends Comparable<? super E>> boolean onkoSisaJarjestyksessa(BTree<E> T) {
        // TODO
        ArrayList<E> ok = new ArrayList<>();
        inorderSearch(T.getRoot(), ok);
        if(ok.size() < 2) {
            return true;
        }
        else {
            for(int i = 0; i < ok.size() -1; i++) {
                if(ok.get(i).compareTo(ok.get(i+1)) >= 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Haku sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti.
     * ja lisää alkiot listaan. 
     */
    public static <E extends Comparable<? super E>> void inorderSearch(BTreeNode<E> n, ArrayList<E> l) {
        if (n == null)
            return;

        inorderSearch(n.getLeftChild(), l);
        l.add(n.getElement());
        inorderSearch(n.getRightChild(), l);
    }
}
