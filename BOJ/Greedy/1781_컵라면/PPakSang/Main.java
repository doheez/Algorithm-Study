package BOJ.Greedy.컵라면.개선;

import java.util.*;
import java.io.*;

/*
데드라인 별로 컵라면을 받는데 가장 많이 받는 경우

고려를 해야하는 부분이
데드라인 1인데 컵라면 너무 적게 줄 때

즉 데드라인이 빠른 것을 컵라면 고르는 등의 강의실 배정과 같은 선택은 무의미

한번 참으면 다음번에 지금 선택 +1 개 선택가능

1에서 참으면 2짜리 2개
2에서도 참으면 3짜리 3개

그럼 현재 선택가능한 컵라면을 체크하면?

현재 선택할 수 있는 가장 높은 합 +

1 1
2 2 / 2 2
3 2 / 3 3 / 3 3

데드라인 까지 컵라면 가질 수 있다


1일 1개 뽑고
2일 2개 뽑고
3일 3개 뽑고


*/

class Question {
    int deadLine;
    int nums;

    public Question(int deadLine, int nums) {
        this.deadLine = deadLine;
        this.nums = nums;
    }
}

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Question> questionList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");

            int deadLine = Integer.parseInt(temp[0]);
            int num = Integer.parseInt(temp[1]);

            questionList.add(new Question(deadLine, num));
        }

        questionList.sort(new Comparator<Question>(){
            public int compare(Question o1, Question o2) {
                if (o1.deadLine == o2.deadLine) return o2.nums - o1.nums;

                return o1.deadLine - o2.deadLine;
            }
        });

        PriorityQueue<Integer> result = new PriorityQueue<>(Comparator.naturalOrder());

        for (Question q : questionList) {
            int remain = q.deadLine - result.size();

            if (remain > 0) result.add(q.nums);
            else {
                if (result.peek() < q.nums) {
                    result.poll();
                    result.add(q.nums);
                }
            }
        }

        int answer = 0;

        while(!result.isEmpty()) {
            answer += result.poll();
        }

        System.out.println(answer);
    }
}
