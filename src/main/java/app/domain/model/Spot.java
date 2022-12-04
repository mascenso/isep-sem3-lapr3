package app.domain.model;

import java.util.Objects;

public class Spot {

    private String spotID;
    private double lat;
    private double lng;
    private Entity entity;

    public Spot(String spotID){
        this.spotID = spotID;
        this.lat = 0;
        this.lng = 0;
        this.entity = new Entity();
    }

    public Spot(String spotID, double lat, double lng, String spotTypeID){
        Entity entity = new Entity(spotTypeID);
        this.spotID = validateSpotID(spotID);
        this.lat = lat;
        this.lng = lng;
        this.entity = entity;
    }

    public String validateSpotID(String spotIDa){
        if(spotIDa == null || spotIDa.isEmpty()){
            throw new IllegalArgumentException("SpotID cannot be null or empty.");
        }
        if(!spotIDa.startsWith("CT"))
            throw new IllegalArgumentException("Invalid SpotID");

        try {
            Integer.parseInt(spotIDa.substring(2));
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid SpotID");
        }
        return spotIDa;
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
