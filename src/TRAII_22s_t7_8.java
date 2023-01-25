// TRAII_22s_t3_4.java SJ

import java.util.*;

public class TRAII_22s_t7_8 {

    public static void main(String[] args) {
        for(int i = 10; i<20; i++) {
            System.out.println("\n"+ Math.pow(2, i)+" alkiota jonossa");
            testContains((int)Math.round(Math.pow(2, i)), 999900000);
        }

        for(int i = 1; i<10; i++) {
            System.out.println("\n"+ Math.pow(2, i)+" alkiota kokoelmassa");
            testaddAll((int)Math.round(Math.pow(2, i)), 999900000);
        }
    } // main()

    static void testContains(int alkiomaara, long aikaraja) {
        PriorityQueue<Integer> V = randomPriorityQueue(alkiomaara);
        int toistot = 0;
        long alku = System.nanoTime();
        while(System.nanoTime() - alku < aikaraja) {
            prioqueContains(V, rndInt());
            toistot++;
        }
        System.out.println("containseja tehty " + toistot + "kpl, " + aikaraja+ "ns aikana.");
        System.out.println("keskimääräinen contains aika: " + aikaraja/toistot +"ns");
    }

    static void testaddAll(int alkiomaara, long aikaraja) {
        PriorityQueue<Integer> V = new PriorityQueue<>();
        Collection<Integer> D = randomCollection(alkiomaara, new HashSet<Integer>());
        long kokoaika = 0;
        int toistot = 0;
        while(kokoaika < aikaraja) {
            kokoaika = kokoaika + prioqueaddAll(V, D);
            toistot++;
        }
        System.out.println("addAll tehty " + toistot + "kpl, " + kokoaika+ "ns aikana.");
        System.out.println("keskimääräinen addAll aika: " + kokoaika/toistot +"ns");
    }

    static PriorityQueue<Integer> randomPriorityQueue(int quekoko) {
        PriorityQueue<Integer> V = new PriorityQueue<>();
        for (int i = 0; i < quekoko; i++)
            V.add(rndInt());
        return V;
    }

    static Collection<Integer> randomCollection(int collkoko, Collection<Integer> V) {
        for (int i = 0; i < collkoko; i++)
            V.add(rndInt());
        return V;
    }

    static void prioqueContains(PriorityQueue<Integer> V, Integer x) {
        V.contains(x);
    }

    static long prioqueaddAll(PriorityQueue<Integer> V, Collection<Integer> D) {
        long alku = System.nanoTime();
        V.addAll(D);
        long loppu = System.nanoTime();
        return loppu - alku;
    }
    
    static Integer rndInt() {
        Random r = new Random();
        int x = r.nextInt(100000);
        return x;
    }

} // class

