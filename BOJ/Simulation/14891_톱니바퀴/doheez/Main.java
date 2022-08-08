import java.io.*;
import java.util.*;

public class Main {
    int N = 4; // 톱니바퀴 개수
    int M = 8; // 톱니바퀴의 톱니 개수
    int K; // 회전 횟수
    LinkedList<Integer>[] gear = new LinkedList[N + 1]; // 톱니가 M개인 톱니바퀴가 N개
    boolean[] visited = new boolean[N + 1]; // 돌릴지 말지 체크했는가

    int LEFT = 6;
    int RIGHT = 2;
    int CLOCKWISE = 1;
    int COUNTERCLOCKWISE = -1;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 입력
        for (int i = 1; i < N + 1; i++) {
            gear[i] = new LinkedList<>();
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                gear[i].add(Integer.parseInt(String.valueOf(temp.charAt(j))));
            }
        }

        // 회전 횟수 입력
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            // 회전 방법 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNumber = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            // visited 초기화
            Arrays.fill(visited, false);

            // 동작
            visited[gearNumber] = true;
            turn(gearNumber, direction);
        }

        // 점수 계산
        int result = caculate();
        System.out.println(result);
        br.close();
    }

    public void turn(int gearNumber, int direction) {
        boolean leftTurn = false; // 왼쪽 톱니바퀴를 돌린다
        boolean rightTurn = false; // 오른쪽 톱니바퀴를 돌린다
        int nextDirection = CLOCKWISE;

        // 왼쪽 톱니와 맞닿은 다른 톱니바퀴의 톱니 확인 후, 돌릴 수 있는지 판단
        if (gearNumber != 1 &&
                gear[gearNumber].get(LEFT) != gear[gearNumber - 1].get(RIGHT) &&
                !visited[gearNumber - 1]) {
            leftTurn = true;
        }
        // 오른쪽 톱니와 맞닿은 다른 톱니바퀴의 톱니 확인 후, 돌릴 수 있는지 판단
        if (gearNumber != 4 &&
                gear[gearNumber].get(RIGHT) != gear[gearNumber + 1].get(LEFT) &&
                !visited[gearNumber + 1]) {
            rightTurn = true;
        }
        // 현재 톱니바퀴 돌리기
        if (direction == CLOCKWISE) {
            int sawtooth = gear[gearNumber].pollLast();
            gear[gearNumber].addFirst(sawtooth);
            nextDirection = COUNTERCLOCKWISE;
        } else if (direction == COUNTERCLOCKWISE) {
            int sawtooth = gear[gearNumber].pollFirst();
            gear[gearNumber].addLast(sawtooth);
            nextDirection = CLOCKWISE;
        }
        // 좌우 톱니바퀴 돌리기
        if (leftTurn) {
            visited[gearNumber - 1] = true;
            turn(gearNumber - 1, nextDirection);
        }
        if (rightTurn) {
            visited[gearNumber + 1] = true;
            turn(gearNumber + 1, nextDirection);
        }
    }

    public int caculate() {
        int sum = 0;

        // 각 톱니바퀴의 12시방향 톱니가 S극이라면 점수 계산
        if (gear[1].getFirst() == 1) sum += 1;
        if (gear[2].getFirst() == 1) sum += 2;
        if (gear[3].getFirst() == 1) sum += 4;
        if (gear[4].getFirst() == 1) sum += 8;

        return sum;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
