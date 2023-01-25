import fi.uef.cs.tra.BTree;

public interface TRAI_22_X4 {

    /**
     * Palauttaa tiedon ovatko binÃ¤Ã¤ripuun alkiot puussa sisÃ¤jÃ¤rjestyksessÃ¤ vai ei.
     * @param T tarkasteltava puu
     * @return true jos alkiot ovat sisÃ¤jÃ¤rjestyksessÃ¤, muuten false
     * @param <E> alkioiden tyyppi
     */
    public <E extends Comparable<? super E>> boolean onkoSisaJarjestyksessa(BTree<E> T);

}
