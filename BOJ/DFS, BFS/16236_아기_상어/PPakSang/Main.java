package BOJ.DFSBFS.아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Location {
    public int row;
    public int col;

    Location(int row, int col) {

        this.row = row;
        this.col = col;
    }
}

public class Main {

    static Integer[] dirRow = {1, -1, 0, 0};
    static Integer[] dirCol = {0, 0, 1, -1};

    static int[][] space;
    static int[][] dist;

    static int size = 2;
    static int full = 0;
    static int n;

    static int total = 0;
    static int cRow;
    static int cCol;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        space = new int[n][n];


        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 9) {
                    cRow = i;
                    cCol = j;
                    space[i][j] = 0;
                }
            }
        }

        while(true) {
            dist = new int[n][n];

            bfs();

            if ((cRow==Integer.MAX_VALUE) && (cCol==Integer.MAX_VALUE)) break;

            total += dist[cRow][cCol];
            space[cRow][cCol] = 0;
            full++;

            if (size == full) {
                size++;
                full = 0;
            }

        }

        System.out.println(total);
    }


    static void bfs() {
        Queue<Location> locations = new LinkedList<>();
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        int minDist = Integer.MAX_VALUE;

        locations.add(new Location(cRow, cCol));
        while(!locations.isEmpty()) {
            Location location = locations.poll();

            int row = location.row;
            int col = location.col;

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dirRow[i];
                int nextCol = col + dirCol[i];

                if ((0<=nextRow && nextRow<n) && (0<=nextCol && nextCol<n)
                        && dist[nextRow][nextCol] == 0
                        && space[nextRow][nextCol] <= size) {

                    int num = space[nextRow][nextCol];
                    dist[nextRow][nextCol] = dist[row][col] + 1;

                    if (num < size && num != 0) {
                        if (dist[nextRow][nextCol] < minDist) {
                            minDist = dist[nextRow][nextCol];
                            minRow = nextRow;
                            minCol = nextCol;
                        } else if (dist[nextRow][nextCol] == minDist) {
                            if (nextRow < minRow) {
                                minCol = nextCol;
                                minRow = nextRow;
                            } else if(nextRow == minRow) {
                                if (nextCol < minCol) {
                                    minCol = nextCol;
                                    minRow = nextRow;
                                }
                            }
                        }
                    }
                    locations.add(new Location(nextRow, nextCol));

                }
            }
        }

        cRow = minRow;
        cCol = minCol;

        return;
    }
}
