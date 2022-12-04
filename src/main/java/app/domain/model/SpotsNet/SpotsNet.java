package app.domain.model.SpotsNet;

import app.domain.model.Route;
import app.domain.model.Spot;
import app.domain.utils.RankableMap;
import app.domain.utils.graph.Algorithms;
import app.domain.utils.graph.Graph;
import app.domain.utils.graph.map.MapGraph;
import app.domain.utils.graph.matrix.MatrixGraph;
import app.enums.EntityType;

import java.util.*;

public class SpotsNet {

    final private Graph<Spot, Route> spots;
    private HashMap<EntityType, List<Spot>> spotsByType;
    private HashMap<String,Spot> spotStore;
    private RankableMap<Spot, Double> hubs;

    public RankableMap<Spot, Double> getHubs() {
        return hubs;
    }

    public SpotsNet(){
        spots = new MapGraph<>(false);
        spotsByType = new HashMap<>();
        spotStore =new HashMap<>();
        hubs = new RankableMap<>();
    }

    public Graph<Spot, Route> getSpots() {
        return spots;
    }

    public HashMap<EntityType, List<Spot>> getSpotsByType() {
        return spotsByType;
    }

    public HashMap<String, Spot> getSpotStore() {
        return spotStore;
    }

    @Override
    public String toString() {
        return spots.toString();
    }


    public void addSpot(Spot spot){
        spots.addVertex(spot);
        if (spotsByType.get(spot.getEntity().getEttyType())!=null) {
            spotsByType.get(spot.getEntity().getEttyType()).add(spot);
        } else {
            List<Spot> spotsList = new ArrayList<>();
            spotsList.add(spot);
            spotsByType.put(spot.getEntity().getEttyType(), spotsList);
        }

        if(spotStore.get(spot.getSpotID())==null){
            spotStore.put(spot.getSpotID(),spot);
        }
    }

    public void addRoute(Route route, Spot initial, Spot end){
        spots.addEdge(initial, end, route);
    }

    /**
     * Return the numeric key of a given spot
     * @param spot
     * @return
     */
    public int keySpots(Spot spot){
        return spots.key(spot);
    }

    public Spot spotbyKey(int key){
        return spots.vertex(key);
    }

    public Boolean connectSpots(Spot spot1, Spot spot2){
        //Quando precisar de verificar quais os vertices acessiveis atraves de um dado aeroporto,
        //é o algo DFS que deve ser utilizado
        if (!spots.validVertex(spot1) || !spots.validVertex(spot2)) return false;
        LinkedList<Spot> spts = Algorithms.DepthFirstSearch(spots, spot1);
        return spts.contains(spot2);
    }

    public MatrixGraph<Spot, Route> getMinimumSpanTree(){
        return Algorithms.getMinimumSpanTreeKruskal(spots, Route::compareTo);
    }

    public boolean isConnected() {
        Spot spot1 = spots.vertex(0);
        for (Spot s : spots.vertices()) {
            if (!connectSpots(spot1, s)) return false;
        }
        return true;
    }

    public int diameter(){

        int diameter = 0;
        //Uses shortestPathEdges for all vertices
        for (int i = 0; i < spots.numVertices(); i++) {
            int max = Algorithms.shortestPathEdges(spots, spots.vertex(i));
            if (diameter < max)
                diameter = max;
        }
        return diameter;
    }

    public void defineNetworkHubs() {
        //Calcular o caminho mais curto de uma empresa a todos os vértices que sejam clientes ou produtores. (Dijkstra)

        Graph<Spot, Route> minDistGraph = Algorithms.minDistGraph(spots, Route::compareTo, Route::sum);
        int nrOfClientsAndProducers = this.getSpotsByType().get(EntityType.CLIENT).size() + this.getSpotsByType().get(EntityType.PRODUCER).size();

        for (Spot spotiEmpresa : this.getSpotsByType().get(EntityType.EMPRESA)) {

            int sum = 0;

            for (Spot spotC : this.getSpotsByType().get(EntityType.CLIENT)) {
                int distEC = minDistGraph.edge(spotiEmpresa, spotC).getWeight().getDistance();
                sum = sum + distEC;
            }
            for (Spot spotP : this.getSpotsByType().get(EntityType.PRODUCER)) {
                int distEP = minDistGraph.edge(spotiEmpresa, spotP).getWeight().getDistance();
                sum = sum + distEP;
            }

            Double media = (double) sum / nrOfClientsAndProducers;

            hubs.put(spotiEmpresa, media);
        }
    }

    /**
     * US304 - Para cada cliente (particular ou empresa) determinar o hub mais próximo.
     */
    public void findNearestHubs(){

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getShortestPathDistance(Spot spot1, Spot spot2) {
        return Algorithms.minDistGraph(spots, Route::compareTo, Route::sum).edge(spot1, spot2).getWeight().getDistance();
    }

}
