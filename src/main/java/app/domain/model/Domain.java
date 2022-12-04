package app.domain.model;
import app.domain.utils.Configurations;
import app.domain.utils.CsvParser;
import app.domain.utils.RankableMap;
import app.domain.model.SpotsNet.*;
import app.domain.store.UserRoleStore;
import app.domain.utils.graph.matrix.MatrixGraph;
import app.interfaces.CONSTANT;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;

public class Domain {

    //todo:talvez não seja necessário
    private String designation;

    private UserRoleStore userRoleStore;

    private SpotsNet spotsNet;


    public Domain(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        setDesignation(designation);
        this.userRoleStore = new UserRoleStore();

        this.spotsNet= new SpotsNet();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public SpotsNet getSpotsNet() {
        return spotsNet;
    }

    public UserRoleStore getUserRoleStore() {
        return this.userRoleStore;
    }


    /**
     * US301 - Constructs a graph based on information from read files data
     */
    public void constructMainGraph() throws IOException {
        CsvParser inputDataGraph = new CsvParser();
        inputDataGraph.getCSVdata(Configurations.getFile(CONSTANT.FILE_CLIENTES_PRODUTORES),
                                  Configurations.getDelimiter(CONSTANT.DELIMITERC),this);
        inputDataGraph.getCSVdata(Configurations.getFile(CONSTANT.FILE_DISTANCES),
                                  Configurations.getDelimiter(CONSTANT.DELIMITERC),this);
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
    public RankableMap<Spot, Double> defineNetworkHubs(int n) {
        //Para cada empresa:
        //Calcular o caminho mais curto de uma empresa a todos os vértices que sejam clientes ou produtores.
        // (Usou-se o FW)
        //Calcular a média: distância/nr de caminhos
        this.spotsNet.defineNetworkHubs();
        RankableMap<Spot, Double> map = this.spotsNet.getHubs();

        return map.getTopN(n);

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
    public MatrixGraph<Spot, Route> obtainMinimumSpanTree() {
        MatrixGraph<Spot, Route> mst = spotsNet.getMinimumSpanTree();
        System.out.println(mst);
            return mst;
    }

}
