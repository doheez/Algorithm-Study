package BOJ.DFSBFS.ABCDE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] nodes;
    static int n;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        nodes = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            strings = br.readLine().split(" ");
            int n1 = Integer.parseInt(strings[0]);
            int n2 = Integer.parseInt(strings[1]);

            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }

        boolean result = false;

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            result = dfs(i, 1);
            if (result) {
                System.out.println(1);
                return;
            }
            visited[i] = false;
        }

        System.out.println(0);
    }

    static boolean dfs(int cur, int cnt) {
        boolean result = false;

        if (cnt == 5) return true;

        for (int node : nodes[cur]) {
            if (!visited[node]) {
                visited[node] = true;
                result = dfs(node, cnt+1);
                if (result) return true;
                visited[node] = false;
            }
        }

        return false;
    }
}
