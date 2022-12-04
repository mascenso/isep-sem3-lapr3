package app.domain.model;

import app.domain.utils.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    final Domain instance = new Domain("Biological Farm");

    @BeforeEach
    public void Domain() throws Exception {

       instance.addSpot("CT1",40.6389,-8.6553,"C1");
       instance.addSpot("CT2",38.0333,-7.8833,"C2");
       instance.addSpot("CT3",41.5333,-8.4167,"C3");
       instance.addSpot("CT4",41.7,-8.8333,"C4");


       instance.addRoute("CT1","CT2",12);
       instance.addRoute("CT2","CT3",23);
       instance.addRoute("CT3","CT4",34);
       instance.addRoute("CT4","CT1",41);

    }

    @Test
    void testGetAdjacentSpotsSmall() {
        Collection<Spot> spotsObtained = instance.getAdjacentSpots("CT1");
        Collection<Spot> spotsExpected = new ArrayList<>();
        spotsExpected.add(new Spot("CT2"));
        spotsExpected.add(new Spot("CT4"));

        System.out.println(spotsObtained);
        System.out.println(spotsExpected);
        assertTrue(spotsObtained.size() == spotsExpected.size() &&
                spotsObtained.containsAll(spotsExpected) &&
                spotsExpected.containsAll(spotsObtained));
    }

    @Test
    void checkIfGraphIsConnectedSmall() {
        assertEquals(instance.checkIfGraphIsConnected(), true);
    }

    @Test
    void testMinimumSpanTreeSmall() {
        Route rExpected = new Route(69);

        MatrixGraph<Spot, Route> mstMatrix = new MatrixGraph<>(instance.obtainMinimumSpanTree());

        assertEquals(rExpected.meters, mstMatrix.calculateTotalWeight(Route::sum, new Route(0)).meters);
    }
}