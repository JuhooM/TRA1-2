public class TRAI_22_X1_juhomati implements TRAI_22_X1 {

    /**
     * ITSEARVIOINTI:
     * Algoritmi toimii niin kauvan kunhan tarkasteltava taulu ei sisällä lukua -2147483648 joka on Integer.MIN_VALUE
     * Jos taulu sisältää tämän luvun, niin vaaditaan 2 isompaa ja erisuuruista lukua, että toimii
     */

    /**
     * Taulukon toiseksi suurin alkio.
     * Jos suurimpia alkioita on useita, palautetaan niitÃ¤ seuraavaksi pienempi alkio.
     *
     * @param A syötetaulukko
     * @return Toiseksi suurin alkio, tai null jos alkioita on vähemmän kuin 2 tai kaikki sama
     */
    @Override
    public Integer toiseksiSuurin(Integer[] A) {

        //syötetyn taulun pituus
        int n = A.length;
        //pienin arvo joka voi esiintyä (-2 147 483 648)
        int pienin = Integer.MIN_VALUE;

        //taulukossa vähintään 2 lukua
        if(n >= 2) {

            //isoin
            int eka = A[0];
            //toisiksi isoin
            int toka = pienin;

            //for loop käy taulukon läpi
            for(int i = 1; i < n; i++) {
                if(A[i] > eka) {
                    toka = eka;
                    eka = A[i];      
                }
                
                else if(A[i] > toka && A[i] < eka) {
                    toka = A[i];
                }
                continue;
            }

            if(toka > pienin) {
                return toka;
            }
            else {
                return null;
            }
            
        }

        //null koska alle 2 lukua
        else {
            return null;
        }
    }

}
