package BOJ.구현.로봇청소기;

import java.io.*;
import java.util.*;

class Robot {
    int dir;
    int row;
    int col;

    public Robot(int dir, int row, int col) {
        this.dir = dir;
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int[] dirCol = {0, 1, 0, -1};
    static int[] dirRow = {-1, 0, 1, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");

        int row = Integer.parseInt(temp[0]);
        int col = Integer.parseInt(temp[1]);
        int dir = Integer.parseInt(temp[2]);

        map = new int[N][M];
        boolean[][] cleanedMap = new boolean[N][M];

        cleanedMap[row][col] = true;

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Robot robot = new Robot(dir, row, col);
        int rotateCnt = 0;
        int cleanRoom = 1;

        while (true) {
            if (rotateCnt == 4) {
                int nextRow = robot.row + dirRow[nextDir(nextDir(robot.dir))];
                int nextCol = robot.col + dirCol[nextDir(nextDir(robot.dir))];

                if ((0<=nextRow && nextRow<N) && (0<=nextCol && nextCol<M)
                        && map[nextRow][nextCol] == 0) {

                    rotateCnt = 0;
                    robot.row = nextRow;
                    robot.col = nextCol;

                    continue;
                } else break;
            }

            dir = nextDir(robot.dir);

            int nextRow = robot.row + dirRow[dir];
            int nextCol = robot.col + dirCol[dir];

            if ((0<=nextRow && nextRow<N) && (0<=nextCol && nextCol<M)
            && map[nextRow][nextCol] == 0 && !cleanedMap[nextRow][nextCol]) {
                robot.dir = dir;
                robot.col = nextCol;
                robot.row = nextRow;
                cleanedMap[nextRow][nextCol] = true;

                rotateCnt = 0;
                cleanRoom++;
            } else {
                robot.dir = dir;
                rotateCnt++;
            }
        }

        System.out.println(cleanRoom);
    }


    static int nextDir(int dir) {
        if (dir-1 == -1) return 3;
        else return dir-1;
    }
}
