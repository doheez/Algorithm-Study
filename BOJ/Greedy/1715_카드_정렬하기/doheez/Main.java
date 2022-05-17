import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public void solution() throws IOException {
        PriorityQueue<Integer> card = new PriorityQueue<>();
        int count = 0;

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            // Min Heap에 추가
            card.add(Integer.parseInt((br.readLine())));
        }

        // 수가 적은 카드 묶음부터 합치고, 그 값을 다시 Min Heap에 넣음
        while (card.size() > 1) {
            int sum = card.poll() + card.poll();
            card.add(sum);
            count += sum;
        }

        // 출력
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}