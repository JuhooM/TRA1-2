import java.util.Iterator;
import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Edge;
import fi.uef.cs.tra.LinkedQueue;
import fi.uef.cs.tra.Vertex;

public class TRAII_22s_X5_juhomati implements TRAII_22s_X5 {
    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  Jos verkko tyhjä eli vertexcount = 0 niin palautetaan Float.NaN, muuten
     *  Lähdetään käymään solmuja läpi iteroimalla, kun tullaan solmuun joka ei ole musta ja on juurisolmu
     *  niin käydään koko muodostuva puu läpi leveyssuuntaisella haulla värjäten mustaksi
     *  samalla lasketaan kaarien painot yhteen ja lopulta palautetaan puun kaarien paino.
     *  Jos tämä paino on suurempi kuin 0.0F tai edellisen puun kaarien paino niin se on uusi suurin.
     *  Lopulta palautetaan suurin puun kaarien paino
     *
     *  Aikavaativuus, jossa n solmujen määrä ja e kaarien määrä verkossa, niin
     *  1)Värjätessä käydään kaikki solmut läpi, O(n)
     *  2)Iteroidessa käydään kaikki solmut ja niiden kaaret läpi, O(n + e)
     *  3)Ja jos iteroidessa satutaan käymättömän puun juurisolmuun,
     *    niin käydään kaikki sen puun solmut n ja kaaret e läpi, O(n + e)
     * 
     *  Täten aikavaativuus:
     *      O(3n + 2e) = O(n + e)
     */

    /**
     * MetsÃ¤n painavimman puun paino. Etsii verkon G komponenteista sen jonka kaarten yhteispaino on suurin. Verkko G
     * koostuu vain puista, eli kussakin komponentissa on yksi solmu johon ei johda kaarta ja kussakin komponentissa on
     * vain puukaaria (ei palukaaria, ristikkÃ¤iskaaria, eikÃ¤ etenemiskaaria). Painavimmasta puusta palautetaan
     * komponentin paino. Jos verkko on tyhjÃ¤, palautetaan Float.NaN. Jos verkossa on ainoastaan kaarettomia
     * komponentteja, palautetaan 0.0.
     *
     * @param G syÃ¶teverkko (koostuu vain puista)
     * @return painavimman komponentin kaarten painojen yhteissumma tai Float.NaN jos verkko on tyhjÃ¤
     */
    @Override
    public float painavimmanPuunPaino(DiGraph G) {
        // TODO
        if(G.vertexCount() == 0) // tyhjä verkko lopetetaan heti
            return Float.NaN;

        GraphMaker.varita(G, DiGraph.WHITE); // värjätään solmut valkoiseksi
        Float paino = 0.0F;

        Iterator<Vertex> v = G.iterator(); // iteroidaan verkon solmut
        while(v.hasNext()) {
            Vertex temp = v.next();
            if(temp.getColor() != DiGraph.WHITE || !onkoJuuri(temp)) // seuraava solmu, jos temp ei ole juuri tai on jo käyty eli ei ole valkoinen
                continue;

            Float x = painoBFS(temp); // juuresta temp muodostuvan puun paino
            if(x > paino) // painavin puu verkosta
                paino = x;
        }
        return paino;
    }

    /**
     * Puun leveyssuuntainen läpikäynti ja puun kaarien painon
     *
     * @param alku aloitussolmu
     * @return puun kaarien paino
     */
    static Float painoBFS(Vertex alku) {
        LinkedQueue<Vertex> vQ = new LinkedQueue<Vertex>();
        Float paino = 0.0F;
        vQ.add(alku);
        
        while (!vQ.isEmpty()) {
            Vertex v = vQ.remove();

            for (Edge e : v.edges()) {
                Vertex uusi = e.getEndPoint();
                if(uusi == v) 
                    continue;

                if(uusi.getColor() == DiGraph.WHITE) {
                    vQ.add(uusi);
                    paino += e.getWeight();
                } 
            }
        }
        return paino;
    }

    /**
     * Palauttaa onko Vertex v juurisolmu
     *
     * @param v tutkittava solmu
     * @return onko solmu v juuri vai ei
     */
    static boolean onkoJuuri(Vertex v) {
        for (Edge e : v.edges()) {
            if(e.getEndPoint() == v) {
                return false;
            }
        }
        return true;
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
}
