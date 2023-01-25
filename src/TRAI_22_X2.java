import java.util.ArrayList;

public interface TRAI_22_X2 {

    /**
     * Kasvavien listojen leikkaus, eli yhteiset alkiot.
     * Palauttaa sellaiset alkiot jotka lÃ¶ytyvÃ¤t listasta A ja listasta B.
     * Kukin alkio tulee tuloslistaan vain kertaalleen vaikka se esiintyisi
     * toisessa tai molemmissa syÃ¶telistoissa useammin kuin kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     * @param A ensimmÃ¤inen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @param B toinen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @return leikkauslista
     */
    ArrayList<Integer> kasvavienLeikkaus(ArrayList<Integer> A, ArrayList<Integer> B);

}
