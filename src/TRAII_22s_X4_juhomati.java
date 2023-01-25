import java.util.Iterator;

import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.Graph;
import fi.uef.cs.tra.Vertex;

public class TRAII_22s_X4_juhomati implements TRAII_22s_X4 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  Lähdetään käymään vertexejä läpi iteroimalla, kun tullaan solmuun joka ei ole musta
     *  niin käydään koko komponentti läpi ja värjätään mustaksi, jos ei ole kehää lisätään maara 1.
     *  Jatketaan läpikäyntiä ja lopulta palautetaan maara
     *
     *  Aikavaativuus, jossa n solmujen määrä, ja e kaarien määrä
     *  iteroidessa käydään kaikki solmut läpi ja jokaiselle komponentille käydään
     *  kerran sen jokainen solmu ja sen naapurit läpi.
     *  O(2n + e) = O(n + e)
     */


    /**
     * Vapaiden puiden lukumÃ¤Ã¤rÃ¤ suuntaamattomassa verkossa G.
     *
     * @param G suuntaamaton verkko
     * @return kehÃ¤ttÃ¶mien komponenttien mÃ¤Ã¤rÃ¤
     */
    @Override
    public int puidenMaara(Graph G) {
        // TODO
        GraphMaker.varita(G, Graph.WHITE);
        int maara = 0;
        Iterator<Vertex> v = G.iterator();
        while(v.hasNext()) {
            Vertex temp = v.next();
            if(temp.getColor() == Graph.BLACK)
                continue;
            if(!kehadfs(temp, null))
                maara++;
        }
        return maara;
    }

    
    /**
     * Solmujen dfs läpikäynti ja kehäisyyden selvittäminen
     *
     * @param alku aloitussolmu
     * @param edeltaja edellinen solmu
     * @return onko kehää
     */
    static boolean kehadfs(Vertex alku, Vertex edeltaja) {

        // lÃ¤pikÃ¤ynti tÃ¤stÃ¤ solmusta kesken == harmaa
        alku.setColor(Graph.GRAY);

        for (Vertex vertex : alku.neighbors()) {
            // ei palata taakse kuljettua reittiä
            if(vertex == edeltaja)
                continue;

            // harmaa naapurisolmu: kehÃ¤ muodostuu
            if (vertex.getColor() == Graph.GRAY)
                return true;
                
            else if (vertex.getColor() == Graph.WHITE)
                // rekursiokutsu lÃ¤pikÃ¤ymÃ¤ttÃ¶mÃ¤Ã¤n naapuriin
                if (kehadfs(vertex, alku))
                    return true;

        }
        // lÃ¤pikÃ¤ynti tÃ¤stÃ¤ solmusta pÃ¤Ã¤ttynyt == musta
        alku.setColor(Graph.BLACK);
        return false;
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

    // apumetodeja saa ja kannattaa kÃ¤yttÃ¤Ã¤
}
