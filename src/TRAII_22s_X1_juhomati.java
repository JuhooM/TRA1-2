import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

/**
 * ITSEARVIOINTI TÃ„HÃ„N:
 *  Aikavaativuus O(n), jossa n puun alkioiden määrä,
 *  sillä pahimmassa tapauksessa koodi käy kaikki alkiot läpi
 *  ja tarkastaa onko edellinen (parent) pienempi tai yhtäsuuri.
 *
 */


/**
 * X1. Kirjoita algoritmi joka tarkistaa onko annettu binÃ¤Ã¤ripuu keko (eli kasa) vai ei. Keko on binÃ¤Ã¤ripuu
 jossa jokaisen solmun sisÃ¤ltÃ¤mÃ¤ alkio edeltÃ¤Ã¤ (on â€pienempiâ€) tai on yhtÃ¤suuri kuin minkÃ¤Ã¤n
 sen jÃ¤lkelÃ¤isen sisÃ¤ltÃ¤mÃ¤ alkio. Jos binÃ¤Ã¤ripuussa on alkioita jotka eivÃ¤t ole keskenÃ¤Ã¤n oikeassa
 jÃ¤rjestyksessÃ¤, algoritmi palauttaa epÃ¤toden, muuten toden. Puun tasapainoa ei tarkastella.
 Parametrina binÃ¤Ã¤ripuu, palautusarvona totuusarvo. Vihje: rekursio. MikÃ¤ on algoritmisi
 aikavaativuus?
 */
public class TRAII_22s_X1_juhomati implements TRAII_22s_X1 {

    /**
     * Palauttaa tiedon onko annettu binÃ¤Ã¤ripuu keko (eli kasa) vai ei.
     * Keossa jokainen solmu edeltÃ¤Ã¤ (tai on samanarvoinen) kuin kaikki jÃ¤lkelÃ¤isensÃ¤.
     *
     * @param T SyÃ¶tepuu.
     * @return true jos kysessÃ¤ on keko, muuten false
     */
    @Override
    public <E extends Comparable<? super E>> boolean onkoKeko(BTree<E> T) {
        // TODO
        // vihje: tarkastele rekursiivisesti kunkin solmun alkiota
        // rekursiivinen metodi ottaa siis parametrinaan (ainakin) binÃ¤Ã¤ripuusolmun
        // apumetodeja saa ja kannattaa kÃ¤yttÃ¤Ã¤  
        // seuraava >= edellinen
        return onkoKekoUtil(T.getRoot());
    }

    private <E extends Comparable<? super E>> boolean onkoKekoUtil(BTreeNode<E> node) {
        if(node == null) 
            return true;
        
        if(node.getParent() == null) 
            return onkoKekoUtil(node.getRightChild()) && onkoKekoUtil(node.getLeftChild());

        else if(node.getElement().compareTo(node.getParent().getElement()) >= 0)
            return onkoKekoUtil(node.getRightChild()) && onkoKekoUtil(node.getLeftChild());

        else
            return false;

    }
}
