package app.domain.model.SpotsNet;

public class Route implements Comparable<Route>{

    public final double meters;

    public Route(double meters) {

        this.meters = meters;
    }

    @Override
    public String toString() {
        return meters + "meters\n";
    }

    @Override
    public int compareTo(Route o) {
        return Double.compare(this.meters, o.meters);
    }
}
