import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class TRAI_22_t11_pohja {
   public static void main(String[] args) {

     Random rnd = new Random();
     // taulukoiden koko
     int N = 10;
     if (args.length > 0)
       N = Integer.parseInt(args[0]);

     // satunnaislukusiemen
     int siemen = N;
     if (args.length > 1)
       siemen = Integer.parseInt(args[1]);


     rnd.setSeed(siemen);

     // testataan parilla eri syÃ¶tteellÃ¤
     LinkedList<String> L = satunnainenMjonolista(rnd, N, 4, 1);
     System.out.println("\nSyÃ¶te: " + L);
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + L);

     L = satunnainenMjonolista(rnd, N, 3, 1);
     System.out.println("\nSyÃ¶te: " + L);
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + L);


     L = satunnainenMjonolista(rnd, N, 2, 1);
     System.out.println("\nSyÃ¶te: " + L);
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + L);

     L = satunnainenMjonolista(rnd, N, 1, 1);
     System.out.println("\nSyÃ¶te: " + L);
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + L);




   }


    /**
     * Poistaa listasta L saman alkion perÃ¤kkÃ¤iset esiintymÃ¤t.
     * @param L muokattava lista
     * @param <E> alkiotyyppi
     */
  static public <E> int poistaPerakkaisetDuplikaatit(LinkedList<E> L) {

    // 0-1 alkioisista listoista ei poisteta mitÃ¤Ã¤n
    if (L.size() < 2)
      return 0;

    // TODO
    else {
      ListIterator<E> li = L.listIterator();
      int poistetut = 0;
      while(li.hasNext()) {
        E x = li.next();
        if(li.hasNext()) {
          E y = li.next();
          if(x.equals(y)) {
            li.remove();
            poistetut++;
            li.previous();
          }
          else {
            li.previous();
          }
        }
      }
      return poistetut;
    }
  }




    public static LinkedList<String> satunnainenMjonolista(Random r, int n, int s, int len) {
        LinkedList<String> L = new LinkedList<>();

        for (int i = 0; i < n; i++)
            L.add(randomString(r, len, s));

        return L;
    }

    public static String randomString(Random r, int len, int s) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(s) + 'a');
        return new String(C);
    }



}
