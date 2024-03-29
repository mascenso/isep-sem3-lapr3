package app.domain.utils.graph;

import app.domain.utils.graph.matrix.MatrixGraph;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 *
 * @author DEI-ISEP
 *
 */
public class Algorithms {

    /** Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {

        LinkedList<V> verticesBFS = null;

        if (g.validVertex(vert)) { //if vertice is in graph
            verticesBFS = new LinkedList<>();

            boolean[] visited = new boolean[g.numVertices()];

            LinkedList<V> verticesBFS_aux = new LinkedList<>();
            verticesBFS_aux.addFirst(vert);
            visited[g.key(verticesBFS_aux.getFirst())] = true;
            verticesBFS.add(vert);

            while (!verticesBFS_aux.isEmpty()) {
                for (Edge<V, E> tmpEdge : g.outgoingEdges(verticesBFS_aux.getFirst())) {
                    if (!visited[g.key(tmpEdge.getVDest())]) {
                        visited[g.key(tmpEdge.getVDest())] = true;
                        verticesBFS_aux.addLast(tmpEdge.getVDest());
                        verticesBFS.add(tmpEdge.getVDest());
                    }
                }
                verticesBFS_aux.removeFirst();
            }
        }

        return verticesBFS;
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vOrig vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        int currIdx = g.key(vOrig);
        visited[currIdx] = true;
        qdfs.addLast(vOrig);
        for (Edge<V, E> tmpEdge : g.outgoingEdges(vOrig)) {
            V vDest = tmpEdge.getVDest();
            int destIdx = g.key(vDest);
            if (!visited[destIdx]) {
                DepthFirstSearch(g, vDest, visited, qdfs);
            }
        }
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vert vertex of graph g that will be the source of the search

     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> result = null;

        if (g.validVertex(vert)) {
            boolean[] visited = new boolean[g.numVertices()];
            result = new LinkedList<>();

            DepthFirstSearch(g, vert, visited, result);
        }

        return result;
    }

    /** Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        int orig = g.key(vOrig);

        if (!visited[orig]) {
            if (vOrig.equals(vDest)) {
                path.addLast(vOrig);
                LinkedList<V> clone = new LinkedList<>(path);
                paths.add(clone);
                path.removeLast();
            } else {
                visited[orig] = true;
                path.addLast(vOrig);
                for (Edge<V, E> tmpEdge : g.outgoingEdges(vOrig)) {
                    allPaths(g, tmpEdge.getVDest(), vDest, visited, path, paths);
                }
                path.removeLast();
                visited[orig] = false;
            }
        }
    }

    /** Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        ArrayList<LinkedList<V>> result = new ArrayList<>();

        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            boolean[] visited = new boolean[g.numVertices()];
            LinkedList<V> path = new LinkedList<>();

            allPaths(g, vOrig, vDest, visited, path, result);
        }

        return result;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V[] pathKeys, E[] dist) {


        for (int i = 0; i < g.numVertices(); i++) {
            dist[i] = null;
            pathKeys[i] = null;
            visited[i] = false;
        }

        dist[g.key(vOrig)] = zero;
        pathKeys[g.key(vOrig)] = vOrig;

        while (vOrig != null) {

            int vOrigKey = g.key(vOrig);
            visited[vOrigKey] = true;

            for (V vAdj : g.adjVertices(vOrig)) {
                Edge<V, E> e = g.edge(vOrig, vAdj);
                int vAdjKey = g.key(vAdj);
                if (!visited[vAdjKey] && (dist[vAdjKey] == null || ce.compare(dist[vAdjKey], sum.apply(dist[vOrigKey], e.getWeight())) > 0)) {
                    dist[vAdjKey] = sum.apply(dist[vOrigKey], e.getWeight());
                    pathKeys[vAdjKey] = vOrig;
                }
            }

            //vOrig = getVertMinDist(dist, visited)
            vOrig = null;
            E minDistance = null;
            for (V vert : g.vertices()) {
                int vVertKey = g.key(vert);
                if (!visited[vVertKey] && dist[vVertKey] != null && (minDistance == null || ce.compare(dist[vVertKey], minDistance) < 0)) {
                    vOrig = vert;
                    minDistance = dist[vVertKey];
                }
            }
        }
    }


    /** Shortest-path between two vertices
     *
     * @param g graph
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {


        if (!g.validVertex(vOrig) || !g.validVertex(vDest))
            return null;

        if (vOrig.equals(vDest)) {
            shortPath.add(vOrig);
            return zero;
        }

        shortPath.clear();

        int nVerts = g.numVertices();

        boolean[] visited = new boolean[nVerts]; //default value: false
        V[] pathKeys = (V[]) new Object[nVerts];
        E[] dist = (E[]) new Object[nVerts];

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        if (dist[g.key(vDest)] != null) {
            getPath(g, vOrig, vDest, pathKeys, shortPath);
        }

        return shortPath.isEmpty() ? null : dist[g.key(vDest)];

    }

    /** Shortest-path between a vertex and all other vertices
     *
     * @param g graph
     * @param vOrig start vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        if (!g.validVertex(vOrig))
            return false;

        int nVerts = g.numVertices();

        boolean[] visited = new boolean[nVerts];
        V[] pathKeys = (V[]) new Object[nVerts];
        E[] dist = (E[]) new Object[nVerts];

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nVerts; i++) {
            paths.add(null);
            dists.add(null);
        }

        for (int i = 0; i < nVerts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != null)
                getPath(g, vOrig, g.vertex(i), pathKeys, shortPath);
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V[] pathKeys, LinkedList<V> path) {

        int destIdx = g.key(vDest);
        if (pathKeys[destIdx] != null) {
            path.addFirst(vDest);
            if (!vOrig.equals(vDest)) {
                getPath(g, vOrig, pathKeys[destIdx], pathKeys, path);
            }
        }
    }

    /** Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g   initial graph
     * @return the minimum distance graph
     */
    public static <V, E> MatrixGraph<V, E> minDistGraphBinary(Graph<V, E> g, E one) {

        int nVerts = g.numVertices();

        // Initialize the solution matrix = input graph matrix.//*--*--*--*--*--*--*--*--*--*
        Graph<V, E> minDistGraph = g.clone();
        //--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-

        // Transitive Closure.
        int k, i, j = 0;

        for (k = 0; k < nVerts; k++) // for each line
        {
            for (i = 0; i < nVerts; i++) // for each column
            {
                //if (i != k && T[i,k] = 1)
                if (i != k && minDistGraph.edge(minDistGraph.vertex(i), minDistGraph.vertex(k)) != null) // if there is an edge from i to k
                    for (j = 0; j < nVerts; j++) {

                        //if (i != j && k != j && T[k,j] = 1 )
                        if (i != j && k != j && g.edge(minDistGraph.vertex(k), minDistGraph.vertex(j)) != null // if there is an edge from k to j
                        ) {
                            minDistGraph.addEdge(
                                    minDistGraph.vertex(i),
                                    minDistGraph.vertex(j),
                                    one);
                        }
                    }
            }
        }
        return new MatrixGraph<>(minDistGraph);

    }


