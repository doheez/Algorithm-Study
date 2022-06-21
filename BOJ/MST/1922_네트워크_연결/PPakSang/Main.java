package BOJ.MST.네트워크연결;

import java.util.*;
import java.io.*;

class Network {
    int p1;
    int p2;
    int weight;

    public Network(int p1, int p2, int weight) {
        this.p1 = p1;
        this.p2 = p2;
        this.weight = weight;
    }
}

public class Main {

    static Comparator<Network> compareWeights = new Comparator<Network>() {
        @Override
        public int compare(Network o1, Network o2) {
            if (o1.weight == o2.weight) {
                if (o1.p1 == o2.p1) {
                    return o1.p2 - o2.p2;
                }
                return o1.p1 - o2.p1;
            }
            return o1.weight - o2.weight;
        }
    };

    static TreeSet<Network> connection = new TreeSet<>(compareWeights);
    static boolean[] visited;
    static int[] group;

    static int find(int index) {
        if (group[index] == index) {
            return index;
        }
        return (group[index] = find(group[index]));
    }

    static boolean union(int index1, int index2) {
        int x = find(index1);
        int y = find(index2);

        if (x < y) {
            group[y] = x;
        } else if (x > y){
            group[x] = y;
        } else {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int cnt = 0;
        int answer = 0;

        visited = new boolean[n+1];
        group = new int[n+1];
        for (int i = 1; i <= n; i++) group[i] = i;

        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            int index1 = Integer.parseInt(temp[0]);
            int index2 = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);

            connection.add(new Network(index1, index2, weight));
        }

        for (Network conn : connection) {
            if (union(conn.p1, conn.p2)) {
                answer += conn.weight;
                cnt += 1;
            }

            if (cnt == n-1) {
                System.out.println(answer);
                return;
            }
        }
    }
}
