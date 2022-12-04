package app.domain.model;


import app.domain.utils.graph.Graph;
import app.domain.utils.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainBigTest {

    final Domain instance = new Domain("Biological Farm");

    @BeforeEach
    public void Domain() throws Exception {
        CsvParserNodes csvParserNodes = new CsvParserNodes();
        System.out.println(csvParserNodes.getCSVdata("src/main/resources/input_files/ESINF/grafos/Big/clientes-produtores_big.csv", ",", instance));
        CsvParserEdges csvParserEdges = new CsvParserEdges();
        System.out.println(csvParserEdges.getCSVdata("src/main/resources/input_files/ESINF/grafos/Big/distancias_big.csv", ",", instance));

        System.out.println(instance.getSpotsNet());
        System.out.println(instance.getSpotsNet().getSpotsByType());

        System.out.println("Nr of producers in the net:" + instance.getSpotsNet().getSpotsByType().get('P').size());
        System.out.println("Nr of clients in the net:" + instance.getSpotsNet().getSpotsByType().get('C').size());
        System.out.println("Nr of empresas in the net:" + instance.getSpotsNet().getSpotsByType().get('E').size());


    }

    @Test
    void testGetAdjacentSpots() {
        Collection<Spot> spotsObtained = instance.getAdjacentSpots("CT1");
        Collection<Spot> spotsExpected = new ArrayList<>();

        //Values obtained in Gephi, for the big files
        spotsExpected.add(new Spot("CT256"));
        spotsExpected.add(new Spot("CT148"));
        spotsExpected.add(new Spot("CT126"));
        spotsExpected.add(new Spot("CT222"));
        spotsExpected.add(new Spot("CT283"));

        System.out.println(spotsObtained);
        System.out.println(spotsExpected);
        assertTrue(spotsObtained.size() == spotsExpected.size() &&
                spotsObtained.containsAll(spotsExpected) &&
                spotsExpected.containsAll(spotsObtained));
    }

    @Test
    void testMinimumSpanTree() {
        double weightMSTGephi = 4231982;

        Graph<Spot, Route> mst = instance.obtainMinimumSpanTree();
        MatrixGraph<Spot, Route> mstMatrix = new MatrixGraph<>(mst);

        assertEquals(weightMSTGephi, mstMatrix.calculateTotalWeight(Route::sum, new Route(0)).meters);
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
        assertEquals(shortestPathGephi_4_17,
        instance.getSpotsNet().getShortestPathDistance(new Spot("CT4"), new Spot("CT17")));

    }

    @Test
    void test2shortestPath() {
        int shortestPathGephi_159_86 = 73845;
        assertEquals(shortestPathGephi_159_86,
                instance.getSpotsNet().getShortestPathDistance(new Spot("CT159"), new Spot("CT86")));
    }
}