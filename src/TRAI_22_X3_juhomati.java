import java.util.LinkedList;
import java.util.ListIterator;

public class TRAI_22_X3_juhomati implements TRAI_22_X3 {
    //                  ^^^^^ oma tunnus tÃ¤hÃ¤n

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  Liikutaan 2 listaa eteenpäin, ja kun pitää lisätä toisesta listasta
     *  alkio, niin pakitetaan lisättävässä 1 ja lisätään se. ->
     *  Eli, O(n + m) = O(n)
     */


    /**
     * Uusien alkioiden lisÃ¤ys jÃ¤rjestettyyn listaan.
     * LisÃ¤Ã¤ listaan A sellaiset B:n alkiot joita ei alkujaan ollut listassa A siten, ettÃ¤ A sÃ¤ilyy jÃ¤rjestyksessÃ¤.
     *
     * @param A   kasvava lista johon lisÃ¤tÃ¤Ã¤n
     * @param B   kasvava lista jonka alkiot lisÃ¤tÃ¤Ã¤n jolleivat ne olleet jo listassa A
     * @param <E> alkiotyyppi
     */
    public <E extends Comparable<? super E>> void lisaaUudet(LinkedList<E> A, LinkedList<E> B) {

        // TODO
        ListIterator<E> li1 = A.listIterator();
        ListIterator<E> li2 = B.listIterator();
        while(li1.hasNext() || li2.hasNext()) {
            if(!li2.hasNext()) {
                break;
            }
            else if(li2.hasNext() && !li1.hasNext()) {
                if(li1.hasPrevious()) {
                    E x = li1.previous();
                    E y = li2.next();
                    if(x.compareTo(y) < 0) {
                        li1.next();
                        li1.add(y);
                        lisaaSamat(li1, li2, y);
                    }
                    else {
                        li1.next();
                    }
                }
                else {
                    E y = li2.next();
                    li1.add(y);
                    lisaaSamat(li1, li2, y);
                }
            }
            else {
                E x = li1.next();
                E y = li2.next();
                if(x.compareTo(y) <= 0) {
                    li2.previous();
                }
                else {
                    li1.previous();
                    if(li1.hasPrevious()) { // Miksi (li1.hasPrevious() && li1.previous().compareTo(y) < 0) yhtenä if-lauseena ei toimi
                        if(li1.previous().compareTo(y) < 0) {
                            li1.next();
                            li1.add(y);
                            lisaaSamat(li1, li2, y);
                        }
                    }
                    else {
                        li1.add(y);
                        lisaaSamat(li1, li2, y);
                    }
                }
            }
        }
    }

    public <E extends Comparable<? super E>> void lisaaSamat(ListIterator<E> lista1, ListIterator<E> lista2, E y2) {
        ListIterator<E> li1 = lista1;
        ListIterator<E> li2 = lista2;
        E y = y2;
        while(li2.hasNext()) {
            if(y.compareTo(li2.next()) == 0) {
                li2.previous();
                li1.add(li2.next());
            }
            else {
                li2.previous();
                break;
            }
        }
    }
}
