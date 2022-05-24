package BOJ.Greedy.저울;

import java.util.*;
import java.io.*;


/*
받아온 숫자들이 있고
만들 수 있는 숫자 배열을 만든다.
그 끝에 있는 숫자 + 1 이 expect 이고
expect 보다 큰 숫자가 들어오면 expect 가 만들 수 없는 최소 정수
받아온 숫자 하나씩 뽑으면서 array 에 있는 모든 애들이랑 더하면서 Set 에 존재하는지 확인?
*/
public class Main {

    static long max = 0L;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] numbers = br.readLine().split(" ");

        List<Long> numList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            numList.add(Long.parseLong(numbers[i]));
        }

        numList.sort(Comparator.naturalOrder());

        for (int i = 0; i < N; i++) {
            long cur = numList.get(i);

            if (max+1 >= cur) {
                max += cur;
            } else {
                System.out.println(max+1);
                System.exit(0);
            }
        }

        System.out.println(max+1);

    }
}
