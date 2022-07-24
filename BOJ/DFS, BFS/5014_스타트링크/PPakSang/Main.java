package BOJ.DFSBFS.스타트링크.복습;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        boolean flag = false;
        int answer = 0;
        int[] count;

        // 전체 높이
        int F = Integer.parseInt(temp[0]);
        // 시작 층
        int S = Integer.parseInt(temp[1]);
        // 목적 층
        int G = Integer.parseInt(temp[2]);
        // 올라갈 수 있는 높이
        int U = Integer.parseInt(temp[3]);
        // 내려갈 수 있는 높이
        int D = Integer.parseInt(temp[4]);


        count = new int[F+1];

        Queue<Integer> q = new LinkedList<>();

        q.add(S);
        count[S] = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if (cur == G) {
                flag = true;
                answer = count[cur];
                break;
            }

            if (cur+U <= F && count[cur+U] == 0) {
                count[cur+U] = count[cur]+1;
                q.add(cur+U);
            }
            if (cur-D >= 1 && count[cur-D] == 0) {
                count[cur-D] = count[cur]+1;
                q.add(cur-D);
            }
        }

        if (flag) System.out.println(answer-1);
        else System.out.println("use the stairs");
    }
}
