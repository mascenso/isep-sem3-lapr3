package app.domain.model;
public class Route implements Comparable<Route> {

    public int meters;
    public Route() {
        this.meters = -1;
    }

    public Route(int meters) {
        setMeters(meters);
    }

    public Route(Route route) {
        setMeters(route.getDistance());
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    public int getDistance() {
        return meters;
    }

    @Override
    public String toString() {
        return String.format("%d meters", meters);
    }

    @Override
    public int compareTo(Route o) {
        return (this.meters > o.getDistance()) ? 1 : (this.meters == o.getDistance()) ? 0 : -1;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Route otherObj = (Route)obj;
        return this.meters == otherObj.getDistance();
    }

    public static Route sum(Route r1, Route r2) {
        return new Route(r1.getDistance() + r2.getDistance());
    }
}