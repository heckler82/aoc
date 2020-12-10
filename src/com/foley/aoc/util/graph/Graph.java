package com.foley.aoc.util.graph;

import java.util.Set;

/**
 * A template for a Collection of vertices and the edges connecting them.
 *
 * @author Evan Foley
 * @version 03 May 2020
 */
public interface Graph<T> {
    /**
     * Adds a vertex to the graph.
     *
     * @param vertex The vertex to add
     * @return True if the vertex was added
     */
    boolean addVertex(T vertex);

    /**
     * Adds all the vertices from the specified graph to this graph.
     *
     * @param graph The specified graph
     */
    void addAllVerticesFrom(Graph<? extends T> graph);

    /**
     * Removes a vertex from the graph.
     * <p>
     * Implementations should be cautious to also remove any edges that the affected vertex may be apart of from the graph.
     *
     * @param vertex The vertex to remove
     * @return True if the operational was successful
     */
    boolean removeVertex(T vertex);

    /**
     * Returns <tt>true</tt> if the graph contains the specified vertex.
     *
     * @param vertex The vertex whose presence in the graph is to be tested
     * @return True if the vertex is in the graph
     */
    boolean containsVertex(T vertex);

    /**
     * Adds an edge between two vertices in the graph.
     * <p>
     * Edges make two vertices neighbors. addEdge(1, 2) should cause areAdjacent(1, 2) to return true if the add
     * operation was successful.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @param cost The cost to travel along the edge
     * @return True if the edge was added between the source and destination vertices
     */
    boolean addEdge(T from, T to, double cost);

    /**
     * Adds an edge between two vertices in the graph.
     * <p>
     * Edges make two vertices neighbors. addEdge(1, 2) should cause areAdjacent(1, 2) to return true if the add
     * operation was successful.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return True if the edge was added between the source and destination vertices
     */
    boolean addEdge(T from, T to);

    /**
     * Removes an edge from the graph by removing the neighboring relationship between two vertices.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return True if the operation was successful
     */
    boolean removeEdge(T from, T to);

    /**
     * Gets the cost to travel along the edge connecting two vertices.
     * <p>
     * This method should not be expected to return a cost to travel to a vertex that is not directly connected to the
     * source vertex, but is connected indirectly through a series of edges between vertices. This behavior should be
     * reserved for pathfinders.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return The cost to travel from the source vertex to the destination vertex
     */
    double getEdgeCost(T from, T to);

    /**
     * Tests if two vertices are adjacent to each other.
     * <p>
     * Should always return true if getEdgeCost(from, to) returns a non-negative number.
     * graph.
     *
     * @param from The source vertex
     * @param to The destination vertex
     * @return True if the two vertices are adjacent
     */
    boolean areAdjacent(T from, T to);

    /**
     * Gets the unique neighbors for the given vertex.
     *
     * @param vertex The given vertex
     * @return The neighbors for the given vertex
     */
    Set<T> getNeighbors(T vertex);

    /**
     * Gets the unique vertices that make up the graph.
     *
     * @return The vertices in the graph
     */
    Set<T> getVertices();

    /**
     * Prints out a text representation of the graph to the console.
     */
    void printGraph();
}