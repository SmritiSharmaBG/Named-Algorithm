package com.dataStructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KahnsAlgo {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = 6;
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        int[] ans = topologicalSort(adj, n);

        for(int i : ans) System.out.print(i+ " ");
    }

    /**
     * Kahn's Algorithm :
     * 1. Find the in-degree of all the nodes.
     * 2. Make a queue and add all the nodes which have in-degree as 0. Here order of such nodes is not important.
     * 3. Take out element from queue and traverse its adjacent nodes. Also showcase that node in Topological Sort.
     * 4. In each traversal, decrease their respective in-degree by 1.
     * 5. While doing so, if that in-degree became zero, add it in the queue
     * 6. Keep on repeating the above process until the queue becomes empty.
     *
     * @param adj
     * @param n
     * @return
     */
    private static int[] topologicalSort(ArrayList<ArrayList<Integer>> adj, int n) {
        int[] ans = new int[n];
        int[] inDegree = new int[n];

        // Find the in-degree of all the nodes.
        for (int i = 0; i < n; i++)
            for (int it : adj.get(i)) inDegree[it]++;

        //  Make a queue and add all the nodes which have in-degree as 0. Here order of such nodes is not important.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0) queue.add(i);

        //  Take out element from queue and traverse its adjacent nodes. Also showcase that node in Topological Sort.
        int pos = 0;
        while(!queue.isEmpty()) {
            int node = queue.remove();
            ans[pos ++] = node;
            for (int it : adj.get(node))
                if(-- inDegree[it] == 0) queue.add(it);
        }
        return ans;
    }
}
