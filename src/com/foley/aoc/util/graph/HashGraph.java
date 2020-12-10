package com.foley.aoc.util.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Graph data structure backed by a hash map.
 * <p>
 * Each entry into the hash map is a vertex contained within the graph. The vertex is mapped to an additional hash map.
 * The inner hash map stores neighboring vertices as well as the cost to travel between the two vertices.
 *
 * @author Evan Foley
 * @version 08 May 2020
 */
public class HashGraph<T> implements Graph<T>{
    private Map<T, Map<T, Double>> graph;

    /**
     * Creates a new hash graph.
     */
    public HashGraph() {
        graph = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     *
     * @param vertex The vertex to add
     * @return True if the vertex was added
     */
    public boolean addVertex(T vertex) {
        if(!graph.containsKey(vertex)) {
            graph.put(vertex, new HashMap<>());
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * Does not add edge data to this graph.
     *
     * @param otherGraph The specified graph
     */
    public void addAllVerticesFrom(Graph<? extends T> otherGraph) {
        Set<? extends T> set = otherGraph.getVertices();
        for(T t : set) {
            addVertex(t);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param vertex The vertex to remove
     * @return True if the operation was successful
     */
    public boolean removeVertex(T vertex) {
        if(graph.containsKey(vertex)) {
            // Get neighboring vertices and remove source vertex
            Map<T, Double> neighbors = graph.get(vertex);
            graph.remove(vertex);
            // Remove vertex from all neighboring connections
            for(T neighbor : neighbors.keySet()) {
                graph.get(neighbor).remove(vertex);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param vertex The vertex whose presence in the graph is to be tested
     * @return True if the vertex is in the graph
     */
    public boolean containsVertex(T vertex) {
        return graph.containsKey(vertex);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * If one of the vertices is not in the graph, the edge will not be added and the operation will fail. This operation will
     * update the cost to travel between vertices if an edge already exists between the source and destination vertices.
     * Note, cost must be greater than or equal to zero, and an IllegalArgumentException will be thrown if this condition is not met.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @param cost The cost to travel along the edge
     * @return True if the edge was added between the source and destination vertices
     */
    public boolean addEdge(T from, T to, double cost) {
        // Verify that both nodes exist in the graph
        if(graph.containsKey(from) && graph.containsKey(to)) {
            if(cost < 0.0) {
                throw new IllegalArgumentException("Edge cost must be non-negative");
            }
            // Add edges
            graph.get(from).put(to, cost);
            graph.get(to).put(from, cost);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * If one of the vertices is not in the graph, it will not be added and the operation will fail. This operation will
     * update the cost to travel between vertices if an edge already exists between the source and destination vertices.
     * Note, cost must be greater than or equal to zero, and an IllegalArgumentException will be thrown if this condition
     * is not met.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return True if the edge was added between the source and destination vertices
     */
    public boolean addEdge(T from, T to) {
        return addEdge(from, to, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return True if the operation was successful
     */
    public boolean removeEdge(T from, T to) {
        if(graph.containsKey(from) && graph.containsKey(to)) {
            graph.get(from).remove(to);
            graph.get(to).remove(from);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return The cost to travel from the source vertex to the destination vertex, or -1.0 if no edge exists
     */
    public double getEdgeCost(T from, T to) {
        // Get the cost only if two vertices are adjacent
        if(areAdjacent(from, to)) {
            return graph.get(from).get(to);
        }
        return -1.0;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This method works under the assumption that the graph is directional. That is to say, it determines if to is
     * a node that has an edge originating at from; will not check if there is an edge originating at to connecting
     * to from
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return True if the two vertices are adjacent
     */
    public boolean areAdjacent(T from, T to) {
        // Validate that vertices are in the graph
        if(graph.containsKey(from) && graph.containsKey(to)) {
            return graph.get(from).containsKey(to);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param vertex The given vertex
     * @return The neighbors for the given vertex
     */
    public Set<T> getNeighbors(T vertex) {
        if(graph.containsKey(vertex)) {
            return graph.get(vertex).keySet();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return The vertices in the graph
     */
    public Set<T> getVertices() {
        return graph.keySet();
    }

    /**
     * {@inheritDoc}
     */
    public void printGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("HashGraph Contents:\n");
        // Add graph vertex contents
        for(T vertex : graph.keySet()) {
            sb.append("\tVertex: " + vertex.toString() + "\n");
            // Append all vertex neighbor data
            for(T neighbor : graph.get(vertex).keySet()) {
                sb.append("\t\t(Neighbor / Edge Cost): " + neighbor.toString() + "\t/\t" + graph.get(vertex).get(neighbor) + "\n");
            }
        }
        sb.append("End HashGraph contents\n");
        sb.append("========================================\n");
        System.out.println(sb.toString());
    }
}