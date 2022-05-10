package BOJ.Greedy.도서관;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.IntFunction;

public class Main {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        TreeSet<Integer> tree = new TreeSet<>(Comparator.naturalOrder());

        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            tree.add(Integer.parseInt(temp[i]));
        }

        Object[] negativeArr = tree.subSet(-10000, 0).stream().map(i -> {
            return i*-1;
        }).toArray();
        Object[] positiveArr = tree.subSet(1, 10001).stream().sorted(Comparator.reverseOrder()).toArray();

        if (negativeArr.length == 0 || positiveArr.length == 0) {
            if (negativeArr.length == 0) {
                answer += (Integer)positiveArr[0];
                greedy(positiveArr, m, m);
            } else {
                answer += (Integer)negativeArr[0];
                greedy(negativeArr, m, m);
            }
        } else {
            if ((Integer)negativeArr[0] > (Integer)positiveArr[0]) {
                answer += (Integer)negativeArr[0];
                greedy(negativeArr, m, m);
                greedy(positiveArr, m, 0);
            } else {
                answer += (Integer)positiveArr[0];
                greedy(positiveArr, m, m);
                greedy(negativeArr, m, 0);
            }
        }

        System.out.println(answer);
    }

    static void greedy(Object[] arr, int interval, int start) {
        while (start <= arr.length - 1) {
            answer += (Integer)arr[start]*2;
            start += interval;
        }
    }
}
