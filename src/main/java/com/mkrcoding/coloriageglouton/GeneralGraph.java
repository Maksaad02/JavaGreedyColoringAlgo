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
        if (index < 0 || index >= V) {
            throw new IllegalArgumentException("Index du sommet invalide.");
        }
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

    public String[] getNomSommets() {
        return nomSommets;
    }

    public List<List<Integer>> getAdj() {
        return adj;
    }

    public int[] greedyColoring() {
        int[] result = new int[V];
        Arrays.fill(result, -1);

        for (int u = 0; u < V; u++) {
            if (result[u] == -1) {
                boolean[] available = new boolean[V];
                Arrays.fill(available, true);

                for (int neighbor : adj.get(u)) {
                    if (result[neighbor] != -1) {
                        available[result[neighbor]] = false;
                    }
                }

                for (int color = 0; color < V; color++) {
                    if (available[color]) {
                        result[u] = color;
                        break;
                    }
                }
            }
        }

        return result;
    }
}