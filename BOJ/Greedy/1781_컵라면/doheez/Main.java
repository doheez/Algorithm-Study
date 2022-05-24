import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Question[] questions = new Question[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            Question question = new Question(deadline, ramen);
            questions[i] = question;
        }

        // 데드라인이 짧고 컵라면이 많은 순으로 정렬
        Arrays.sort(questions);

        // 컵라면 오름차순 우선순위 큐 생성
        Comparator<Question> comparator = new Comparator<Question>() {
            @Override
            public int compare(Question o1, Question o2) {
                return Integer.compare(o1.ramen, o2.ramen);
            }
        };
        PriorityQueue<Question> pq = new PriorityQueue<>(N, comparator);

        // 우선순위 큐의 원소 개수: 전체 푼 문제 개수 == 흐른 시간
        for (int i = 0; i < N; i++) {
            if (pq.size() < questions[i].deadline) {
                pq.add(questions[i]);
            } else if (pq.size() == questions[i].deadline) {
                if (pq.peek().ramen < questions[i].ramen) {
                    pq.poll();
                    pq.add(questions[i]);
                }
            }
        }

        int ramenCnt = 0;
        while (!pq.isEmpty()) {
            ramenCnt += pq.poll().ramen;
        }

        System.out.println(ramenCnt);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Question implements Comparable<Question> {
    int deadline;
    int ramen;

    Question(int deadline, int ramen) {
        this.deadline = deadline;
        this.ramen = ramen;
    }

    @Override
    public int compareTo(Question o) {
        // 데드라인이 짧을수록 우선순위가 높다.
        if (this.deadline < o.deadline) {
            return -1;
        } else if (this.deadline == o.deadline) {
            // 데드라인이 같다면 컵라면이 많을수록 우선순위가 높다.
            return Integer.compare(o.ramen, this.ramen);
        } else {
            return 1;
        }
    }
}