import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {
    public void solution() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weight = new int[N];

        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(weight);

        // 추가 아무리 많아도 제일 작은 추가 1이 아니라면 1의 무게를 잴 수 X
        if (weight[0] != 1) {
            System.out.println(1);
            exit(0);
        }


        int min = 0; // min까지는 무게를 잴 수 있다
        for (int i = 0; i < N; i++) {
            if (weight[i] <= min + 1) {
                min += weight[i];
            } else {
                break;
            }
        }

        System.out.println(min + 1);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}