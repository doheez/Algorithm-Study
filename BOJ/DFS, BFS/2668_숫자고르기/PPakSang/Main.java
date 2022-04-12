package BOJ.DFSBFS.숫자고르기;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

    static int n;

    static int[] arr;
    static boolean visited[];


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        pickNumber();

        Queue<Integer> answer = new LinkedList<>();
        int total = 0;
        for (int i = 1; i < n+1; i++) {
            if (visited[i]) {
                total++;
                answer.add(i);
            }
        }

        System.out.println(total);
        while(!answer.isEmpty()) {
            System.out.println(answer.poll());
        }
    }

    public static void pickNumber() {

        for (int i = 1; i < n+1; i++) {
            if (!visited[i]) {
                nodeSearch(i, arr[i]);
            }
        }
    }

    public static int nodeSearch(int i, int next) {
        if (visited[next]) return 0;

        if (i == next) {
            visited[i] = true;
            return 1;
        } else {
            visited[next] = true;
            if (nodeSearch(i, arr[next]) == 1) {
                return 1;
            }
            visited[next] = false;
            return 0;
        }

    }

}
