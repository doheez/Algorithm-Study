package BOJ.Greedy.카드정렬하기;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Long> l = new ArrayList<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        long answer = 0;

        while (pq.size() != 1) {
            long temp = 0;
            temp += pq.remove() + pq.remove();
            pq.add(temp);
            answer += temp;
        }

        System.out.println(answer);
    }
}
