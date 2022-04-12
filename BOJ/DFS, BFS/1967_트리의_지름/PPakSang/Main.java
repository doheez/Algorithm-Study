package BOJ.DFSBFS.트리의지름;

import java.io.BufferedReader;
import java.util.*;
import java.io.*;

class Node {
    List<Node> children = new ArrayList<>();
    int weight;
}

public class Main {
    static int max = Integer.MIN_VALUE;
    static Node[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }

        tree = new Node[n+1];
        visited = new boolean[n+1];

        for (int i = 0; i < n-1; i++) {
            String[] temp = br.readLine().split(" ");
            int parent = Integer.parseInt(temp[0]);
            int child = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);

            Node parentNode = getNode(parent);
            Node childNode = getNode(child);

            childNode.weight = weight;
            parentNode.children.add(childNode);
        }

        findLongDistance(tree[1]);

        System.out.println(max);
    }

    static Node getNode(int i) {
        if (tree[i] == null) {
            tree[i] = new Node();
        }

        return tree[i];
    }


    static int findLongDistance(Node node) {
        if (node.children.isEmpty()) {
            return node.weight;
        }

        List<Integer> list =  new ArrayList<>();

        for (Node child : node.children) {
            list.add(findLongDistance(child));
        }

        list.sort(Comparator.naturalOrder());
        int maxLine = list.get(list.size()-1);
        int secondLine = list.size() > 1 ? list.get(list.size()-2) : 0;


        max = Math.max(max, maxLine+secondLine);

        return maxLine + node.weight;
    }

}
