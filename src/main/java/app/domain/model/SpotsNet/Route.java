package app.domain.model.SpotsNet;

public class Route implements Comparable<Route>{

    public final int meters;

    public Route(int meters) {

        this.meters = meters;
    }

    public int getDistance() {
        return meters;
    }

    @Override
    public String toString() {
        return meters + "meters\n";
    }

    @Override
    public int compareTo(Route o) {
        return Double.compare(this.meters, o.meters);
    }

    public static Route sum(Route r1, Route r2){
        return new Route(r1.getDistance() + r2.getDistance());
    }
}
