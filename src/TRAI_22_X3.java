import java.util.LinkedList;

public interface TRAI_22_X3 {


  /**
   * Uusien alkioiden lisÃ¤ys jÃ¤rjestettyyn listaan.
   * LisÃ¤Ã¤ listaan A sellaiset B:n alkiot joita ei alkujaan ollut listassa A siten, ettÃ¤ A sÃ¤ilyy jÃ¤rjestyksessÃ¤.
   * @param A kasvava lista johon lisÃ¤tÃ¤Ã¤n
   * @param B kasvava lista jonka alkiot lisÃ¤tÃ¤Ã¤n jolleivat ne olleet jo listassa A
   * @param <E> alkiotyyppi
   */
    public <E extends Comparable<? super E>> void lisaaUudet(LinkedList<E> A, LinkedList<E> B);

}
