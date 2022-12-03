package app.domain.model.SpotsNet;

import app.domain.graph.Algorithms;
import app.domain.graph.Edge;
import app.domain.graph.Graph;
import app.domain.graph.map.MapGraph;

import java.util.*;

public class SpotsNet {

    final private Graph<Spot, Route> spots;
    private Map<Character, List<Spot>> spotsByType;
    private Map<Double, List<Spot>> hubs;

    public SpotsNet(){
        spots = new MapGraph<>(false);
        spotsByType = new HashMap<>();
        hubs = new TreeMap<>();
    }

    public Graph<Spot, Route> getSpots() {
        return spots;
    }


    public HashMap<Character, List<Spot>> getSpotsByType() {
        return new HashMap<>(spotsByType);
    }

    public HashMap<Double, List<Spot>> getHubs() {
        return new HashMap<>(hubs);
    }

    public void addSpot(String spotID, double lat, double lng, String spotTypeID){
        try {

            Character keyType = validateSpotTypeID(spotTypeID);
            Spot spot = new Spot(spotID, lat, lng, spotTypeID);
            spots.addVertex(spot);
            if (spotsByType.containsKey(keyType)) {
                spotsByType.get(keyType).add(spot);
            } else {
                List<Spot> spotsList = new ArrayList<>();
                spotsList.add(spot);
                spotsByType.put(keyType, spotsList);
            }

        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void addRoute(String spotID1, String spotID2, int distance){
        //change this to find the spots on the maps
        Spot spot1 = new Spot(spotID1);
        Spot spot2 = new Spot(spotID2);
        Route rt = new Route(distance);

        spots.addEdge(spot1, spot2, rt);
    }

    public List<Route> getAllRoutes(){
        List<Route> routes = new ArrayList<>();

        Collection<Edge<Spot, Route>> edges = spots.edges();

           for (Edge<Spot, Route> edge : edges) {
                routes.add( edge.getWeight() );
            }
        return routes;
    }

    public Character validateSpotTypeID(String spotID){

        //remove charAt(0) and check if the rest is a number
        String spotIDNumber = spotID.substring(1);
        try{
            Integer.parseInt(spotIDNumber);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid SpotID");
        }catch (NullPointerException e){
            throw new IllegalArgumentException("SpotID cannot be null");
        }
        if(spotID.charAt(0) == 'C' || spotID.charAt(0) == 'E' || spotID.charAt(0) == 'P'){
            return spotID.charAt(0);
        }
        else{
            throw new IllegalArgumentException("Invalid SpotID");
        }
    }

    public Collection<Spot> getAdjacentSpots(String spotID) {
        return spots.adjVertices(new Spot(spotID));
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

    @Override
    public String toString() {
        return spots.toString();
    }
}
