package app.domain.model;
import java.util.Objects;
public class Spot implements Comparable<Spot>{

    private String spotID;
    private double lat;
    private double lng;
    private Entity entity;
    private boolean isHub;
    public Spot() {
        spotID = null;
        lat = 0;
        lng = 0;
        entity = null;
        isHub = false;
    }

    public Spot(String spotID, double lat, double lng, Entity entity) {
        setSpotID(spotID);
        setLat(lat);
        setLng(lng);
        setEntity(entity);
        isHub = false;
    }

    public Spot(Spot spot) {
        setSpotID(spot.getSpotID());
        setLat(spot.getLat());
        setLng(spot.getLng());
        setEntity(spot.getEntity());
        setHub(spot.getIsHub());
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

    public void setHub(boolean hub) {
        isHub = hub;
    }

    public String getSpotID() {
        return spotID;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean getIsHub() {
        return isHub;
    }

    @Override
    public String toString() {
        return "spotID: " + spotID;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Spot spot = (Spot)o;
        return this.isHub == spot.getIsHub() && this.spotID.equals(spot.getSpotID()) && this.lng == spot.getLng() &&this.lat == spot.getLat() && this.entity.equals(spot.getEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotID);
    }

    @Override
    public int compareTo(Spot s){
        return this.spotID.compareTo(s.getSpotID());
    }

}