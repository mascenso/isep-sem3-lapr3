package app.domain.model;


import app.domain.utils.graph.Graph;
import app.domain.utils.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainTest {

    final Domain instance = new Domain("Biological Farm");

    @BeforeEach
    public void Domain() throws Exception {
        instance.constructMainGraph();

        System.out.println(instance.getSpotsNet());
        System.out.println(instance.getSpotsNet().getSpotsByType());
        System.out.println("Nr of producers in the net:" + instance.getSpotsNet().getSpotsByType().get('P').size());
        System.out.println("Nr of clients in the net:" + instance.getSpotsNet().getSpotsByType().get('C').size());
        System.out.println("Nr of empresas in the net:" + instance.getSpotsNet().getSpotsByType().get('E').size());
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
        instance.defineNetworkHubs(4);
    }

    @Test
    void test1shortestPath() {
        int shortestPathGephi_4_17 = 473192;
        Spot spot4 =new Spot();
        Spot spot17 =new Spot();
        spot4.setSpotID("CT4");
        spot17.setSpotID("CT17");
        assertEquals(shortestPathGephi_4_17,
        instance.getSpotsNet().getShortestPathDistance(spot4, spot17));
    }

    @Test
    void test2shortestPath() {
        int shortestPathGephi_159_86 = 73845;
        Spot spot159 =new Spot();
        Spot spot86 =new Spot();
        spot159.setSpotID("CT159");
        spot86.setSpotID("CT86");
        assertEquals(shortestPathGephi_159_86,
                instance.getSpotsNet().getShortestPathDistance(spot159, spot86));
    }
}