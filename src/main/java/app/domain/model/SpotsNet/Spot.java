package app.domain.model.SpotsNet;

import app.domain.model.Entity;

import java.util.Objects;

public class Spot {

    private String spotID;
    private double lat;
    private double lng;
    private Entity entity;

    public Spot() {
    }

    public Spot(String spotID){
        this.spotID = spotID;
        this.lat = 0; //TODO
        this.lng = 0; //TODO
        this.entity = new Entity();
    }

    public Spot(String spotID, double lat, double lng, Entity entity){
        this.spotID = spotID;
        this.lat = lat;
        this.lng = lng;
        this.entity = entity;
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

    public void setEntity(Entity entity) {
        this.entity = entity;
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
