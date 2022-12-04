package app.domain.model.SpotsNet;

import app.domain.model.Route;
import app.domain.model.Spot;
import app.domain.utils.graph.Algorithms;
import app.domain.utils.graph.Edge;
import app.domain.utils.graph.Graph;
import app.domain.utils.graph.map.MapGraph;

import java.util.*;

public class SpotsNet {

    final private Graph<Spot, Route> spots;
    private HashMap<Character, List<Spot>> spotsByType;
    private HashMap<String,Spot> spotStore;
    private List<Spot> hubs;

    public SpotsNet(){
        spots = new MapGraph<>(false);
        spotsByType = new HashMap<>();
        spotStore =new HashMap<>();
        hubs = new ArrayList<>();
    }

    public Graph<Spot, Route> getSpots() {
        return spots;
    }

    public HashMap<Character, List<Spot>> getSpotsByType() {
        return spotsByType;
    }

    public List<Spot> getHubs() {
        return hubs;
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
            spotStore.put(spot.getSpotID(),spot);
        }
    }


    public void addRoute(Route route, Spot initial, Spot end){
        spots.addEdge(initial, end, route);
    }



    //todo:rever
    public List<Route> getAllRoutes(){
        List<Route> routes = new ArrayList<>();

        Collection<Edge<Spot, Route>> edges = spots.edges();

           for (Edge<Spot, Route> edge : edges) {
                routes.add( edge.getWeight() );
            }
        return routes;
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

    public Graph<Spot, Route> getMinimumSpanTree(){
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


}
