import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public int carry(int N, ArrayList<Integer> crane, ArrayList<Integer> box) {
        int time = 0; // 모든 박스를 배로 옮기는데 드는 시간

        // 내림차순 정렬
        crane.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        // 예외 케이스 처리
        if (box.get(0) > crane.get(0)) {
            time = -1;
            return time;
        }

        // 박스 리스트가 빌 때까지
        while (!box.isEmpty()) {
            int index = 0;
            for (int i = 0; i < N; i++) {
                while (index < box.size()) {
                    // 박스가 크레인 제한 무게보다 가볍다면 박스 리스트에서 제거 (배로 옮긴다)
                    if (box.get(index) <= crane.get(i)) {
                        box.remove(index);
                        break;
                    } else { // 현재 박스를 못 옮긴다면 다음으로 큰 박스를 검사
                        index++;
                    }
                }
            }
            time++;
        }
        return time;
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>(N);
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>(M);
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        // 배에 화물 싣기
        int time = carry(N, crane, box);

        // 출력
        System.out.println(time);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}