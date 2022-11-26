package app.domain.model;

import app.domain.graph.Graph;
import app.domain.graph.map.MapGraph;

import java.util.Collection;

public class SpotsNet {

    private static class Route {

        public final double meters;

        public Route(double meters) {

            this.meters = meters;
        }

        @Override
        public String toString() {
            return meters + "meters";
        }
    }

    final private Graph<Spot, Route> spots;

    public SpotsNet(){
        spots = new MapGraph<>(true);
    }

    public void addSpot(String spotID, double lat, double lng, Entity entity ){
        Spot spot = new Spot(spotID, lat, lng, entity);
        spots.addVertex(spot);
    }

    public void addRoute(String spotID1, String spotID2, double distance){
        Spot spot1 = new Spot(spotID1);
        Spot spot2 = new Spot(spotID2);
        Route rt = new Route(distance);

        spots.addEdge(spot1, spot2, rt);
    }

    public Collection<Spot> getAdjacentSpots(String spotID) {
        return spots.adjVertices(new Spot(spotID));
    }



    public int keySpot(String spot){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String spotbyKey(int key){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean connectSpots(String spot1, String spot2){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return spots.toString();
    }
}
