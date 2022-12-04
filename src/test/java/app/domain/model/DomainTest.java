package app.domain.model;


import app.domain.utils.RankableMap;
import app.domain.utils.graph.Graph;
import app.domain.utils.graph.matrix.MatrixGraph;
import com.sun.source.tree.NewArrayTree;
import app.enums.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainTest {

    final Domain instance = new Domain("Biological Farm");

    @BeforeEach
    public void Domain() throws Exception {
        instance.constructMainGraph();

        System.out.println(instance.getSpotsNet());
        System.out.println(instance.getSpotsNet().getSpotsByType());
        System.out.println("Nr of producers in the net:" + instance.getSpotsNet().getSpotsByType().get(EntityType.PRODUCER).size());
        System.out.println("Nr of clients in the net:" + instance.getSpotsNet().getSpotsByType().get(EntityType.CLIENT).size());
        System.out.println("Nr of empresas in the net:" + instance.getSpotsNet().getSpotsByType().get(EntityType.EMPRESA).size());
    }

    @Test
    void testMinimumSpanTree() {
        double weightMSTGephi = 4231982;

        MatrixGraph<Spot, Route> mst = instance.obtainMinimumSpanTree();
        assertEquals(weightMSTGephi, mst.calculateTotalWeight(Route::sum, new Route(0)).meters);
    }

    @Test
    void checkIfGraphIsConnected() {
        assertTrue(instance.checkIfGraphIsConnected());
    }

    @Test
    void testDiameter() {
        int diameterGephi = 28;
        assertEquals(diameterGephi, instance.getSpotsNet().diameter());
    }

    @Test
    void defineNetworkHubs() {

        RankableMap<Spot, Double> hubsTop4 = instance.defineNetworkHubs(4);

        RankableMap<Spot, Double> hubsExpected = new RankableMap<>();

        Double value1 = 169295.25;
        Double value2 = 170292.30;
        Double value3 = 170317.06;
        Double value4 = 170557.35;

        Spot spot1 = new Spot ("CT146",40.1125,-8.2469,new Entity("E49"));
        Spot spot2 = new Spot ("CT142",40.2594,-8.3168,new Entity("E71"));
        Spot spot3 = new Spot ("CT33",39.9167,-8.4333,new Entity("E86"));
        Spot spot4 = new Spot ("CT209",40.2667,-8.2667,new Entity("E40"));

        hubsExpected.put(spot1, value1);
        hubsExpected.put(spot2, value2);
        hubsExpected.put(spot3, value3);
        hubsExpected.put(spot4, value4);

        assertEquals(hubsExpected.get(spot1), hubsTop4.get(spot1), 0.1);
        assertEquals(hubsExpected.get(spot2), hubsTop4.get(spot2), 0.1);
        assertEquals(hubsExpected.get(spot3), hubsTop4.get(spot3), 0.1);
        assertEquals(hubsExpected.get(spot4), hubsTop4.get(spot4), 0.1);

        assertEquals(hubsExpected.size(), hubsTop4.size());

    }

    @Test
    void test1shortestPath() {
        int shortestPathGephi_4_17 = 473192;
        Spot spot4 =new Spot("CT4", 40.818, -7.5414, new Entity("C59"));
        Spot spot17 =new Spot("CT17", 37.3178, -8.8, new Entity("C76"));
        assertEquals(shortestPathGephi_4_17,
        instance.getSpotsNet().getShortestPathDistance(spot4, spot17));
    }

    @Test
    void test2shortestPath() {
        int shortestPathGephi_159_86 = 73845;
        Spot spot159 =new Spot("CT159", 41.2077, -8.6674, new Entity("E90"));
        Spot spot86 =new Spot("CT86", 41.3869, -8.0022, new Entity("P32"));
        assertEquals(shortestPathGephi_159_86,
                instance.getSpotsNet().getShortestPathDistance(spot159, spot86));
    }
}