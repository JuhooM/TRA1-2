

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class TRAI_22_t28_pohja {

    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_22_t28 [N] [K] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, K on sotkemisen aste (alkioiden etÃ¤isyys oikealta paikalta)
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukon koko
        int n = 15;
        if (args.length > 0) n = Integer.parseInt(args[0]);

        // kuinka kauas alkiota voidaan siirtÃ¤Ã¤ oikealta paikalta
        int k = 3;
        if (args.length > 1) k = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2) siemen = Integer.parseInt(args[2]);

        // luodaan esimerkkitaulukko
        Integer[] A = new Integer[n];

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n; i++) {
            A[i] = r.nextInt(n*2);
        }

        Arrays.sort(A);

        // tulostetaan taulukko jos alkioita ei ole paljoa
        if (n <= 20 && k <= 20) {
            System.out.println("Aalkuper:  " + Arrays.toString(A));
        }


        sotkeK(A, k);

        // tulostetaan taulukko jos alkioita ei ole paljoa
        if (n <= 20 && k <= 20) {
            System.out.println("Asotkettu: " + Arrays.toString(A));
        }

        jarjesta28(A, k);

        System.out.print("Atulos:    ");
        if (n <= 20 && k <= 20) {
            System.out.println(Arrays.toString(A));
        } else {
            System.out.println(A.length + " alkioinen taulukko");
            // huom: tÃ¤mÃ¤ tulostaa taulukon koon, ei todellisten alkioiden mÃ¤Ã¤rÃ¤Ã¤!
        }

    } // main()


    /**
     * JÃ¤rjestÃ¤Ã¤ taulukon A alkiot kasvavaan jÃ¤rjestykseen kunhan kukin alkio
     * on korkeintaan k aseman pÃ¤Ã¤ssÃ¤ oikealta kohdalta
     * @param A     syÃ¶tetaulukko
     * @param k     maksimi virhe-etÃ¤isyys
     * @param <E>   alkiotyyppi
     */
    public static <E extends Comparable<? super E>> void jarjesta28(E[] A, int k) {

        // TODO
        // kopioi tÃ¤hÃ¤n tehtÃ¤vÃ¤n 27 algoritmi algoritminotaatiolla kommenteiksi
        // ja kÃ¤Ã¤nnÃ¤ algoritminotaatio rivi kerrallaan Javaksi
        // Ã„LÃ„ TEE MITÃ„Ã„N MUUTA!
        // Kaiken pitÃ¤Ã¤ tulla suunnitelmasta!
        // Aluksi lisätään for loopilla k verran alkioita pq(priorityqueue)
        // Sitten aletaan käymään alkioita läoi, lisäämällä pq 1. alkio A[0] jne.
        // Kun ollaan A listan viimeinen alkio lisäätty niin lisätään loput
        // pq olevat alkiot järjestyksessä viimeisille A paikoille.
        // Aikavaativuus: pq add ja poll ovat logk aikavaativia, joten 
        // 1. for loop k*logk || 2. for loop A*2logk || 3. for loop k*logk
        // joten näistä saadaan O(A*logk), jossa A on tarkasteltava lista ja k järjestyksessä olevien määrä
        PriorityQueue<E> pq = new PriorityQueue<>();
        for(int i = 0; i <= k; i++) {
            pq.add(A[i]);
        }
        int indeksi = 0;
        for(int i = k + 1; i <= A.length - 1; i++) {
            A[indeksi++] = pq.peek(); //O(1)
            pq.poll();
            pq.add(A[i]);
        }

        Iterator<E> itr = pq.iterator();
        while (itr.hasNext()) {
            A[indeksi++] = pq.peek();
            pq.poll();
        }
    }


    /**
     * Sotkee taulukon A alkiot siten, ettÃ¤ kukin alkio siirtyy
     * korkeintaan k indeksi pÃ¤Ã¤hÃ¤n
     * @param A sotkettava taulukko
     * @param k maksimi siirtymisetÃ¤isyys
     * @param <E>   alkiotyyppi
     */
    public static <E> void sotkeK(E[] A, int k) {
        Random rnd = new Random(A.length + k);
        int n = A.length;
        // pidetÃ¤Ã¤n kirjaa mitkÃ¤ indeksit on jo siirretty niin ei
        // siiretÃ¤ mitÃ¤Ã¤n uudestaan (ehkÃ¤ liian kauaksi)
        boolean[] siirretty = new boolean[n];
        for (int i = 0; i < n; i++)
            siirretty[i] = false;
        for (int i = 0; i < n; i++) {
            // satunnaiset indeksi
            int i1 = rnd.nextInt(n-k);
            int i2 = i1+rnd.nextInt(k)+1;
            // ei siirretÃ¤ jos jompi kumpi on jo aiemmin siirretty
            if (siirretty[i1] || siirretty[i2])
                continue;
            // vaihdetaan pÃ¤ikseen
            E tmp = A[i1];
            A[i1] = A[i2];
            A[i2] = tmp;
            // merkitÃ¤Ã¤n nÃ¤mÃ¤ siirretyiksi
            siirretty[i1] = true;
            siirretty[i2] = true;
        }
    }


} // class
