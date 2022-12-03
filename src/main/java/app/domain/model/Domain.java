package app.domain.model;
import app.domain.graph.Graph;
import app.domain.model.SpotsNet.*;
import app.domain.store.UserRoleStore;
import org.junit.platform.commons.util.StringUtils;

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
     * For each Empresa, find shortest path to Cliente and Produtores Agricolas
     */
    public void defineNetworkHubs() {
        //US303 - Definir os hubs da rede de distribuição, ou seja, encontrar as N empresas mais próximas de
        //todos os pontos da rede (clientes e produtores agrícolas). A medida de proximidade deve ser
        //calculada como a média do comprimento do caminho mais curto de cada empresa a todos os
        //clientes e produtores agrícolas. (small, N=3)

        //Para cada empresa:
        //Calcular o caminho mais curto de uma empresa a todos os vértices que sejam clientes ou produtores. (Dijkstra)
        //Calcular a média: distância/nr de caminhos

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
    public Graph<Spot, Route> obtainMinimumSpanTree() {
        Graph<Spot, Route> mst = spotsNet.getMinimumSpanTree();
        System.out.println(mst);
            return mst;
    }


    public int obtainDiameter() {
        return (int) spotsNet.diameter();
    }
}
