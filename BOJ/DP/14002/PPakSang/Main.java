package BOJ.DP.가장긴증가하는부분수열4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.*;

public class Main {

    static Queue<Integer>[] result;
    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        result = new Queue[n];
        arr = new int[n];
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        IntStream.range(0, n).forEach(i -> {
            int nextNum = Integer.parseInt(st.nextToken());
            arr[i] = nextNum;
            result[i] = new LinkedList<>();
        });


        for (int i = 0; i < n; i++) {
            int max = 1;
            int temp = i;
            for (int j = i-1; j >= 0; j--) {

                if (arr[j] < arr[i]) {
                    if ((dp[j] + 1) > max) {
                        max = dp[j] + 1;
                        temp = j; //최대 부분수열을 만드는 index
                    }
                }
            }
            //다 돌고나오면 앞에 붙일 index 인 temp 와 그 때의 최대 부분수열 길이 max 가 나옴
            dp[i] = max;
            Queue<Integer> addQ = new LinkedList<>(result[temp]);
            result[i] = addQ;
            result[i].add(arr[i]);
        }

        int max = dp[0];
        int index = 0;
        for (int i = 1; i < n; i++) {

            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }

        System.out.println(max);
        for (int i : result[index]) {
            System.out.print(i + " ");
        }
    }
}
