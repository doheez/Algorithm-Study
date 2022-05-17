package BOJ.Greedy.배;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Integer[] crane = new Integer[N];

        String[] temp = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(temp[i]);
        }

        int M = Integer.parseInt(br.readLine());

        List<Integer> boxArr = new ArrayList<>();

        temp = br.readLine().split(" ");

        for (int i = 0; i < M; i++) {
            boxArr.add(Integer.parseInt(temp[i]));
        }

        Arrays.sort(crane, Comparator.reverseOrder());
        boxArr.sort(Comparator.reverseOrder());

        int answer = 0;

        if (crane[0] < boxArr.get(0)) System.out.println(-1);
        else {
            while (true) {
                int cur = 0;
                for (int j = 0; j < boxArr.size(); j++) {
                    if (cur >= N) break;
                    if (crane[cur] < boxArr.get(j)) continue;

                    boxArr.remove(j);
                    j--;
                    cur++;
                }

                answer++;
                if (boxArr.size() == 0) break;
            }
            System.out.println(answer);
        }

    }
}
