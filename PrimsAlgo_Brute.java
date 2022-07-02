package com.dataStructure.graph;

import java.util.ArrayList;

class Node {
    int toNode, weight;

    Node(int toNode, int weight) {
        this.toNode = toNode;
        this.weight = weight;
    }
}

public class PrimsAlgo_Brute {
    private static ArrayList<ArrayList<Node>> adj = new ArrayList<>();

    public static void main(String[] args) {
        int n = 5;
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        addEdge(0, 1, 2);
        addEdge(0, 3, 6);
        addEdge(1, 3, 8);
        addEdge(1, 4, 5);
        addEdge(1, 2, 3);
        addEdge(2, 4, 7);

        int[] mst = primsAlgoBrute(n);
        for (int i = 0; i < n; i++) System.out.println(i + " -- " + mst[i]);
    }

    private static int[] primsAlgoBrute(int n) {
        int[] key = new int[n]; // to store minimum weighted edges of all nodes
        int[] parent = new int[n]; // to store the connecting node, will be used to make final mst
        boolean[] mst = new boolean[n]; // for tracking which nodes are done

        for (int i = 1; i < n; i++) key[i] = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) { // we need to traverse for (n-1) edges

            // find the smallest value in key which is also false in mst
            int min = Integer.MAX_VALUE; // for comparing weights
            int node = -1; // for storing the node which is found eligible
            for (int j = 0; j < n; j++) {
                if (key[j] < min && !mst[j]) {
                    min = key[j];
                    node = j;
                }
            }

            // mark it true as if adding in mst
            mst[node] = true;

            //traverse through the adjacent of node
            for (Node it : adj.get(node)) {
                // if that node is not already considered and we found an edge whose weight is less that what is currently stored for that node, then change the weight and the parent of the node
                if (!mst[it.toNode] && it.weight < key[it.toNode]) {
                    key[it.toNode] = it.weight;
                    parent[it.toNode] = node;
                }
            }
        }

        return parent;
    }

    private static void addEdge(int u, int v, int wt) {
        adj.get(u).add(new Node(v, wt));
        adj.get(v).add(new Node(u, wt));
    }
}
