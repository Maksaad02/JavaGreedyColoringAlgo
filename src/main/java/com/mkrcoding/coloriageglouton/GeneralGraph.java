package com.mkrcoding.coloriageglouton;

import java.util.*;

public class GeneralGraph {
    private final int V;
    private final List<List<Integer>> adj;
    private final Map<String, Integer> mapSommet;
    private final String[] nomSommets;

    public GeneralGraph(int v) {
        this.V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new LinkedList<>());
        }
        mapSommet = new HashMap<>();
        nomSommets = new String[v];
    }

    public void addSommet(String name, int index) {
        mapSommet.put(name, index);
        nomSommets[index] = name;
    }


    public void addEdge(String from, String to) {
        if (!mapSommet.containsKey(from) || !mapSommet.containsKey(to)) {
            throw new IllegalArgumentException("Les sommets " + from + " ou " + to + " n'existent pas.");
        }
        int v = mapSommet.get(from);
        int w = mapSommet.get(to);
        adj.get(v).add(w);
        adj.get(w).add(v);
    }


    public void displayColors() {
        int[] colors = greedyColoring();
        System.out.println("Nombre de couleurs nécessaires : " + (Arrays.stream(colors).max().orElse(-1) + 1));

        // Optionally, print the colors for each vertex
        for (int i = 0; i < colors.length; i++) {
            System.out.println(nomSommets[i] + " : Couleur " + (colors[i] + 1));
        }
    }


    private int[] greedyColoring() {
        int[] result = new int[V]; // Result array to store colors of vertices
        Arrays.fill(result, -1);  // Initialize all vertices as uncolored (-1)

        // Iterate over all vertices to ensure all are considered, including disconnected ones
        for (int u = 0; u < V; u++) {
            // If the vertex is uncolored, we need to color it
            if (result[u] == -1) {
                // Create a boolean array to track the availability of colors
                boolean[] available = new boolean[V];
                Arrays.fill(available, true);  // Initially, all colors are available

                // Mark the colors of adjacent vertices as unavailable
                for (int neighbor : adj.get(u)) {
                    if (result[neighbor] != -1) {
                        available[result[neighbor]] = false;
                    }
                }

                // Assign the first available color
                for (int color = 0; color < V; color++) {
                    if (available[color]) {
                        result[u] = color;  // Assign the color to the vertex
                        break;
                    }
                }
            }
        }

        return result;
    }


}
