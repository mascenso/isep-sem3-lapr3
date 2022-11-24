package app.domain.model;

import java.util.Objects;

public class Spot {

    private String spotID;
    private double lat;
    private double lng;
    private String spotTypeID;

    public Spot() {
    }

    public Spot(String spotID){
        this.spotID = spotID;
        this.lat = 0; //TODO
        this.lng = 0; //TODO
        this.spotTypeID = "C1"; //TODO
    }

    public Spot(String spotID, double lat, double lng, String spotTypeID ){
        this.spotID = spotID;
        this.lat = lat;
        this.lng = lng;
        this.spotTypeID = spotTypeID;
    }

    public void setSpotID(String spotID) {
        this.spotID = spotID;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setSpotTypeID(String spotTypeID) {
        this.spotTypeID = spotTypeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return Objects.equals(spotID, spot.spotID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotID);
    }

    @Override
    public String toString() {
        return "spotID: " + spotID;
    }
}
