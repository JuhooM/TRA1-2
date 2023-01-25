// TRAII_22s_t15.java SJ

import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Edge;
import fi.uef.cs.tra.Graph;
import fi.uef.cs.tra.LinkedQueue;
import fi.uef.cs.tra.Vertex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class TRAII_22s_t15_pohja {

    public static void main(String[] args) {

        // DiGraph graph = GraphMaker.createDiGraph(vertices, edges, rseed);
        DiGraph graph = Q1();

        System.out.println(GraphMaker.toString(graph, 1));

        Vertex dst = null;
        for (Vertex v : graph.vertices())
            if (v.getLabel().equals("0"))
                dst = v;
        if (dst == null)
            return;


        Set<Vertex> q = quorum(graph, dst, 0.5F);
        System.out.println("\nCompanies that are under quorum of " + dst + " : " + q);

    }   // main() 


    /**
     * 14. YhtiÃ¶llÃ¤ x on mÃ¤Ã¤rÃ¤ysvalta yhtiÃ¶ssÃ¤ y jos ja vain jos on olemassa yhtiÃ¶t z1,z 2,...,z k joissa yhtiÃ¶llÃ¤
     x on mÃ¤Ã¤rÃ¤ysvalta ja yhtiÃ¶t x,z1,z2,...,z k omistavat yhteensÃ¤ yli 50% yhtiÃ¶n y osakkeista. TÃ¤llaista
     laskentaa tarvitaan esimerkiksi yt-neuvotteluissa ja muissa lakiteknisissÃ¤ asioissa. Mallinnetaan
     omistuksia suunnatulla verkolla jossa jokainen yhtiÃ¶ on solmu ja kun yhtiÃ¶ x omistaa r% yhtiÃ¶n
     y osakkeista, niin verkossa on kaari (x,y) jonka paino on r. Hahmottele algoritmi joka etsii kaikki
     ne yhtiÃ¶t joihin yhtiÃ¶llÃ¤ x on mÃ¤Ã¤rÃ¤ysvalta.
        
        Käytettään osuuksien määrän tallentamiseen MAP.
        Merkataan mustalla omistetut vertex:it, joiden edget käydään QUE avulla läpi

        1. Lisätään omistettu vertex (alussa 0) QUE:n
        2. Käydään kaikki omistetun vertex:in edget läpi ja päivitetään mappiin omistetut arvot
        3. Jos päivityksen yhteydessä arvo ylitti limit, niin lisätään solmu QUE:n, jos se ei ole jo musta, eli lisätty kertaalleen
        4. Toistetaan kunnes kaikki solmut ja edget käyty läpi.
        5. Lisätään mapista kaikki key, joiden value ylittää limit tulokseen ja palautetaan tulos
        
     15. Toteuta tehtÃ¤vÃ¤n 15 algoritmi. SyÃ¶tteenÃ¤ ovat verkko yhtiÃ¶iden omistusosuuksista, tarkasteltava
     yhtiÃ¶ y (siis verkon solmu) ja mÃ¤Ã¤rÃ¤ysvaltaan riittÃ¤vÃ¤ osuus (yleensÃ¤ 50%). Tuloksena on se joukko
     yhtiÃ¶itÃ¤ (solmuja) jotka ovat yhtiÃ¶n y mÃ¤Ã¤rÃ¤ysvallassa.
     *
     * @param g graph of owning stocks
      * @param v the company under inspection
      * @param limit required limit of owning (e.g., 0.5)
      * @return the set of companies under quorum of v. Including v.
      **/
    static Set<Vertex> quorum(DiGraph g, Vertex v, float limit) {

        Set<Vertex> tulos = new HashSet<>();
        Map<Vertex, Float> m = new HashMap<>();
        
        // TODO
        varita(g, Graph.WHITE);
        v.setColor(Graph.BLACK);
        m.put(v, 1.0F);
        omistus(v, m, limit);
        m.forEach((ver,f) -> { 
            if(f>limit && ver != v) 
                tulos.add(ver);
        });
        return tulos;
    }

    static void omistus(Vertex start, Map<Vertex, Float> tulos, float limit) {
        LinkedQueue<Vertex> vQ = new LinkedQueue<Vertex>();
        vQ.add(start);
        
        while (!vQ.isEmpty()) {
            Vertex v = vQ.remove();

            for (Edge e : v.edges()) {
                Vertex uusi = e.getEndPoint();
                if(uusi == v) 
                    continue;

                if(v.getColor() == Graph.BLACK) 
                    tulos.merge(uusi, e.getWeight(), Float::sum);
                
                if(uusi.getColor() != Graph.BLACK && tulos.get(uusi) > limit) {
                    vQ.add(uusi);
                    uusi.setColor(Graph.BLACK);
                }
            }
        }
    }


    /**
     * VÃ¤ritÃ¤ verkon kaikki solmut.
     * @param G vÃ¤ritettÃ¤vÃ¤ verkko
     * @param c vÃ¤ri jota kÃ¤ytetÃ¤Ã¤n
     */
    static void varita(AbstractGraph G, int c) {
        for (Vertex v : G.vertices())
            v.setColor(c);
    }
    

    // example graph
    // for company "0" and limit:
    //  0.5, the result should be (0,) 1, 2, 3, 4
    //  0.6, the result should be (0,) 2
    //  0.39, the result should be (0,) 1, 2, 3, 4, 5, 6
    static DiGraph Q1() {

        int n = 7;
        DiGraph g = new DiGraph();
        Vertex[] va = new Vertex[n];
        for (int i = 0; i < n; i++) 
            va[i] = g.addVertex(""+i);

        va[0].addEdge(va[1], 0.3F);
        va[0].addEdge(va[2], 0.7F);
        va[0].addEdge(va[4], 0.2F);
        va[1].addEdge(va[3], 0.2F);
        va[2].addEdge(va[1], 0.3F);
        va[2].addEdge(va[3], 0.6F);
        va[3].addEdge(va[4], 0.4F);
        va[3].addEdge(va[5], 0.4F);
        va[3].addEdge(va[6], 0.2F);
        va[4].addEdge(va[6], 0.2F);
        va[6].addEdge(va[5], 0.2F);

        return g;
    }


}
