import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TRAII_22s_X6_juhomati implements TRAII_22s_X6 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  Aluksi jaotellaan alkiot osalistoihin, siten että kummassa summa pienempi niin siihen laitetaan suurempi
     *  ja pienempi toistesta alkioista täten laitetaan listaan jonka summa suurempi.
     *  Jos ei olla päästy summien ero 0 tapaukseen, niin vaihdellaan listojen alkioita keskenään, siten että
     *  valitaan satunnaisia alkioita kunnes näiden vaihtaminen pienentäisi listojen keskeistä eroa.
     *  
     *  Koodissa ongelma, jos luvut eivät lähellä toisia ja niitä vähän niin ei välttämä päästä
     *  hyvin pieneen ero tapaukseen, koska ei löydy lukupareja siten että ero pienenisi. esim
     *     Testi, n=20 sum=7628 maxAika=3
     *      SyÃ¶te: [144, 415, 207, 260, 540, 276, 352, 380, 217, 426, 320, 450, 452, 493, 480, 562, 517, 504, 278, 355]
     *      summa1=3815 summa2=3813 ero=2
     *      tulos1: [415, 207, 276, 380, 426, 320, 452, 480, 504, 355]  3,815
     *      tulos2: [144, 260, 540, 217, 450, 493, 562, 278, 517, 352]  3,813
     *  tästä ei voi enää edetä tällä valitulla haaralla, mutta jos vaihtoja olisi tehnyt eri
     *  järjestyksessä olisi mahdollista päästä tarkempaan 
     *  
     *  Joten algoritmia voisi parantaa tekemällä alkioJako useampaan kertaan ensimmäisen vaiheen listalle josta haaraudutaan erilaisiin vaihtoehtoihin
     *  ja lopuksi valitaan pienimmän eron listat.
     * 
     *  Yritin tätä, mutta kun tein map johon alkioJako metodi lisäsin key ArrayList<ArrayList<Integer>>(0 indeksissä lista1 ja 1 indeksissä lista2) ja value Integer(summien ero)
     *  ja lopuksi loopatun jakoAlkio jälkeen asetetaan tulos1 ja tulos2 mappiin tallenetut arraylistit joiden ero vastaa mapissa olevaa pienintä value.
     *  
     *  Tämä ei kuitenkaan toiminut, vaikka printissä min value näytti melkein aina 0 niin testiohjelma näytti silti tulos1 ja tulos2 eroksi suuria. Ja luovutin.
     *  
     *  aikaa käytetään (aikajal)/2 ,jossa /2 kuinka kauan alkoJako ajetaan per listat.
     *  n=20 pieniä lukuja: Ero keskimÃ¤Ã¤rin 0.00
     *  n=20 isompia lukuja: Ero keskimÃ¤Ã¤rin 2.40
     *  n=2000: Ero keskimÃ¤Ã¤rin 0.00
     *  n=20 000: Ero keskimÃ¤Ã¤rin 0.00
     *  Ero kaikissa 20 testeissÃ¤ keskimÃ¤Ã¤rin 0.60
     * 
     *  Testasin myös (aikajal)/300 ja antoi hyviä tuloksia, joten alkiojako looppaaminen olisi hyvä idea,
     *  koska aikaa jää selvästi yli kun ajaa vain yhden tapauksen. Tietysti riippuu koneestakin.
     */

    /**
     * Jaottelee kokonaislukulista alkiot kahteen tasapituiseen listaan osaan siten, ettÃ¤ kukin syÃ¶telistan alkio kopioidaan
     * jompaankumpaan tuloslistaan (mutta ei molempiin). Kumpaankin tuloslistaan on tultava yhtÃ¤ monta alkiota
     * (syÃ¶telistassa on siis parillinen mÃ¤Ã¤rÃ¤ alkioita). Tavoitteena on, ettÃ¤ tuloslistojen alkioiden summat
     * olisivat mahdollisimman lÃ¤hellÃ¤ toisiaan. Koska tehtÃ¤vÃ¤ on NP vaikea, optimaalista tulosta ei yleensÃ¤
     * saavuteta, mutta pyritÃ¤Ã¤n kohtuullisessa ajassa jotenkin kohtuulliseen tulokseen.
     * Algoritmin on suoriuduttava tehtÃ¤vÃ¤stÃ¤ maxAika sekunnissa.
     * @param syote syÃ¶telista, tÃ¤tÃ¤ ei saa muuttaa mitenkÃ¤Ã¤n
     * @param tulos1 toinen tulos (kutsuttaessa tyhjÃ¤, palautettaessa sisÃ¤ltÃ¤Ã¤ tasan puolet alkioista)
     * @param tulos2 toinen tulos (kutsuttaessa tyhjÃ¤, palautettaessa sisÃ¤ltÃ¤Ã¤ tasan puolet alkioista)
     * @param maxAika suurin kÃ¤ytettÃ¤vissÃ¤ oleva aika (sekunteja)
     */
    @Override
    public void jaaTasanKahteenTasakokoiseen(ArrayList<Integer> syote, ArrayList<Integer> tulos1, ArrayList<Integer> tulos2, int maxAika) {
        // Aika maxAikaa varten
        long aika = System.nanoTime();

        // Varmuuden vuoksi tarkastus viallisen syÃ¶tteen varalle
        if (syote.size() % 2 != 0)
            throw new RuntimeException("SyÃ¶telistan alkiomÃ¤Ã¤rÃ¤ oli pariton, ei voi tasajakaa!");

        // Jaottelee alkiot listaan siten, että kummassa isompi summa, 
        // niin siihen laitetaan pienempi alkio ja isompi alkio listaan,
        // jossa pienenmpi summa.
        Iterator<Integer> it = syote.iterator();
        Integer t1summa = 0;
        Integer t2summa = 0;
        while (it.hasNext()) {
            Integer x = it.next();
            Integer y = it.next();
            if(t1summa > t2summa) {
                if(x > y) {
                    tulos2.add(x);
                    tulos1.add(y);
                    t2summa += x;
                    t1summa += y;
                }
                else {
                    tulos2.add(y);
                    tulos1.add(x);
                    t2summa += y;
                    t1summa += x;
                }
            }
            else {
                if(x > y) {
                    tulos2.add(y);
                    tulos1.add(x);
                    t2summa += y;
                    t1summa += x;
                }
                else {
                    tulos2.add(x);
                    tulos1.add(y);
                    t2summa += x;
                    t1summa += y;
                }
            }
        }
        Integer ero = Math.abs(t1summa-t2summa);
        if(ero == 0)
            return;
        
        // Yritetään pienentää summien eroa vaihtamalla alkioita keskenään
        long aikajal = maxAika*1000000000L - (System.nanoTime() - aika);
        alkioJako(tulos1, t1summa, tulos2, t2summa, aikajal/2, 26);
    }

    public void alkioJako(ArrayList<Integer> t1, Integer t1s, ArrayList<Integer> t2, Integer t2s, long mAika, int seed) {
        long x = System.nanoTime();
        Integer uusit1summa = t1s;
        Integer uusit2summa = t2s;
        Random rnd = new Random(seed);
        while(System.nanoTime()-x < mAika) {
            if(t1s-t2s == 0)
                break;
                
            boolean seis = false;
            if(t1s > t2s) {       
                int t1ind = rnd.nextInt(t1.size());
                int t2ind = rnd.nextInt(t2.size());
                while(t1.get(t1ind) < t2.get(t2ind)) {
                    if(System.nanoTime()-x > mAika) {
                        seis = true;
                        break;
                    }
                    t1ind = rnd.nextInt(t1.size()-1);
                    t2ind = rnd.nextInt(t2.size()-1);
                }
                if(seis) 
                    break;
                uusit1summa = uusit1summa - t1.get(t1ind) + t2.get(t2ind);
                uusit2summa = uusit2summa - t2.get(t2ind) + t1.get(t1ind);
                if(Math.abs(t1s-t2s) > Math.abs(uusit1summa - uusit2summa)) {
                    t1s = uusit1summa;
                    t2s = uusit2summa;
                    t1.add(t2.remove(t2ind));
                    t2.add(t1.remove(t1ind));
                }
                else {
                    uusit1summa = t1s;
                    uusit2summa = t2s;
                }
            }
            else {
                int t1ind = rnd.nextInt(t1.size());
                int t2ind = rnd.nextInt(t2.size());
                while(t1.get(t1ind) > t2.get(t2ind)) {
                    if(System.nanoTime()-x > mAika) {
                        seis = true;
                        break;
                    }
                    t1ind = rnd.nextInt(t1.size()-1);
                    t2ind = rnd.nextInt(t2.size()-1);
                }
                if(seis) 
                    break;
                uusit1summa = uusit1summa - t1.get(t1ind) + t2.get(t2ind);
                uusit2summa = uusit2summa - t2.get(t2ind) + t1.get(t1ind);
                if(Math.abs(t1s-t2s) > Math.abs(uusit1summa - uusit2summa)) {
                    t1s = uusit1summa;
                    t2s = uusit2summa;
                    t1.add(t2.remove(t2ind));
                    t2.add(t1.remove(t1ind));
                }
                else {
                    uusit1summa = t1s;
                    uusit2summa = t2s;
                }
            }
        }
    }
}
