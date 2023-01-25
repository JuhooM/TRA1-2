import fi.uef.cs.tra.ListNode;
import fi.uef.cs.tra.TraLinkedList;

import java.util.Random;

public class TRAI_22_t12_pohja {
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
     TraLinkedList<String> L = satunnainenMjonolista(rnd, N, 4, 1);
     System.out.println("\nSyÃ¶te: " + listastaMerkkijono(L));
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + listastaMerkkijono(L));

     L = satunnainenMjonolista(rnd, N, 3, 1);
     System.out.println("\nSyÃ¶te: " + listastaMerkkijono(L));
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + listastaMerkkijono(L));


     L = satunnainenMjonolista(rnd, N, 2, 1);
     System.out.println("\nSyÃ¶te: " + listastaMerkkijono(L));
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + listastaMerkkijono(L));

     L = satunnainenMjonolista(rnd, N, 1, 1);
     System.out.println("\nSyÃ¶te: " + listastaMerkkijono(L));
     poistaPerakkaisetDuplikaatit(L);
     System.out.println("Tulos: " + listastaMerkkijono(L));




   }


    /**
     * Poistaa listasta L saman alkion perÃ¤kkÃ¤iset esiintymÃ¤t.
     * @param L muokattava lista
     * @param <E> alkiotyyppi
     */
  static public <E> int poistaPerakkaisetDuplikaatit(TraLinkedList<E> L) {

      // TODO
        int poistetut = 0;
        ListNode<E> x = L.first();
        while(x != L.EOL) {
            ListNode<E> y = x.next();
            if(y != L.EOL) {
              if(x.getElement().equals(y.getElement())) {
                L.remove(y);
                poistetut++;
              }
              else {
                x = x.next();
              }
            }
            else {
              break;
            }
        }
        return poistetut;
  }
  




    public static TraLinkedList<String> satunnainenMjonolista(Random r, int n, int s, int len) {
        TraLinkedList<String> L = new TraLinkedList<>();

        for (int i = 0; i < n; i++)
            L.insert(L.EOL, randomString(r, len, s));

        return L;
    }

    public static String randomString(Random r, int len, int s) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(s) + 'a');
        return new String(C);
    }

    public static  <E> String listastaMerkkijono(TraLinkedList<E> L) {
     StringBuilder sb = new StringBuilder();
     sb.append("( ");
     ListNode<E> n = L.first();
     while (n != L.EOL) {
       sb.append(n.getElement());
       sb.append(" ");
       n = n.next();
     }
     sb.append(")");
     return sb.toString();
    }


}
