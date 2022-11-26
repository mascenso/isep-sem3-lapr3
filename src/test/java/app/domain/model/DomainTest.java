package app.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    final Domain instance = new Domain("Biological Farm");

    @BeforeEach
    public void Domain() throws Exception {

        //instance.setSpotsNet(new SpotsNet());

        instance.addSpot("CT1",40.6389,-8.6553,"C1");
        instance.addSpot("CT2",38.0333,-7.8833,"C2");
        instance.addSpot("CT3",41.5333,-8.4167,"C3");
        instance.addSpot("CT15",41.7,-8.8333,"C4");
        instance.addSpot("CT16",41.3002,-7.7398,"C5");
        instance.addSpot("CT12",41.1495,-8.6108,"C6");
        instance.addSpot("CT7",38.5667,-7.9,"C7");
        instance.addSpot("CT8",37.0161,-7.935,"C8");
        instance.addSpot("CT13",39.2369,-8.685,"C9");
        instance.addSpot("CT14",38.5243,-8.8926,"E1");
        instance.addSpot("CT11",39.3167,-7.4167,"E2");
        instance.addSpot("CT5",39.823,-7.4931,"E3");
        instance.addSpot("CT9",40.5364,-7.2683,"E4");
        instance.addSpot("CT4",41.8,-6.75,"E5");
        instance.addSpot("CT17",40.6667,-7.9167,"P1");
        instance.addSpot("CT6",40.2111,-8.4291,"P2");
        instance.addSpot("CT10",39.7444,-8.8072,"P3");

        instance.addRoute("CT10","CT13",63448);
        instance.addRoute("CT10","CT13",63448);
        instance.addRoute("CT10","CT6",67584);
        instance.addRoute("CT10","CT1",110848);
        instance.addRoute("CT10","CT5",125041);
        instance.addRoute("CT12","CT3",50467);
        instance.addRoute("CT12","CT1",62877);
        instance.addRoute("CT12","CT15",70717);
        instance.addRoute("CT11","CT5",62655);
        instance.addRoute("CT11","CT13",121584);
        instance.addRoute("CT11","CT10",142470);
        instance.addRoute("CT14","CT13",89813);
        instance.addRoute("CT14","CT7",95957);
        instance.addRoute("CT14","CT2",114913);
        instance.addRoute("CT14","CT8",207558);
        instance.addRoute("CT13","CT7",111686);
        instance.addRoute("CT16","CT3",68957);
        instance.addRoute("CT16","CT1",779560);
        instance.addRoute("CT16","CT1",282996);
        instance.addRoute("CT16","CT9",103704);
        instance.addRoute("CT16","CT4",110133);
        instance.addRoute("CT15","CT3",43598);
        instance.addRoute("CT17","CT9",62879);
        instance.addRoute("CT17","CT1",69282);
        instance.addRoute("CT17","CT6",73828);
        instance.addRoute("CT1","CT6",56717);
        instance.addRoute("CT2","CT7",65574);
        instance.addRoute("CT2","CT8",125105);
        instance.addRoute("CT2","CT11",163996);
        instance.addRoute("CT4","CT3",157223);
        instance.addRoute("CT4","CT9",162527);
        instance.addRoute("CT5","CT9",90186);
        instance.addRoute("CT5","CT6",100563);
        instance.addRoute("CT5","CT17",111134);

    }

    @Test
    void getAdjacentSpots() {
        Collection<Spot> a = instance.getAdjacentSpots("CT1");
        System.out.println(instance.getSpotsNet());
        System.out.println(a);

    }
}