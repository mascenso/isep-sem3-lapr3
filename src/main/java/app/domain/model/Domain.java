package app.domain.model;
import app.domain.graph.Graph;
import app.domain.model.SpotsNet.*;
import app.domain.store.UserRoleStore;
import com.sun.source.tree.Tree;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;

public class Domain {

    private String designation;
    private UserRoleStore userRoleStore;

    //Main graph:
    //Nodes: clientes-produtores.csv
    //Edges: distancias.csv
    private SpotsNet spotsNet;


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
        this.spotsNet.addSpot(spotID, lat, lng, spotTypeID);
    }


    public Collection<Spot> getAdjacentSpots(String spotID) {
        return spotsNet.getAdjacentSpots(spotID);
    }

    public void addRoute(String spotID1, String spotID2, int meters){
        this.spotsNet.addRoute(spotID1, spotID2, meters);
    }

    /**
     * US301 - Constructs a graph based on information from read files data
     */
    public void constructMainGraph() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * US302 - Checks if the graph is connected, and if it's not,
     * it returns the minimum number of edges
     */
    public boolean checkIfGraphIsConnected() {
        //Se a busca em largura (DFS) chegar a todos os vértices, é conexo!!
        return spotsNet.isConnected();
    }

    /**
     * US303 - define network Hubs
     * Find the most central enterprises in the network, and define them as hubs
     * Centrality is defined as length of all shortests paths / number of nodes
     */
    public TreeMap<Double, List<Spot>> defineNetworkHubs(int n) {
        //Para cada empresa:
        //Calcular o caminho mais curto de uma empresa a todos os vértices que sejam clientes ou produtores.
        // (Usou-se o FW)
        //Calcular a média: distância/nr de caminhos
        this.spotsNet.defineNetworkHubs();
        TreeMap<Double, List<Spot>> map = this.spotsNet.getHubs();

        TreeMap<Double, List<Spot>> mapN = map.entrySet().stream()
                .limit(n)
                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        System.out.println("Hubs: " + mapN);
        return mapN;
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
    public Graph<Spot, Route> obtainMinimumSpanTree() {
        Graph<Spot, Route> mst = spotsNet.getMinimumSpanTree();
        System.out.println(mst);
            return mst;
    }


    public int obtainDiameter() {
        return spotsNet.diameter();
    }
}
