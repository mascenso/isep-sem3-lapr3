package app.domain.model.SpotsNet;

public class Route implements Comparable<Route>{

    public final double meters;

    public Route(double meters) {

        this.meters = meters;
    }

    public double getDistance() {
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
