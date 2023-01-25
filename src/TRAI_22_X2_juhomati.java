import java.util.ArrayList;

public class TRAI_22_X2_juhomati implements TRAI_22_X2 {
    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * koodiin merkattu aikavaativuus: 
     * paljon vakioita ja 2 lineaarista tapausta
     * 1. lineaarinen while loop on listojen "A" ja "B" yhteinen pituus, mutta se ei kiinnosta eli O(n) joka on,
     * pidemmän listan pituus. Myöhemmin for loop aika on "vali" listan pituus eli myös O(n), täten
     * V: aikakompleksisuus O(2n) eli O(n) "lineaarinen"
     */

    /**
     * Kasvavien listojen leikkaus, eli yhteiset alkiot.
     * Palauttaa sellaiset alkiot jotka lÃ¶ytyvÃ¤t listasta A ja listasta B.
     * Kukin alkio tulee tuloslistaan vain kertaalleen vaikka se esiintyisi
     * toisessa tai molemmissa syÃ¶telistoissa useammin kuin kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     *
     * @param A ensimmÃ¤inen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @param B toinen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @return leikkauslista
     */
    @Override
    public ArrayList<Integer> kasvavienLeikkaus(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> tulos = new ArrayList<>(); //O(1)
            
        // TODO
        ArrayList<Integer> vali = new ArrayList<>(); //O(1)
        int i = 0; //O(1)
        int j = 0; //O(1)
        while(i<A.size() && j<B.size()) { //O(n + m) = O(n), jossa n A.size() ja m B.size()
            if(A.get(i).intValue() < B.get(j).intValue()) { //O(1)
                i++; //O(1)
            }
            else if(B.get(j).intValue() < A.get(i).intValue()) { //O(1)
                j++; //O(1)
            }
            else {
                vali.add(A.get(i)); //O(1)
                i++; //O(1)
                j++; //O(1)
            }
        }

        if(vali.size() > 1) { //O(1)
            for(int x = 0; x < vali.size() - 1; x++) { //O(n) vali listan pituus
                if(vali.get(x).intValue() != vali.get(x+1).intValue()) { //O(1)
                    tulos.add(vali.get(x)); //O(1)
                }
            }
            if(tulos.size() > 0) { //O(1)
                if(vali.get(vali.size()-1).intValue() != tulos.get(tulos.size()-1).intValue()) { //O(1)
                    tulos.add(vali.get(vali.size()-1)); //O(1)
                }
            }
            else {
                tulos.add(vali.get(0)); //O(1)
            }
        }
        else {
            tulos = vali; //O(1)
        }
        return tulos; //O(1)
    }
}
