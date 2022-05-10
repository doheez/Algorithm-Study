package BOJ.Greedy.주사위;

import java.util.*;
import java.io.*;

class Side {
    int value;
    int idx;

    public Side(int value, int idx) {
        this.value = value;
        this.idx = idx;
    }
}
public class Main {
    static long[] alpha;
    static long one = Long.MAX_VALUE;

    static long two = Long.MAX_VALUE;
    static long three = Long.MAX_VALUE;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer = 0;


        alpha = Arrays.stream(br.readLine().split(" ")).mapToLong((Long::parseLong)).toArray();

        for (Long n : alpha) {
            one = Math.min(one, n);
        }

        comb(0, 2, "");

        three = Math.min(alpha[0], alpha[5]) + Math.min(alpha[1], alpha[4]) + Math.min(alpha[2], alpha[3]);


        if (N == 1) {
            long max = 0L;
            for (int i = 0; i < 6; i++) {
                max = Math.max(max, alpha[i]);
                answer += alpha[i];
            }

            answer -= max;
        }
        else {
            answer = three*4 + two*((N-2)* 8L + 4) + one*((long) (N - 1) *(N-2)*4 + (long) (N - 2) *(N-2));
        }

        System.out.println(answer);
    }

    static void comb(int start, int target, String idx) {
        if (idx.length() == target) {
            if (idx.contains("0") && idx.contains("5") ||
            idx.contains("1") && idx.contains("4") ||
            idx.contains("2") && idx.contains("3")) return;

            two = Math.min(two,alpha[Integer.parseInt(idx.substring(0, 1))] +
                    alpha[Integer.parseInt(idx.substring(1, 2))]);
        }

        for (int i = start; i < 6; i++) {
            comb(i+1, target, idx + i);
        }
    }
}
