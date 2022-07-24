package BOJ.DFSBFS.게리맨더링;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] area;
    static int[] population;
    static boolean[] visited;
    static int[][] conn;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N+1];
        conn = new int[N+1][N+1];
        area = new int[N+1];


        String[] temp = br.readLine().split(" ");

        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(temp[i-1]);
        }

        for (int i = 1; i <= N; i++) {
            temp = br.readLine().split(" ");
            int J = Integer.parseInt(temp[0]);
            for (int j = 1; j <= J; j++) {
                int to = Integer.parseInt(temp[j]);
                conn[i][to] = 1;
            }
        }

        combination(1);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);

    }

    static void combination(int k) {
        if (k == N+1) {
            int area1Population = 0;
            int area2Population = 0;

            for (int i = 1; i <= N; i++) {
                if (area[i] == 1) area1Population += population[i];
                else area2Population += population[i];
            }

            visited = new boolean[N+1];
            int areaNum = 0;

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    checkArea(i);
                    areaNum++;
                }
            }

            if (areaNum == 2) answer = Math.min(answer, Math.abs(area1Population-area2Population));

            return;
        }


        area[k] = 1;
        combination(k+1);

        area[k] = 2;
        combination(k+1);

    }

    static void checkArea(int i) {


        Queue<Integer> q = new LinkedList<>();
        q.add(i);


        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int j = 1; j <= N; j++) {
                if (conn[cur][j] == 1  && !visited[j] && area[cur] == area[j]){
                    visited[j] = true;
                    q.add(j);
                }
            }
        }
    }
}