    /**
     * Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    public static <V, E> MatrixGraph<V, E> minDistGraph(Graph<V, E> g, Comparator<E> ce, BinaryOperator<E> sum) {

        int nVerts = g.numVertices();

        // Initialize the solution matrix = input graph matrix.//*--*--*--*--*--*--*--*--*--*
        Graph<V, E> minDistGraph = g.clone();
        //--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-

        // Transitive Closure.
        int k, i, j = 0;

        for (k = 0; k < nVerts; k++) // for each line
        {
            for (i = 0; i < nVerts; i++) // for each column
            {
                //if (i != k && T[i,k] = 1)
                if (i != k && minDistGraph.edge(i, k) != null) // if there is an edge from i to k
                    for (j = 0; j < nVerts; j++) {

                        //if (i != j && k != j && T[k,j] = 1 )
                        if (i != j && k != j && minDistGraph.edge(minDistGraph.vertex(k), minDistGraph.vertex(j)) != null) // if there is an edge from k to j
                        {
                            E e = sum.apply(minDistGraph.edge(i, k).getWeight(), minDistGraph.edge(k, j).getWeight());
                            if (minDistGraph.edge(minDistGraph.vertex(i), minDistGraph.vertex(j)) == null)
                            {
                                minDistGraph.addEdge(
                                        minDistGraph.vertex(i),
                                        minDistGraph.vertex(j),
                                        e)       ;

                            }
                            else if (
                                    ce.compare(
                                            minDistGraph.edge(i, j).getWeight() ,
                                            e
                                    ) > 0 )
                            {
                                minDistGraph.edge(i, j).setWeight(e);
                            }
                        }
                        // if there is an edge from k to j, and the sum is less than the current value
                    }
            }
        }
        System.out.println(new MatrixGraph<>(minDistGraph));
        return new MatrixGraph<>(minDistGraph);
    }

    public static <V, E> int shortestPathEdges(Graph<V,E> g, V vOrig) {

        int eccentricity[] = new int[g.numVertices()];
        int dist[] = new int[g.numVertices()];
        int path[] = new int[g.numVertices()];

        int max = 0;

        for (int i = 0; i < g.numVertices(); i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }


        //add vOrig to the queue
        dist[g.key(vOrig)] = 0;

        //create a queue
        Queue<V> q = new LinkedList<>();
        int ecty = 0;

        q.add(vOrig);

        while (!q.isEmpty()) {
            V v = q.remove();
            for (Edge<V, E> e : g.outgoingEdges(v)) {
                int w = g.key(e.getVDest());
                if (dist[w] == Integer.MAX_VALUE) {
                    dist[w] = dist[g.key(v)] + 1;
                    path[w] = g.key(v);
                    q.add(e.getVDest());

                        if (ecty< dist[w])
                            ecty = dist[w];

                        if (max < dist[w])
                        max = dist[w];
                }
            }
        }
/*
        System.out.println("Eccentricity of " + vOrig + ": " + eccentricity);
        for (int i = 0; i < g.numVertices(); i++) {
            System.out.println("Shortest path from " + vOrig + " to " + g.vertex(i) + " is " + dist[i]);
        }
 */
        return max;
    }




    /**
     * Returns the Minimum Span Tree of a given Graph g
     * @param g initial graph
     * @param ce
     * @return
     * @param <V>
     * @param <E>
     */
    public static <V, E> MatrixGraph<V, E> getMinimumSpanTreeKruskal(Graph<V,E> g, Comparator<E> ce) {

        //int nVerts = g.numVertices();
        Graph<V, E> mst = g.clone();

        List<Edge<V,E>> lstEdges = new ArrayList<>(mst.edges());

        for (Edge<V,E> e : mst.edges()) {
            lstEdges.add(e);
            mst.removeEdge(e.getVOrig(), e.getVDest());
        }

        lstEdges.sort((e1,e2) -> ce.compare(e1.getWeight(), e2.getWeight()));

        for (Edge<V,E> e : lstEdges) {
            LinkedList<V> connectedVerts = DepthFirstSearch(mst, e.getVOrig());
            if (!connectedVerts.contains(e.getVDest()))
                mst.addEdge(e.getVOrig(), e.getVDest(), e.getWeight());
        }
        return new MatrixGraph<>(mst);
    }

    public static <V,E> E calculateTotalWeight(Graph<V, E> g, BinaryOperator<E> sum, E zero) {
       MatrixGraph<V, E> mg = new MatrixGraph<>(g);
       return mg.calculateTotalWeight(sum, zero);
    }
}