import java.util.*;

public class TRAII_22s_X3_juhomati implements TRAII_22s_X3 {
    //                    ^^^^^ oma tunnus tÃ¤hÃ¤n


    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  C täytetään random luvuilla eri kuin 0 ja 0 on etsittävä luku jos loytyy=false
     *  Jos loytyy==true niin asetetaan etsittäväksi alkioksi(y) jokin satunnainen C alkio.
     *  Testien ottamiseen while loopille annettu aikaa 0.01s per alkiomäärä.
     *  Testissä otetaan sadasta containssista keskimääräinen suoritusaika, 5 eri kertaa ja näistä valitaan pienin.
     *  Tämä pienin lisätään tuloksia tauluun, josta otetaan size()/3 alkio eli mediaanista hiukan pienempi.
     * 
     *  100 containsin keskimääräinen suoritusaika mahdollistaa nopean contains operaation mittaamisen.
     *  Tulokset kuitenkin voivat heitellä, joten 5stä kerrasta pienin antaa realistisemman arvon.
     *  Lopuksi järjestetty taulu ja sieltä 1/3 alkio myös sulkee pois mahdollisia virheellisiä mittauksia.
     *  
     *  Testeistä huomaa että,
     * 
     *  Hashset contains vaikioaikainen selvästi
     *      tulokset: löytyy false; kaikki 4ns, 
     *                löytyy true; kaikki 6ns paitsi 1kpl 7ns eli 6ns 
     *                  (en tiedä miksi löytyy true menee pidempään kuin löytyy falsessa)
     * 
     *  Treeset contains logaritiminen selvästi
     *      tulokset: löytyy false; kasvaa aina noin 1ns kun syötekoko kaksinkertaistuu.
     *                löytyy true; kasvaa aina noin 1ns kun syötekoko kaksinkertaistuu.
     *                  (löytyy true taas kestää pidempään)
     * 
     *  Arraylist contains lineaarinen selvästi
     *      tulokset: löytyy false; aika kaksinkertaistuu kun syöte kaksinkertaistuu.
     *                löytyy true; aika kaksinkertaistuu kun syöte kaksinkertaistuu.
     *                  (nyt löytyy false hitaampi koska joutuu käymään koko C läpi, 
     *                   puolestaan jos true voi lopettaa kun satunnainen alkio löytyy)
     */

    Random rnd = new Random(System.currentTimeMillis());


    /**
     * Annetun kokoelman contains-operaation aikavaativuus erilaisilla kokoelman alkiomÃ¤Ã¤rillÃ¤.
     * Aika lasketaan kullekin alkiomÃ¤Ã¤rÃ¤lle jotka lÃ¶ytyvÃ¤t kuvauksen tulokset avaimista.
     * Kukin aika talletetaan kuvauksen tulokset ko. avaimen arvoksi.
     * @param C        testattava kokoelma (aluksi tyhjÃ¤)
     * @param tulokset kuvaus jonka avaimet kertovat testattavat alkiomÃ¤Ã¤rÃ¤t
     * @param loytyy   testataanko tilannetta jossa alkiot lÃ¶ytyvÃ¤t (true) kokoelmasta vai ei
     */
    @Override
    public void containsNopeus(Collection<Double> C, SortedMap<Integer, Long> tulokset, boolean loytyy) {

        // TODO ehkÃ¤ tÃ¤hÃ¤n

        // eri alkiomÃ¤Ã¤rÃ¤t joilla mittaus pitÃ¤Ã¤ tehdÃ¤:
        for (int alkioMaara : tulokset.keySet()) {
            int x = rnd.nextInt(alkioMaara);
            double y = 0;
            ArrayList<Long> tuloksia = new ArrayList<>();

            // TODO tÃ¤hÃ¤n ainakin pitÃ¤Ã¤ tehdÃ¤ jotain
            for(int i = 0; i < alkioMaara; i++) {
                double temp = rnd.nextDouble();
                while(temp == 0) {
                    temp = rnd.nextDouble();
                }
                C.add(temp);
                if(loytyy && x == i)
                    y = temp;
            }

            long aika1 = System.nanoTime();
            while(System.nanoTime()-aika1 < tulokset.keySet().size()*10000000) {
                long tulos = Long.MAX_VALUE;
                for(int j = 0; j < 5; j++) {
                    long alku = System.nanoTime();
                    for(int i = 0; i < 100; i++) {
                        C.contains(y);
                    }
                    long loppu = System.nanoTime();
                    if(loppu-alku < tulos)
                        tulos = loppu-alku;
                }
                tuloksia.add((tulos)/100);
            }
            Collections.sort(tuloksia);

            // tulosten tallettaminen
            tulokset.put(alkioMaara, tuloksia.get(tuloksia.size()/3) /* TODO tÃ¤hÃ¤n se tulos nanosekunteina */ );
            
        }

        // TODO ehkÃ¤ tÃ¤nnekin

    }


    /**
     * Luo ArrayList:n jossa jossa on n satunnaista liukulukua.
     * Saa kÃ¤yttÃ¤Ã¤ jos haluaa.
     * @param n liukulukujen mÃ¤Ã¤rÃ¤
     * @return liukulukutaulukko
     */
    private ArrayList<Double> satunnaisia(int n) {
        ArrayList<Double> A = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            A.add(rnd.nextDouble()*n);
        return A;
    }


}
