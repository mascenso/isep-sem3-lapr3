package app.domain.model;
import app.domain.store.UserRoleStore;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

public class Domain {

    private String designation;
    private UserRoleStore userRoleStore;

    private SpotsNet spotsNet;
    //Main graph:
    //Nodes: clientes-produtores.csv
    //Edges: distancias.csv

    public SpotsNet getSpotsNet() {
        return spotsNet;
    }

    public void setSpotsNet(SpotsNet spotsNet) {
        this.spotsNet = spotsNet;
    }

    public Domain(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.userRoleStore = new UserRoleStore();
        setSpotsNet(new SpotsNet());
        //Default roles added in App/Bootstrap()
    }

    public String getDesignation() {
        return designation;
    }

    public List<UserRole> getUserRoles() {
        return userRoleStore.getUserRoles();
    }
    public UserRoleStore getUserRoleStore() {
        return this.userRoleStore;
    }

    public void addSpot(String spotID, double lat, double lng, String spotTypeID ){
        Entity entity = new Entity(spotTypeID);
        this.spotsNet.addSpot(spotID, lat, lng, entity);
    }

    public Collection<Spot> getAdjacentSpots(String spotID) {
        return spotsNet.getAdjacentSpots(spotID);
    }

    public void addRoute(String spotID1, String spotID2, double meters){
        this.spotsNet.addRoute(spotID1, spotID2, meters);
    }

    /**
     * US301 - Constructs a graph based on information from read files data
     */
    public void constructMainGraph() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * US302 - Checks if the graph is connected, and if it is, it returns the number of generations
     */
    public void checkIfGraphIsConnected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * US303 - define network Hubs
     * For each Empresa, find shortest path to Cliente and Produtores Agricolas
     */
    public void defineNetworkHubs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * US304 - For each Client (Particular ou Empresa), find nearest HUB.
     */
    public void findNearestHub() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * US305 - Obtain MST for all nodes in the graph
     */
    public void obtainMinimumSpanTree() {
            spotsNet.getMinimumSpanTree();
    }





}
