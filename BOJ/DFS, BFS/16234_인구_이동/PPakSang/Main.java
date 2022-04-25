package BOJ.DFSBFS.인구이동;

import java.util.*;
import java.io.*;

class Location {
    int row;
    int col;

    Location (int row, int col) {
        this.row = row;
        this.col = col;
    }
}


public class Main {

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    static int n;
    static int l;
    static int r;

    static int[][] country;
    static boolean[][] visited;
    static Stack<Location> union = new Stack<>();
    static int unionPeople = 0;

    public static void main(String[] args) throws IOException{
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp =  br.readLine().split(" ");

        n = Integer.parseInt(temp[0]);
        l = Integer.parseInt(temp[1]);
        r = Integer.parseInt(temp[2]);

        country = new int[n][n];


        for (int i = 0; i < n; i++) {
            String[] countries = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                country[i][j] = Integer.parseInt(countries[j]);
            }
        }

        while(true) {
            boolean move = false;
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        dfs(i, j);

                        if (union.size() == 1) {
                            union.pop();
                            unionPeople = 0;
                        } else {
                            move = true;
                            int size = union.size();
                            int afterMove = unionPeople / size;

                            while (!union.isEmpty()) {
                                Location location = union.pop();
                                country[location.row][location.col] = afterMove;
                            }
                            unionPeople = 0;
                        }
                    }
                }
            }

            if (move) {
                answer++;
            } else {
                System.out.println(answer);
                return;
            }
        }

    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        union.push(new Location(x, y));
        unionPeople += country[x][y];

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if ((0<=nextX && nextX<n) && (0<=nextY && nextY<n)
            && !visited[nextX][nextY]) {
                int now = country[x][y];
                int next = country[nextX][nextY];
                int diff = (now - next) >= 0 ? now - next : next - now;

                if ((l<=diff) && (diff<=r)) {
                    dfs(nextX, nextY);
                }
            }
        }

    }
}
