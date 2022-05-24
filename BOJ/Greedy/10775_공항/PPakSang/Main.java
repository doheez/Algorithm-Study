package BOJ.Greedy.비행기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    static int[] gates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        gates = new int[G+1];

        int answer = 0;

        for (int i = 0; i < G+1; i++) {
            gates[i] = i;
        }

        for (int i = 0; i < P; i++) {
            int range = Integer.parseInt(br.readLine());
            int where = find(range);

            if (where == 0) break;

            union(where-1, where);
            answer++;

            Map<String, Integer> a = new HashMap<>();
        }

        System.out.println(answer);
    }

    static int find(int idx) {
        if (idx == gates[idx]) return idx;
        else return gates[idx] = find(gates[idx]);
    }
    static void union(int idx1, int idx2) {
        int i1 = find(idx1);
        int i2 = find(idx2);

        if (i1 < i2) {
            gates[i2] = gates[i1];
        } else gates[i1] = gates[i2];
    }
}
