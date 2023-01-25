
public interface TRAI_22_X1 {

    /**
     * Taulukon toiseksi suurin alkio.
     * Jos suurimpia alkioita on useita, palautetaan niitä seuraavaksi pienempi alkio.
     * @param A Syötetaulukko.
     * @return Toiseksi pienin alkio, tai null jos alkioita on vähemmän kuin 2 tai alkioita on vain yhdenkokoisia.
     */
    Integer toiseksiSuurin(Integer[] A);

}
