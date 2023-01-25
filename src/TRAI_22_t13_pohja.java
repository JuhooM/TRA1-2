import java.util.LinkedList;
import java.util.Stack;

public class TRAI_22_t13_pohja {

	public static void main(String[] args) {

        // pinon koko
		int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // kÃ¤Ã¤nnettÃ¤vien mÃ¤Ã¤rÃ¤
		int k = 3;
        if (args.length > 1)
            k = Integer.parseInt(args[1]);


		Stack<String> S = new Stack<>();
		for (int i = 0; i < N; i++)
			S.push("a" + i);

		if (N <= 20)
			System.out.println(S);

		reverse_k(S, k);
		if (N <= 20)
			System.out.println(S);

	} // main()

    /**
      * 13) Kirjoita algoritmi joka kÃ¤Ã¤ntÃ¤Ã¤ pinon k pÃ¤Ã¤llimmÃ¤istÃ¤ alkiota, eli
      * vaihtaa pinon k:n pÃ¤Ã¤llimmÃ¤isen alkion jÃ¤rjestyksen pÃ¤invastaiseksi.
      * Jos pinossa on â‰¤k alkiota, algoritmi kÃ¤Ã¤ntÃ¤Ã¤ koko pinon. KÃ¤ytÃ¤ apuna
      * joko tietorakennekirjaston LinkedStack -kokoelmaa tai Java API:n
      * Stack -kokoelmaa.  Aikavaativuus? Tilavaativuus?
      */
    public static <E> void reverse_k(Stack<E> S, int k) {

		// TODO
        LinkedList<E> ok = new LinkedList<E>();
        for(int i = 0; i < k; i++) {
            if(!S.isEmpty()) {
                ok.add(S.peek());
                S.pop();
            }
            else {
                break;
            }
        }
        for(E x : ok) {
            S.push(x);
        }
	}

} // class
