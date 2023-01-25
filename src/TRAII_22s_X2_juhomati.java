import java.util.*;

public class TRAII_22s_X2_juhomati implements TRAII_22s_X2 {
    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  Mittauksessa offerpoolia toistetaan niin monta kertaa kuin kerkeää
     *  muuttujan aikaraja rajaaman ajan verran. Sitten palautetaan yhdelle toistolle 
     *  keskimääräinen aika jakamalla aikaraja tehtyjen toistojen määrällä.
     *  Tehtävässä aikaraja oli 0.1s max kesto, omalla koneella 99990000ns toimi vielä
     *  ja tietysti suuremmalla ajalla saa vielä tarkempia tuloksia. Koneesta riippuen
     *  aikarajaa voi muuttaa alemmas, jos 0.1s ei toteudu esim. aikaraja = 100000000/2.
     * 
     *  Testeistä huomaa selvästi, että offer/poll metodit ovat vakioaikaisia, sillä
     *  pakan koon muuttaminen ei vaikuta testien aikaan kun aikaraja 0.05s = 100000000/2,
     *  jos aikarajaksi asetetaan liian pieni, niin testit ei enää tarkkoja, tällöin parempi olisi
     *  ottaa mediaani yksittäisten offerpoll kestojen tuloksista. 
     *  Puolestaan jos aikarajaa asettaa suureksi saadaan keskiarvot tarkemmiksi. 
     *  Omalla koneella 99990000ns antoi eri syötesuuruuksille vain 1ns heittoja.
     *  Välimuistikin näytti riittävän omassa koneessa 10000 alkioisille pakoille sillä
     *  ajat samoja kuin muilla syötesuuruuksilla. Myöskään syötteen muuttaminen ei vaikuta
     *  vakioaikaisiin operaatioihin. Randomi luku metodi tietysti vain hidastaa suoritusta,
     *  mutta syötteestä riippumatta lineaarisia.
     * 
     *  Keskiarvon ottamisessa tuloksissa on yksi huono puoli sillä se laskee mukaan molemmat ääri tulokset.
     *  Mutta tässä tapauksessa äärituloksia ei tapahdu paljoa ja ne eivät eroa muista tuloksista paljoa.
     *  Joten riittää ottaa tarpeeksi iso testien toisto aika tarkkoja tuloksia varten.
     * 
     *  Aikaraja: 100000000/2 ns
     *  Random luku metodin kanssa: ArrayDeque kaikki 65-67ns, LinkedList 67-68ns, ConcurrentLinkedDeque 75-79ns
     *  Vakio luku: ArrayDeque kaikki 28-30ns, LinkedList 31-33ns, ConcurrentLinkedDeque 38-39ns
     *  
     *  Aikaraja: 99990000 ns
     *  Random luku metodin kanssa: ArrayDeque kaikki 65-66ns, LinkedList 67-68ns, ConcurrentLinkedDeque 74-75ns
     *  Vakio luku: ArrayDeque kaikki 28-29ns, LinkedList 31-32ns, ConcurrentLinkedDeque 38-39ns
     **/


    /**
     * Mittaa annetun pakan offerFirst-pollLast -operaatioparin aikavaativuuden nanosekunteina.
     * Mittaa ns. normaalin onnistuneen suorituksen tilanteessa jossa lisÃ¤ys ja poisto tehtiin
     * kokoelmasta.
     * Pakkaan jÃ¤Ã¤ lopuksi yhtÃ¤ monta alkiota kuin siellÃ¤ oli entuudestaan.
     *
     * @param D testattava pakka
     * @return offerFirst-pollLast -operaatioparin operaation normikesto nanosekunteina
     */
    @Override
    public long offerPollAika(Deque<Integer> D) {

        // TODO
        int toistot = 0;
        long aikaraja = 99990000;
        long alku = System.nanoTime();
        while(System.nanoTime() - alku < aikaraja) {
            offerFirstpollLast(D, true);
            toistot++;
        }
        return aikaraja/toistot;
    }

    public Integer rndInt() {
        Random r = new Random();
        int x = r.nextInt(100000);
        return x;
    }

    public void offerFirstpollLast(Deque<Integer> D, boolean satunnainen) {
        if(satunnainen) {
            D.offerFirst(rndInt());
            D.pollLast();
        }
        else {
            D.offerFirst(1);
            D.pollLast();
        }
    }
}
