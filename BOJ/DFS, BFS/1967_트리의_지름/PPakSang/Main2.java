package BOJ.DFSBFS.트리의지름;

import java.util.*;
import java.io.*;

class Node2 {
    int index;
    int weight;

    Node2(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }
}

public class Main2 {

    static List<Node2>[] tree;
    static int longNode;
    static int maxDistance = Integer.MIN_VALUE;

    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }
        tree = new List[n+1];
        visited = new boolean[n+1];

        for (int i = 0; i < n+1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            String[] temp = br.readLine().split(" ");
            int parent = Integer.parseInt(temp[0]);
            int child = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);

            tree[parent].add(new Node2(child, weight));
            tree[child].add(new Node2(parent, weight));
        }

        visited[1] = true;
        for (Node2 child : tree[1]) {
            dfs(child.index, child.weight);
        }

        visited = new boolean[n+1];
        visited[longNode] = true;
        for (Node2 child : tree[longNode]) {
            dfs(child.index, child.weight);
        }


        System.out.println(maxDistance);
    }

    static void dfs(int node, int distance) {
        visited[node] = true;
        boolean flag = false;



        for (Node2 child : tree[node]) {
            if (!visited[child.index]) {
                flag = true;
                dfs(child.index, distance + child.weight);
            }
        }

        if (!flag) {
            if (distance > maxDistance) {
                maxDistance = distance;
                longNode = node;
                return;
            }
        }
    }
}
